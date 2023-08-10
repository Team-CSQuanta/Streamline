package com.csquanta.streamline.Models;
import javafx.scene.image.Image;

import java.io.*;

import static java.util.Objects.requireNonNull;

public class UserInformation implements Serializable {
    public static UserInformation userInfo = new UserInformation();
    public  String avatarImageBg;
    public  String avatarImageHead;
    public  String avatarImageHair;
    public  String avatarImageHeadGear;
    public  String avatarImageBody;
    public  String avatarImageArmor;
    public  String avatarImagePet;

    public UserInformation() {
    }

    public UserInformation(String avatarImageBg, String avatarImageHead, String avatarImageHair, String avatarImageHeadGear, String avatarImageBody, String avatarImageArmor, String avatarImagePet) {
        this.avatarImageBg = avatarImageBg;
        this.avatarImageHead = avatarImageHead;
        this.avatarImageHair = avatarImageHair;
        this.avatarImageHeadGear = avatarImageHeadGear;
        this.avatarImageBody = avatarImageBody;
        this.avatarImageArmor = avatarImageArmor;
        this.avatarImagePet = avatarImagePet;
    }

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
            UserInformation userInformation = new UserInformation(userInfo.getAvatarImageBg(), userInfo.getAvatarImageHead(), userInfo.getAvatarImageHair(), userInfo.getAvatarImageHeadGear(), userInfo.getAvatarImageBody(), userInfo.getAvatarImageArmor(), userInfo.getAvatarImagePet());
            objOStream.writeObject(userInformation);
        }catch (Exception e){
            System.out.println("Serialization failed");
        }
    }
    public static void deserializeUserInfo(){
        try(ObjectInputStream objIStream = new ObjectInputStream(new FileInputStream("User_Information_file"))){
            UserInformation user = (UserInformation) objIStream.readObject();
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

        }catch (Exception e){
            System.out.println("Deserialization failed");
            e.printStackTrace();
        }
    }
}
