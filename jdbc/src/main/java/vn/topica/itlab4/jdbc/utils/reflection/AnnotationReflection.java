package vn.topica.itlab4.jdbc.utils.reflection;


import vn.topica.itlab4.jdbc.annotation.Column;
import vn.topica.itlab4.jdbc.annotation.JoinColumn;
import vn.topica.itlab4.jdbc.annotation.OneToMany;
import vn.topica.itlab4.jdbc.annotation.Table;
import vn.topica.itlab4.jdbc.entity.parser.EntityParser;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static org.reflections.ReflectionUtils.getAllFields;
import static org.reflections.ReflectionUtils.withAnnotation;

public class AnnotationReflection {
    public final static AnnotationReflection INSTANCE = new AnnotationReflection();

    private AnnotationReflection() {
    }

    /**
     * Read class annotations
     * Then store in EntityParser class
     *
     * @param cls
     * @return
     */
    public EntityParser reflectAnnotations(Class cls) {
        Table table = (Table) cls.getAnnotations()[0];
        String tbName = table.name();
        Map<Field, String> fieldMap = new HashMap<>();
        getAllFields(cls, withAnnotation(Column.class))
                .forEach(f ->
                        fieldMap.put(f, f.getAnnotation(Column.class).name()));
        final Field[] joinField = new Field[1];
        final String[] joinFieldValue = new String[1];
        getAllFields(cls, withAnnotation(OneToMany.class))
                .forEach(f -> {
                    joinField[0] = f;
                    joinFieldValue[0] = f.getAnnotation(OneToMany.class).mappedBy();
                });
        getAllFields(cls, withAnnotation(JoinColumn.class))
                .forEach(f -> {
                    joinField[0] = f;
                    joinFieldValue[0] = f.getAnnotation(JoinColumn.class).name();
                });

        EntityParser er = new EntityParser(tbName, fieldMap, joinField[0], joinFieldValue[0]);
        return er;
    }

    /**
     * From input class get all field has annotation Column
     * @param clazz
     * @return
     */
    public Map<Field, String> getColumns(Class clazz) {
        Map<Field, String> fieldMap = new HashMap<>();
        getAllFields(clazz, withAnnotation(Column.class))
                .forEach(f ->
                        fieldMap.put(f, f.getAnnotation(Column.class).name()));
        return fieldMap;
    }
}
