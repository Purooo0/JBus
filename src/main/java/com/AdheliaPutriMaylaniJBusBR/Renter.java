package com.AdheliaPutriMaylaniJBusBR;

import com.AdheliaPutriMaylaniJBusBR.dbjson.Serializable;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Renter extends Serializable{
    public String address;
    public String companyName;
    public String phoneNumber;
    public static final String REGEX_NAME = "^[A-Z][a-zA-Z0-9_]{4,20}$";
    public static final String REGEX_PHONE_NUMBER = "^[0-9]{9,12}$";

    public Renter(int id, String companyName, String phoneNumber) {
        this.companyName = companyName;
        this.address = "";
        this.phoneNumber = phoneNumber;
    }

    public Renter(String companyName, String phoneNumber, String address) {
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Renter(String companyName) {
        this.companyName = companyName;
        this.address = "";
        this.phoneNumber = "";
    }

    public Renter(String companyName, String address) {
        this.companyName = companyName;
        this.address = address;
        this.phoneNumber = "";
    }

    public boolean validate(){
        if (this.companyName.matches(REGEX_NAME) && this.phoneNumber.matches(REGEX_PHONE_NUMBER)){
            return true;
        }
        return false;
    }
}