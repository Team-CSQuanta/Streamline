package com.csquanta.streamline.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class DashBoardController implements Initializable {
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
    private Label Userlabel;

    @FXML
    public void getUser(String user) {
        Userlabel.setText(user);
    }


}
