package com.csquanta.streamline.Controllers;

import animatefx.animation.FadeIn;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.csquanta.streamline.Controllers.HeaderController.modalPaneForHeader;

public class ProfileEditController implements Initializable {
    @FXML
    private VBox mainComponent;
    @FXML
    private AnchorPane profileArea;

    @FXML
    private ImageView avatarArmor;

    @FXML
    private ImageView avatarBody;

    @FXML
    private ImageView avatarHair;

    @FXML
    private ImageView avatarHead;

    @FXML
    private ImageView avatarPet;

    @FXML
    private HBox closeBtn;

    @FXML
    private ImageView headGear;

    @FXML
    private ImageView image_bg;

    @FXML
    private GridPane gridPaneBody;

    private CustomizeBlockController selectedBlockController;

    public void setHeadGear(ImageView headGear) {
        this.headGear = headGear;
    }

    public void setAvatarArmor(ImageView avatarArmor) {
        this.avatarArmor = avatarArmor;
    }

    public void setAvatarBody(ImageView newAvatarBody) {

        this.avatarBody.setImage(newAvatarBody.getImage());

    }


    public void setAvatarHair(ImageView avatarHair) {
        this.avatarHair = avatarHair;
    }

    public void setAvatarHead(ImageView avatarHead) {
        this.avatarHead = avatarHead;
    }

    public void setAvatarPet(ImageView avatarPet) {
        this.avatarPet = avatarPet;
    }

    @FXML
    void closeEditView(MouseEvent event) {
        modalPaneForHeader.hide(true);
    }

    @FXML
    void goBackToProfileView(MouseEvent event) throws IOException {
        VBox profileView = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Fxml/Profile.fxml")));
        modalPaneForHeader.setAlignment(Pos.CENTER);
        modalPaneForHeader.show(profileView);
        FadeIn fadeIn = new FadeIn();
        fadeIn.setNode(profileView);
        fadeIn.play();
    }


    void setComponent(MouseEvent event) {
        if (event.getSource() instanceof ImageView clickedImageView) {
            selectedBlockController = (CustomizeBlockController) clickedImageView.getProperties().get("controller");
            setAvatarBody(clickedImageView);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File shirts = new File("src/main/resources/Images/customize/shirts");
        File[] listImg = shirts.listFiles();
        int row = 1;
        int column = 0;
        for (int i = 0; i < Objects.requireNonNull(listImg).length; i++) {
            try {
                FXMLScene fxmlScene = FXMLScene.load("/Fxml/customizeBlock.fxml");
                CustomizeBlockController customizeBlockController = (CustomizeBlockController) fxmlScene.controller;

                String imagePath = getRelativePath(listImg[i]);
                InputStream imageStream = getClass().getResourceAsStream(imagePath);
                if (imageStream != null) {
                    customizeBlockController.setCustomizeBlockDate(new Image(imageStream));
                    ImageView imageView = (ImageView) fxmlScene.root.lookup("#componentImg");
                    imageView.getProperties().put("controller", customizeBlockController);
                    imageView.setOnMouseClicked(this::setComponent);

                } else {
                    System.err.println("Image not found: " + imagePath);
                }

                gridPaneBody.add(fxmlScene.root, column++, row);
                if (column == 7) {
                    column = 0;
                    row++;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public String getRelativePath(File file) throws IOException {
        String getPath = file.getCanonicalPath();
        String parentPath = new File("src/main/resources").getCanonicalPath();

        if (getPath.startsWith(parentPath)) {
            String relativePath = getPath.substring(parentPath.length() );
            return relativePath.replace("\\", "/");
        } else {
            throw new IllegalArgumentException("File is not within the resources directory: " + getPath);
        }
    }
}

