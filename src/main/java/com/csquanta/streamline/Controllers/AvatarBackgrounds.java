package com.csquanta.streamline.Controllers;

import com.csquanta.streamline.Models.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.Objects;

public class AvatarBackgrounds {

    @FXML
    private HBox avatarBgMainContainer;

    @FXML
    private Button bgBuyBtn;

    @FXML
    private ImageView bgImage;

    @FXML
    private Label bgPriceTag;

    @FXML
    private Label bgTitle;

    @FXML
    void buyBtnClicked(ActionEvent event) {

    }
    public void setBGData(Item item){
        bgImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(item.getImgSrc()))));
        bgTitle.setText(item.getTitle());
        bgPriceTag.setText(item.getPrice());
    }
}
