package AdheliaPutriMaylaniJBusBR;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Adhelia Putri Maylani [2206814816]
 * Version 9.9.23
 * CS - OOP
 */

public class Renter{
    public String address;
    public String companyName;
    public int phoneNumber;
    private final String REGEX_PHONE = "^[0-9]{9,12}$";
    private final String REGEX_NAME = "^[A-Z][A-Za-z0-9_]{3,19}$";

    public boolean validate(){
        Pattern namePattern = Pattern.compile(REGEX_NAME);
        Matcher name = namePattern.matcher(companyName);
        Pattern phonePattern = Pattern.compile(REGEX_PHONE);
        Matcher phone = phonePattern.matcher("1234567890");

        boolean companyName = name.matches();
        boolean phoneNumber = phone.matches();

        return companyName && phoneNumber;

    }
    public Renter(String companyName, int phoneNumber){
        this.companyName = companyName;
        this.address = "";
        this.phoneNumber = phoneNumber;
    }
    
    public Renter(String companyName, int phoneNumber, String address){
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
    
    public Renter(String companyName){
        this.companyName = companyName;
        this.address = "";
        this.phoneNumber = 0;
    }
    
    public Renter(String companyName, String address){
        this.companyName = "";
        this.address = "";
        this.phoneNumber = 0;
    }

    public int getId() {
        int id = 0;
        return id;
    }
}