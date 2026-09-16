package com.college.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    static Connection con;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3307/demodbjanbatch",
                "root",
                "12345"
            );

            System.out.println("Database Connected Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return con;
    }
}