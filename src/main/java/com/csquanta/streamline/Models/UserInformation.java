package com.csquanta.streamline.Models;
import com.csquanta.streamline.Controllers.ChallengeController;
import com.csquanta.streamline.Controllers.HeaderController;
import com.csquanta.streamline.Controllers.ProfileViewController;
import javafx.scene.image.Image;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

import static java.util.Objects.requireNonNull;

public class UserInformation implements Serializable {
    @Serial
    private static final long serialVersionUID = -7282062709951824673L;
    public static UserInformation userInfo = new UserInformation();

    // Challenge Related fields


    // Avatar related Field
    private  String avatarImageBg;
    private  String avatarImageHead;
    private  String avatarImageHair;
    private  String avatarImageHeadGear;
    private  String avatarImageBody;
    private  String avatarImageArmor;
    private  String avatarImagePet;

    // Users points, health points related
    private Double totalGoldCoins;
    private int userHealth;



    // Account related information

    private String userName;
    private String displayName;
    private String email;
    private String password;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    //time
    private  String pomodoroSessionTime;
    private String BreakTime;

    public String getPomodoroSessionTime() {
        return pomodoroSessionTime;
    }

    public void setPomodoroSessionTime(String pomodoroSessionTime) {
        this.pomodoroSessionTime = pomodoroSessionTime;
    }

    public String getBreakTime() {
        return BreakTime;
    }

    public void setBreakTime(String breakTime) {
        BreakTime = breakTime;
    }

// User points health points related getter & Setter

    public Double getTotalGoldCoins() {
        return totalGoldCoins;
    }

    public void setTotalGoldCoins(Double totalGoldCoins) {
        this.totalGoldCoins = totalGoldCoins;
    }
    public void deductGoldCoins(Double amounts){
        double remainingGoldCoins = this.totalGoldCoins -amounts;
        if((remainingGoldCoins)< 0){
            this.totalGoldCoins = remainingGoldCoins;
            StaticUserInformation.totalGoldCoins = 0.0;
        }
        else{
            StaticUserInformation.totalGoldCoins = remainingGoldCoins;
            this.totalGoldCoins = remainingGoldCoins;
        }
    }
    public void addGoldCoins(Double amounts){
        this.totalGoldCoins += amounts;
        StaticUserInformation.totalGoldCoins = this.totalGoldCoins;
    }

    public int getUserHealth() {
        return userHealth;
    }

    public void setUserHealth(int userHealth) {
        this.userHealth = userHealth;
        ProfileViewController profileViewController = HeaderController.getController();
        if (profileViewController != null) {
            profileViewController.updateHealthProgress(userHealth);
        }
    }


    public void deductHealthPointsBasedOnTasks(ArrayList<Task> incompleteTasks) {
        int maxDeductionPercentage = 40; // Maximum percentage of health points that can be deducted

        int totalIncompleteTasks = incompleteTasks.size();
        Random random = new Random();

        int deductionPercentage = Math.min(maxDeductionPercentage, totalIncompleteTasks * 10);

        int randomFactor = random.nextInt(11); // Generates a random number between 0 and 10
        deductionPercentage = deductionPercentage + randomFactor;

        int deductionValue = (int) Math.round(userHealth * deductionPercentage / 100.0);
        int healthAfterDeduction = userHealth - deductionValue;

        ProfileViewController profileViewController = HeaderController.getController();
        if (profileViewController != null) {
            profileViewController.updateHealthProgress(healthAfterDeduction);
        }

        System.out.println("Deducted " + deductionValue + " health points due to incomplete tasks.");
        System.out.println("Health After Deduction: " + healthAfterDeduction);
    }

    public UserInformation() {
    }

    public UserInformation(String avatarImageBg, String avatarImageHead, String avatarImageHair, String avatarImageHeadGear, String avatarImageBody, String avatarImageArmor, String avatarImagePet, Double totalGoldCoins, Integer userHealth, String userName, String displayName,String email, String password,String pomodoroSessionTime,String breakTime) {
        this.avatarImageBg = avatarImageBg;
        this.avatarImageHead = avatarImageHead;
        this.avatarImageHair = avatarImageHair;
        this.avatarImageHeadGear = avatarImageHeadGear;
        this.avatarImageBody = avatarImageBody;
        this.avatarImageArmor = avatarImageArmor;
        this.avatarImagePet = avatarImagePet;
        this.totalGoldCoins = totalGoldCoins;
        this.userHealth = userHealth;
        this.userName = userName;
        this.displayName = displayName;
        this.email = email;
        this.password = password;
        this.pomodoroSessionTime = pomodoroSessionTime;
        this.BreakTime = breakTime;
    }
    // Avatar related getter & setter
    public String getAvatarImageBg() {
        return avatarImageBg;
    }

    public void setAvatarImageBg(String avatarImageBg) {
        this.avatarImageBg = avatarImageBg;
    }

    public String getAvatarImageHead() {
        return avatarImageHead;
    }

    public void setAvatarImageHead(String avatarImageHead) {
        this.avatarImageHead = avatarImageHead;
    }

    public String getAvatarImageHair() {
        return avatarImageHair;
    }

    public void setAvatarImageHair(String avatarImageHair) {
        this.avatarImageHair = avatarImageHair;
    }

    public String getAvatarImageHeadGear() {
        return avatarImageHeadGear;
    }

    public void setAvatarImageHeadGear(String avatarImageHeadGear) {
        this.avatarImageHeadGear = avatarImageHeadGear;
    }

    public String getAvatarImageBody() {
        return avatarImageBody;
    }

    public void setAvatarImageBody(String avatarImageBody) {
        this.avatarImageBody = avatarImageBody;
    }

    public String getAvatarImageArmor() {
        return avatarImageArmor;
    }

    public void setAvatarImageArmor(String avatarImageArmor) {
        this.avatarImageArmor = avatarImageArmor;
    }

    public String getAvatarImagePet() {
        return avatarImagePet;
    }

    public void setAvatarImagePet(String avatarImagePet) {
        this.avatarImagePet = avatarImagePet;
    }

    public static void serializeUserInfo(){
        try(ObjectOutputStream objOStream = new ObjectOutputStream(new FileOutputStream("User_Information_file"))){
            UserInformation userInformation = new UserInformation(userInfo.getAvatarImageBg(), userInfo.getAvatarImageHead(), userInfo.getAvatarImageHair(), userInfo.getAvatarImageHeadGear(), userInfo.getAvatarImageBody(), userInfo.getAvatarImageArmor(), userInfo.getAvatarImagePet(), userInfo.totalGoldCoins, userInfo.getUserHealth(),userInfo.getUserName(), userInfo.getDisplayName(), userInfo.getEmail(), userInfo.getPassword(),userInfo.getPomodoroSessionTime(),userInfo.getBreakTime());
            objOStream.writeObject(userInformation);
        }catch (Exception e){
            System.out.println("Serialization failed");
        }
    }
    public static void deserializeUserInfo(){
        try(ObjectInputStream objIStream = new ObjectInputStream(new FileInputStream("User_Information_file"))){
            UserInformation user = (UserInformation) objIStream.readObject();
            // User health point
            StaticUserInformation.userHealth = user.getUserHealth();
            userInfo.setUserHealth(user.getUserHealth());

            // Total points
            StaticUserInformation.totalGoldCoins = user.getTotalGoldCoins();
            userInfo.setTotalGoldCoins(user.getTotalGoldCoins());

            // Account related information
            StaticUserInformation.userName = user.getUserName();
            userInfo.setUserName(user.getUserName());

            StaticUserInformation.displayName = user.getDisplayName();
            userInfo.setDisplayName(user.getDisplayName());

            StaticUserInformation.email = user.getEmail();
            userInfo.setEmail(user.getEmail());

            StaticUserInformation.password = user.getPassword();
            userInfo.setPassword(user.getPassword());


            //time

            StaticUserInformation.pomodoroSessionTime = user.getPomodoroSessionTime();
            userInfo.setPomodoroSessionTime(user.getPomodoroSessionTime());
            StaticUserInformation.BreakTime = user.getBreakTime();
            userInfo.setBreakTime(user.getBreakTime());

            //avatar

            if(user.avatarImageBody != null)
            {
                StaticUserInformation.avatarImageBody = new Image(requireNonNull(UserInformation.class.getResourceAsStream(user.avatarImageBody)));
                userInfo.setAvatarImageBody(user.avatarImageBody);

            }
            if(user.avatarImageHead != null){
                StaticUserInformation.avatarImageHead = new Image(requireNonNull(UserInformation.class.getResourceAsStream(user.avatarImageHead)));
                userInfo.setAvatarImageHead(user.avatarImageHead);
            }
            if(user.avatarImageHair != null){
                StaticUserInformation.avatarImageHair = new Image(requireNonNull(UserInformation.class.getResourceAsStream(user.avatarImageHair)));
                userInfo.setAvatarImageHair(user.avatarImageHair);
            }
            if(user.avatarImageBg != null){
                StaticUserInformation.avatarImageBg = new Image(requireNonNull(UserInformation.class.getResourceAsStream(user.avatarImageBg)));
                userInfo.setAvatarImageBg(user.avatarImageBg);
            }
            if(user.avatarImageHeadGear != null){
                StaticUserInformation.avatarImageHeadGear = new Image(requireNonNull(UserInformation.class.getResourceAsStream(user.avatarImageHeadGear)));
                userInfo.setAvatarImageHeadGear(user.avatarImageHeadGear);
            }
            if(user.avatarImagePet != null){
                StaticUserInformation.avatarImagePet = new Image(requireNonNull(UserInformation.class.getResourceAsStream(user.avatarImagePet)));
                userInfo.setAvatarImagePet(user.avatarImagePet);
            }
            if(user.avatarImageArmor != null){
                StaticUserInformation.avatarImageArmor = new Image(requireNonNull(UserInformation.class.getResourceAsStream(user.avatarImageArmor)));
                userInfo.setAvatarImageArmor(user.avatarImageArmor);
            }

        }catch (Exception e){
            System.out.println("Deserialization failed");
            e.printStackTrace();
        }
    }
}
