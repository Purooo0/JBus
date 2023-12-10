package com.AdheliaPutriMaylaniJBusBR;

/**
 * The Price class represents the cost of a bus ride.
 * It encapsulates information about the base price and rebate amount.
 *
 * @author Adhelia Putri Maylani
 */

public class Price{
    public double rebate; // The rebate amount
    public double price; // The base price without any rebate

    /**
     * Constructs a Price object with a given base price and no rebate.
     *
     * @param price The base price of the bus ride.
     */
    public Price(double price){
        this.price = price;
        this.rebate = 0;
    }

    /**
     * Constructs a Price object with a given base price and rebate amount.
     *
     * @param price The base price of the bus ride.
     * @param discount The rebate amount to be applied.
     */
    public Price(double price, int discount){
        this.price = price;
        this.rebate = discount;
    }

    /**
     * Returns a string representation of the Price object.
     *
     * @return A string containing the base price and rebate.
     */
    public String toString(){
        return "Price: "+ price + "\n" +
               "Rebate: "+ rebate + "\n";
    }
}