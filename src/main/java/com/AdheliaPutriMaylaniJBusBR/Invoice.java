package com.AdheliaPutriMaylaniJBusBR;
import com.AdheliaPutriMaylaniJBusBR.dbjson.Serializable;

import java.sql.Timestamp;


public class Invoice extends Serializable{
    public enum BusRating {NONE, NEUTRAL, GOOD, BAD}
    public enum PaymentStatus {FAILED, WAITING, SUCCESS}

    public int buyerId;
    public int renterId;
    public Account buyer;
    public Renter renter;
    public BusRating rating;
    public PaymentStatus status;

    protected Invoice(int buyerId, int renterId){
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.rating = BusRating.NONE;
        this.status = PaymentStatus.WAITING;
    }

    public Invoice(Account buyer, Renter renter){
        this.buyerId = buyer.id;
        this.renterId = renterId;
        this.rating = BusRating.NONE;
        this.status = PaymentStatus.WAITING;
    }
    
    public String toString(){
        return ("buyerId: " + this.buyerId +
                " renterId: " + this.renterId);
    }    
}