package com.AdheliaPutriMaylaniJBusBR;

import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.List;

public class Schedule {
    public Timestamp departureSchedule;
    public Map<String, Boolean> seatAvailability;

    public Schedule(Timestamp departureSchedule, int numberOfSeats){
        this.departureSchedule = departureSchedule;
        initializeSeatAvailability(numberOfSeats);
    }
    public void bookSeat(String seat){
        if (isSeatAvailable(seat)) {
            seatAvailability.put(seat, false);
            System.out.println("Seat " + seat + " ordered successfully");
        } else {
            System.out.println("Seat " + seat + " not available to order");
        }
        /*if (seatAvailability.containsKey(seat)) {
            seatAvailability.put(seat, false);
        }*/
    }
    public void bookSeat(List<String> seats){
        for (String seat : seats) {
            bookSeat(seat);
        }
    }
    public boolean isSeatAvailable(String seat){
        Boolean availability = seatAvailability.get(seat);
        return availability != null && availability;
    }
    public boolean isSeatAvailable(List<String> seats){
        for (String seat : seats) {
            Boolean availability = seatAvailability.get(seat);
            if (availability == null || !availability) {
                return false;
            }
        }
        return true;
    }
    private void initializeSeatAvailability(int numberOfSeats){
        seatAvailability = new LinkedHashMap<>();

        for (int seatNumber = 1; seatNumber <= numberOfSeats; seatNumber++){
            String sn = seatNumber < 10 ? "0" + seatNumber : "" + seatNumber;
            seatAvailability.put("BR" + sn, true);
        }
    }
    public String toString(){
        /*SimpleDateFormat sdFormat = new SimpleDateFormat("MMMM d, yyyy HH:mm:ss");
        String formattedDepartureSchedule = sdFormat.format(this.departureSchedule.getTime());

        int totalSeats = seatAvailability.size();
        int occupiedSeats = (int) seatAvailability.values().stream().filter(avail -> !avail).count();

        return "Departure Schedule: " + formattedDepartureSchedule +
                "\nOccupied Seats: " + occupiedSeats + "/" + totalSeats;*/

        StringBuilder result = new StringBuilder();

        result.append("Schedule: ").append(departureSchedule).append("\n");

        int totalSeats = seatAvailability.size();
        int occupiedSeats = Algorithm.count(seatAvailability.values().iterator(), true);

        result.append("occupied: ").append(occupiedSeats).append("/").append(totalSeats).append("\n");
        return result.toString();
    }


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
