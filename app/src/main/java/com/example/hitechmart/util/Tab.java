package com.example.hitechmart.util;

public enum Tab {
    HOME("HOME"), PRODUCTS("PRODUCTS"), CART("CART"), PROFILE("PROFILE");

    private String tag;

    public String getTag() {
        return tag;
    }

    Tab(String tag) {
        this.tag = tag;
    }
}
