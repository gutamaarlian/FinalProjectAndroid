package com.example.myapplication.entity;

public class JWToken {
    private String userId;
    private String userName;
    private String userEmail;
    private String agencyId;

    public JWToken(String userId, String userName, String userEmail, String agencyId) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.agencyId = agencyId;
    }

    public JWToken() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }
}
