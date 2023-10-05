package AdheliaPutriMaylaniJBusBR;
import java.sql.Timestamp;

/**
 * Adhelia Putri Maylani [2206814816]
 * Version 9.9.23
 * CS - OOP
 */

public class Invoice extends Serializable{
    public enum BusRating {NONE, BAD, AVERAGE, GOOD, EXCELLENT}
    public enum PaymentStatus {WAITING, PAID, CANCELED}
    
    public Timestamp time;
    public int buyerId;
    public int renterId;
    public Account buyer;
    public Renter renter;
    public BusRating rating = BusRating.NONE;
    public PaymentStatus status = PaymentStatus.WAITING;
    
    protected Invoice(int id, int buyerId, int renterId){
        super(id);
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.time = new Timestamp(System.currentTimeMillis());
        this.buyer = null;
        this.renter = null;
    }
    
    public Invoice(int id, Account buyer, Renter renter){
        super(id);
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.time = new Timestamp(System.currentTimeMillis());
        this.buyerId = (buyer != null) ? buyer.getId() : 0;
        this.renterId = (renter != null) ? renter.getId() : 0;
    }
    
    public String toString(){
        return "Invoice Id: "+ id + "\n" +
               "Buyer Id: " +buyerId + "\n" +
               "Renter Id: " + renterId + "\n" +
               "Rating: " + rating + "\n" +
               "Time: " + time + "\n" +
               "Status: " + status;
    }    
}