package com.tiddar.miniframework.orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;
import java.util.Vector;

/**
 * 数据库连接类，初始化时建立5个链接，
 */
public class DBConnection {
    private static int connIndex = -1;
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/LLL?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8";
    private static final String user = "root";
    private static final String password = "Zhang123@";
    private static Stack<Connection> connArray;

    public synchronized static void init() {
        try {
            Class.forName(driver);
            connArray = new Stack<Connection>();
            for (int i = 0; i < 5; i++) {
                connArray.add(DriverManager.getConnection(url, user, password));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public   static Connection getConnection() {
        while (connArray.size() != 0)
            return connArray.pop();
        return null;
    }

    public static void release(Connection connection) {
        connArray.add(connection);
    }

}
