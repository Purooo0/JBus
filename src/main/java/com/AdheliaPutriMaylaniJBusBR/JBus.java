package com.AdheliaPutriMaylaniJBusBR;

import com.AdheliaPutriMaylaniJBusBR.dbjson.JsonDBEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.AdheliaPutriMaylaniJBusBR.dbjson.JsonTable;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class JBus {
    public static void main(String[] args) throws IOException, InterruptedException {
        JsonDBEngine.Run(JBus.class);
        SpringApplication.run(JBus.class, args);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> JsonDBEngine.join()));
        try {
            JsonTable<Account> tableAccount = new JsonTable<>(Account.class, "D:\\KULIAHTAHUN2\\OOP\\JBus\\data\\accountDatabase.json");

            Account testAccount1 = new Account("Adhelia Putri", "adhelia@gmail.com", "Adhelia123");
            tableAccount.add(testAccount1);
            tableAccount.writeJson();

            System.out.println(testAccount1.toString());
            System.out.println("Successfully added data to the database");
        }
        catch (IOException e) {
            System.err.println("An error occurred while adding data to the database");
        }

        Bus bus = createBus();
        bus.schedules.forEach(Schedule::printSchedule);
        for(int i =0; i < 10; i++){
            BookingThread thread = new BookingThread("Thread " + i,bus, Timestamp.valueOf("2023-07-27 19:00:00"));
        }
        Thread.sleep(1000);
        bus.schedules.forEach(Schedule::printSchedule);
    }

    public static Bus createBus() {
        List<Facility> facilities = new ArrayList<>();
        Price price = new Price(750000, 5);
        Bus bus = new Bus(1, "Netlab Bus", facilities, price, 25,
                BusType.REGULER, new Station("Depok Terminal", City.DEPOK,
                "Jl. Margonda Raya"), new Station("Halte UI", City.JAKARTA, "Universitas Indonesia"));
        Timestamp timestamp = Timestamp.valueOf("2023-07-27 19:00:00");
        bus.addSchedule(timestamp);
        return bus;
    }

    /*public static List<Bus> filterByDeparture(List<Bus> buses, City departure, int page, int pageSize) {
        List<Bus> filteredBuses = new ArrayList<>();
        for (Bus bus : buses) {
            if (bus.city == departure) {
                filteredBuses.add(bus);
            }
        }

        int start = (page - 1) * pageSize;
        int end = Math.min(start + pageSize, filteredBuses.size());

        if (start >= filteredBuses.size()) {
            return new ArrayList<>();
        }

        return filteredBuses.subList(start, end);
    }
    public static List<Bus> filterByPrice(List<Bus> buses, int min, int max) {
        List<Bus> filteredBuses = new ArrayList<>();
        for (Bus bus : buses) {
            if (bus.price.price >= min && bus.price.price <= max) {
                filteredBuses.add(bus);
            }
        }
        return filteredBuses;
    }
    public static Bus filterBusId(List<Bus> buses, int id) {
        for (Bus bus : buses) {
            if (bus.id == id) {
                return bus;
            }
        }
        return null;
    }
    public static List<Bus> filterByDepartureAndArrival (List<Bus> buses, City Departure, City Arrival, int page, int pageSize) {
        List<Bus> filteredBuses = new ArrayList<>();
        for (Bus bus : buses) {
            if (bus.city == Departure && bus.city == Arrival) {
                filteredBuses.add(bus);
            }
        }

        int start = (page - 1) * pageSize;
        int end = Math.min(start + pageSize, filteredBuses.size());

        if (start >= filteredBuses.size()) {
            return new ArrayList<>(); // Return an empty list if page is out of range
        }

        return filteredBuses.subList(start, end);
    }
    public static int getBusId(){
        return 0;
    }
    public static String getBusName(){
        return "Bus";
    }
    public static boolean isDiscount(){
        return true;
    }
    public static float getDiscountPercentage(int beforeDiscount, int afterDiscount){
        if (beforeDiscount <= afterDiscount){
            return 0.0f;
        }
        float hargaSetelah = (float)beforeDiscount - (float)afterDiscount;
        return hargaSetelah / (float)beforeDiscount * 100.0f;
    }
    public static int getDiscountedPrice(int price, float discountPercentage){
        if(discountPercentage >= 100.0f || price == 0){
            return 0;
        }
        int discountedPrice = (int)(price - ((discountPercentage/100)*(float)price));
        return discountedPrice;

    }
    public static int getOriginalPrice(int discountedPrice, float discountPercentage){
        if(discountPercentage == 0.0f){
            return discountedPrice;
        }
        else if(discountPercentage == 100.0f){
            return 0;
        }
        else if(discountPercentage < 0.0f || discountPercentage > 100.0f){
            System.out.print("Invalid");
            return 0;
        }
        return (100 * discountedPrice)/((int)(100.0f - discountPercentage));
    }
    public static float getAdminFeePercentage(){
        return 0.05f;
    }
    public static int getAdminFee(int price){
        return (int) (0.05f * price);
    }
    public static int getTotalPrice(int price, int numberOfSeat){
        return ((price * numberOfSeat) + (numberOfSeat * getAdminFee(price)));
    }*/
}