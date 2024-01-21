package com.example.hitechmart.model;

public class ResetRequest {

    private String confirm_password;
    private String password;

    public ResetRequest() {

    }
    public ResetRequest(String confirm_password, String password) {
        this.confirm_password = confirm_password;
        this.password = password;
    }

}
