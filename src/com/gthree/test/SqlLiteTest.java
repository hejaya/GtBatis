package com.gthree.test;

import java.sql.*;

/**
 * @Description
 * @Author Gthree
 * @Date 2020-01-03 15:53
 */
public class SqlLiteTest {
    private static final String Class_Name = "org.sqlite.JDBC";
    private static final String DB_URL = "jdbc:sqlite:D:\\guan.db";

    public static void main(String[] args) {
        try {
            Connection connection = createConnection();
            func1(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 创建Sqlite数据库连接
    public static Connection createConnection() throws SQLException, ClassNotFoundException {
        Class.forName(Class_Name);
        return DriverManager.getConnection(DB_URL);
    }

    public static void func1(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        // 执行查询语句
        ResultSet rs = statement.executeQuery("select * from users");
        while (rs.next()) {
            String col1 = rs.getString("id");
            String col2 = rs.getString("name");
            System.out.println("id = " + col1 + "  name = " + col2);
        }
    }
}
