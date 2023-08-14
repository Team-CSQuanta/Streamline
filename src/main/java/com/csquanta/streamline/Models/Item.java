package com.csquanta.streamline.Models;

import java.io.Serializable;

public class Item implements Serializable {
    private String imgSrc;
    private String title;
    private String Price;
    private String itemType;

    public Item(String imgSrc, String title, String price, String itemType) {
        this.imgSrc = imgSrc;
        this.title = title;
        this.itemType = itemType;
        Price = price;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

}
