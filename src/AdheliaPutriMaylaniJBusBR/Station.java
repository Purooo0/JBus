package AdheliaPutriMaylaniJBusBR;

/**
 * Adhelia Putri Maylani [2206814816]
 * Version 14.9.23
 * CS - OOP
 */

public class Station extends Serializable{
    public City city;
    public String stationName;
    public String address;
    
    public Station (String stationName, City city, String address){
        super(getLastAssignedId(Review.class) + 1);
        this.city = city;
        this.stationName = stationName;
        this.address = address;
    }
    
    public String toString(){
        return "Station ID: "+ super.id  + "\n"+
                "Station Name: "+ stationName + "\n"+
                "City: "+ city + "\n" +
                "Address: "+ address;
    }
}