package com.csquanta.streamline.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class TaskCreatorController implements Initializable {
    ObservableList<String> tagList = FXCollections.observableArrayList("Urgent and Important", "Important but Not Urgent", "Urgent but Not Important", "Not Urgent and Not Important");

    @FXML
    private Button cancel;

    @FXML
    private TextArea description;

    @FXML
    private DatePicker dueDate;

    @FXML
    private ComboBox<String> priority;

    @FXML
    private Button submit;

    @FXML
    private TextField tag;

    @FXML
    private TextField title;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set the items to the ComboBox
        priority.setItems(tagList);
    }


}