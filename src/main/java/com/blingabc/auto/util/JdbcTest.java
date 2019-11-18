package com.blingabc.auto.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcTest {

    private static String url="jdbc:mysql://localhost:3306/blingabc_qa?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static String user="root";
    private static String password="123456";
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn=(Connection) DriverManager.getConnection(url,user,password);
        System.out.println(conn);

    }

}
