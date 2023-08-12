package com.csquanta.streamline.Controllers;

import animatefx.animation.FadeIn;
import animatefx.animation.ZoomIn;
import com.csquanta.streamline.Models.Item;
import com.csquanta.streamline.Models.StaticUserInformation;
import com.csquanta.streamline.Models.UserInformation;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.event.Event;
import javafx.scene.paint.Color;

import java.io.*;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.csquanta.streamline.Controllers.HeaderController.modalPaneForHeader;

public class ProfileEditController implements Initializable {
    static int rowBackground = 1;
    static int columnBackground = 0;
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


    private CustomizeBlockController selectedBlockController;
    private CustomizeBlockBgController selectedBgBlockController;

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
    void onHairSelectionChanged(Event event) {
        if (hairTab.isSelected()) {
            addProfileEditItems("/Fxml/customizeBlock.fxml", "src/main/resources/Images/customize/hair", gridPaneHair);
        }
    }

    @FXML
    void onBGSelectionChanged(Event event) {
        if (backgroundTab.isSelected()) {
            for(Item item: ShopController.getShop().getBuyedArmorList()){
                try {
                    FXMLScene fxmlScene = FXMLScene.load("/Fxml/CustomizeBlockBg.fxml");
                    CustomizeBlockBgController customizeBlockBgController = (CustomizeBlockBgController) fxmlScene.controller;
                    String imagePath = item.getImgSrc();
                    InputStream imageStream = getClass().getResourceAsStream(imagePath);
                    if (imageStream != null) {
                        customizeBlockBgController.setCustomizeBlockBgData(new Image(imageStream));
                        customizeBlockBgController.setBgPath(imagePath);
                        ImageView imageView = (ImageView) fxmlScene.root.lookup("#bgImage");
                        imageView.getProperties().put("controller", customizeBlockBgController);
                        imageView.setOnMouseClicked(this::setBgComponent);

                    } else {
                        System.err.println("Shirt Image not found: " + imagePath);
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
            System.out.println("Background Tab is selected");

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
//            addShopItems("/Fxml/customizeBlock.fxml", );
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
            selectedBlockController = (CustomizeBlockController) clickedImageView.getProperties().get("controller");

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

            }
        }
    }

    private void setBgComponent(MouseEvent event) {
        if (event.getSource() instanceof ImageView clickedImageView) {
            selectedBgBlockController = (CustomizeBlockBgController) clickedImageView.getProperties().get("controller");

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        avatarBody.setImage(StaticUserInformation.avatarImageBody);
        avatarHead.setImage(StaticUserInformation.avatarImageHead);
        avatarHair.setImage(StaticUserInformation.avatarImageHair);
        image_bg.setImage(StaticUserInformation.avatarImageBg);
        // Adding Default Backgrounds
        File bgFile = new File("src/main/resources/Images/backgrounds/Defaults");
        File[] listBGFile = bgFile.listFiles();

        for (int i = 0; i < listBGFile.length; i++) {

            try {
                FXMLScene fxmlScene = FXMLScene.load("/Fxml/CustomizeBlockBg.fxml");
                CustomizeBlockBgController customizeBlockBgController = (CustomizeBlockBgController) fxmlScene.controller;
                String imagePath = getRelativePath(listBGFile[i]);
                InputStream imageStream = getClass().getResourceAsStream(imagePath);
                if (imageStream != null) {
                    customizeBlockBgController.setCustomizeBlockBgData(new Image(imageStream));
                    customizeBlockBgController.setBgPath(imagePath);
                    ImageView imageView = (ImageView) fxmlScene.root.lookup("#bgImage");
                    imageView.getProperties().put("controller", customizeBlockBgController);
                    imageView.setOnMouseClicked(this::setBgComponent);

                } else {
                    System.err.println("Shirt Image not found: " + imagePath);
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
                System.out.println(column);
                System.out.println(row);
                if (column == 7) {
                    column = 0;
                    row++;
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }



    //I will complete it later!

//    public void addPurchasedItemToAvatar(ImageView purchasedItemImageView, String gridPaneName) {
//
//        FXMLScene fxmlScene;
//        try {
//            fxmlScene = FXMLScene.load("/Fxml/ProfileEdit.fxml");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        ProfileEditController controller = (ProfileEditController) fxmlScene.controller;
//
//        GridPane targetGridPane = null;
//        switch (gridPaneName) {
//            case "gridPaneArmor":
//                targetGridPane = controller.gridPaneArmor;
//                break;
//
//            case "gridPaneHeadGear":
//                targetGridPane = gridPaneHeadGear;
//                break;
//        }
//
//
//        int nextRow = 0;
//        int nextCol = 0;
//
//        if (targetGridPane != null) {
//
//            int numRows = targetGridPane.getRowCount();
//            int numCols = targetGridPane.getColumnCount();
//            nextRow = numRows -1;
//
//
//            boolean[] occupiedCols = new boolean[numCols];
//
//
//            for (Node node : targetGridPane.getChildren()) {
//                Integer colIndex = GridPane.getColumnIndex(node);
//                Integer rowIndex = GridPane.getRowIndex(node);
//
//                if (colIndex != null && rowIndex != null && rowIndex == nextRow) {
//                    occupiedCols[colIndex] = true;
//                }
//            }
//
//
//            for (int col = 0; col < numCols; col++) {
//                if (!occupiedCols[col]) {
//                    nextCol = col;
//                    break;
//                }
//            }
//            VBox vbox = new VBox();
//            vbox.setPrefSize(100, 100);
//            vbox.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(10), null)));
//            vbox.setBorder(new Border(new BorderStroke(Color.valueOf("#9580FF"), BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
//            vbox.getChildren().add(purchasedItemImageView);
//
//            targetGridPane.add(vbox, nextCol, nextRow);
//            System.out.println("CC");
//            purchasedItemImageView.setOnMouseClicked(this::setComponent);
//
//           // targetGridPane.layout();
//
//
//        }
//
//    }

}