package com.csquanta.streamline.Controllers;
import com.csquanta.streamline.Controllers.loginPageController;
import com.csquanta.streamline.Models.registrationPageModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class registrationPageController {

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
           loginPageController.showAlert(Alert.AlertType.ERROR, "Password mismatches!", "Password and confirm password do not match!");
        } else {
            int age = calculateAge(selectedDate);
            if (age <= 0) {
                loginPageController.showAlert(Alert.AlertType.INFORMATION, "Under age!", "You are not born yet!");
            } else {
                registrationPageModel.insertUserData(firstname, lastname, username, Email, password, age);
            }
        }

    }

    private int calculateAge(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthDate, currentDate);
        return period.getYears();

    }



    @FXML
    public void goToLoginPage(ActionEvent event) throws Exception {
        Pane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Fxml/loginPage.fxml")));
        Stage window = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        Scene scene = (new Scene(root, 1500, 1000));
        window.setScene(scene);
        // setting global stylesheet
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/global.css")).toExternalForm());
        window.setScene(scene);
        // window.setFullScreen(true);
        window.show();

    }

}
