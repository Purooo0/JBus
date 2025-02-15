package com.AdheliaPutriMaylaniJBusBR.controller;

import com.AdheliaPutriMaylaniJBusBR.*;
import com.AdheliaPutriMaylaniJBusBR.dbjson.JsonAutowired;
import com.AdheliaPutriMaylaniJBusBR.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

/**
 * The {@code StationController} class handles HTTP requests related to stations, providing functionality for station creation.
 *
 * @see BasicGetController
 * @see Station
 * @see JsonAutowired
 * @see JsonTable
 * @author Adhelia Putri Maylani
 */

@RestController
@RequestMapping("/station")
public class StationController implements BasicGetController<Station> {
    /**
     * The JSON table storing station data.
     */
    @JsonAutowired(value = Station.class, filepath = "/Users/adhelia/Desktop/CS/JBus/src/main/java/com/AdheliaPutriMaylaniJBusBR/json/station.json")
    public static JsonTable<Station> stationTable;
    /**
     * Retrieves the JSON table for stations.
     *
     * @return the JSON table for stations
     */
    @Override
    public JsonTable<Station> getJsonTable() {
        return stationTable;
    }
    /**
     * Creates a new station with the provided parameters.
     *
     * @param stationName the name of the station
     * @param city        the city where the station is located
     * @param address     the address of the station
     * @return a {@code BaseResponse} containing information about the operation's success and the created station
     */
    @PostMapping("/create")
    public BaseResponse<Station> createStation(
            @RequestParam String stationName,
            @RequestParam String city,
            @RequestParam String address
    ) {
        try {

            if (stationName.isBlank() || city.isBlank() || address.isBlank()) {
                return new BaseResponse<>(false, "Failed To Create Station! There's Empty Parameter", null);
            }
            // Converting the city parameter to City enum
            City newCity = City.valueOf(city.toUpperCase());
            Station newStation = new Station(stationName, newCity, address);

            // Checking if the station with the same name, city, and address already exists
            if (Algorithm.<Station>exists(getJsonTable(), s -> s.stationName.equals(stationName) && s.city.equals(newCity) & s.address.equals(address))){
                return new BaseResponse<>(false, "Failed To Create Station! This Station Already Exists", null);
            }

            // Adding the new station to the JSON table
            stationTable.add(newStation);
            return new BaseResponse<>(true, "Successfully Created Station", newStation);
        } catch (IllegalArgumentException e) {
            return new BaseResponse<>(false, "Failed To Create Station! City Is Not Available", null);
        } catch (Exception e) {
            return new BaseResponse<>(false, "Error!", null);
        }
    }
}