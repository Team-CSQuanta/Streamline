package com.csquanta.streamline.Models;
import com.csquanta.streamline.dbConnection;
import java.sql.*;

public class loginPageModel {
    Connection connection;
    public loginPageModel () {
        connection = dbConnection.connector();
        if (connection == null) {

            System.out.println("connection not successful");
            System.exit(1);}
    }

    public boolean isDbConnected() {
        try {
            return !connection.isClosed();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

}
