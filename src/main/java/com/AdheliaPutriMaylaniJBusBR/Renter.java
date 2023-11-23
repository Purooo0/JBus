package com.AdheliaPutriMaylaniJBusBR;

import com.AdheliaPutriMaylaniJBusBR.dbjson.Serializable;

public class Renter extends Serializable{
    public String address;
    public String companyName;
    public String phoneNumber;
    public static final String REGEX_NAME = "^[A-Z][a-zA-Z0-9_]{4,20}$";
    public static final String REGEX_PHONE_NUMBER = "^[0-9]{9,12}$";

    public Renter(String companyName){
        this.companyName = companyName;
        this.address = " ";
        this.phoneNumber = " ";
    }
    public Renter(String companyName, String phoneNumber){
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = " ";
    }
    public Renter(String companyName, String phoneNumber, String Address){
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
    public boolean validate() {
        if (phoneNumber.matches(REGEX_PHONE_NUMBER) && companyName.matches(REGEX_NAME)) {
            return true;
        }
        return false;
    }
}