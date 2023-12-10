package com.AdheliaPutriMaylaniJBusBR;

import com.AdheliaPutriMaylaniJBusBR.dbjson.Serializable;

/**
 * The Station class represents a bus station with a station name, city, and address. It extends the Serializable class.
 *
 * @author Adhelia Putri Maylani
 */
public class Station extends Serializable {
    public City city; /** The city where the station is located. */
    public String stationName; /** The name of the bus station. */
    public String address; /** The address of the bus station. */

    /**
     * Constructs a Station object with the given station name, city, and address.
     *
     * @param stationName The name of the bus station.
     * @param city The city where the station is located.
     * @param address The address of the bus station.
     */
    public Station(String stationName, City city, String address) {
        super();
        this.stationName = stationName;
        this.city = city;
        this.address = address;
    }

    /**
     * Returns a string representation of the station, including its ID, name, city, and address.
     *
     * @return The string representation of the station.
     */
    public String toString(){
        return "Station ID: "+ super.id  + "\n"+
                "Station Name: "+ stationName + "\n"+
                "City: "+ city + "\n" +
                "Address: "+ address;
    }
}