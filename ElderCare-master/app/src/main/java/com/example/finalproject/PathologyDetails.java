package com.example.finalproject;
import java.nio.file.Path;
public class PathologyDetails {

    private String patho;
    private String physio;

    public String getPhysio() {
        return physio;
    }

    public void setPhysio(String physio) {
        this.physio = physio;
    }

    public String getPatho() {
        return patho;
    }

    public void setPatho(String patho) {
        this.patho = patho;
    }

    public PathologyDetails(String patho,String physio) {

        this.patho = patho;
        this.physio = physio;
    }

    public PathologyDetails()
    {}
}
