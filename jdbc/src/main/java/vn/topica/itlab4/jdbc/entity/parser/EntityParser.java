package vn.topica.itlab4.jdbc.entity.parser;

import java.lang.reflect.Field;
import java.util.Map;

public class EntityParser {
    private String table; // name of table in database
    private Map<Field, String> columns; // Field: field of class - String: column name in database
    private RelationParser<Field, String> relation; // for annotations mapping between tables

    public EntityParser() {
    }

    public EntityParser(String table, Map<Field, String> fieldMap, Field joinField, String joinFieldValue) {
        this.table = table;
        this.columns = fieldMap;
        this.relation = new RelationParser<>(joinField, joinFieldValue);
    }

    public String getTable() {
        return table;
    }

    public Map<Field, String> getColumns() {
        return columns;
    }

    public RelationParser<Field, String> getRelation() {
        return relation;
    }

    public class RelationParser<F extends Field, S extends String>{

        public RelationParser(F field, S value) {
            this.field = field;
            this.value = value;
        }

        private F field;
        private S value; // value of annotation

        public F getField() {
            return field;
        }

        public S getValue() {
            return value;
        }
    }

}
