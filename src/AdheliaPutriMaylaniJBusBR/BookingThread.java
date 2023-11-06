package AdheliaPutriMaylaniJBusBR;

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

    public void run() {
        System.out.println("Thread ID: " + getId() + ", Name: " + getName() + " is running.");

        String seatToBook = "BR01";
        bus.makeBooking(seatToBook, timestamp);
    }
}

