package com.AdheliaPutriMaylaniJBusBR;

import com.AdheliaPutriMaylaniJBusBR.dbjson.Serializable;

import java.sql.Timestamp;

public class Invoice extends Serializable{
    public enum BusRating {NONE, NEUTRAL, GOOD, BAD}
    public enum PaymentStatus {FAILED, WAITING, SUCCESS}

    public int buyerId;
    public int renterId;
    public BusRating rating;
    public PaymentStatus status;
    public Timestamp time;

    protected Invoice(int buyerId, int renterId){
        super();
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.rating = BusRating.NONE;
        this.status = PaymentStatus.WAITING;
        this.time = new Timestamp(System.currentTimeMillis());
    }

    public Invoice(Account buyer, Renter renter){
        super();
        this.buyerId = buyer.id;
        this.renterId = renterId;
        this.rating = BusRating.NONE;
        this.status = PaymentStatus.WAITING;
        this.time = new Timestamp(System.currentTimeMillis());
    }
    
    public String toString(){
        return ("ID: "+ super.id +
                "buyerId: " + this.buyerId +
                " renterId: " + this.renterId) +
                "; Time: "+ time.toString();
    }    
}