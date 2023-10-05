package AdheliaPutriMaylaniJBusBR;
import java.util.ArrayList;
/**
 * Adhelia Putri Maylani [2206814816]
 * Version 28.9.23
 * CS - OOP
 */
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
