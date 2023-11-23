package com.AdheliaPutriMaylaniJBusBR;

import com.AdheliaPutriMaylaniJBusBR.dbjson.Serializable;

public class Station extends Serializable {
    public City city;
    public String stationName;
    public String address;

    public Station(String stationName, City city, String address) {
        super();
        this.stationName = stationName;
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