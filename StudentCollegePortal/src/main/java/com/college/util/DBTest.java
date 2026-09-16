package com.college.util;

import java.sql.Connection;

public class DBTest {
 

    public static void main(String[] args) {

        
        Connection con =
            DBConnection.getConnection();

        if (con !
            System.out.println(
                "Connection Test Successful!"
            );

        } else {

            System.out.println(
                "Connection Failed!"
            );
        }
    }
}