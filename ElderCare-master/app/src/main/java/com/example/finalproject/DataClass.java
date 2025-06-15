package com.example.finalproject;


public class DataClass {
    private String name;
    private String DOB;
    private String weight;
    private String height;
    private String imageURL;
    private String key;
    private String gender;

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getName() {
        return name;
    }
    public String getDOB() {
        return DOB;
    }
    public String getWeight() {
        return weight;
    }
    public String getHeight() {
        return height;
    }
    public String getImageURL() {
        return imageURL;
    }
    public DataClass(String name, String DOB, String weight, String height, String imageURL,String gender) {
        this.name = name;
        this.DOB = DOB;
        this.weight = weight;
        this.height = height;
        this.imageURL = imageURL;
        this.gender=gender;
    }
    public DataClass(){
    }
}