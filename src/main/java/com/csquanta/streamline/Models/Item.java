package com.csquanta.streamline.Models;

public class Item {
    private String imgSrc;
    private String title;
    private String Price;

    public Item(String imgSrc, String title, String price) {
        this.imgSrc = imgSrc;
        this.title = title;
        Price = price;
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
