package com.AdheliaPutriMaylaniJBusBR;

import java.sql.Timestamp;

public class BookingThread extends Thread {
    private Bus bus;
    private Timestamp timestamp;

    public BookingThread(String name, Bus bus, Timestamp timestamp) {
        super(name);
        this.bus = bus;
        this.timestamp = timestamp;
        this.start();
    }

    public void run(){
        System.out.println(getName() + " ID: "+ getId() + " is running");

        synchronized (bus){
            try {
                 bus.addSchedule(timestamp);
                 System.out.println(getName() + " successfully made a booking");
            } catch (RuntimeException e){
                System.out.println(getName() + " failed to make booking");
            }
        }
    }
}


