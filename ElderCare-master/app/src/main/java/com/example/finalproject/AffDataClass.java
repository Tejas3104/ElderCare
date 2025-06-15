package com.example.finalproject;

public class AffDataClass {
    private String affname;
    private String affDOB;
    private String affimageURL;
    private String key;
    private String affgender;
    public String getAffname() {
        return affname;
    }
    public void setAffname(String affname) {
        this.affname = affname;
    }
    public String getAffDOB() {
        return affDOB;
    }
    public void setAffDOB(String affDOB) {
        this.affDOB = affDOB;
    }
    public String getAffimageURL() {
        return affimageURL;
    }
    public void setAffimageURL(String affimageURL) {
        this.affimageURL = affimageURL;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getAffgender() {
        return affgender;
    }
    public void setAffgender(String affgender) {
        this.affgender = affgender;
    }
    public AffDataClass(String affname, String affDOB, String affimageURL, String affgender) {
        this.affname = affname;
        this.affDOB = affDOB;
        this.affimageURL = affimageURL;
        this.affgender = affgender;
    }
}
