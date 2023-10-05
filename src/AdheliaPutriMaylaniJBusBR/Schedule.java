package AdheliaPutriMaylaniJBusBR;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * Adhelia Putri Maylani [2206814816]
 * Version 29.9.23
 * CS - OOP
 */

public class Schedule {
    public Timestamp departureSchedule;
    public Map<String, Boolean> seatAvailability;
    
    public Schedule(Timestamp departureSchedule, int numberOfSeats){
        this.departureSchedule = departureSchedule; 
        initializeSeatAvailability(numberOfSeats);
    }
    
    public void initializeSeatAvailability(int numberOfSeats) {
        seatAvailability = new LinkedHashMap<>();
        for (int seatNumber = 1; seatNumber <= numberOfSeats; seatNumber++) {
            String sn = (seatNumber < 10) ? "0" + seatNumber : String.valueOf(seatNumber);
            seatAvailability.put("BR" + sn, true);
        }
    }
    
    public void printSchedule() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy HH:mm:ss");
        String formattedDepartureSchedule = dateFormat.format(this.departureSchedule.getTime());
        System.out.println("Tanggal keberangkatan: " + formattedDepartureSchedule);
        System.out.println("Daftar kursi dan ketersediaan kursinya: ");
        int maxSeatsPerRow = 4;
        int currentSeat = 1;
        for (Map.Entry<String, Boolean> entry : seatAvailability.entrySet()) {
            String seat = entry.getKey();
            boolean isAvailable = entry.getValue();
            String symbol = isAvailable ? "O" : "X";
            System.out.print(seat + " : " + symbol + "\t");
            if (currentSeat % maxSeatsPerRow == 0) {
                System.out.println();
            }
            currentSeat++;
        }
        System.out.println("\n");
    }
    
    public Timestamp getDepartureSchedule(){
        return departureSchedule;
    }
    
    public Map<String, Boolean> getSeatAvailability(){
        return seatAvailability;
    }
}
