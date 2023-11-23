package com.AdheliaPutriMaylaniJBusBR;

import com.AdheliaPutriMaylaniJBusBR.dbjson.Serializable;

public class Review extends Serializable {
    public String date;
    public String desc;
    
    public Review(String date, String desc){
        super();
        this.date = date;
        this.desc = desc;
    }
    
    public String toString(){
        return "Review ID: "+
               "\nDate: "+ date +
               "\nDescription: "+ desc;
    }
}
