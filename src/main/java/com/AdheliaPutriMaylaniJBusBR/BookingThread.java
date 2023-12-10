package com.AdheliaPutriMaylaniJBusBR;

import java.sql.Timestamp;

/**
 * Represents a thread for making bookings on a bus at a specified timestamp.
 * This class extends the Thread class to run booking logic concurrently.
 *
 * @author Adhelia Putri Maylani
 */

public class BookingThread extends Thread {
    private Bus bus;
    private Timestamp timestamp;

    /**
     * Constructs a BookingThread with the given name, bus, and timestamp.
     *
     * @param name      The name of the thread.
     * @param bus       The bus to make the booking on.
     * @param timestamp The timestamp for the booking.
     */
    public BookingThread(String name, Bus bus, Timestamp timestamp) {
        super(name);
        this.bus = bus;
        this.timestamp = timestamp;
        this.start(); // Start the thread immediately upon construction
    }

    /**
     * The main logic of the thread. It attempts to add a schedule to the bus at the specified timestamp.
     * Overrides the run method in the Thread class.
     */
    public void run(){
        System.out.println(getName() + " ID: "+ getId() + " is running");
        // Synchronize on the bus object to ensure thread safety
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


