package AdheliaPutriMaylaniJBusBR;

/**
 * Adhelia Putri Maylani [2206814816]
 * Version 14.9.23
 * CS - OOP
 */

public class Price{
    public double rebate;
    public double price;
    public int discount;
    public double value;
    
    public double getValue(){
        return value;
    }
    
    public Price(double price){
        this.price = price;
        this.discount = 0;
        this.rebate = 0;
    }
    
    public Price(double price, int discount){
        this.price = price;
        this.discount = discount;
        this.rebate = 0;
    }
    
    public Price(double price, double rebate){
        this.price = price;
        this.discount = 0;
        this.rebate = rebate;
    }
    
    private double getDiscountedPrice(){
        if(discount > 100.0){
            return price - (price * 100.0 / 100.0);
        } else if (discount == 100.0) {
            return 0.0;
        } else {
            return price - (price * discount / 100.0);
        }
    }
    
    private double getRebatedPrice(){
        double discountedPrice = price - rebate;
        return (discountedPrice < 0.0) ? 0.0 : discountedPrice; 
    }
    
    public String toString(){
        return "Discount: "+ price + "\n" +
               "Discount: " + discount + "\n" +
               "Rebate: "+ rebate + "\n" +
               "Value: "+ value;
    }
}