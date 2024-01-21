package com.example.hitechmart.model;

public class VerifyEmailResponse {
    private Integer id;
    private String email;
    private String code;

    public VerifyEmailResponse(Integer id, String email, String code) {
    this.id = id;
    this.email = email;
    this.code = code;
    }
    public VerifyEmailResponse() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
