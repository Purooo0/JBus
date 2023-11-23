package com.AdheliaPutriMaylaniJBusBR;

import java.util.ArrayList;

public class Validate {
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
