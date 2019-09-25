package vn.topica.itlab4.jdbc.utils.db;

import java.util.List;

public class Query {
    public static final Query INSTANCE = new Query();

    private Query() {
    }

    public String select(String table) {
        StringBuilder sb = new StringBuilder();
        sb.append("select * from ")
                .append(table);
        return sb.toString();
    }

    public String where(String table, String keyFK, String value) {
        StringBuilder sb = new StringBuilder();
        sb.append(" where ")
                .append(table)
                .append(".")
                .append(keyFK)
                .append("=")
                .append(value);
        return sb.toString();
    }

    // Select c.id as class_id,c.name as class_name,s.id as student_id,
//s.name as student_name from Class c, Student s where c.id = s.class_id
    public String select(String table, String joinTable, List<String> columns, List<String> joinColumns) {
        StringBuilder sb = new StringBuilder();
        sb.append("select ");
        columns.forEach(s ->
                sb.append(table)
                        .append(".")
                        .append(s)
                        .append(" as ")
                        .append(table)
                        .append("_")
                        .append(s)
                        .append(", ")
        );
        joinColumns.forEach(s ->
                sb.append(joinTable)
                        .append(".")
                        .append(s)
                        .append(" as ")
                        .append(joinTable)
                        .append("_")
                        .append(s)
                        .append(", ")
        );
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append(" from ")
                .append(table)
                .append(" , ")
                .append(joinTable);
        return sb.toString();
    }

    public String where(String table, String joinTable, String keyFK, String joinTbKey) {
        StringBuilder sb = new StringBuilder();
        sb.append(" where ")
                .append(joinTable)
                .append(".")
                .append(keyFK)
                .append("=")
                .append(table)
                .append(".")
                .append(joinTbKey);
        return sb.toString();
    }
}
