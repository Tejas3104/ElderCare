package com.example.finalproject;

import android.widget.EditText;

public class MedClass {
    private String firm;
    private String owner;
    private String mobile;
    private String email;
    private String registration;
    private String gst;

    public String getFirm() {
        return firm;
    }

    public String getOwner() {
        return owner;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getRegistration() {
        return registration;
    }

    public String getGst() {
        return gst;
    }

    public MedClass(String firm, String owner, String mobile, String email, String registration, String gst) {
        this.firm = firm;
        this.owner = owner;
        this.mobile = mobile;
        this.email = email;
        this.registration = registration;
        this.gst = gst;
    }
}
