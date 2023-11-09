package com.AdheliaPutriMaylaniJBusBR;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JBus {
    public JBus() {
        try {
            JsonTable<Account> tableAccount = new JsonTable<>(Account.class, "accountDatabase.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Bus createBus() {
        Price price = new Price(750000, 5);
        Bus bus = new Bus("Netlab Bus", Facility.LUNCH, price, 25, BusType.REGULER, City.BANDUNG,
                new Station("Depok Terminal", City.DEPOK, "Jl. Margonda Raya"),
                new Station("Halte UI", City.JAKARTA, "Universitas Indonesia"));
        Timestamp timestamp = Timestamp.valueOf("2023-07-27 19:00:00");
        bus.addSchedule(timestamp);
        return bus;
    }

    public static void main(String[] args) throws IOException {
        SpringApplication.run(JBus.class, args);

        JBus jBus = new JBus();
        Bus bus = createBus();
        bus.schedules.forEach(Schedule::printSchedule);

        for (int i = 0; i < 10; i++) {
            BookingThread thread = new BookingThread("Thread " + i, bus, Timestamp.valueOf("2023-07-27 19:00:00"));
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bus.schedules.forEach(Schedule::printSchedule);
    }
}
        /*
        JBus jBus = new JBus();

        JsonTable<Account> tableAccount = new JsonTable<>(Account.class, "accountDatabase.json");
        Account newAccount = new Account("Nama Anda", "email@example.com", "Password123");

        try {
            tableAccount.add(newAccount);
            tableAccount.writeJson();
        } catch (IOException e) {
            e.printStackTrace();
        }/
    }

}
/*
    public static List<Bus> filterByDeparture(List<Bus> buses, City departure, int page, int pageSize) {
        List<Bus> filteredBuses = buses.stream()
                .filter(bus -> bus.getDepartureSchedule().equals(departure))
                .collect(Collectors.toList());
        int start = (page - 1) * pageSize;
        int end = Math.min(start + pageSize, filteredBuses.size());
        if (start >= filteredBuses.size()) {
            return List.of();
        }
        return filteredBuses.subList(start, end);
    }
    public static List<Bus> filterByPrice(List<Bus> buses, int min, int max) {
        List<Bus> filteredBuses = new ArrayList<>();
        for(Bus bus : buses){
            if(bus.price.price >= min && bus.price.price <= max){
                filteredBuses.add(bus);
            }
        }
        return filteredBuses;
    }

    public static Bus filterBusId(List<Bus> buses, int id) {
        for (Bus bus : buses){
            if(bus.id == id){
                return bus;
            }
        }
        return null;
    }

    public static List<Bus> filterByDepartureAndArrival(List<Bus> buses, City departure, City arrival, int page, int pageSize){
        Predicate<Bus> cityExists = (bus) -> bus.city.equals(departure) && bus.city.equals(arrival);
        return Algorithm.paginate(buses, page, pageSize, cityExists);
    }

    public static void main(String[] args) {
        try {
            String filepath = "/Users/adhelia/Desktop/CS/JBus/buses.json";
            JsonTable<Bus> busList = new JsonTable<>(Bus.class, filepath);
            List<Bus> filteredBus = filterByDeparture(busList, City.JAKARTA, 1, 10);
            filteredBus.forEach(bus -> System.out.println(bus.toString()));
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}

    /*
    public static Bus createBus(){
        Price price = new Price (750000, 5);
        Bus bus = new Bus (10, "Netlab Bus", Facility.LUNCH, price, 25);
        return bus;
    }

    public int getBusId() {
        return 0;
    }

    public String getBusName() {
        return "Bus";
    }

    public boolean isDiscount() {
        return true;
    }

    public float getDiscountPercentage(int beforeDiscount, int afterDiscount) {
        if (beforeDiscount > afterDiscount) {
            float percentage = ((float) (beforeDiscount - afterDiscount) / beforeDiscount) * 100;
            return percentage;
        } else {
            return 0.0f;
        }
    }

    public int getDiscountedPrice(int price, float discountPercentage) {
        if (discountPercentage > 100.0f) {
            discountPercentage = 100.0f;
        }

        float discount = (discountPercentage / 100) * price;
        int discountedPrice = price - (int) discount;
        return discountedPrice;
    }

    public int getOriginalPrice(int discountedPrice, float discountPercentage) {
        if (discountPercentage > 100.0f) {
            discountPercentage = 100.0f;
        }

        float discount = (discountPercentage / 100) * discountedPrice;
        int originalPrice = discountedPrice + (int) discount;
        return originalPrice;
    }

    public float getAdminFeePercentage() {
        return 0.05f;
    }

    public int getAdminFee(int price) {
        float adminFeePercentage = getAdminFeePercentage();
        float adminFee = (adminFeePercentage / 100) * price;
        return (int) adminFee;
    }

    public int getTotalPrice(int price, int numberOfSeat) {
        int adminFee = getAdminFee(price * numberOfSeat);
        return (price * numberOfSeat) + adminFee;
    }

    public static void main(String[] args) {
        // TP Modul 6
        String filepath = "/Users/adhelia/Desktop/CS/JBus/data/station.json";
        Gson gson = new Gson();

        try{
            BufferedReader buffer = new BufferedReader(new FileReader(filepath));
            List<Station> stationjson = gson.fromJson(buffer, new TypeToken<List<Station>>() {}.getType());
            stationjson.forEach((e -> System.out.println(e.toString())));
            System.out.println();
            buffer.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
        /*
            Bus b = createBus();
            List<Timestamp> listOfSchedules = new ArrayList<>();
            listOfSchedules.add(Timestamp.valueOf("2023-7-18 15:00:00"));
            listOfSchedules.add(Timestamp.valueOf("2023-7-20 12:00:00"));
            listOfSchedules.add(Timestamp.valueOf("2023-7-22 10:00:00"));
            listOfSchedules.add(Timestamp.valueOf("2023-7-26 12:00:00"));

            listOfSchedules.forEach(b::addSchedule);
            System.out.println("Page 1");
            Algorithm.paginate(b.schedules, 0, 3, t -> true).forEach(System.out::println);
            System.out.println("=====================================================");
            System.out.println("Page 2");
            Algorithm.paginate(b.schedules, 1, 3, t -> true).forEach(System.out::println);
            System.out.println("=====================================================");

            // Tes Booking
            String msgSuccess = "Booking Success!";
            String msgFailed = "Booking Failed";
            // valid date, invalid seat = Booking Failed
            Timestamp t1 = Timestamp.valueOf("2023-7-19 15:00:00");
            System.out.println("\nMake booking at July 19, 2023 15:00:00 Seats: S17 S18");
            System.out.println(Payment.makeBooking(t1, List.of("S17", "S18"), b)? msgSuccess : msgFailed);
            // valid date, invalid seat = Booking Failed
            Timestamp t2 = Timestamp.valueOf("2023-7-18 15:00:00");
            System.out.println("Make booking at July 18, 2023 15:00:00 Seat S26");
            System.out.println(Payment.makeBooking(t2, "S26", b)? msgSuccess : msgFailed);
            // valid date, valid seat = Booking Success
            System.out.println("Make booking at July 18, 2023 15:00:00 Seats: S7 S8");
            System.out.println(Payment.makeBooking(t2, List.of("S7", "S8"), b)? msgSuccess : msgFailed);
            // valid date, valid seat = Booking Success
            Timestamp t3 = Timestamp.valueOf("2023-7-20 12:00:00");
            System.out.println("Make booking at July 20, 2023 12:00:00 Seats: S1 S2");
            System.out.println(Payment.makeBooking(t3, List.of("S1", "S2"), b)? msgSuccess : msgFailed);
            // valid date, book the same seat = Booking Failed
            System.out.println("Make booking at July 20, 2023 12:00:00 Seat S1");
            System.out.println(Payment.makeBooking(t3, "S1", b)? msgSuccess : msgFailed);
            // check if the data changed
            System.out.println("\nUpdated Schedule");
            Algorithm.paginate(b.schedules, 0, 4, t-> true).forEach(System.out::println);
        }

        public static Bus createBus() {
            Price price = new Price(750000, 5);
            Bus bus = new Bus(1, "Netlab Bus", Facility.LUNCH, price, 25, BusType.REGULER, City.BANDUNG, new Station(1, "Depok Terminal", City.DEPOK, "Jl. Margonda Raya"), new Station(2, "Halte UI", City.JAKARTA, "Universitas Indonesia"));
                return bus;
        }
    }

        /*
        Integer[] numbers = {18, 10, 22, 43, 18, 67, 12, 11, 88, 22, 18};
        System.out.println("Number " + Arrays.toString(numbers));

        // Tes Algorithm
        System.out.print("1. ");
        testCount(numbers);
        System.out.print("2. ");
        testFind(numbers);
        System.out.print("3. ");
        testExist(numbers);
        System.out.println("4. Filtering");
        testCollect(numbers);
    }

    private static void testExist(Integer[] t) {
        int valueToCheck = 67;
        boolean result3 = Algorithm.exists(t, valueToCheck);
        if (result3) {
            System.out.println(valueToCheck + " exist in the array.");
        } else {
            System.out.println(valueToCheck + " doesn't exists in the array.");
        }
    }

    public static void testCount(Integer[] t) {
        int valueToCount = 18;
        int result = Algorithm.count(t, valueToCount);
        System.out.println("Number " + valueToCount + " appears " + result + " times");
    }

    public static void testFind(Integer[] t) {
        Integer valueToFind = 69;
        Integer result2 = Algorithm.find(t, valueToFind);
        System.out.print("Finding " + valueToFind + " inside the array : ");
        if (result2 != null) {
            System.out.println("Found!" + result2);
        } else {
            System.out.println("Not Found");
        }
    }

    private static void testCollect(Integer[] t) {
        Predicate<Integer> below = (val) -> val <= 22;
        Predicate<Integer> above = (val) -> val > 43;

        List<Integer> integerBelow = Algorithm.collect(t, below);
        List<Integer> integerAbove = Algorithm.collect(t, above);

        System.out.println("Below 22");
        System.out.println(integerBelow);
        System.out.println("Above 43");
        System.out.println(integerAbove);
    }
}
        /*
        Integer[] numbers = {10, 20, 30, 40, 50};
        int valueToCheck = 30;

        boolean result = Algorithm.exists(numbers, valueToCheck);

        if(result){
            System.out.println(valueToCheck + " terdapat dalam array.");
        } else {
            System.out.println(valueToCheck + " tidak terdapat dalam array.");
        }

        System.out.println("Hello from Intellij :)");

        Bus b = createBus();
        Timestamp schedule1 = Timestamp.valueOf("2023-07-18 15:00:00");
        Timestamp schedule2 = Timestamp.valueOf("2023-07-20 12:00:00");
        b.addSchedule(schedule1, 12);
        b.addSchedule(schedule2, 12);
        b.schedules.forEach(schedule -> schedule.printSchedule());

        Timestamp t1 = Timestamp.valueOf("2023-07-19 15:00:00");
        System.out.println("Make booking at July 19, 2023 15:00:00 Seat BR12");
        System.out.println(Payment.makeBooking(t1, "BR12", b));

        Timestamp t2 = Timestamp.valueOf("2023-07-18 15:00:00");
        System.out.println("\nMake booking at July 18, 2023 15:00:00 Seat BR20");
        System.out.println(Payment.makeBooking(t2, "BR20", b));

        System.out.println("\nMake booking at July 18, 2023 15:00:00 Seat BR07");
        System.out.println(Payment.makeBooking(t2, "BR07", b));

        Timestamp t3 = Timestamp.valueOf("2023-07-20 12:00:00");
        System.out.println("\nMake booking at July 20, 2023 12:00:00 Seat BR01");
        System.out.println(Payment.makeBooking(t3, "BR01", b));

        System.out.println("\nMake booking at July 20, 2023 12:00:00 Seat BR01 again");
        System.out.println(Payment.makeBooking(t3, "BR01", b));

        System.out.println("\nUpdated Schedule\n");
        b.schedules.forEach(schedule -> schedule.printSchedule());

        int s = 5;
        Price[] unfilteredArray = new Price[s];
        for(int i = 0; i < unfilteredArray.length; i++){
            int j = 5000;
            unfilteredArray[i] = new Price ((1 + 1) * j);
        }
        
        System.out.println("Price List");
        for (Price price : unfilteredArray){
            System.out.println(price.price);
        }
        
        System.out.println("Below 12000.0");
        ArrayList below12000 = Validate.filter(unfilteredArray, 12000, true);
        System.out.println(below12000);
        
        System.out.println("Above 10000.0");
        ArrayList above10000 = Validate.filter(unfilteredArray, 10000, false);
        System.out.println(above10000);
        
        Bus testBus = createBus();
        
        //Payment
        Payment testPayment = new Payment(1,1,1,1,"S1");
        System.out.println(testPayment.getDepartureInfo());
        System.out.println(testPayment.getTime());
        
        //Schedule
        Calendar schedule1 = Calendar.getInstance();
        testBus.addSchedule(schedule1);
        Calendar schedule2 = Calendar.getInstance();
        schedule2.add(Calendar.DAY_OF_MONTH, 3);
        testBus.addSchedule(schedule2);
        
        
        for(Schedule S: testBus.schedules){
            testBus.printSchedules();
        }
        
        /*
        Payment testPayment = new Payment (1, 1, 1, "A", 1, "A", "A");
        Invoice testInvoice = new Invoice (2, 2, 2, "B");
        Station testStation = new Station (3, "C", City.DEPOK);
        
        System.out.println(testPayment.print());
        System.out.println(testInvoice.print());
        System.out.println(testStation.print());
        
        Bus testBus = createBus();
        System.out.println(testBus.name);
        System.out.println(testBus.facility);
        System.out.println(testBus.price.price);
        System.out.println(testBus.capacity);
        
        JBus driver = new JBus();
        
        int beforeDiscount = 1000;
        int afterDiscount = 900;
        float discountPercentage = driver.getDiscountPercentage(beforeDiscount, afterDiscount);
        System.out.println ("Discount percentage: "+ discountPercentage + "%");
        
        int price = 100;
        float discountedPrice = driver.getDiscountedPrice(price, discountPercentage);
        System.out.println ("Discounted price: "+ discountedPrice);
        
        int originalPrice = driver.getOriginalPrice((int) discountedPrice, discountPercentage);
        System.out.println("Original price: "+ originalPrice);
        
        int adminFee = driver.getAdminFee(price);
        System.out.println("Admin Fee: "+ adminFee);
        
        int numberOfSeat = 2;
        int totalPrice = driver.getTotalPrice(price, numberOfSeat);
        System.out.println("Total price for "+ numberOfSeat + " seats: "+ totalPrice);
        */


   /* public static Bus createBus() {
        Price price = new Price(750000, 5);
        Bus bus = new Bus(1, "Netlab Bus", Facility.LUNCH, price, 25, BusType.REGULER, City.BANDUNG, new Station(1, "Depok Terminal", City.DEPOK, "Jl. Margonda Raya"), new Station(2, "Halte UI", City.JAKARTA, "Universitas Indonesia"));
        return bus;
    }
}*/