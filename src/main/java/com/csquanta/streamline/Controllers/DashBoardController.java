package com.csquanta.streamline.Controllers;

import com.csquanta.streamline.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;



public class DashBoardController implements Initializable {

    @FXML
    private Label Userlabel;
    @FXML
    private BarChart<String, Number> todaysTopActivityChart;
    @FXML
    private Circle image;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        Image imageSrc = new Image("/Images/Avatar5.png");
//        ImagePattern imagePattern = new ImagePattern(imageSrc);
//        image.setFill(imagePattern);

        /**********************************************/

        XYChart.Data<String, Number> playingData  = new XYChart.Data<>("Playing", 4);
        XYChart.Series<String, Number> playing= new XYChart.Series<>();
        playing.setName("Playing");
        playing.getData().add(playingData);

        XYChart.Data<String, Number> readingData = new XYChart.Data<>("Reading", 13);
        XYChart.Series<String, Number> reading = new XYChart.Series<>();
        reading.setName("Reading");
        reading.getData().add(readingData);

        XYChart.Data<String, Number> entertainmentData = new XYChart.Data<>("Entertainment", 6);
        XYChart.Series<String, Number> entertainment = new XYChart.Series<>();
        reading.setName("Entertainment");
        reading.getData().add(entertainmentData);

        todaysTopActivityChart.getData().addAll(reading, entertainment);
        todaysTopActivityChart.getData().add(playing);
    }
    @FXML
    void tasksBtnClicked(MouseEvent event) throws IOException {
        FXMLScene scene = FXMLScene.load("/Fxml/TodoList.fxml");
        Parent root =  scene.root;
        Scene toDoList = new Scene(root);
        Stage primaryStage = App.mainStage;
        primaryStage.setScene(toDoList);
    }
    @FXML
    void onHabitTrackerBtnClicked(MouseEvent event) throws IOException{
        FXMLScene scene = FXMLScene.load("/Fxml/HabitTracker.fxml");
        Parent root =  scene.root;
        Scene habitTracker = new Scene(root);
        Stage primaryStage = App.mainStage;
        primaryStage.setScene(habitTracker);
    }



    @FXML
    public void getUser(String user) {
        Userlabel.setText(user);
    }


}
