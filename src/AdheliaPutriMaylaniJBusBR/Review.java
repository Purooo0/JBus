package AdheliaPutriMaylaniJBusBR;

/**
 * Adhelia Putri Maylani [2206814816]
 * Version 14.9.23
 * CS - OOP
 */

public class Review{
    public String date;
    public String desc;
    
    public Review(String date, String desc){
        this.date = date;
        this.desc = desc;
    }
    
    public String toString(){
        return "Review ID: "+
               "\nDate: "+ date +
               "\nDescription: "+ desc;
    }
}
