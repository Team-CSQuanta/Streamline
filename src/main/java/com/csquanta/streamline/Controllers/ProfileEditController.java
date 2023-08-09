package com.csquanta.streamline.Controllers;

import animatefx.animation.FadeIn;
import com.csquanta.streamline.Models.StaticUserInformation;
import com.csquanta.streamline.Models.UserInformation;
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

import java.io.*;
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

    @FXML
    private GridPane gridPaneBg;

    @FXML
    private GridPane gridPaneHead;

    @FXML
    private GridPane gridPaneHeadGear;


    private CustomizeBlockController selectedBlockController;

    public void setHeadGear(ImageView headGear) {
        this.headGear.setImage(headGear.getImage());
    }

    public void setAvatarArmor(ImageView avatarArmor) {
        this.avatarArmor.setImage(avatarArmor.getImage());
    }

    public void setAvatarBody(ImageView newAvatarBody) {

        this.avatarBody.setImage(newAvatarBody.getImage());

    }


    public void setAvatarHair(ImageView avatarHair) {
        this.avatarHair.setImage(avatarHair.getImage());
    }

    public void setAvatarHead(ImageView NewAvatarHead) {
        this.avatarHead.setImage(NewAvatarHead.getImage());
    }

    public void setAvatarPet(ImageView avatarPet) {
        this.avatarPet.setImage(avatarPet.getImage());
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


    @FXML
    void setComponent(MouseEvent event) {
        if (event.getSource() instanceof ImageView clickedImageView) {
            selectedBlockController = (CustomizeBlockController) clickedImageView.getProperties().get("controller");

            // Determine the source GridPane based on the parent of the parent (ImageView -> VBox -> GridPane)
            GridPane parentGrid = null;

            if (clickedImageView.getParent().getParent() instanceof GridPane) {
                parentGrid = (GridPane) clickedImageView.getParent().getParent();
            }

            if (selectedBlockController != null && parentGrid != null) {
                if (parentGrid == gridPaneBody) {
                    setAvatarBody(clickedImageView);
                    if(clickedImageView.getImage() != null){
                        StaticUserInformation.avatarImageBody = clickedImageView.getImage();
                        UserInformation.userInfo.setAvatarImageBody(selectedBlockController.getPath());
                    }
                    else{
                        UserInformation.userInfo.setAvatarImageBody(UserInformation.userInfo.avatarImageBody);
                    }
                } else if (parentGrid == gridPaneHead) {
                    setAvatarHead(clickedImageView);
                    if(clickedImageView.getImage() != null){
                        StaticUserInformation.avatarImageHead = clickedImageView.getImage();
                        UserInformation.userInfo.setAvatarImageHead(selectedBlockController.getPath());
                    }
                    else{
                        UserInformation.userInfo.setAvatarImageHead(UserInformation.userInfo.avatarImageHead);
                    }
                }

            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        avatarBody.setImage(StaticUserInformation.avatarImageBody);
        avatarHead.setImage(StaticUserInformation.avatarImageHead);
        File shirts = new File("src/main/resources/Images/customize/shirts");
        File skin = new File("src/main/resources/Images/customize/skin");
        File[] listImg = shirts.listFiles();
        File[] listSkin = skin.listFiles();

        int rowShirt = 1;
        int columnShirt = 0;
        int rowSkin = 1;
        int columnSkin = 0;

        int numShirts = listImg != null ? listImg.length : 0;
        int numSkins = listSkin != null ? listSkin.length : 0;
        int maxIterations = Math.max(numShirts, numSkins);

        for (int i = 0; i < maxIterations; i++) {
            try {
                // Load and set up for shirts
                if (i < numShirts) {
                    FXMLScene fxmlSceneShirt = FXMLScene.load("/Fxml/customizeBlock.fxml");
                    CustomizeBlockController customizeBlockControllerShirt = (CustomizeBlockController) fxmlSceneShirt.controller;

                    String imagePathShirt = getRelativePath(listImg[i]);
                    InputStream imageStreamShirt = getClass().getResourceAsStream(imagePathShirt);
                    if (imageStreamShirt != null) {
                        customizeBlockControllerShirt.setCustomizeBlockData(new Image(imageStreamShirt));
                        customizeBlockControllerShirt.setPath(imagePathShirt);
                        ImageView imageViewShirt = (ImageView) fxmlSceneShirt.root.lookup("#componentImg");
                        imageViewShirt.getProperties().put("controller", customizeBlockControllerShirt);
                        imageViewShirt.setOnMouseClicked(this::setComponent);

                    } else {
                        System.err.println("Shirt Image not found: " + imagePathShirt);
                    }

                    gridPaneBody.add(fxmlSceneShirt.root, columnShirt++, rowShirt);
                    if (columnShirt == 7) {
                        columnShirt = 0;
                        rowShirt++;
                    }
                }

                // Load and set up for skin
                if (i < numSkins) {
                    FXMLScene fxmlSceneSkin = FXMLScene.load("/Fxml/customizeBlock.fxml");
                    CustomizeBlockController customizeBlockControllerSkin = (CustomizeBlockController) fxmlSceneSkin.controller;

                    String imagePathSkin = getRelativePath(listSkin[i]);
                    InputStream imageStreamSkin = getClass().getResourceAsStream(imagePathSkin);
                    if (imageStreamSkin != null) {
                        customizeBlockControllerSkin.setCustomizeBlockData(new Image(imageStreamSkin));
                        customizeBlockControllerSkin.setPath(imagePathSkin);
                        ImageView imageViewSkin = (ImageView) fxmlSceneSkin.root.lookup("#componentImg");
                        imageViewSkin.getProperties().put("controller", customizeBlockControllerSkin);
                        imageViewSkin.setOnMouseClicked(this::setComponent);

                    } else {
                        System.err.println("Skin Image not found: " + imagePathSkin);
                    }

                    gridPaneHead.add(fxmlSceneSkin.root, columnSkin++, rowSkin);
                    if (columnSkin == 7) {
                        columnSkin = 0;
                        rowSkin++;
                    }
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

