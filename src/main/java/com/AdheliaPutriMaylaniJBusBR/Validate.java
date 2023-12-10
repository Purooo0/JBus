package com.AdheliaPutriMaylaniJBusBR;

import java.util.ArrayList;

/**
 * The Validate class provides methods for filtering Price objects based on specific criteria.
 *
 * @author Adhelia Putri Maylani
 */

public class Validate {
    /**
     * Filters an array of Price objects based on a specified value and comparison condition.
     *
     * @param list An array of Price objects to be filtered.
     * @param value The value used for filtering.
     * @param less A boolean indicating whether to filter prices less than or equal to the specified value.
     *             If true, filters prices less than or equal to the value; otherwise, filters prices greater
     *             than the value.
     * @return An ArrayList containing filtered Price values based on the specified criteria.
     */
    public static ArrayList<Double> filter (Price[] list, int value, boolean less){
        ArrayList<Double> filteredPrice = new ArrayList();
        for(Price price : list){
            if(less && price.price <= value){
                filteredPrice.add(price.price);
            }
            else if(!less && price.price > value){
                filteredPrice.add(price.price);
            }
        }
        return filteredPrice;
    }
}
