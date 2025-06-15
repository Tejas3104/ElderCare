package com.example.finalproject;
public class CoClass {
    private String coname;
    private String comobile;
    private String coemail;
    private String coqualification;
    private String coexperience;
    private String colicense;
    public CoClass(String coname, String comobile, String coemail, String coqualification, String coexperience, String colicense) {
        this.coname = coname;
        this.comobile = comobile;
        this.coemail = coemail;
        this.coqualification = coqualification;
        this.coexperience = coexperience;
        this.colicense = colicense;
    }

    public String getConame() {
        return coname;
    }

    public String getComobile() {
        return comobile;
    }

    public String getCoemail() {
        return coemail;
    }

    public String getCoqualification() {
        return coqualification;
    }

    public String getCoexperience() {
        return coexperience;
    }

    public String getColicense() {
        return colicense;
    }
}
