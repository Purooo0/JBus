package com.AdheliaPutriMaylaniJBusBR;

import com.AdheliaPutriMaylaniJBusBR.dbjson.Serializable;

import java.util.List;
import java.util.ArrayList;
import java.sql.Timestamp;

/**
 * Represents a bus entity with details such as name, facilities, price, capacity, etc.
 * Extends Serializable to support JSON serialization.
 * This class encapsulates information about a bus and its schedules.
 *
 * @author Adhelia Putri Maylani
 */

public class Bus extends Serializable {
    public String name;
    public List<Facility> facilities;
    public Price price;
    public int capacity;
    public BusType busType;
    public Station departure;
    public Station arrival;
    public List<Schedule> schedules;
    public int accountId;

    /**
     * Constructs a Bus object with the specified details.
     *
     * @param name       The name of the bus.
     * @param facilities The list of facilities on the bus.
     * @param price      The price details for the bus.
     * @param capacity   The capacity of the bus.
     * @param busType    The type of the bus.
     * @param departure  The departure station of the bus.
     * @param arrival    The arrival station of the bus.
     * @param accountId  The ID of the account associated with the bus.
     */
    public Bus(String name, List<Facility> facilities, Price price, int capacity, BusType busType, Station departure, Station arrival, int accountId){
        this.name = name;
        this.facilities = facilities;
        this.price = price;
        this.capacity = capacity;
        this.busType = busType;
        this.departure = departure;
        this.arrival = arrival;
        this.schedules = new ArrayList<>();
        this.accountId = accountId;
    }

    /**
     * Adds a schedule to the bus with the specified departure timestamp.
     *
     * @param departureSchedule The timestamp of the departure schedule.
     */
    public void addSchedule(Timestamp departureSchedule){
        try {
            boolean isDuplicate = false;
            for (Schedule existingSchedule : schedules) {
                if (existingSchedule.departureSchedule.equals(departureSchedule)) {
                    isDuplicate = true;
                    break;
                }
            }
            if (isDuplicate) {
                System.out.println("Terdapat jadwal dengan waktu yang sama.");
            } else {
                Schedule newSchedule = new Schedule(departureSchedule, capacity);
                schedules.add(newSchedule);
            }
        } catch (RuntimeException e) {
            System.out.println("Terjadi kesalahan saat menambahkan jadwal: "+ e.getMessage());
        }
    }

    /**
     * Placeholder for the write method.
     *
     * @return The written object.
     */
    public Object write(){
        return null;
    }

    /**
     * Placeholder for the read method.
     *
     * @param string The string to read.
     * @return True if reading is successful, false otherwise.
     */
    public boolean read(String string){
        return false;
    }

    /**
     * Returns a string representation of the Bus object.
     *
     * @return The string representation.
     */
    public String toString(){
        return "\nID: "+ super.id +
                "\nNama: "+ name +
                "\nFasilitas: "+ facilities +
                ""+ price +
                "\nCapacity: "+ capacity +
                "\nBus Type: "+ busType +
                "\nAccount ID: "+ accountId +
                ""+ departure +
                ""+ arrival;
    }
}