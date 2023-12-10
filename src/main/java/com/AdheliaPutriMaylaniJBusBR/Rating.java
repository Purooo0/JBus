package com.AdheliaPutriMaylaniJBusBR;

/**
 * The Rating class represents a rating system, keeping track of the total
 * and count of ratings to calculate the average rating.
 *
 * @author Adhelia Putri Maylani
 */

public class Rating{
    private long total; // The total sum of all ratings
    private long count; // The count of ratings

    /**
     * Constructs a Rating object with initial total and count values set to 0.
     */
    public Rating(){
        this.total = 0; 
        this.count = 0;
    }

    /**
     * Inserts a new rating into the system, updating the total and count.
     *
     * @param rating The rating to be inserted.
     */
    public void insert(int rating){
        total = total + rating;
        count++;
    }

    /**
     * Calculates and returns the average rating.
     *
     * @return The average rating.
     */
    public double getAverage(){
        // Avoid division by zero
        return (count > 0) ? ((double) total / count) : 0;
    }

    /**
     * Gets the count of ratings.
     *
     * @return The count of ratings.
     */
    public long getCount(){
        return count;
    }

    /**
     * Gets the total sum of ratings.
     *
     * @return The total sum of ratings.
     */
    public long getTotal(){
        return total;
    }

    /**
     * Returns a string representation of the Rating object.
     *
     * @return A string containing the total and count of ratings.
     */
    public String toString(){
        return "Total : "+ this.total +
                "\nCount : "+ this.count;
    }
}