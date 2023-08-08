package com.csquanta.streamline.Controllers;


import com.csquanta.streamline.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class TaskController {

    @FXML
    private Label Header;

    @FXML
    private Text dueDate;

    @FXML
    private Label priority;

    @FXML
    private Label tag;

    @FXML
    void Delete(ActionEvent event) {

    }

    public void updateItem(Task item) {

        Header.setText(item.getTitle());
        tag.setText(item.getTag());
        dueDate.setText(String.valueOf(item.getDueDate()));
        priority.setText(item.getPriority());
    }

}