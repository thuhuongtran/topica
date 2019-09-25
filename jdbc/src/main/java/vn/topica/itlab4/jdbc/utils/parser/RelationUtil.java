package vn.topica.itlab4.jdbc.utils.parser;

import vn.topica.itlab4.jdbc.annotation.JoinColumn;
import vn.topica.itlab4.jdbc.annotation.OneToMany;
import vn.topica.itlab4.jdbc.annotation.Table;
import vn.topica.itlab4.jdbc.entity.parser.EntityParser;
import vn.topica.itlab4.jdbc.entity.parser.Relation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import static org.reflections.ReflectionUtils.getAllFields;
import static org.reflections.ReflectionUtils.withAnnotation;

public class RelationUtil {
    public static final RelationUtil INSTANCE = new RelationUtil();
    private Class joinClass;
    private RelationUtil() {
    }

    /**
     * return tableName, joinTableName, keyFK
     *
     * @param ep
     * @return
     */
    public Relation getRelation(EntityParser ep) {
        Relation relation = new Relation();
        String table = ep.getTable();
        relation.setTable(table);

        EntityParser.RelationParser rp = ep.getRelation();
        Class joinClass = getJoinClass(rp.getField());
        String joinTable = getTableAnnotationValue(joinClass);
        relation.setJoinTable(joinTable);

        String keyFK = getKeyFK(joinClass);
        relation.setKeyFK(keyFK);
        return relation;
    }

    /**
     * From class get annotation table
     * Return Table annotation value
     *
     * @param clazz
     * @return
     */
    private String getTableAnnotationValue(Class clazz) {
        Table table = (Table) clazz.getAnnotations()[0];
        String tbName = table.name();
        return tbName;
    }

    private String getKeyFK(Class clazz) {
        final String[] keyFK = new String[1];
        getAllFields(clazz, withAnnotation(JoinColumn.class))
                .forEach(f -> keyFK[0] = f.getAnnotation(JoinColumn.class).name());
        return keyFK[0];
    }

    /**
     * From field that is not column then get join class
     *
     * @param field
     * @return
     */
    private Class getJoinClass(Field field) {
        Type genericFieldType = field.getGenericType();
        Class joinClass;
        if (genericFieldType instanceof ParameterizedType) {
            ParameterizedType paramType = (ParameterizedType) genericFieldType;
            joinClass = (Class) paramType.getActualTypeArguments()[0];
        } else joinClass = (Class) genericFieldType;
        this.joinClass = joinClass;
        return joinClass;
    }

    public Class getJoinClass() {
        return joinClass;
    }
}
