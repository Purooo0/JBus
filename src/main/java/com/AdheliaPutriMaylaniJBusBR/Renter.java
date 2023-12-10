package com.AdheliaPutriMaylaniJBusBR;

import com.AdheliaPutriMaylaniJBusBR.dbjson.Serializable;

/**
 * The Renter class represents a company renting buses. It extends the Serializable class.
 *
 * @author Adhelia Putri Maylani
 */
public class Renter extends Serializable{
    public String address; /** The address of the renter company. */
    public String companyName; // The name of the renter company
    public String phoneNumber; /** The name of the renter company. */
    /** The regular expression pattern for validating company names */
    public static final String REGEX_NAME = "^[A-Z][a-zA-Z0-9_]{4,20}$";
    /** The regular expression pattern for validating phone numbers */
    public static final String REGEX_PHONE_NUMBER = "^[0-9]{9,12}$";

    /**
     * Constructs a Renter object with the given company name.
     *
     * @param companyName The name of the renter company.
     */
    public Renter(String companyName){
        this.companyName = companyName;
        this.address = " ";
        this.phoneNumber = " ";
    }

    /**
     * Constructs a Renter object with the given company name and phone number.
     *
     * @param companyName The name of the renter company.
     * @param phoneNumber The phone number of the renter company.
     */
    public Renter(String companyName, String phoneNumber){
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = " ";
    }

    /**
     * Constructs a Renter object with the given company name, phone number, and address.
     *
     * @param companyName The name of the renter company.
     * @param phoneNumber The phone number of the renter company.
     * @param Address     The address of the renter company.
     */
    public Renter(String companyName, String phoneNumber, String Address){
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = Address;
    }

    /**
     * Validates the renter's information based on company name and phone number.
     *
     * @return True if the information is valid, false otherwise.
     */
    public boolean validate() {
        // Check if phone number is empty or matches the phone number pattern, and company name matches the name pattern
        if ((phoneNumber.isEmpty() || phoneNumber.matches(REGEX_PHONE_NUMBER)) && companyName.matches(REGEX_NAME)) {
            return true;
        }
        return false;
    }
}