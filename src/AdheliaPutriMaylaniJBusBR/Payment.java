package AdheliaPutriMaylaniJBusBR;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Adhelia Putri Maylani [2206814816]
 * Version 14.9.23
 * CS - OOP
 */

public class Payment extends Invoice{
    private int busId;
    public Timestamp departureDate;
    public String busSeat;
    
    public Payment(int id, int buyerId, int renterId, int busId, String busSeat, Timestamp departureDate) {
        super(id, buyerId, renterId);
        this.busId = busId;
        this.busSeat = busSeat;
        this.departureDate = departureDate;
    }

    public Payment(int id, Account buyer, Renter renter, int busId, String busSeat, Timestamp departureDate) {
        super(id, buyer, renter);
        this.busId = busId;
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
    
    public String getDepartureInfo(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM dd, yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        
        String departureDateString = dateFormat.format(departureDate.getTime());
        String departureTimeString = timeFormat.format(departureDate.getTime());
        
        return "Departure Date: "+ departureDateString + "\n" +
               "Departure Time: "+ departureTimeString + "\n";
        
    }

    public static boolean makeBooking(Timestamp departureSchedule, String seat, Bus bus) {
        if (isAvailable(departureSchedule, seat, bus)) {
            bus.bookSeat(seat);
            return true;
        } else {
            return false;
        }
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
