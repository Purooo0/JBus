package AdheliaPutriMaylaniJBusBR;

/**
 * Adhelia Putri Maylani [2206814816]
 * Version 14.9.23
 * CS - OOP
 */

public class Review extends Serializable{
    public String date;
    public String desc;
    
    public Review(int id, String date, String desc){
        super(id);
        this.date = date;
        this.desc = desc;
    }
    
    public String toString(){
        return "Review ID: "+ id + 
               "\nDate: "+ date +
               "\nDescription: "+ desc;
    }
}
