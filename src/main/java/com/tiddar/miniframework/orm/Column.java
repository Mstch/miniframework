package com.tiddar.miniframework.orm;

/**
 * TODO 未完成
 */
public class Column {
    public String name;
    public String type;
    public boolean canBeNull = false;
    public boolean autoIncrement;
    public String defaultValue = "NULL";
    public String primaryKey = "id";

    public String toCreateTableSQL() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("`" + this.name + "`").append(" " + this.type + " ").append(canBeNull ? " NULL " : " NOT NULL ").append(defaultValue).append(this.autoIncrement ? "AUTO_INCREMENT" : "");
        return stringBuilder.toString();
    }

}
