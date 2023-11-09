package com.AdheliaPutriMaylaniJBusBR;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account {
    public String email;
    public String name;
    public String password;
    public final static String REGEX_PASSWORD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
    public final static String REGEX_EMAIL = "^[A-Za-z\\d]+@[A-Za-z]+\\.[A-Za-z]+$";
    
    public Object write(){
        return this;
    }
    
    public boolean read(String file){
        return true;
    }

    public Account(String name, String email, String password) {
        this.name = name;
        if (email.matches(REGEX_EMAIL)) {
            this.email = email;
        } else {
            this.email = "";
        }
        if (password.matches(REGEX_PASSWORD)) {
            this.password = password;
        } else {
            this.password = "";
        }
    }

    public boolean validate() {
        Pattern emailPattern = Pattern.compile(REGEX_EMAIL);
        Matcher emailMatcher = emailPattern.matcher(email);
        Pattern passwordPattern = Pattern.compile(REGEX_PASSWORD);
        Matcher passwordMatcher = passwordPattern.matcher(password);

        boolean emailValid = emailMatcher.matches();
        boolean passwordValid = passwordMatcher.matches();

        return emailValid && passwordValid;
    }
    
    public String toString(){
        return "Account ID: "+ "\n" +
               "Username: "+ name + "\n" +
               "Email: "+ email;
    }

}