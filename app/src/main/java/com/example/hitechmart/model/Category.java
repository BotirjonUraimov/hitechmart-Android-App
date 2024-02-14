package com.example.hitechmart.model;

import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("id")
    private int id;
    @SerializedName("classification")
    private int classification;
    @SerializedName("title")
    private String title;
    @SerializedName("is_active")
    private Boolean isActive;
    @SerializedName("is_default")
    private Boolean isDefault;


    public Category() {
    }

    public Category(int id, String title, Boolean isActive, Boolean isDefault, int classification) {
        this.id = id;
        this.title = title;
        this.isActive = isActive;
        this.isDefault = isDefault;
        this.classification = classification;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

    public int getClassification() {
        return classification;
    }

    public void setClassification(int classification) {
        this.classification = classification;
    }
}
