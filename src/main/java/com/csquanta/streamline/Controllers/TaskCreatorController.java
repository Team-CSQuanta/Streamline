package com.csquanta.streamline.Controllers;

import animatefx.animation.FadeIn;
import animatefx.animation.FadeOut;
import atlantafx.base.controls.Notification;
import com.csquanta.streamline.App;
import com.csquanta.streamline.Models.Task;
import com.csquanta.streamline.Models.TaskComparator;
import com.csquanta.streamline.Models.TaskIdGenerator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Collections;
import java.util.ResourceBundle;

import static com.csquanta.streamline.Models.Task.taskObject;
import static java.util.Objects.requireNonNull;

public class TaskCreatorController implements Initializable {

    public static ObservableList<String> eishenHowerMatrix = FXCollections.observableArrayList("Urgent and important", "Important but not urgent", "Urgent but not important", "Not urgent and not important");
    public static ObservableList<String> pomodoroSession = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8");
    public static ObservableList<String> tags = FXCollections.observableArrayList("Book Reading", "Academic Studies", "Programming");
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

    public ComboBox<String> getPomodoroSessions() {
        return pomodoroSessions;
    }

    @FXML
    void onCancelButtonClicked(ActionEvent event) {
        CreateANewTaskController.modalPaneForTaskCreator.hide(true);
    }

    @FXML
    void onSaveButtonClicked(ActionEvent event) throws IOException {
        String taskTitle = title.getText();
        String selectedPomodoroSession = pomodoroSessions.getSelectionModel().getSelectedItem();
        LocalDate selectedDueDate = dueDate.getValue();
        String selectedPriority = priority.getSelectionModel().getSelectedItem();
        String selectedTag = tag.getSelectionModel().getSelectedItem();
        String taskDescription = description.getText();

        if (taskTitle.isEmpty() || selectedPomodoroSession == null || selectedDueDate == null || selectedPriority == null || selectedTag == null || taskDescription.isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Missing Fields");
            alert.setHeaderText(null);
            alert.setContentText("Please fill up all fields.");
            alert.showAndWait();
            return;
        }

        // Check if the due date is in the future or today
        LocalDate currentDate = LocalDate.now();
        if (selectedDueDate.isBefore(currentDate)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Due Date");
            alert.setHeaderText(null);
            alert.setContentText("Due date must be today or in the future.");
            alert.showAndWait();
            return;
        }

        Task newTask = new Task(title.getText(), Integer.parseInt(pomodoroSessions.getSelectionModel().getSelectedItem()), dueDate.getValue(), priority.getSelectionModel().getSelectedItem(), tag.getSelectionModel().getSelectedItem(), description.getText(), TaskIdGenerator.taskIdGenerator.getTaskID());
        taskObject.addTask(newTask);
        TaskComparator taskComparator = new TaskComparator();
        taskObject.getTasksList().sort(taskComparator);
        VBox taskPage = FXMLLoader.load(requireNonNull(getClass().getResource("/Fxml/ToDoListGridPane.fxml")));
        App.root.getChildren().removeAll();
        App.newLoad();
        FadeOut fadeIn = new FadeOut(taskPage);
        fadeIn.play();
        VBox newTaskPage = FXMLLoader.load(requireNonNull(getClass().getResource("/Fxml/ToDoListGridPane.fxml")));
        StackPane.setAlignment(newTaskPage, Pos.BOTTOM_CENTER);
        App.root.getChildren().add(newTaskPage);
        FadeIn fadeInNew = new FadeIn(newTaskPage);
        fadeInNew.setSpeed(3);
        fadeInNew.play();
        CreateANewTaskController.modalPaneForTaskCreator.hide(true);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        priority.setItems(eishenHowerMatrix);
        pomodoroSessions.setItems(pomodoroSession);
        tag.setItems(tags);
    }
}
