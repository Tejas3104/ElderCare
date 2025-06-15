package com.example.finalproject;
public class User {
    private DoctorDetails docd;
    private MedDetails medd;
    private Pathology pathd;
    private UserDetails userd;
    public User(DoctorDetails docd, MedDetails medd, Pathology pathd, UserDetails userd) {
        this.docd = docd;
        this.medd = medd;
        this.pathd = pathd;
        this.userd = userd;
    }
    public User(){}
    public DoctorDetails getDocd() {
        return docd;
    }
    public void setDocd(DoctorDetails docd) {
        this.docd = docd;
    }

    public MedDetails getMedd() {
        return medd;
    }

    public void setMedd(MedDetails medd) {
        this.medd = medd;
    }

    public Pathology getPathd() {
        return pathd;
    }

    public void setPathd(Pathology pathd) {
        this.pathd = pathd;
    }

    public UserDetails getUserd() {
        return userd;
    }

    public void setUserd(UserDetails userd) {
        this.userd = userd;
    }

    public static class DoctorDetails {
        String doc1;
        String med1;

        public String getDoc1() {
            return doc1;
        }

        public void setDoc1(String doc1) {
            this.doc1 = doc1;
        }

        public String getMed1() {
            return med1;
        }

        public void setMed1(String med1) {
            this.med1 = med1;
        }

        public DoctorDetails(String doc1, String med1) {
            this.doc1 = doc1;
            this.med1 = med1;
        }
        public DoctorDetails(){}
    }
    public static class MedDetails {
        String medical, mod;

        public String getMedical() {
            return medical;
        }

        public void setMedical(String medical) {
            this.medical = medical;
        }

        public String getMod() {
            return mod;
        }

        public void setMod(String mod) {
            this.mod = mod;
        }

        public MedDetails(String medical, String mod) {
            this.medical = medical;
            this.mod = mod;
        }
        public MedDetails(){}
    }
    public static class Pathology {
        String patho, physio;

        public String getPatho() {
            return patho;
        }

        public void setPatho(String patho) {
            this.patho = patho;
        }

        public String getPhysio() {
            return physio;
        }

        public void setPhysio(String physio) {
            this.physio = physio;
        }

        public Pathology(String patho, String physio) {
            this.patho = patho;
            this.physio = physio;
        }
        public Pathology(){}
    }

    public static class UserDetails {
        String dob, gender, height, imageURL, name, weight;

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getImageURL() {
            return imageURL;
        }

        public void setImageURL(String imageURL) {
            this.imageURL = imageURL;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public UserDetails(String dob, String gender, String height, String imageURL, String name, String weight) {
            this.dob = dob;
            this.gender = gender;
            this.height = height;
            this.imageURL = imageURL;
            this.name = name;
            this.weight = weight;
        }
        public UserDetails(){}
    }
}
