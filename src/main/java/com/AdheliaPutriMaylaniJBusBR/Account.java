package com.AdheliaPutriMaylaniJBusBR;

import com.AdheliaPutriMaylaniJBusBR.dbjson.Serializable;

public class Account extends Serializable {
    public Renter company;
    public double balance;
    public String email;
    public String name;
    public String password;
    public final static String REGEX_PASSWORD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
    public final static String REGEX_EMAIL = "^[A-Za-z\\d]+@[A-Za-z]+\\.[A-Za-z]+$";

    public Account(String name, String email, String password) {
        this.balance = 0;
        this.name = name;
        this.email = email;
        this.password = password;
        this.company = null;
    }

    public boolean validate() {
        return this.email.matches(REGEX_EMAIL) && this.password.matches(REGEX_PASSWORD);
    }

    public boolean read(String file){
        return true;
    }

    public Object write(){
        return null;
    }

    public boolean topUp(double amount){
        if (amount <= 0){
            return false;
        }
        balance += balance + amount;
        return true;
    }
    
    public String toString(){
        return "Account ID: "+ super.id +
                "\nName: "+ name +
                "\nEmail: "+ email +
                "\nPassword: "+ password;
    }
}