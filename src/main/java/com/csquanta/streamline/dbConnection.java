package com.csquanta.streamline;
import java.sql.*;
public class dbConnection {
    public static Connection connector(){
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn =DriverManager.getConnection("jdbc:sqlite:userLoginInfo.db");
            return conn;
        } catch (Exception e) {
            System.out.println(e);
            return null;
            // TODO: handle exception
        }

    }
}
