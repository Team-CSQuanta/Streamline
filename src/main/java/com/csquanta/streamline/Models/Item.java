package com.csquanta.streamline.Models;

public class Item {
    private String imgSrc;
    private String title;
    private int Price;

    public Item(String imgSrc, String title, int price) {
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

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

}
