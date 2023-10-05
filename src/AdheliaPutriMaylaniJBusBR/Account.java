package AdheliaPutriMaylaniJBusBR;

/**
 * Adhelia Putri Maylani [2206814816]
 * Version 9.9.23
 * CS - OOP
 */

public class Account extends Serializable implements FileParser {
    public String email;
    public String name;
    public String password;
    
    public Object write(){
        return this;
    }
    
    public boolean read(String file){
        return true;
    }
    
    public Account(int id, String name, String email, String password){
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    public String toString(){
        return "Account ID: "+ id + "\n" +
               "Username: "+ name + "\n" +
               "Email: "+ email;
    }
    
    public int getId(){
        return id;
    }
}