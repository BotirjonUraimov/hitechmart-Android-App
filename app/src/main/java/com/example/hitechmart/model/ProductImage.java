package com.example.hitechmart.model;

import com.google.gson.annotations.SerializedName;

public class ProductImage {

    @SerializedName("image")
    private String image;

    public ProductImage(String image) {
        this.image = image;
    }

    public ProductImage() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
