package com.AdheliaPutriMaylaniJBusBR;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Represents a payment entity with details such as buyer and renter information, bus ID, bus seats, departure date, etc.
 * Extends Invoice to inherit buyer, renter, rating, payment status, and timestamp information.
 * This class encapsulates information about a payment, including buyer, renter, bus ID, bus seats, departure date, and associated methods.
 *
 * @author Adhelia Putri Maylani
 */

public class Payment extends Invoice {
    // Properties of the Payment class
    private int busId;
    public  List<String> busSeats;
    public Timestamp departureDate;

    /**
     * Constructor for creating a Payment object with buyer, renter, bus ID, bus seats, and departure date.
     *
     * @param buyerId The ID of the buyer.
     * @param renterId The ID of the renter.
     * @param busId The ID of the bus.
     * @param busSeat List of bus seats.
     * @param departureDate The departure date.
     */
    public Payment (int buyerId, int renterId, int busId, String busSeat, Timestamp departureDate){
        super(buyerId, renterId);
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.busId = busId;
        this.busSeats = busSeats;
        this.departureDate = departureDate;
    }

    /**
     * Constructor for creating a Payment object with Account (buyer), Renter, bus ID, bus seats, and departure date.
     *
     * @param buyer The buyer account.
     * @param renter The renter information.
     * @param busId The ID of the bus.
     * @param busSeat List of bus seats.
     * @param departureDate The departure date.
     */
    public Payment(Account buyer, Renter renter, int busId, String busSeat, Timestamp departureDate){
        super(buyer.id, renter.id);
        this.buyerId = buyer.id;
        this.renterId = renter.id;
        this.busId = busId;
        this.busSeats = busSeats;
        this.departureDate = departureDate;
    }

    /**
     * Gets a string representation of the departure information.
     *
     * @return String representation of departure information.
     */
    public String getDepartureInfo(){
        SimpleDateFormat sdf = new SimpleDateFormat("M dd, yyyy");
        return "ID: " + super.id + "; Buyer ID: " + buyerId + "; Renter ID: " + renterId + "; Bus ID: " + busId + "; Departure Date: " + sdf.format(departureDate.getTime()) + "; Bus Seat: " + busSeats;
    }

    /**
     * Makes a booking for a specific seat on a given bus schedule.
     *
     * @param departureSchedule The departure schedule timestamp.
     * @param seat The seat to book.
     * @param bus The bus for which the booking is made.
     * @return True if the booking is successful, false otherwise.
     */
    public static boolean makeBooking(Timestamp departureSchedule, String seat, Bus bus){
        for (Schedule schedule : bus.schedules) {
            if (schedule.isSeatAvailable(seat) && schedule.departureSchedule.equals(departureSchedule)) {
                schedule.bookSeat(seat);
                return true;
            }
        }
        return false;
    }

    /**
     * Makes bookings for a list of seats on a given bus schedule.
     *
     * @param departureSchedule The departure schedule timestamp.
     * @param seats The list of seats to book.
     * @param bus The bus for which the bookings are made.
     * @return True if at least one booking is successful, false otherwise.
     */
    public static boolean makeBooking(Timestamp departureSchedule, List<String> seats, Bus bus){
        boolean successBook = false;

        for (String seat : seats) {
            boolean booked = makeBooking(departureSchedule, seat, bus);
            if (booked) {
                successBook = true;
            }
        }
        return successBook;
    }

    /**
     * Finds an available schedule for a specific seat on a given bus schedule.
     *
     * @param departureSchedule The departure schedule timestamp.
     * @param seat The list of seats to check for availability.
     * @param bus The bus for which the availability is checked.
     * @return The schedule if at least one seat is available, null otherwise.
     */
    public static Schedule availableSchedule(Timestamp departureSchedule, String seat, Bus bus){
        for (Schedule schedule : bus.schedules) {
            if (schedule.departureSchedule.equals(departureSchedule) && schedule.isSeatAvailable(seat)) {
                return schedule;
            }
        }
        return null;
    }
    public static Schedule availableSchedule(Timestamp departureSchedule, List<String> seats, Bus bus){
        for (String seat : seats) {
            Schedule schedule =  availableSchedule(departureSchedule, seat, bus);
            if (schedule != null) {
                return schedule;
            }
        }
        return null;
    }

    /**
     * Gets the bus ID associated with the payment.
     *
     * @return The bus ID.
     */
    public int getBusId(){
        return busId;
    }

    /**
     * Gets a formatted string representation of the payment timestamp.
     *
     * @return Formatted timestamp string.
     */
    public String getTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("M dd, yyyy hh:mm:ss");
        return sdf.format(this.time.getTime());
    }
}