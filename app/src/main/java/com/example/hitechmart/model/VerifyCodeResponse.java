package com.example.hitechmart.model;

public class VerifyCodeResponse  {
    private String access_token;

    public VerifyCodeResponse(String access_token) {
        this.access_token = access_token;
    }
    public  VerifyCodeResponse () {

    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }


}
