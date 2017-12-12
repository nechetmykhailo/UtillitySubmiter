package com.example.mixazp.utillitysubmiter.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ElectrModel {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("utiles")
    @Expose
    private String utiles;
    @SerializedName("adress")
    @Expose
    private String adress;
    @SerializedName("email")
    @Expose
    private String email;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUtiles() {
        return utiles;
    }

    public void setUtiles(String utiles) {
        this.utiles = utiles;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ElectrModel{" +
                "date='" + date + '\'' +
                ", utiles='" + utiles + '\'' +
                ", adress='" + adress + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}


//package com.example.mixazp.utillitysubmiter.model;
//
//public class ElectrModel {
//        private String date;
//        private String utiles;
//        private String adress;
//        private String email;
//
//        public String getUtiles() {
//            return utiles;
//        }
//
//        public void setUtiles(String utiles) {
//            this.utiles = utiles;
//        }
//
//        public String getAdress() {
//            return adress;
//        }
//
//        public void setAdress(String adress) {
//            this.adress = adress;
//        }
//
//        public String getEmail() {
//            return email;
//        }
//
//        public void setEmail(String email) {
//            this.email = email;
//        }
//
//        public String getDate() {
//            return date;
//        }
//
//        public void setDate(String date) {
//            this.date = date;
//        }
//
//        public ElectrModel() {
//        }
//}
