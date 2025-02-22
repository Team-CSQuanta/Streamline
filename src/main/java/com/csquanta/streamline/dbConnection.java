package com.csquanta.streamline;
import com.csquanta.streamline.Controllers.LoginPageController;
import javafx.scene.control.Alert;

import java.sql.*;

public class dbConnection {
    public static Connection connector() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:userLoginInfo.db");
            return conn;
        } catch (Exception e) {
            LoginPageController.showAlert(Alert.AlertType.ERROR, "Database connection", "Database connection is not successful!");
            return null;
        }

    }
}
