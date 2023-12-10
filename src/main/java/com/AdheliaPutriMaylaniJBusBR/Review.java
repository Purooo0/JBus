package com.AdheliaPutriMaylaniJBusBR;

import com.AdheliaPutriMaylaniJBusBR.dbjson.Serializable;

/**
 * The Review class represents a review with a date and description. It extends the Serializable class.
 *
 * @author Adhelia Putri Maylani
 */
public class Review extends Serializable {
    public String date; /** The date of the review. */
    public String desc; /** The description of the review. */

    /**
     * Constructs a Review object with the given date and description.
     *
     * @param date The date of the review.
     * @param desc The description of the review.
     */
    public Review(String date, String desc){
        super();
        this.date = date;
        this.desc = desc;
    }

    /**
     * Returns a string representation of the review, including its ID, date, and description.
     *
     * @return The string representation of the review.
     */
    public String toString(){
        return "Review ID: "+
               "\nDate: "+ date +
               "\nDescription: "+ desc;
    }
}
