package com.AdheliaPutriMaylaniJBusBR;

import com.AdheliaPutriMaylaniJBusBR.dbjson.Serializable;

import java.sql.Timestamp;

/**
 * Represents an invoice entity with details such as buyer and renter information, rating, payment status, and timestamp.
 * Extends Serializable to support JSON serialization.
 * This class encapsulates information about an invoice, including buyer and renter details, rating, payment status, and timestamp.
 *
 * @author Adhelia Putri Maylani
 */

public class Invoice extends Serializable{
    // Enumerations for bus rating and payment status
    public enum BusRating {NONE, NEUTRAL, GOOD, BAD}
    public enum PaymentStatus {FAILED, WAITING, SUCCESS}

    // Properties of the Invoice class
    public int buyerId;
    public int renterId;
    public BusRating rating;
    public PaymentStatus status;
    public Timestamp time;

    /**
     * Protected constructor for creating an Invoice object with buyer and renter information.
     *
     * @param buyerId The ID of the buyer.
     * @param renterId The ID of the renter.
     */
    protected Invoice(int buyerId, int renterId){
        super();
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.rating = BusRating.NONE;
        this.status = PaymentStatus.WAITING;
        this.time = new Timestamp(System.currentTimeMillis());
    }

    /**
     * Constructor for creating an Invoice object with Account (buyer) and Renter information.
     *
     * @param buyer The buyer account.
     * @param renter The renter information.
     */
    public Invoice(Account buyer, Renter renter){
        super();
        this.buyerId = buyer.id;
        this.renterId = renterId;
        this.rating = BusRating.NONE;
        this.status = PaymentStatus.WAITING;
        this.time = new Timestamp(System.currentTimeMillis());
    }

    /**
     * Returns a string representation of the Invoice object.
     *
     * @return The string representation.
     */
    public String toString(){
        return ("ID: "+ super.id +
                "buyerId: " + this.buyerId +
                " renterId: " + this.renterId) +
                "; Time: "+ time.toString();
    }    
}