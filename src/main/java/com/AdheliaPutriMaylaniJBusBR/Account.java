package com.AdheliaPutriMaylaniJBusBR;

import com.AdheliaPutriMaylaniJBusBR.dbjson.Serializable;
import com.AdheliaPutriMaylaniJBusBR.dbjson.*;
import com.AdheliaPutriMaylaniJBusBR.controller.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account extends Serializable {
    private Renter renter;
    public double balance;
    public String email;
    public String name;
    public String password;
    public final static String REGEX_PASSWORD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
    public final static String REGEX_EMAIL = "^[A-Za-z\\d]+@[A-Za-z]+\\.[A-Za-z]+$";

    public Account(String name, String email, String password) {
        super();
        this.balance = 0;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public boolean validate() {
        return this.email.matches(REGEX_EMAIL) && this.password.matches(REGEX_PASSWORD);
    }
    
    public String toString(){
        return "Account ID: "+ super.id +
                "\nName: "+ name +
                "\nEmail: "+ email +
                "\nPassword: "+ password;
    }
}