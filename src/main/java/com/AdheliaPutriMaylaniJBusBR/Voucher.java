package com.AdheliaPutriMaylaniJBusBR;

import com.AdheliaPutriMaylaniJBusBR.dbjson.Serializable;

public class Voucher extends Serializable {
    public String name;
    private boolean used;
    public double minimum;
    public double cut;
    public int code;
    public Type type;

    public Voucher(int id, String name, int code, Type type, double minimum, double cut) {
        super();
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

    public boolean canApply(Price price) {
        if (price.price > minimum && !used) {
            return true;
        }
        return false;
    }

    public double apply(Price price){
        used = true;

        if(type == Type.DISCOUNT){
            if(cut > 100){
                cut = 100;
            }
            if(cut == 100){
                return 0;
            }
            return price.price - ((cut/100.0) * price.price);
        }
        if(type == Type.REBATE){
            if(cut >= price.price){
                return 0;
            }
            else{
                return price.price - cut;
            }
        }
        return price.price;
    }

    public boolean read(String file){
        return true;
    }
    public Object write(){
        return null;
    }
}