package AdheliaPutriMaylaniJBusBR;

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
