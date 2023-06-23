package com.csquanta.streamline.Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class passwordUpdatingModel {

    // Database connection details
    private static final String DB_URL = "jdbc:sqlite:userLoginInfo.db";

    public boolean updatePassword(String username, String email, String newPassword) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement("UPDATE users SET pass_word = ? WHERE user_name = ? AND email = ?")) {

            statement.setString(1, newPassword);
            statement.setString(2, username);
            statement.setString(3, email);

            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0;
        }
    }
}
