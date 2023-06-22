package com.csquanta.streamline.Controllers;

import com.csquanta.streamline.Models.registerPageModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;
import java.time.Period;

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

        if (!pass.equals(cPass)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Password and Confirm Password do not match!");
            alert.showAndWait();
        }

        int age = calculateAge(selectedDate);
        if (age <= 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid Date of Birth!");
            alert.showAndWait();
        }


        registerPageModel.insertUserData(firstname, lastname, username, Email, password, age);


    }

    private int calculateAge(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthDate, currentDate);
        return period.getYears();

    }


}
