package com.example.finalproject;
public class MedicalDetails {
    private String medical;
    private String mob;

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    public String getMedical() {
        return medical;
    }

    public void setMedical(String medical) {
        this.medical = medical;
    }

    public MedicalDetails(String medical,String mob) {
        this.medical = medical;
        this.mob=mob;
    }


}
