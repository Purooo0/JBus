package AdheliaPutriMaylaniJBusBR;

/**
 * Adhelia Putri Maylani [2206814816]
 * Version 9.9.23
 * CS - OOP
 */

public class Account {
    public String email;
    public String name;
    public String password;
    
    public Object write(){
        return this;
    }
    
    public boolean read(String file){
        return true;
    }
    
    public Account(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    public String toString(){
        return "Account ID: "+ "\n" +
               "Username: "+ name + "\n" +
               "Email: "+ email;
    }
}