package com.example.finalproject;
public class UserRequest {
    private String userId;
    private String requestMessage;
    private String status;
    private String key;
    public UserRequest() {
        // Default constructor required for Firebase Realtime Database
    }
    public UserRequest(String userId, String requestMessage) {
        this.userId = userId;
        this.requestMessage = requestMessage;
        this.status = "Pending";
    }

    public String getUserId() {
        return userId;
    }

    public String getRequestMessage() {
        return requestMessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key=key;
    }
}