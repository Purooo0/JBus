package com.AdheliaPutriMaylaniJBusBR;

import com.AdheliaPutriMaylaniJBusBR.dbjson.Serializable;

import javax.sql.rowset.serial.SerialArray;
import java.util.List;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.*;

import static com.AdheliaPutriMaylaniJBusBR.Payment.availableSchedule;

public class Bus extends Serializable {
    public String name;
    public Facility facility;
    public Price price;
    public int capacity;
    public City city;
    public BusType busType;
    public Station departure;
    public Station arrival;
    public List<Schedule> schedules;

    public Bus(String name, Facility facility, Price price, int capacity, BusType busType, City city, Station departure, Station arrival){
        this.name = name;
        this.facility = facility;
        this.price = price;
        this.capacity = capacity;
        this.busType = busType;
        this.city = city;
        this.departure = departure;
        this.arrival = arrival;
        this.schedules = new ArrayList<>();
    }

    public void addSchedule(Timestamp calendar){
        try {
            boolean isDuplicate = false;
            for (Schedule existingSchedule : schedules) {
                if (existingSchedule.departureSchedule.equals(calendar)) {
                    isDuplicate = true;
                    break;
                }
            }
            if (isDuplicate) {
                System.out.println("Terdapat jadwal dengan waktu yang sama.");
            } else {
                Schedule newSchedule = new Schedule(calendar, capacity);
                schedules.add(newSchedule);
            }
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menambahkan jadwal: "+ e.getMessage());
        }
        Schedule newSchedule = new Schedule(calendar, capacity);
        schedules.add(newSchedule);
    }

    public Object write(){
        return null;
    }
    
    public boolean read(String string){
        return false;
    }

    public String toString(){
        return "\nID: "+ super.id +
                "\nNama: "+ name +
                "\nFasilitas: "+ facility +
                ""+ price +
                "\nCapacity: "+ capacity +
                "\nBus Type: "+ busType +
                "\nCity: "+ city +
                ""+ departure +
                ""+ arrival;
    }
}