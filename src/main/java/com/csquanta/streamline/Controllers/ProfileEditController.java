package com.csquanta.streamline.Controllers;

import animatefx.animation.ZoomIn;
import com.csquanta.streamline.Models.Item;
import com.csquanta.streamline.Models.StaticUserInformation;
import com.csquanta.streamline.Models.UserInformation;
import javafx.event.Event;
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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
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
    private GridPane gridPaneArmor;

    @FXML
    private GridPane gridPaneHeadGear;

    @FXML
    private GridPane gridPaneHair;

    @FXML
    private Tab backgroundTab;

    @FXML
    private Tab hairTab;

    @FXML
    private Tab armorTab;

    @FXML
    private Tab bodyTab;

    @FXML
    private Tab headWearTab;

    @FXML
    private Tab skinTab;

    @FXML
    private GridPane petGridPane;
    @FXML
    private Tab petTab;

    public void setHeadGear(ImageView headGear) {
        this.headGear.setImage(headGear.getImage());
    }

    public void setAvatarArmor(ImageView avatarArmor) {
        this.avatarArmor.setImage(avatarArmor.getImage());
    }

    public void setAvatarBody(ImageView newAvatarBody) {

        this.avatarBody.setImage(newAvatarBody.getImage());

    }

    public ImageView getHeadGear() {
        return headGear;
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

    public void setAvatarBg(ImageView avatarBg) {
        this.image_bg.setImage(avatarBg.getImage());
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
    void onPetTabSelected(Event event) {
        int columnPet = 0;
        int rowPet = 1;
        for(Item item: ShopController.getShop().getBoughtPetList()){
            try {
                FXMLScene fxmlScene = FXMLScene.load("/Fxml/customizeBlockPet.fxml");
                CustomizeBlockPetController customizeBlockPetController = (CustomizeBlockPetController) fxmlScene.controller;
                String imagePath = item.getImgSrc();
                InputStream imageStream = getClass().getResourceAsStream(imagePath);
                if (imageStream != null) {
                    customizeBlockPetController.setCustomizePetData(new Image(imageStream));
                    customizeBlockPetController.setPetPath(imagePath);
                    ImageView imageView = (ImageView) fxmlScene.root.lookup("#componentImg");
                    imageView.getProperties().put("controller", customizeBlockPetController);
                    imageView.setOnMouseClicked(this::setPetComponent);

                } else {
                    System.err.println("Pet Image not found: " + imagePath);
                }

                petGridPane.add(fxmlScene.root, columnPet++, rowPet);
                if (columnPet == 7) {
                    columnPet = 0;
                    rowPet++;
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    @FXML
    void onArmorTabSelected(Event event) {
        int columnArmor = 0;
        int rowArmor = 1;
        for(Item item: ShopController.getShop().getBoughtArmorList()){
            try {
                FXMLScene fxmlScene = FXMLScene.load("/Fxml/customizeBlock.fxml");
                CustomizeBlockController customizeBlockController = (CustomizeBlockController) fxmlScene.controller;
                String imagePath = item.getImgSrc();
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

                gridPaneArmor.add(fxmlScene.root, columnArmor++, rowArmor);
                if (columnArmor == 7) {
                    columnArmor = 0;
                    rowArmor++;
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    @FXML
    void onHairSelectionChanged(Event event) {
        if (hairTab.isSelected()) {
            addProfileEditItems("/Fxml/customizeBlock.fxml", "src/main/resources/Images/customize/hair", gridPaneHair);
        }
    }

    @FXML
    void onBGSelectionChanged(Event event) {
        if (backgroundTab.isSelected()) {
            int rowBackground = 1;
            int columnBackground = 0;
            HashSet<Item> backgrounds = ShopController.getShop().getBoughtBackgroundList();
            Iterator<Item> backgroundsIterator = backgrounds.iterator();
            while(backgroundsIterator.hasNext()) {
                try {
                    FXMLScene fxmlScene = FXMLScene.load("/Fxml/CustomizeBlockBg.fxml");
                    CustomizeBlockBgController customizeBlockBgController = (CustomizeBlockBgController) fxmlScene.controller;
                    String imagePath = backgroundsIterator.next().getImgSrc();
                    InputStream imageStream = getClass().getResourceAsStream(imagePath);
                    if (imageStream != null) {
                        customizeBlockBgController.setCustomizeBlockBgData(new Image(imageStream));
                        customizeBlockBgController.setBgPath(imagePath);
                        ImageView imageView = (ImageView) fxmlScene.root.lookup("#bgImage");
                        imageView.getProperties().put("controller", customizeBlockBgController);
                        imageView.setOnMouseClicked(this::setBgComponent);

                    } else {
                        System.err.println("Background Image not found: " + imagePath);
                    }

                    gridPaneBg.add(fxmlScene.root, columnBackground++, rowBackground);
                    if (columnBackground == 4) {
                        columnBackground = 0;
                        rowBackground++;
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

    }

    @FXML
    void onBodySelectionChanged(Event event) {
        if (bodyTab.isSelected()) {
            addProfileEditItems("/Fxml/customizeBlock.fxml", "src/main/resources/Images/customize/shirts", gridPaneBody);
        }
    }

    @FXML
    void onHeadGearSelectionChanged(Event event) {
        if (headWearTab.isSelected()) {
            int columnArmor = 0;
            int rowArmor = 1;
            for(Item item: ShopController.getShop().getBoughtHeadWearList()){
                try {
                    FXMLScene fxmlScene = FXMLScene.load("/Fxml/customizeBlock.fxml");
                    CustomizeBlockController customizeBlockController = (CustomizeBlockController) fxmlScene.controller;
                    String imagePath = item.getImgSrc();
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

                    gridPaneHeadGear.add(fxmlScene.root, columnArmor++, rowArmor);
                    if (columnArmor == 7) {
                        columnArmor = 0;
                        rowArmor++;
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    @FXML
    void OnSkinSelectionChanged(Event event) {
        if (skinTab.isSelected()) {
            addProfileEditItems("/Fxml/customizeBlock.fxml", "src/main/resources/Images/customize/skin", gridPaneHead);
        }
    }


    void setComponent(MouseEvent event) {
        if (event.getSource() instanceof ImageView clickedImageView) {
            CustomizeBlockController selectedBlockController = (CustomizeBlockController) clickedImageView.getProperties().get("controller");

            // Determine the source GridPane based on the parent of the parent (ImageView -> VBox -> GridPane)
            GridPane parentGrid = null;

            if (clickedImageView.getParent().getParent() instanceof GridPane) {
                parentGrid = (GridPane) clickedImageView.getParent().getParent();
            }

            if (selectedBlockController != null && parentGrid != null) {
                if (parentGrid == gridPaneBody) {
                    setAvatarBody(clickedImageView);
                    if (clickedImageView.getImage() != null) {
                        StaticUserInformation.avatarImageBody = clickedImageView.getImage();
                        UserInformation.userInfo.setAvatarImageBody(selectedBlockController.getPath());
                    } else {
                        UserInformation.userInfo.setAvatarImageBody(UserInformation.userInfo.getAvatarImageBody());
                    }
                } else if (parentGrid == gridPaneHead) {
                    setAvatarHead(clickedImageView);
                    if (clickedImageView.getImage() != null) {
                        StaticUserInformation.avatarImageHead = clickedImageView.getImage();
                        UserInformation.userInfo.setAvatarImageHead(selectedBlockController.getPath());
                    } else {
                        UserInformation.userInfo.setAvatarImageHead(UserInformation.userInfo.getAvatarImageHead());
                    }
                } else if (parentGrid == gridPaneHair) {
                    setAvatarHair(clickedImageView);
                    if (clickedImageView.getImage() != null) {
                        StaticUserInformation.avatarImageHair = clickedImageView.getImage();
                        UserInformation.userInfo.setAvatarImageHair(selectedBlockController.getPath());
                    } else {
                        UserInformation.userInfo.setAvatarImageHair(UserInformation.userInfo.getAvatarImageHair());
                    }
                }
                else if (parentGrid == gridPaneArmor) {
                    setAvatarArmor(clickedImageView);
                    if (clickedImageView.getImage() != null) {
                        StaticUserInformation.avatarImageArmor = clickedImageView.getImage();
                        UserInformation.userInfo.setAvatarImageArmor(selectedBlockController.getPath());
                    } else {
                        UserInformation.userInfo.setAvatarImageArmor(UserInformation.userInfo.getAvatarImageArmor());
                    }
                }
                else if (parentGrid == gridPaneHeadGear) {
                    setHeadGear(clickedImageView);
                    if (clickedImageView.getImage() != null) {
                        StaticUserInformation.avatarImageHeadGear = clickedImageView.getImage();
                        UserInformation.userInfo.setAvatarImageHeadGear(selectedBlockController.getPath());
                    } else {
                        UserInformation.userInfo.setAvatarImageHeadGear(UserInformation.userInfo.getAvatarImageHeadGear());
                    }
                }
                else if (parentGrid == petGridPane) {
                    setAvatarPet(clickedImageView);
                    if (clickedImageView.getImage() != null) {
                        StaticUserInformation.avatarImagePet = clickedImageView.getImage();
                        UserInformation.userInfo.setAvatarImagePet(selectedBlockController.getPath());
                    } else {
                        UserInformation.userInfo.setAvatarImagePet(UserInformation.userInfo.getAvatarImagePet());
                    }
                }
            }
        }
    }

    private void setBgComponent(MouseEvent event) {
        if (event.getSource() instanceof ImageView clickedImageView) {
            CustomizeBlockBgController selectedBgBlockController = (CustomizeBlockBgController) clickedImageView.getProperties().get("controller");

            // Determine the source GridPane based on the parent of the parent (ImageView -> VBox -> GridPane)
            GridPane parentGrid = null;

            if (clickedImageView.getParent().getParent() instanceof GridPane) {
                parentGrid = (GridPane) clickedImageView.getParent().getParent();
            }

            if (selectedBgBlockController != null && parentGrid != null) {
                if (parentGrid == gridPaneBg) {
                    setAvatarBg(clickedImageView);
                    if (clickedImageView.getImage() != null) {
                        StaticUserInformation.avatarImageBg = clickedImageView.getImage();
                        UserInformation.userInfo.setAvatarImageBg(selectedBgBlockController.getBgPath());
                    } else {
                        UserInformation.userInfo.setAvatarImageBg(UserInformation.userInfo.getAvatarImageBg());
                    }
                }

            }

        }
    }
    private void setPetComponent(MouseEvent event) {
        if (event.getSource() instanceof ImageView clickedImageView) {
            CustomizeBlockPetController selectedPetBlockController = (CustomizeBlockPetController) clickedImageView.getProperties().get("controller");

            // Determine the source GridPane based on the parent of the parent (ImageView -> VBox -> GridPane)
            GridPane parentGrid = null;

            if (clickedImageView.getParent().getParent() instanceof GridPane) {
                parentGrid = (GridPane) clickedImageView.getParent().getParent();
            }

            if (selectedPetBlockController != null && parentGrid != null) {
                if (parentGrid == petGridPane) {
                    setAvatarPet(clickedImageView);
                    if (clickedImageView.getImage() != null) {
                        StaticUserInformation.avatarImagePet = clickedImageView.getImage();
                        UserInformation.userInfo.setAvatarImagePet(selectedPetBlockController.getPetPath());
                    } else {
                        UserInformation.userInfo.setAvatarImagePet(UserInformation.userInfo.getAvatarImagePet());
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
        image_bg.setImage(StaticUserInformation.avatarImageBg);
        avatarArmor.setImage(StaticUserInformation.avatarImageArmor);
        headGear.setImage(StaticUserInformation.avatarImageHeadGear);
        avatarPet.setImage(StaticUserInformation.avatarImagePet);




    }

    public String getRelativePath(File file) throws IOException {
        String getPath = file.getCanonicalPath();
        String parentPath = new File("src/main/resources").getCanonicalPath();

        if (getPath.startsWith(parentPath)) {
            String relativePath = getPath.substring(parentPath.length());
            return relativePath.replace("\\", "/");
        } else {
            throw new IllegalArgumentException("File is not within the resources directory: " + getPath);
        }
    }

    public void addProfileEditItems(String fxmlPath, String filePath, GridPane gridPane) {
        File itemFile = new File(filePath);
        File[] itemList = itemFile.listFiles();
        int row = 1;
        int column = 0;
        int numOfItem = itemList != null ? itemList.length : 0;
        for (int i = 0; i < numOfItem; i++) {
            // Load and set up for shirts
            try {
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
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}