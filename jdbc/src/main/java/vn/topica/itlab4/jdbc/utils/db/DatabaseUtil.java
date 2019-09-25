package vn.topica.itlab4.jdbc.utils.db;

import vn.topica.itlab4.jdbc.entity.parser.EntityParser;
import vn.topica.itlab4.jdbc.entity.parser.Relation;
import vn.topica.itlab4.jdbc.utils.parser.RelationUtil;
import vn.topica.itlab4.jdbc.utils.reflection.AnnotationReflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DatabaseUtil {
    public final static DatabaseUtil INSTANCE = new DatabaseUtil();
    private Query query = Query.INSTANCE;
    private AnnotationReflection reflection = AnnotationReflection.INSTANCE;
    private RelationUtil relationUtil = RelationUtil.INSTANCE;

    private Relation relation;
    private EntityParser.RelationParser<Field, String> join;
    private Map<Field, String> columns;
    private Class clazz;

    private DatabaseUtil() {
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/topica", "root", "");
    }

    /**
     * Lazy fetch table data
     * Example: id, name, description  - classEntity
     *
     * @param clazz
     * @return
     */
    public List<?> fetchLazy(Class clazz) {
        this.clazz = clazz;
        parseClass();
        List<Object> objs = (List<Object>) selectLazy(query.select(this.relation.getTable()), this.columns, this.clazz);
        return objs;
    }

    /**
     * Fetch Lazy Join Table Data
     * Example: selectLazy each student id, name when is called
     *
     * @param obj
     * @return
     */
    public List<?> fetchLazyJoinData(Object obj) {
        Map<Field, String> columns = reflection.getColumns(relationUtil.getJoinClass());
        List<?> joinObjs = new ArrayList<>();
        Method getter = null;
        String sql = "";
        try {
            getter = this.clazz.getMethod("getId", null);
            Long id = (Long) getter.invoke(obj, null);
            sql = query.select(relation.getJoinTable())
                    .concat(query.where(relation.getJoinTable(), relation.getKeyFK(), String.valueOf(id)));
            joinObjs = selectLazy(sql, columns, relationUtil.getJoinClass());
            Method setter = this.clazz.getMethod(getSetterMethod(join.getField()), List.class);
            setter.invoke(obj, joinObjs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return joinObjs;
    }

    /**
     * Fetch Eager
     *
     * @param clazz
     * @return objs
     */
    public List<?> fetchEager(Class clazz) {
        this.clazz = clazz;
        parseClass();
        Map<Field, String> joinColumns = reflection.getColumns(relationUtil.getJoinClass());
        String sql = query.select(this.relation.getTable(), relation.getJoinTable()
                , new ArrayList<>(this.columns.values())
                , new ArrayList<>(joinColumns.values()))
                .concat(query.where(relation.getTable(), relation.getJoinTable(), relation.getKeyFK(), relation.getJoinTbKey()));
        List<Object> objs = (List<Object>) selectEager(sql, this.columns, this.clazz);
        return objs;
    }

    /**
     * From class get all annotations, and its value
     * Return columns, relation between two tables
     */
    private void parseClass() {
        EntityParser entityParser = reflection.reflectAnnotations(this.clazz);
        this.join = entityParser.getRelation();
        this.relation = relationUtil.getRelation(entityParser);
        this.columns = entityParser.getColumns();
    }

    /**
     * Select from database by input query
     * Use reflection to invoke setter method
     * Return list of object
     *
     * @param query
     * @param columns
     * @return
     */
    public List<?> selectLazy(String query, Map<Field, String> columns, Class clazz) {
        List<Object> objs = new ArrayList<>();
        System.out.println(query);
        try {
            Connection connection = getConnection();
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    Object obj = clazz.newInstance();
                    setValue(clazz, columns, obj, resultSet);
                    objs.add(obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objs;
    }

    /**
     * Select from database by input query
     * Use reflection to invoke setter method
     * Return list of object
     *
     * @param query
     * @param columns
     * @return
     */
    public List<?> selectEager(String query, Map<Field, String> columns, Class clazz) {
        List<Object> objs = new ArrayList<>();
        Class joinClass = relationUtil.getJoinClass();
        System.out.println(query);
        try {
            Connection connection = getConnection();
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                Object obj = clazz.newInstance();
                final long[] id = {0};
                List<Object> joinObjs = new ArrayList<>();
                while (resultSet.next()) {
                    Map<Field, String> joinColumns = reflection.getColumns(relationUtil.getJoinClass());
                    // set value to Class Obj
                    for (Field f : columns.keySet()) {
                        Type type = f.getType();
                        try {
                            if (type.equals(Long.class)) {
                                long temp = resultSet.getLong(relation.getTable() + "_" + columns.get(f));
                                if(id[0] == temp) continue;
                                else if(id[0] == 0) id[0] = temp;
                                else{
                                    // if new ClassEntity
                                    try {
                                        Method setter = this.clazz.getMethod(getSetterMethod(join.getField()), List.class);
                                        setter.invoke(obj, joinObjs);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    objs.add(obj);
                                    joinObjs = new ArrayList<>();
                                    obj = clazz.newInstance();
                                    id[0] = temp;
                                }
                                Method setter = clazz.getMethod(getSetterMethod(f), Long.class);
                                setter.invoke(obj, id[0]);
                            } else if (type.equals(String.class)) {
                                Method setter = clazz.getMethod(getSetterMethod(f), String.class);
                                setter.invoke(obj, resultSet.getString(relation.getTable() + "_" + columns.get(f)));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    // set list student
                    Object joinObj = joinClass.newInstance();
                    setEagerValue(joinClass, joinColumns, joinObj, resultSet, relation.getJoinTable());
                    joinObjs.add(joinObj);

                    if (resultSet.isLast()) {
                        try {
                            Method setter = this.clazz.getMethod(getSetterMethod(join.getField()), List.class);
                            setter.invoke(obj, joinObjs);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        objs.add(obj);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objs;
    }

    /**
     * Get value from resultSet
     * reflect setter method
     *
     * @param clazz
     * @param columns
     * @param obj
     * @param resultSet
     */
    private void setValue(Class clazz, Map<Field, String> columns, Object obj, ResultSet resultSet) {
        columns.forEach((f, c) -> {
            Type type = f.getType();
            try {
                if (type.equals(Long.class)) {
                    Method setter = clazz.getMethod(getSetterMethod(f), Long.class);
                    setter.invoke(obj, resultSet.getLong(c));
                } else if (type.equals(String.class)) {
                    Method setter = clazz.getMethod(getSetterMethod(f), String.class);
                    setter.invoke(obj, resultSet.getString(c));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Get value from resultSet
     * reflect setter method
     *
     * @param clazz
     * @param columns
     * @param obj
     * @param resultSet
     */
    private void setEagerValue(Class clazz, Map<Field, String> columns, Object obj, ResultSet resultSet, String joinTable) {
        columns.forEach((f, c) -> {
            Type type = f.getType();
            try {
                if (type.equals(Long.class)) {
                    long temp =  resultSet.getLong(joinTable+"_"+c);
                    Method setter = clazz.getMethod(getSetterMethod(f), Long.class);
                    setter.invoke(obj,temp);
                } else if (type.equals(String.class)) {
                    Method setter = clazz.getMethod(getSetterMethod(f), String.class);
                    setter.invoke(obj, resultSet.getString(joinTable+"_"+c));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * From field get String of setter method name
     *
     * @param field
     * @return
     */
    public String getSetterMethod(Field field) {
        String firstChar = String.valueOf(field.getName().charAt(0)).toUpperCase();
        String handledFieldName = firstChar.concat(field.getName().substring(1));
        return new StringBuilder().append("set")
                .append(handledFieldName)
                .toString();
    }

}
