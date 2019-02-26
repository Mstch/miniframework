package com.tiddar.miniframework.orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static int connIndex = -1;
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/LLL?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8";
    private static final String user = "root";
    private static final String password = "Zhang123@";
    private static Connection[] connArray;
    public static void init(){
        try {
            Class.forName(driver);
            connArray = new Connection[5];
            for (int i = 0; i < 5; i++) {
                connArray[i] = DriverManager.getConnection(url, user, password);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() {
        connIndex = (connIndex+1)%5;
        return connArray[connIndex];
    }
}
