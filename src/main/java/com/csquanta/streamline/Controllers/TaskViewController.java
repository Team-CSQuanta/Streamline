package com.csquanta.streamline.Controllers;

import animatefx.animation.FadeIn;
import animatefx.animation.FadeOut;
import animatefx.animation.Pulse;
import com.csquanta.streamline.App;
import com.csquanta.streamline.Models.Task;
import com.csquanta.streamline.Models.TaskIdGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static java.util.Objects.requireNonNull;

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

        new Pulse(taskViewContainer).play();
    }

    @FXML
    void onCancelButtonClicked(ActionEvent event) {
        CreateANewTaskController.modalPaneForTaskCreator.hide(true);
    }

    @FXML
    void onSaveButtonClicked(ActionEvent event) throws IOException {
        updateTask(task);
        reload();
    }
    @FXML
    void deleteButtonClicked(MouseEvent event) throws IOException {
        deleteATask(task);
        reload();
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
            if(task.getTaskID() == t.getTaskID()){
                task.setDueDate(dueDate.getValue());
                task.setDescription(description.getText());
                task.setNumOfSessions(Integer.parseInt(pomodoroSessions.getSelectionModel().getSelectedItem()));
                task.setPriority(priority.getSelectionModel().getSelectedItem());
                task.setTag(tag.getSelectionModel().getSelectedItem());
                task.setTaskTitle(title.getText());
            }
        }
    }
    public void deleteATask(Task t){
        Task.taskObject.getTasksList().removeIf(task1 -> task1.getTaskID() == t.getTaskID());
        TaskIdGenerator.taskIdGenerator.decrementId();
    }
    public void reload() throws IOException {
        VBox taskPage = FXMLLoader.load(requireNonNull(getClass().getResource("/Fxml/ToDoListGridPane.fxml")));
        App.root.getChildren().removeAll();
        App.newLoad();
        FadeOut fadeIn = new FadeOut(taskPage);
        fadeIn.play();
        VBox newTaskPage = FXMLLoader.load(requireNonNull(getClass().getResource("/Fxml/ToDoListGridPane.fxml")));
        StackPane.setAlignment(newTaskPage, Pos.BOTTOM_CENTER);
        App.root.getChildren().add(newTaskPage);
        CreateANewTaskController.modalPaneForTaskCreator.hide(true);
        FadeIn fadeInNew = new FadeIn(newTaskPage);
        fadeInNew.play();
    }
}
