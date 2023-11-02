package AdheliaPutriMaylaniJBusBR;

/**
 * Adhelia Putri Maylani [2206814816]
 * Version 9.9.23
 * CS - OOP
 */

public class Renter{
    public String address;
    public String companyName;
    public int phoneNumber;
    
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
}