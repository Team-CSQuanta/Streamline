package com.csquanta.streamline.Controllers;
import com.csquanta.streamline.Models.PasswordUpdatingModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class PasswordUpdatingController {

    @FXML
    private PasswordField Cpass;

    @FXML
    private TextField email;

    @FXML
    private PasswordField pass;

    @FXML
    private Button submitButton;

    @FXML
    private TextField userName;
    public PasswordUpdatingModel model = new PasswordUpdatingModel();

    @FXML
    void confirmChange(ActionEvent event) {
        String username = userName.getText();
        String newPassword = pass.getText();
        String confirmNewPassword = Cpass.getText();
        String Email = email.getText();

        if (newPassword.compareTo(confirmNewPassword) != 0) {
            LoginPageController.showAlert(Alert.AlertType.ERROR, "Password Mismatch", "New password and confirmed password do not match!");
            return;
        }

        try {
            boolean success = model.updatePassword(username, Email, newPassword);

            if (success) {
                LoginPageController.showAlert(Alert.AlertType.INFORMATION, "Password Updated", "Password updated successfully!");
            } else {
                LoginPageController.showAlert(Alert.AlertType.ERROR, "Error", "Failed to update password!");
            }
        } catch (SQLException e) {
            LoginPageController.showAlert(Alert.AlertType.ERROR, "Error", "Error updating password: " + e.getMessage());
        }
    }



}