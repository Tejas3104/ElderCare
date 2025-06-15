package com.example.finalproject;

public class MedData {
    private String docname1;
    private String docname2;
    private String medicines1;
    private String medicines2;

    public String getMedicines1() {
        return medicines1;
    }

    public void setMedicines1(String medicines1) {
        this.medicines1 = medicines1;
    }

    public String getMedicines2() {
        return medicines2;
    }

    public void setMedicines2(String medicines2) {
        this.medicines2 = medicines2;
    }

    public String getDocname2() {
        return docname2;
    }
    public void setDocname2(String docname2) {
        this.docname2 = docname2;
    }

    public String getDocname1() {
        return docname1;
    }
    public void setDocname1(String docname1) {
        this.docname1 = docname1;
    }
    public MedData(String docname1, String docname2,String medicines1,String medicines2) {
        this.docname1 = docname1;
        this.docname2 = docname2;
        this.medicines1=medicines1;
        this.medicines2=medicines2;
    }
}
