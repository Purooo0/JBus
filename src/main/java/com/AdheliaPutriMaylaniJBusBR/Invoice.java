package com.AdheliaPutriMaylaniJBusBR;
import com.AdheliaPutriMaylaniJBusBR.dbjson.Serializable;

import java.sql.Timestamp;


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
        super();
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.time = new Timestamp(System.currentTimeMillis());
        this.buyerId = (buyer != null) ? buyer: 0;
        this.renterId = (renter != null) ? Serializable.getId() : 0;
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