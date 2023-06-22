package com.csquanta.streamline.Controllers;

import com.csquanta.streamline.Models.registerPageModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class landingPageController {

    @FXML
    private DatePicker DateOfBirth;

    @FXML
    private TextField email;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private PasswordField pass;
    @FXML
    private PasswordField cPass;

    @FXML
    private Button registerButton;

    @FXML
    private TextField userName;

    @FXML
    public void getRegistered(ActionEvent event) {
        String firstname = firstName.getText();
        String lastname = lastName.getText();
        String username = userName.getText();
        String Email = email.getText();
        String password = pass.getText();
        String confirmPassword = cPass.getText();
        LocalDate selectedDate = DateOfBirth.getValue();

        if (password.compareTo(confirmPassword) != 0) {
            showErrorAlert("Passwords do not match!");
        } else {
            int age = calculateAge(selectedDate);
            if (age <= 0) {
                showErrorAlert("You are too young!");
            } else {
                registerPageModel.insertUserData(firstname, lastname, username, Email, password, age);
            }
        }

    }



    private int calculateAge(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthDate, currentDate);
        return period.getYears();

    }
    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    public void goToLoginPage(ActionEvent event)throws Exception  {
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(Objects.requireNonNull(Objects.requireNonNull(getClass().getResource("/Fxml/loginPage.fxml")).openStream()));
        primaryStage.setTitle("Login");
        Scene scene = new Scene(root);
        // setting global stylesheet
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/global.css")).toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
