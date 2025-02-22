package com.csquanta.streamline.Models;

import animatefx.animation.ZoomIn;
import com.csquanta.streamline.App;
import com.csquanta.streamline.Controllers.*;
import com.csquanta.streamline.Networking.ChallengeInfoWhenParticipated;
import com.csquanta.streamline.Networking.ChallengeTaskLog;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import static com.csquanta.streamline.Models.UserInformation.userInfo;

public class ChallengeUI {
    public static ChallengeUI challengeUI= new ChallengeUI();
    private ChallengeLogController challengeLogController;
    private VBox challengeLog;
    private boolean challengeMode;
    private boolean pendingStatus;
    private String requestSenderEmail;
    private ChallengeController challengeController;
    private VBox challengePage;
    private MonsterInChallengeController monsterInChallengeController;
    private HBox monsterInChallengePage;
    private VBox notHavingAnyChallengePage;

    private VBox challengeRequestSentPage;

    private EvilMonsters selectedMonsterForChallenge;
    public EvilMonsters getSelectedMonsterForChallenge() {
        return selectedMonsterForChallenge;
    }

    public void setSelectedMonsterForChallenge(EvilMonsters selectedMonsterForChallenge) {
        this.selectedMonsterForChallenge = selectedMonsterForChallenge;
    }

    public ChallengeUI(){
        try {
            FXMLScene scene = FXMLScene.load("/Fxml/Challenge.fxml");
            FXMLScene  challengeLogPage = FXMLScene.load("/Fxml/ChallengeLog.fxml");
            FXMLScene challengeMonster = FXMLScene.load("/Fxml/MonsterInChallenge.fxml");
            FXMLScene noChallengeScene = FXMLScene.load("/Fxml/NotHavingAnyChallenge.fxml");
            FXMLScene challengeRequestSent = FXMLScene.load("/Fxml/ChallengeRequestSent.fxml");


            challengeRequestSentPage = (VBox) challengeRequestSent.root;
            notHavingAnyChallengePage = (VBox) noChallengeScene.root;
            monsterInChallengePage = (HBox) challengeMonster.root;
            monsterInChallengeController = (MonsterInChallengeController) challengeMonster.controller;
            challengeLog = (VBox) challengeLogPage.root;
            challengeLogController = (ChallengeLogController) challengeLogPage.controller;
            challengeController = (ChallengeController) scene.controller;
            challengePage = (VBox) scene.root;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public VBox getChallengeRequestSentPage() {
        return challengeRequestSentPage;
    }

    public void setChallengeRequestSentPage(VBox challengeRequestSentPage) {
        this.challengeRequestSentPage = challengeRequestSentPage;
    }

    public MonsterInChallengeController getMonsterInChallengeController() {
        return monsterInChallengeController;
    }

    public void setMonsterInChallengeController(MonsterInChallengeController monsterInChallengeController) {
        this.monsterInChallengeController = monsterInChallengeController;
    }

    public HBox getMonsterInChallengePage() {
        return monsterInChallengePage;
    }

    public void setMonsterInChallengePage(HBox monsterInChallengePage) {
        this.monsterInChallengePage = monsterInChallengePage;
    }

    public VBox getNotHavingAnyChallengePage() {
        return notHavingAnyChallengePage;
    }

    public void setNotHavingAnyChallengePage(VBox notHavingAnyChallengePage) {
        this.notHavingAnyChallengePage = notHavingAnyChallengePage;
    }

    public VBox getChallengeLog() {
        return challengeLog;
    }

    public void setChallengeLog(VBox challengeLog) {
        this.challengeLog = challengeLog;
    }

    public ChallengeLogController getChallengeLogController() {
        return challengeLogController;
    }

    public void setChallengeLogController(ChallengeLogController challengeLogController) {
        this.challengeLogController = challengeLogController;
    }

    public VBox getChallengePage() {
        return challengePage;
    }

    public void setChallengePage(VBox challengePage) {
        this.challengePage = challengePage;
    }

    public ChallengeController getChallengeController() {
        return challengeController;
    }

    public void setChallengeController(ChallengeController challengeController) {
        this.challengeController = challengeController;
    }

    public String getRequestSenderEmail() {
        return requestSenderEmail;
    }

    public void setRequestSenderEmail(String requestSenderEmail) {
        this.requestSenderEmail = requestSenderEmail;
    }

    public boolean getChallengeMode(){
        return challengeMode;
    }
    public void setChallengeMode(boolean mode){
        challengeMode = mode;
    }
    public boolean isPending(){
        return  pendingStatus;
    }
    public void setPendingStatus(boolean status){
        pendingStatus = status;
    }
    public void addChallengeTaskLog(){
        int currentRow = 0;
        FXMLScene block;
        // Removing existing children as it will cause some problem
        if(!ChallengeUI.challengeUI.getChallengeLogController().getLogGridPane().getChildren().isEmpty()){
            ChallengeUI.challengeUI.getChallengeLogController().getLogGridPane().getChildren().clear();
        }
        System.out.println(ChallengeTaskLog.taskLog.getChallengeTaskLogs().size() + " Challenge task log size");
        for(ChallengeTaskLog t: ChallengeTaskLog.taskLog.getChallengeTaskLogs()){

            if(t.getUserEmail().equals(userInfo.getEmail())){

                try {
                    block = FXMLScene.load("/Fxml/ChallengeBlockForSender.fxml");
                    ChallengeBlockController receiverController = (ChallengeBlockController) block.controller;
                    receiverController.setDescriptionMsg("has damaged the monster's health by completing the task titled " + "\"" + t.getTaskTitle()+ "\"");
                    receiverController.setUserName(userInfo.getDisplayName());
                    InputStream stream = new FileInputStream("ProfileImage.png");
                    Image im = new Image(stream);
                    receiverController.getImageCircle().setFill(new ImagePattern(im));
                    ChallengeUI.challengeUI.getChallengeLogController().getLogGridPane().add(block.root, 0, currentRow++);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else {
                try {
                    block = FXMLScene.load("/Fxml/ChallengeBlockForReceiver.fxml");
                    ChallengeBlockController receiverController = (ChallengeBlockController) block.controller;
                    receiverController.setDescriptionMsg("has damaged the monster's health by completing the task titled " + "\"" + t.getTaskTitle() + "\"");
                    receiverController.setUserName(ChallengeInfoWhenParticipated.challengeInfoWhenParticipated.getParticipantsName());

                    InputStream stream = new FileInputStream("received_profile_image.png");
                    Image im = new Image(stream);
                    receiverController.getImageCircle().setFill(new ImagePattern(im));


                    ChallengeUI.challengeUI.getChallengeLogController().getLogGridPane().add(block.root, 1, currentRow++);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }

        }
    }

    public void newLoadForChallengeUI() throws IOException {
        App.newLoad();
        ChallengeController controller = challengeUI.getChallengeController();

        if(!challengeUI.getChallengeMode() && !challengeUI.isPending()){
            controller.getBottomVbox().getChildren().setAll(challengeUI.getNotHavingAnyChallengePage());
            StackPane.setAlignment(challengeUI.getChallengePage(), Pos.BOTTOM_CENTER);
            App.root.getChildren().add(challengeUI.getChallengePage());

        }else if(challengeUI.isPending() && !challengeUI.getChallengeMode()){
            controller.getBottomVbox().getChildren().setAll(challengeUI.getChallengeRequestSentPage());
            StackPane.setAlignment(challengeUI.getChallengePage(), Pos.BOTTOM_CENTER);
            App.root.getChildren().add(challengeUI.getChallengePage());
        }else{
            for(EvilMonsters monster: EvilMonsters.evilMonstersStaticObject.getEvilMonstersList()){
                if(monster.getName().equals(ChallengeInfoWhenParticipated.challengeInfoWhenParticipated.getSelectedMonsterName()) && (monsterInChallengeController.getMonsterImage().getImage() == null && !ChallengeInfoWhenParticipated.challengeInfoWhenParticipated.isChallengeSenderStatus())){
                    // Setting monster initial health

                    setSelectedMonsterForChallenge(monster);
                    System.out.println("(Accessing the monster health by monster.getHealth())Setting monster initial Health " + monster.getHealth());
                    monsterInChallengeController.setMonsterName(monster.getName());
                    selectedMonsterForChallenge.setRemainingHealth(monster.getHealth());
                    monsterInChallengeController.setStrikeProgressBar(0.0);
                    monsterInChallengeController.setMonsterHealthRemaining(1.0);
                    monsterInChallengeController.setMonsterImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(monster.getMonsterImagePath()))));
                }else if(monster.getName().equals(ChallengeInfoWhenParticipated.challengeInfoWhenParticipated.getMonsterNameForChallengeSender()) && (monsterInChallengeController.getMonsterImage().getImage() == null) && ChallengeInfoWhenParticipated.challengeInfoWhenParticipated.isChallengeSenderStatus()){
                    setSelectedMonsterForChallenge(monster);
                    monsterInChallengeController.setMonsterName(monster.getName());
                    selectedMonsterForChallenge.setRemainingHealth(monster.getHealth());
                    monsterInChallengeController.setStrikeProgressBar(0.0);
                    monsterInChallengeController.setMonsterHealthRemaining(1.0);
                    monsterInChallengeController.setMonsterImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(monster.getMonsterImagePath()))));
                }
            }
            controller.getTopHbox().getChildren().setAll(challengeUI.getMonsterInChallengePage());


            controller.getBottomVbox().getChildren().setAll(challengeUI.getChallengeLog());
            if(ChallengeTaskLog.taskLog.getChallengeTaskLogs().size() > 0){
                challengeUI.addChallengeTaskLog();
            }

            StackPane.setAlignment(challengeUI.getChallengePage(), Pos.BOTTOM_CENTER);
            App.root.getChildren().add(challengeUI.getChallengePage());
        }
        ZoomIn zoomIn = new ZoomIn();
        zoomIn.setNode(ChallengeUI.challengeUI.getChallengePage());
        zoomIn.setSpeed(3);
        zoomIn.play();
    }

    public void deductMonsterHealth(int numOfSession){
        System.out.println("Selected monster health: " + selectedMonsterForChallenge.getHealth());
        double remainingHealthOfMonster = selectedMonsterForChallenge.getRemainingHealth() - (numOfSession* 100);
        selectedMonsterForChallenge.setRemainingHealth(remainingHealthOfMonster);
        System.out.println("Deductible Health per session: " + remainingHealthOfMonster);
        double deductibleHealth = ((selectedMonsterForChallenge.getHealth() -remainingHealthOfMonster) / selectedMonsterForChallenge.getHealth());
        System.out.println("Deductible health: " + deductibleHealth);
        Platform.runLater(() ->{
            monsterInChallengeController.setMonsterHealthRemaining(1.0  -deductibleHealth);
        });

    }
    public void monsterAcquisition(){
        Random randomChoice = new Random();
        Random randomItemPicker = new Random();
        switch (randomChoice.nextInt(4)) {
            case 0 -> {
                int armorBoughtListSize = ShopController.shop.getBoughtArmorList().size();
                if(armorBoughtListSize == 0)
                    break;
                ArrayList<Item> armorBoughtList = new ArrayList<>(ShopController.shop.getBoughtArmorList());
                ShopController.shop.removeArmorFromBoughtList(armorBoughtList.get(randomItemPicker.nextInt(armorBoughtListSize)));
                System.out.println("Armor item removed");
            }
            case 1 -> {
                int headWearBoughtSize = ShopController.shop.getBoughtHeadWearList().size();
                if(headWearBoughtSize == 0)
                    break;
                ArrayList<Item> headWearBoughtList = new ArrayList<>(ShopController.shop.getBoughtHeadWearList());
                ShopController.shop.removeHeadWearFromBoughtList(headWearBoughtList.get(randomItemPicker.nextInt(headWearBoughtSize)));
                System.out.println("Head Wear item removed");
            }
            case 2 -> {
                int petListSize = ShopController.shop.getBoughtPetList().size();
                if(petListSize == 0)
                    break;
                ArrayList<Item> petBoughtList = new ArrayList<>(ShopController.shop.getBoughtPetList());
                ShopController.shop.removePetFromBoughtList(petBoughtList.get(randomItemPicker.nextInt(petListSize)));
                System.out.println("Pet item removed");
            }
            case 3 -> {
                int bgListSize = ShopController.shop.getBoughtBackgroundList().size();
                if(bgListSize == 0)
                    break;
                ArrayList<Item> bgBoughtList = new ArrayList<>(ShopController.shop.getBoughtBackgroundList());
                ShopController.shop.removeBackgroundFrommBoughtList(bgBoughtList.get(randomItemPicker.nextInt(bgListSize)));
                System.out.println("Background Item removed");
            }
            default -> System.out.println("In  default mode");
        }
    }


    public ConsistenceInformation consistencyChecker(){
        ArrayList<Integer> list = userInfo.getConsistencyTracker();
        int consistencyStreak = 0;
        boolean isConsistence = false;
        for(int i = 0; i <(list.size() -1); i++){
            if(list.get(i) == 1 && list.get(i + 1) == 1){
                consistencyStreak++;
                isConsistence = true;
            }else {
                isConsistence = false;
                break;
            }
        }
        return new ConsistenceInformation(consistencyStreak, isConsistence);
    }

    public void  deductHealthPointsBasedOnMonsterDamagePerAttack(){
        if(ChallengeInfoWhenParticipated.challengeInfoWhenParticipated.isChallengeSenderStatus()){
            String monsterName = ChallengeInfoWhenParticipated.challengeInfoWhenParticipated.getMonsterNameForChallengeSender();
            for(EvilMonsters e: EvilMonsters.evilMonstersStaticObject.getEvilMonstersList()){
                if(e.getName().equals(monsterName)){
                    int damagePerAttack = e.getDamagePerAttack();
                    if(userInfo.getUserHealth() -damagePerAttack > 0)
                        userInfo.setUserHealth(userInfo.getUserHealth() - damagePerAttack);
                    else
                        userInfo.setUserHealth(0);
                    double previousValue = monsterInChallengeController.getStrikeProgressBar().getProgress()* 100;
                    System.out.println("Previous value of strike progress bar is : " + previousValue);
                    monsterInChallengeController.getStrikeProgressBar().setProgress((previousValue + damagePerAttack)/100);
                    System.out.println("Monster attacked as there is no future task scheduled");
                    break;
                }
            }
        }else {
            String monsterName = ChallengeInfoWhenParticipated.challengeInfoWhenParticipated.getSelectedMonsterName();
            for(EvilMonsters e: EvilMonsters.evilMonstersStaticObject.getEvilMonstersList()){
                if(e.getName().equals(monsterName)){
                    int damagePerAttack = e.getDamagePerAttack();
                    if(userInfo.getUserHealth() -damagePerAttack > 0)
                        userInfo.setUserHealth(userInfo.getUserHealth() - damagePerAttack);
                    else
                        userInfo.setUserHealth(0);
                    double previousValue = monsterInChallengeController.getStrikeProgressBar().getProgress()* 100;
                    System.out.println("Previous value of strike progress bar is : " + previousValue);
                    monsterInChallengeController.getStrikeProgressBar().setProgress((previousValue + damagePerAttack)/100);
                    System.out.println("Monster attacked as there is no future task scheduled");
                    break;
                }
            }
        }

    }
}
