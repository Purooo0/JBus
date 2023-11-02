package AdheliaPutriMaylaniJBusBR;

/**
 * Adhelia Putri Maylani [2206814816]
 * Version 14.9.23
 * CS - OOP
 */

public class Voucher implements FileParser {
    public String name;
    private boolean used;
    public double minimum;
    public double cut;
    public int code;
    public Type type;
    
    public Object write(){
        return this;
    }
    
    public boolean read(String file){
        return true;
    }
    
    public Voucher(int code, String name, Type type, double minimum, double cut){
        this.name = name;
        this.code = code;
        this.type = type;
        this.minimum = minimum;
        this.cut = cut;
        this.used = false;
    }
    
    public boolean isUsed(){
        return used;
    }
    
    public boolean canApply(Price price){
        if(!used && price.getValue() > minimum){
            return true;
        }
        return false;
    }
    
    public double apply(Price price){
        if (canApply(price)){
            used = true;
            if (type == Type.DISCOUNT){
                double discountAmount = price.getValue() * (cut / 100.0);
                return price.getValue() - discountAmount;
            } else if (type == Type.REBATE){
                return price.getValue() - cut;
            }
        }
        return price.getValue();
    }
}