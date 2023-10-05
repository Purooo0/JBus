package AdheliaPutriMaylaniJBusBR;

/**
 * Adhelia Putri Maylani [2206814816]
 * Version 14.9.23
 * CS - OOP
 */

public class Rating{
    private long total;
    private long count;
    
    public Rating(){
        this.total = 0; 
        this.count = 0;
    }
    
    public void insert(int rating){
        total += rating;
        count++;
    }
    
    public double getAverage(){
        if(count == 0){
            return 0.0;
        }
        return (double) total / count;
    }
    
    public long getCount(){
        return count;
    }
    
    public long getTotal(){
        return total;
    }
    
    public String toString(){
        return "Total Ratings: "+ total + "\n" +
               "Number of Ratings: "+ count + "\n" +
               "Average Rating: "+ getAverage();
    }
}