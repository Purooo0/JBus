package AdheliaPutriMaylaniJBusBR;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Adhelia Putri Maylani [2206814816]
 * Version 9.10.23
 * CS - OOP
 */

public class Payment extends Invoice{
    private int busId;
    public Timestamp departureDate;
    public String busSeat;
    
    public Payment(int buyerId, int renterId, int busId, String busSeat, Timestamp departureDate) {
        super(buyerId, renterId);
        this.busSeat = busSeat;
        this.departureDate = departureDate;
    }

    public Payment(Account buyer, Renter renter, String busSeat, Timestamp departureDate) {
        super(buyer, renter);
        this.busSeat = busSeat;
        this.departureDate = departureDate;
    }
    
    public static boolean isAvailable(Timestamp departureSchedule, String seat, Bus bus) {
        Timestamp busDepartureSchedule = bus.getDepartureSchedule();
        if (busDepartureSchedule.equals(departureSchedule) && bus.isSeatAvailable(seat)) {
            return true;
        } else {
            return false;
        }
    }

    public String getDepartureInfo() {
        return "Departure Date: " + departureDate.toString();
    }

    public static boolean makeBooking(Timestamp departureSchedule, List<String> seats, Bus bus) {
        Schedule schedule = availableSchedule(departureSchedule, seats, bus);
        if(schedule != null){
            schedule.bookSeat(seats);
            return true;
        }
        return false;
    }

    public static Schedule availableSchedule(Timestamp departureSchedule, List<String> seats, Bus bus) {
        for (Schedule schedule : bus.getSchedules()) {
            if (schedule.getDepartureSchedule().equals(departureSchedule) && schedule.isSeatAvailable(seats)) {
                return schedule;
            }
        }
        return null;
    }

    public static boolean makeBooking(Timestamp departureSchedule, String seat, Bus bus) {
        if (availableSchedule(departureSchedule, seat, bus) != null) {
            bus.bookSeat(seat);
            return true;
        } else {
            return false;
        }
    }

    public static Schedule availableSchedule(Timestamp departureSchedule, String seat, Bus bus) {
        for (Schedule schedule : bus.getSchedules()) {
            if (schedule.getDepartureSchedule().equals(departureSchedule) && schedule.isSeatAvailable(seat)) {
                return schedule;
            }
        }
        return null;
    }

    public String toString(){
        return "Bus ID: "+ busId  + "\n"+
                "Departure Date: "+ departureDate + "\n"+
                "Bus Seat: "+ busSeat;
    }
    
    public String getTime(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss");
        return dateFormat.format(time.getTime());
    }
    
    public int getBusId(){
        return busId;
    }
}
