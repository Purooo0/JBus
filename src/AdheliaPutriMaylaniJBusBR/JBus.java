package AdheliaPutriMaylaniJBusBR;
import java.util.ArrayList;
import java.sql.Timestamp;

/**
 * Adhelia Putri Maylani [2206814816]
 * Version 9.9.23
 * CS - OOP
 */

public class JBus {
    /*
    public static Bus createBus(){
        Price price = new Price (750000, 5);
        Bus bus = new Bus (10, "Netlab Bus", Facility.LUNCH, price, 25);
        return bus;
    }
    */
    public int getBusId(){
        return 0;
    }
    public String getBusName() {
        return "Bus";
    }
    public boolean isDiscount(){
        return true;
    }
    public float getDiscountPercentage(int beforeDiscount, int afterDiscount){
        if (beforeDiscount > afterDiscount){
            float percentage = ((float) (beforeDiscount - afterDiscount) / beforeDiscount) * 100;
            return percentage;
        } else {
            return 0.0f;
        }
    }
    public int getDiscountedPrice(int price, float discountPercentage){
        if (discountPercentage > 100.0f){
            discountPercentage = 100.0f;
        }
        
        float discount = (discountPercentage / 100) * price;
        int discountedPrice = price - (int) discount;
        return discountedPrice;
    }
    public int getOriginalPrice(int discountedPrice, float discountPercentage){
        if (discountPercentage > 100.0f){
            discountPercentage = 100.0f;
        }
        
        float discount = (discountPercentage / 100) * discountedPrice;
        int originalPrice = discountedPrice + (int) discount;
        return originalPrice;
    }
    public float getAdminFeePercentage(){
        return 0.05f;
    }
    public int getAdminFee(int price){
        float adminFeePercentage = getAdminFeePercentage();
        float adminFee = (adminFeePercentage / 100) * price;
        return (int) adminFee;
    }
    public int getTotalPrice(int price, int numberOfSeat){
        int adminFee = getAdminFee(price * numberOfSeat);
        return (price * numberOfSeat) + adminFee;
    }
    
    public static void main(String[] args){
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

        /*int s = 5;
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
    }

    public static Bus createBus() {
        Price price = new Price(750000, 5);
        Bus bus = new Bus(1, "Netlab Bus", Facility.LUNCH, price, 25, BusType.REGULER, City.BANDUNG, new Station(1, "Depok Terminal", City.DEPOK, "Jl. Margonda Raya"), new Station(2, "Halte UI", City.JAKARTA, "Universitas Indonesia"));
        return bus;
    }
}