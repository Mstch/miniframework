package com.tiddar.miniframework.orm;
/**
 * TODO 未完成
 */
public class Table {
    public String tableName;
    public Column[] columns;
    public String keyColumn;

    public String toCreateTableSQL(boolean dropExistingTable){
        StringBuilder stringBuilder = new StringBuilder();
        if(dropExistingTable){
            stringBuilder.append(" DROP TABLE IF EXISTS `"+this.tableName+"`;");
        }
        stringBuilder.append(" CREATE TABLE `"+this.tableName+"` IF NOT EXISTS (");
        for (Column column : columns) {
            stringBuilder.append(column.toCreateTableSQL());
        }
        stringBuilder.append("  PRIMARY KEY (`"+this.keyColumn+"`) USING BTREE");
        stringBuilder.append(") CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_c ROW_FORMAT = Dynamic;");
        return stringBuilder.toString();
    }


}
