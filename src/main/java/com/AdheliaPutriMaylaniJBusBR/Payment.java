package com.AdheliaPutriMaylaniJBusBR;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

public class Payment extends Invoice
{
    private int busId;
    public  List<String> busSeats;
    public Timestamp departureDate;

    public Payment (int buyerId, int renterId, int busId, String busSeat, Timestamp departureDate){
        super(buyerId, renterId);
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.busId = busId;
        this.busSeats = busSeats;
        this.departureDate = departureDate;
    }
    public Payment(Account buyer, Renter renter, int busId, String busSeat, Timestamp departureDate){
        super(buyer.id, renter.id);
        this.buyerId = buyer.id;
        this.renterId = renter.id;
        this.busId = busId;
        this.busSeats = busSeats;
        this.departureDate = departureDate;
    }
    public String getDepartureInfo(){
        SimpleDateFormat sdf = new SimpleDateFormat("M dd, yyyy");
        return "ID: " + super.id + "; Buyer ID: " + buyerId + "; Renter ID: " + renterId + "; Bus ID: " + busId + "; Departure Date: " + sdf.format(departureDate.getTime()) + "; Bus Seat: " + busSeats;
    }
    public static boolean makeBooking(Timestamp departureSchedule, String seat, Bus bus){
        for (Schedule schedule : bus.schedules) {
            if (schedule.isSeatAvailable(seat) && schedule.departureSchedule.equals(departureSchedule)) {
                schedule.bookSeat(seat);
                return true;
            }
        }
        return false;
    }
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
    public int getBusId(){
        return busId;
    }
    public String getTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("M dd, yyyy hh:mm:ss");
        return sdf.format(this.time.getTime());
    }
}