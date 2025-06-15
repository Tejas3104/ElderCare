package com.example.finalproject;
public class Doctor {
    private String name;
    private String specialization;
    private String email;
    private String phone;
    private String personalDetails;
    private String professionalDetails;
    public Doctor() {}
    public Doctor(String name, String specialization, String email, String phone) {
        this.name = name;
        this.specialization = specialization;
        this.email = email;
        this.phone = phone;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSpecialization() {
        return specialization;
    }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setPersonalDetails(String personalDetails) {
        this.personalDetails = personalDetails;
    }

    public void setProfessionalDetails(String professionalDetails) {
        this.professionalDetails = professionalDetails;
    }
    public String getPersonalDetails() {
        return personalDetails;
    }

    public String getProfessionalDetails() {
        return professionalDetails;
    }

}
