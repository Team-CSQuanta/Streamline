package com.csquanta.streamline.Controllers;

import animatefx.animation.FadeIn;
import animatefx.animation.ZoomIn;
import com.csquanta.streamline.Models.StaticUserInformation;
import com.csquanta.streamline.Models.UserInformation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.event.Event;
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
    @FXML
    private GridPane gridPaneHair;

    @FXML
    private Tab backgroundTab;

    @FXML
    private Tab hairTab;

    @FXML
    private Tab bodyTab;

    @FXML
    private Tab headWearTab;

    @FXML
    private Tab skinTab;


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
        ZoomIn zoomIn = new ZoomIn();
        zoomIn.setNode(profileView);
        zoomIn.setSpeed(3);
        zoomIn.play();
    }
    @FXML
    void onHairSelectionChanged(Event event) {
        if(hairTab.isSelected()){
            addProfileEditItems("/Fxml/customizeBlock.fxml", "src/main/resources/Images/customize/hair", gridPaneHair);
        }
    }
    @FXML
    void onBGSelectionChanged(Event event) {
        if(backgroundTab.isSelected()){
            System.out.println("Background Tab is selected");
        }
    }

    @FXML
    void onBodySelectionChanged(Event event) {
        if(bodyTab.isSelected()){
            System.out.println("Body Tab is selected");
            addProfileEditItems("/Fxml/customizeBlock.fxml", "src/main/resources/Images/customize/shirts", gridPaneBody);
        }
    }

    @FXML
    void onHeadGearSelectionChanged(Event event) {
        if(headWearTab.isSelected()){
            System.out.println("Head Wear Tab is selected");
//            addShopItems("/Fxml/customizeBlock.fxml", );
        }
    }

    @FXML
    void OnSkinSelectionChanged(Event event) {
        if(skinTab.isSelected()){
            System.out.println("Skin Tab is selected");
            addProfileEditItems("/Fxml/customizeBlock.fxml", "src/main/resources/Images/customize/skin", gridPaneHead);
        }
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
                }else if(parentGrid == gridPaneHair){
                    setAvatarHair(clickedImageView);
                    if(clickedImageView.getImage() != null){
                        StaticUserInformation.avatarImageHair = clickedImageView.getImage();
                        UserInformation.userInfo.setAvatarImageHair(selectedBlockController.getPath());
                    }
                    else{
                        UserInformation.userInfo.setAvatarImageHair(UserInformation.userInfo.avatarImageHair);
                    }

                }

            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        avatarBody.setImage(StaticUserInformation.avatarImageBody);
        avatarHead.setImage(StaticUserInformation.avatarImageHead);
        avatarHair.setImage(StaticUserInformation.avatarImageHair);
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
    public void addProfileEditItems(String fxmlPath, String filePath, GridPane gridPane){
        File itemFile = new File(filePath);
        File[] itemList = itemFile.listFiles();
        int row= 1;
        int column= 0;
        int numOfItem = itemList != null ? itemList.length : 0;
        for(int i = 0; i<numOfItem; i++){
            // Load and set up for shirts
            try{
                FXMLScene fxmlScene = FXMLScene.load(fxmlPath);
                CustomizeBlockController customizeBlockController = (CustomizeBlockController) fxmlScene.controller;
                String imagePath = getRelativePath(itemList[i]);
                InputStream imageStream = getClass().getResourceAsStream(imagePath);
                if (imageStream != null) {
                    customizeBlockController.setCustomizeBlockData(new Image(imageStream));
                    customizeBlockController.setPath(imagePath);
                    ImageView imageView = (ImageView) fxmlScene.root.lookup("#componentImg");
                    imageView.getProperties().put("controller", customizeBlockController);
                    imageView.setOnMouseClicked(this::setComponent);

                } else {
                    System.err.println("Shirt Image not found: " + imagePath);
                }

                gridPane.add(fxmlScene.root, column++, row);
                if (column == 7) {
                    column = 0;
                    row++;
                }
            }catch (IOException e){
                System.out.println(e.getMessage());
            }

        }
    }
}

