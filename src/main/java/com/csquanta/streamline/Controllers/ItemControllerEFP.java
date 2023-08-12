package com.csquanta.streamline.Controllers;

import com.csquanta.streamline.Models.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ItemControllerEFP implements Initializable {

    @FXML
    private Button itemBuyBtn;

    @FXML
    private VBox itemEFPMainContainer;

    @FXML
    private ImageView itemImage;

    @FXML
    private Label itemPriceTag;

    @FXML
    private Label itemTitle;

    public ImageView getItemImage() {
        return itemImage;
    }

    public void setItemImage(ImageView itemImage) {
        this.itemImage = itemImage;
    }

    public Label getItemPriceTag() {
        return itemPriceTag;
    }

    public void setItemPriceTag(Label itemPriceTag) {
        this.itemPriceTag = itemPriceTag;
    }

    public Label getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(Label itemTitle) {
        this.itemTitle = itemTitle;
    }
    public void setPetData(Item item){
        itemImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(item.getImgSrc()))));
        itemTitle.setText(item.getTitle());
        itemPriceTag.setText(item.getPrice());
    }
    @FXML
    void buyBtnClicked(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
