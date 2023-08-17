package com.csquanta.streamline.Controllers;

import animatefx.animation.FadeIn;
import animatefx.animation.Pulse;
import com.csquanta.streamline.Models.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TaskViewController implements Initializable{
    @FXML
    private VBox taskViewContainer;

    @FXML
    private ImageView editImage;
    @FXML
    private ImageView deleteImage;
    private Task task;
    @FXML
    private Button cancel;

    @FXML
    private TextArea description;

    @FXML
    private DatePicker dueDate;

    @FXML
    private ComboBox<String> pomodoroSessions;

    @FXML
    private ComboBox<String> priority;

    @FXML
    private Button submit;

    @FXML
    private ComboBox<String> tag;

    @FXML
    private TextField title;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getTitle() {
        return title.getText();
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public String getDescription() {
        return description.getText();
    }

    public void setDescription(String description) {
        this.description.setText(description);
    }

    public DatePicker getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate.setValue(dueDate);
    }

    @FXML
    void editTask(MouseEvent event) {
        description.setDisable(false);
        dueDate.setDisable(false);
        pomodoroSessions.setDisable(false);
        priority.setDisable(false);
        tag.setDisable(false);
        title.setDisable(false);;
        // making not visible (Button)
        cancel.setVisible(true);
        submit.setVisible(true);
        editImage.setVisible(false);
        updateTask(task);
        new Pulse(taskViewContainer).play();
    }

    @FXML
    void onCancelButtonClicked(ActionEvent event) {
        CreateANewTaskController.modalPaneForTaskCreator.hide(true);
    }

    @FXML
    void onSaveButtonClicked(ActionEvent event) {

    }
    @FXML
    void deleteButtonClicked(MouseEvent event) {

    }
    public void setPriority(String value){
        priority.setValue(value);
    }
    public void setTag(String value){
        tag.setValue(value);
    }
    public void setPomodoroSessions(String value){
        pomodoroSessions.setValue(value);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        priority.setItems(TaskCreatorController.eishenHowerMatrix);
        pomodoroSessions.setItems(TaskCreatorController.pomodoroSession);
        tag.setItems(TaskCreatorController.tags);

        // making not editable
        description.setDisable(true);
        dueDate.setDisable(true);
        pomodoroSessions.setDisable(true);
        priority.setDisable(true);
        tag.setDisable(true);
        title.setDisable(true);;
        // making not visible (Button)
        cancel.setVisible(false);
        submit.setVisible(false);
    }

    public void updateTask(Task t){

        for(Task task: Task.taskObject.getTasksList()){
            if(task.equals(t)){
                System.out.println("Yes they are same");
            }
        }
    }
}
