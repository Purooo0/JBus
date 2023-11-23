package com.AdheliaPutriMaylaniJBusBR;

public class Price{
    public double rebate;
    public double price;

    public Price(double price){
        this.price = price;
        this.rebate = 0;
    }
    
    public Price(double price, int discount){
        this.price = price;
        this.rebate = rebate;
    }
    
    public String toString(){
        return "Price: "+ price + "\n" +
               "Rebate: "+ rebate + "\n";
    }
}