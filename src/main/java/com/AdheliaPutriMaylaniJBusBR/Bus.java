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
    public List<Facility> facilities;
    public Price price;
    public int capacity;
    public BusType busType;
    public Station departure;
    public Station arrival;
    public List<Schedule> schedules;
    public int accountId;

    public Bus(int accountId, String name, List<Facility> facilities, Price price, int capacity, BusType busType, Station departure, Station arrival){
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
                "\nFasilitas: "+ facilities +
                ""+ price +
                "\nCapacity: "+ capacity +
                "\nBus Type: "+ busType +
                "\nAccount ID: "+ accountId +
                ""+ departure +
                ""+ arrival;
    }
}