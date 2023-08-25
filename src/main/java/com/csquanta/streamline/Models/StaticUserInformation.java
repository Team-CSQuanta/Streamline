package com.csquanta.streamline.Models;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class StaticUserInformation {
    // Avatar picture information
    public static Image avatarImageBg;
    public static Image avatarImageHead;
    public static Image avatarImageHair;
    public static Image avatarImageHeadGear;

    public static Image avatarImageBody;
    public static Image avatarImageArmor;
    public static Image avatarImagePet;


    // Users point related
    public static Double totalGoldCoins;
    public static int userHealth;

    public static String userName;
    public static String displayName;
    public static String email;
    public static String password;
    public static String pomodoroSessionTime;
    public static String BreakTime;

    public static int getUserHealth() {
        return userHealth;
    }

    public static void setUserHealth(int userHealth) {
        StaticUserInformation.userHealth = userHealth;
    }
}
