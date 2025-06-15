package com.example.finalproject;

public class PhyClass {
    private String pname;
    private String pmobile;
    private String pemail;
    private String pqualification;
    private String pexperience;
    private String plicense;
    public PhyClass(String pname, String pmobile, String pemail, String pqualification, String pexperience, String plicense) {
        this.pname = pname;
        this.pmobile = pmobile;
        this.pemail = pemail;
        this.pqualification = pqualification;
        this.pexperience = pexperience;
        this.plicense = plicense;
    }
    public String getPname() {
        return pname;
    }

    public String getPmobile() {
        return pmobile;
    }

    public String getPemail() {
        return pemail;
    }

    public String getPqualification() {
        return pqualification;
    }

    public String getPexperience() {
        return pexperience;
    }

    public String getPlicense() {
        return plicense;
    }
}
