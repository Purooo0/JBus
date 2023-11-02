package AdheliaPutriMaylaniJBusBR;
import java.sql.Timestamp;

/**
 * Adhelia Putri Maylani [2206814816]
 * Version 9.9.23
 * CS - OOP
 */

public class Invoice{
    public enum BusRating {NONE, NEUTRAL, GOOD, BAD}
    public enum PaymentStatus {FAILED, WAITING, SUCCESS}
    
    public Timestamp time;
    public Object buyerId;
    public int renterId;
    public Account buyer;
    public Renter renter;
    public BusRating rating = BusRating.NONE;
    public PaymentStatus status = PaymentStatus.WAITING;
    
    protected Invoice(int buyerId, int renterId){
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.time = new Timestamp(System.currentTimeMillis());
        this.buyer = null;
        this.renter = null;
    }
    
    public Invoice(Account buyer, Renter renter){
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.time = new Timestamp(System.currentTimeMillis());
        this.buyerId = (buyer != null) ? buyer: 0;
        this.renterId = (renter != null) ? renter.getId() : 0;
    }
    
    public String toString(){
        return "Invoice Id: "+ "\n" +
               "Buyer Id: " +buyerId + "\n" +
               "Renter Id: " + renterId + "\n" +
               "Rating: " + rating + "\n" +
               "Time: " + time + "\n" +
               "Status: " + status;
    }    
}