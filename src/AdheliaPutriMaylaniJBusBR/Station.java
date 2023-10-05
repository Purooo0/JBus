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
    
    public Station (int id, String stationName, City city, String address){
        super(id);
        this.city = city;
        this.stationName = stationName;
        this.address = address;
    }
    
    public String print(){
        return "Station ID: "+ id  + "\n"+
                "Station Name: "+ stationName + "\n"+
                "City: "+ city + "\n" +
                "Address: "+ address;
    }
    
    public static void main(String[] args){
        Review testReview = new Review (1, "23 August 2023", "Bad Quality");
        Price testPrice = new Price (100000, 20000);
        Station testDeparture = new Station (2, "Depok Terminal", City.DEPOK, "Jl. Margonda Raya");
        Station testArrival = new Station (3, "Halte UI", City.JAKARTA, "Universitas Indonesia");
        Bus testBus = new Bus (1, "Busway", Facility.AC, testPrice, 50, BusType.REGULER, City.DEPOK, testDeparture, testArrival);
        Account testAccount = new Account (1, "Bob", "bob@gmail.com", "bob");
        Rating testRating = new Rating();
        
        System.out.println(testReview);
        System.out.println(testBus);
        System.out.println(testAccount);
        System.out.println(testPrice);
        System.out.println(testRating);
    }
}