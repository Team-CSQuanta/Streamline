package com.csquanta.streamline.Models;
import com.csquanta.streamline.Controllers.LoginPageController;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class RegistrationPageModel {
    private static final String DB_URL = "jdbc:sqlite:userLoginInfo.db";

    public static void insertUserData(String firstName, String lastName, String userName, String email, String password, int age) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {

            String sql = "INSERT INTO users (first_name, last_name, user_name, email, pass_word, user_age) " +
                    "VALUES ('" + firstName + "', '" + lastName + "', '" + userName + "', '" + email + "', '" + password + "', " + age + ")";
            stmt.executeUpdate(sql);

           LoginPageController.showAlert(Alert.AlertType.INFORMATION, "Success", "User inserted successfully!");

        } catch (SQLException e) {
            LoginPageController.showAlert(Alert.AlertType.ERROR, "Error", "Inserting new user is unsuccessful: " + e.getMessage());
        }
    }


}
