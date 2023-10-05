package AdheliaPutriMaylaniJBusBR;

/**
 * Adhelia Putri Maylani [2206814816]
 * Version 9.9.23
 * CS - OOP
 */

public class Renter extends Serializable {
    public String address;
    public String companyName;
    public int phoneNumber;
    
    public Renter(int id, String companyName, int phoneNumber){
        super(id);
        this.companyName = companyName;
        this.address = "";
        this.phoneNumber = phoneNumber;
    }
    
    public Renter(int id, String companyName, int phoneNumber, String address){
        super(id);
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
    
    public Renter(int id, String companyName){
        super(id);
        this.companyName = companyName;
        this.address = "";
        this.phoneNumber = 0;
    }
    
    public Renter(int id, String companyName, String address){
        super(id);
        this.companyName = "";
        this.address = "";
        this.phoneNumber = 0;
    }
    
    public int getId(){
        return id;
    }
}