//package com.csquanta.streamline.Controllers;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.*;
//
//import java.net.URL;
//import java.util.ResourceBundle;
//
//public class TaskCreatorController implements Initializable {
//    ObservableList<String> tagListForPriority = FXCollections.observableArrayList("Urgent and Important", "Important but Not Urgent", "Urgent but Not Important", "Not Urgent and Not Important");
//
//    @FXML
//    private Button cancel;
//
//    @FXML
//    private TextArea description;
//
//    @FXML
//    private DatePicker dueDate;
//
//    @FXML
//    private ComboBox<String> priority;
//
//    @FXML
//    private Button submit;
//
//    @FXML
//    private TextField tag;
//
//    @FXML
//    private TextField title;
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        // Set the items to the ComboBox
//        priority.setItems(tagListForPriority);
//    }
//
//
//}

package com.csquanta.streamline.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class TaskCreatorController implements Initializable {
    ObservableList<String> eishenHowerMatrix = FXCollections.observableArrayList("Urgent and Important", "Important but Not Urgent", "Urgent but Not Important", "Not Urgent and Not Important");
    ObservableList<String> pomodoroSession = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8");
    ObservableList<String> tags = FXCollections.observableArrayList("Book Reading", "Academic Studies", "Programming");
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
    private ComboBox<String> tag;

    @FXML
    private ComboBox<String> pomodoroSessions;

    @FXML
    private TextField title;

    @FXML
    void onCancelButtonClicked(ActionEvent event) {

    }

    @FXML
    void onSaveButtonClicked(ActionEvent event) {



    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        priority.setItems(eishenHowerMatrix);
        pomodoroSessions.setItems(pomodoroSession);
        tag.setItems(tags);
    }
}
