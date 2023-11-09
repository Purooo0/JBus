package com.AdheliaPutriMaylaniJBusBR;
import java.util.ArrayList;

public class Validate {
    public static ArrayList filter(Price[] list, double value, boolean less){
        ArrayList result = new ArrayList();
        
        for(Price price : list){
        double priceValue = price.getValue();
        
        if(less && priceValue <= value){
            result.add(price.price);
        } else if (!less && priceValue > value){
            result.add(price.price);
        }
    }
    
    return result;
    }
}
