package AdheliaPutriMaylaniJBusBR;
import java.util.List;
import java.util.ArrayList;
import java.sql.Timestamp; 
import java.util.LinkedHashMap;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Adhelia Putri Maylani [2206814816]
 * Version 14.9.23
 * CS - OOP
 */

public class Bus {
    public String name;
    public Facility facility;
    public Price price;
    public int capacity;
    public City city;
    public BusType busType;
    public Station departure;
    public Station arrival;
    public List<Schedule> schedules = new ArrayList<>();
    public Timestamp departureSchedule;
    public Map<String, Boolean> seatAvailability;
    private Set<String> bookedSeats = new HashSet<>();

    public void addSchedule(Timestamp schedule) {
        Schedule newSchedule = new Schedule(schedule, this.capacity);
        try {
            if (schedules.contains(newSchedule)) {
                System.out.println("Jadwal dengan waktu yang sama sudah ada. Penambahan dibatalkan.");
            } else {
                schedules.add(newSchedule);
                System.out.println("Jadwal baru berhasil ditambahkan.");
            }
        } catch (Exception e) {
            System.err.println("Terjadi kesalahan dalam penambahan jadwal: " + e.getMessage());
        }
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public Schedule getSchedule(Timestamp departureSchedule) {
        for (Schedule schedule : schedules) {
            if (schedule.getDepartureSchedule().equals(departureSchedule)) {
                return schedule;
            }
        }
        return null;
    }

    private String formatCalendar(Calendar calendar) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss", Locale.ENGLISH);
        return dateFormat.format(calendar.getTime());
    }
    
    public Object write(){
        return this;
    }
    
    public boolean read(String file){
        return true;
    }

    public Timestamp getDepartureSchedule() {
        if (schedules.isEmpty()) {
            return null;
        }
        return schedules.get(0).getDepartureSchedule();
    }


    public boolean isSeatAvailable(String seat) {
        Boolean availability = seatAvailability.get(seat);
        return availability != null && availability;
    }

    public Bus(String name, Facility facility, Price price, int capacity, BusType busType, City city, Station departure, Station arrival){
        this.name = name;
        this.facility = facility;
        this.price = price;
        this.capacity = capacity;
        this.busType = busType;
        this.city = city;
        this.departure = departure;
        this.arrival = arrival;
    }
    
    public String toString(){
        return "Bus ID: "+ "\n" +
               "Name: "+ name + "\n" +
               "Facility: "+ facility + "\n" +
               "Price: "+ price + "\n" +
               "Capacity: "+ capacity + "\n" +
               "Bus Type: "+ busType + "\n" +
               "City: "+ city + "\n" +
               "Departure Station: "+ departure + "\n" +
               "Arrival Station: "+ arrival;
    }

    public void bookSeat(String seat) {
        if (!bookedSeats.contains(seat)) {
            bookedSeats.add(seat);
            System.out.println("Seat " + seat + " has been booked.");
        } else {
            System.out.println("Seat " + seat + " is already booked.");
        }
    }
}