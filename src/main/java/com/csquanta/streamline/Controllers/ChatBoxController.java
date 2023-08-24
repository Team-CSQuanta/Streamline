package com.csquanta.streamline.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;
public class ChatBoxController implements Initializable {

    @FXML
    private GridPane gridPane;

    private int currentRow = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public void setGridPane(GridPane gridPane) {
        this.gridPane = gridPane;
    }


}
