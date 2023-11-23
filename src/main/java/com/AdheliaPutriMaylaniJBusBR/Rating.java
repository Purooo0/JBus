package com.AdheliaPutriMaylaniJBusBR;

public class Rating{
    private long total;
    private long count;
    
    public Rating(){
        this.total = 0; 
        this.count = 0;
    }
    
    public void insert(int rating){
        total = total + rating;
        count++;
    }
    
    public double getAverage(){
        return total / count;
    }
    
    public long getCount(){
        return count;
    }
    
    public long getTotal(){
        return total;
    }

    public String toString(){
        return "Total : "+ this.total +
                "\nCount : "+ this.count;
    }
}