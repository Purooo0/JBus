package AdheliaPutriMaylaniJBusBR;

public class Station extends Serializable{
    public String name;
    public City city;
    public String stationName;
    public String address;

    public Station(String name, City city, String address) {
        super(id);
        this.name = name;
        this.city = city;
        this.address = address;
    }
    
    public String toString(){
        return "Station ID: "+ super.id  + "\n"+
                "Station Name: "+ stationName + "\n"+
                "City: "+ city + "\n" +
                "Address: "+ address;
    }
}