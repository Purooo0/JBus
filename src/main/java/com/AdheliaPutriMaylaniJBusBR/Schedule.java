package com.AdheliaPutriMaylaniJBusBR;

import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.List;

/**
 * The Schedule class represents a bus schedule with departure timestamp and seat availability.
 *
 * @author Adhelia Putri Maylani
 */
public class Schedule {
    public Timestamp departureSchedule;
    public Map<String, Boolean> seatAvailability;

    /**
     * Constructs a Schedule object with the specified departure timestamp and number of seats.
     *
     * @param departureSchedule The departure timestamp.
     * @param numberOfSeats The number of seats in the bus.
     */
    public Schedule(Timestamp departureSchedule, int numberOfSeats){
        this.departureSchedule = departureSchedule;
        initializeSeatAvailability(numberOfSeats);
    }

    /**
     * Books a single seat on the schedule.
     *
     * @param seat The seat to be booked.
     */
    public void bookSeat(String seat){
        if (isSeatAvailable(seat)) {
            seatAvailability.put(seat, false);
            System.out.println("Seat " + seat + " ordered successfully");
        } else {
            System.out.println("Seat " + seat + " not available to order");
        }
    }

    /**
     * Books a list of seats on the schedule.
     *
     * @param seats The list of seats to be booked.
     */
    public void bookSeat(List<String> seats){
        for (String seat : seats) {
            bookSeat(seat);
        }
    }

    /**
     * Checks if a seat is available on the schedule.
     *
     * @param seat The seat to check.
     * @return True if the seat is available, false otherwise.
     */
    public boolean isSeatAvailable(String seat){
        Boolean availability = seatAvailability.get(seat);
        return availability != null && availability;
    }

    /**
     * Checks if a list of seats is available on the schedule.
     *
     * @param seats The list of seats to check.
     * @return True if all seats are available, false otherwise.
     */
    public boolean isSeatAvailable(List<String> seats){
        for (String seat : seats) {
            Boolean availability = seatAvailability.get(seat);
            if (availability == null || !availability) {
                return false;
            }
        }
        return true;
    }

    /**
     * Initializes seat availability based on the total number of seats.
     *
     * @param numberOfSeats The total number of seats.
     */
    private void initializeSeatAvailability(int numberOfSeats){
        seatAvailability = new LinkedHashMap<>();

        for (int seatNumber = 1; seatNumber <= numberOfSeats; seatNumber++){
            String sn = seatNumber < 10 ? "0" + seatNumber : "" + seatNumber;
            seatAvailability.put("BR" + sn, true);
        }
    }

    /**
     * Returns a string representation of the Schedule object.
     *
     * @return The string representation.
     */
    public String toString(){
        StringBuilder result = new StringBuilder();

        result.append("Schedule: ").append(departureSchedule).append("\n");

        int totalSeats = seatAvailability.size();
        int occupiedSeats = Algorithm.count(seatAvailability.values().iterator(), true);

        result.append("occupied: ").append(occupiedSeats).append("/").append(totalSeats).append("\n");
        return result.toString();
    }

    /**
     * Prints a detailed view of the schedule, including seat availability.
     */
    public void printSchedule() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy HH:mm:ss");

        String formattedDepartureSchedule = dateFormat.format(this.departureSchedule.getTime());
        System.out.println("Date of departure: " + formattedDepartureSchedule);
        System.out.println("List of seats and seat availability: ");

        int maxSeatsPerRow = 4;
        int currentSeat = 1;

        for (String seat : this.seatAvailability.keySet()) {
            String symbol = this.seatAvailability.get(seat)? "O" : "X";
            System.out.print(seat + " : " + symbol + "\t");
            if (currentSeat % maxSeatsPerRow == 0) {
                System.out.println();
            }
            currentSeat++;
        }
        System.out.println("\n");
    }
}
