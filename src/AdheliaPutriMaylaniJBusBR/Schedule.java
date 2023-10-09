package AdheliaPutriMaylaniJBusBR;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.List;

/**
 * Adhelia Putri Maylani [2206814816]
 * Version 9.10.23
 * CS - OOP
 */

public class Schedule {
    private Bus bus;
    public Timestamp departureSchedule;
    public Map<String, Boolean> seatAvailability;
    
    public Schedule(Timestamp departureSchedule, int numberOfSeats){
        this.departureSchedule = departureSchedule; 
        initializeSeatAvailability(numberOfSeats);
    }
    
    private void initializeSeatAvailability(int numberOfSeats) {
        seatAvailability = new LinkedHashMap<>();
        for (int seatNumber = 1; seatNumber <= numberOfSeats; seatNumber++) {
            String sn = (seatNumber < 10) ? "0" + seatNumber : String.valueOf(seatNumber);
            seatAvailability.put("BR" + sn, true);
        }
    }

    public void bookSeat(String seat) {
        if (isSeatAvailable(seat)) {
            seatAvailability.put(seat, false);
        }
    }

    public void bookSeat(List<String> seats) {
        for (String seat : seats) {
            bookSeat(seat);
        }
    }

    public boolean isSeatAvailable(String seat) {
        Boolean availability = seatAvailability.get(seat);
        return availability != null && availability;
    }

    public void printSchedule() {
        System.out.println("Schedule: " + departureSchedule);
        int totalSeats = seatAvailability.size();
        int occupiedSeats = (int) seatAvailability.values().stream().filter(b -> !b).count();
        System.out.println("Occupied: " + occupiedSeats + "/" + totalSeats);
    }

    public String toString() {
        return "Schedule: " + departureSchedule + "\nOccupied: " +
                seatAvailability.values().stream().filter(b -> !b).count() +
                "/" + seatAvailability.size();
    }

    public boolean isSeatAvailable(List<String> seats) {
        for (String seat : seats) {
            if (!isSeatAvailable(seat)) {
                return false;
            }
        }
        return true;
    }

    public Timestamp getDepartureSchedule(){
        return departureSchedule;
    }
    
    public Map<String, Boolean> getSeatAvailability(){
        return seatAvailability;
    }
    public void setBus(Bus bus) {
        this.bus = bus;
    }
}
