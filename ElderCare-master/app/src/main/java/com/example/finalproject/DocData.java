package com.example.finalproject;

public class DocData {
    private String doctorname;
    private String doctormobile;
    private String doctoremail;
    private String doctorqualification;
    private String doctorexperience;
    private String doctorspecification;

    public String getDoctorspecification() {
        return doctorspecification;
    }

    public void setDoctorspecification(String doctorspecification) {
        this.doctorspecification = doctorspecification;
    }

    private String doctorlicense;
    public DocData(String doctorname, String doctormobile, String doctoremail, String doctorqualification, String doctorexperience, String doctorlicense,String doctorspecification) {
        this.doctorname = doctorname;
        this.doctormobile = doctormobile;
        this.doctoremail = doctoremail;
        this.doctorqualification = doctorqualification;
        this.doctorexperience = doctorexperience;
        this.doctorlicense = doctorlicense;
        this.doctorspecification=doctorspecification;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public String getDoctormobile() {
        return doctormobile;
    }

    public String getDoctoremail() {
        return doctoremail;
    }

    public String getDoctorqualification() {
        return doctorqualification;
    }

    public String getDoctorexperience() {
        return doctorexperience;
    }

    public String getDoctorlicense() {
        return doctorlicense;
    }


    public DocData(){}

}
