package vn.topica.itlab4.jdbc.entity.parser;

public class Relation {
    private String table;
    private String joinTable;
    private String keyFK;
    private String joinTbKey = "id";

    public void setTable(String table) {
        this.table = table;
    }

    public void setJoinTable(String joinTable) {
        this.joinTable = joinTable;
    }

    public void setKeyFK(String keyFK) {
        this.keyFK = keyFK;
    }

    public void setJoinTbKey(String joinTbKey) {
        this.joinTbKey = joinTbKey;
    }

    public String getTable() {
        return table;
    }

    public String getJoinTable() {
        return joinTable;
    }

    public String getKeyFK() {
        return keyFK;
    }

    public String getJoinTbKey() {
        return joinTbKey;
    }
}
