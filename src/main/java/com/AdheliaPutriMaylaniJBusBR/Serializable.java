package com.AdheliaPutriMaylaniJBusBR;
import java.util.HashMap;

public class Serializable {
    public static int id = 0;
    private static HashMap<Class<?>, Integer> mapCounter = new HashMap<Class <?>, Integer>();
    protected Serializable(int i){
        Integer counter = mapCounter.get(getClass());
        counter = counter == null ? 0 : counter + 1;
        mapCounter.put(getClass(), counter);
        this.id = counter;
    }


    public static <T> Integer getLastAssignedId(Class<T> getter ){
        return mapCounter.get(getter);
    }

    public static <T> Integer setLastAssignedId(Class<T> setter, int number){
        return mapCounter.put(setter, number);
    }

    public int compareTo(Serializable temp){
        return ((Integer)this.id).compareTo(temp.id);
    }

    public boolean equals(Serializable temp){
        return temp.id == this.id;
    }

    public boolean equals(Object object){
        return object instanceof Serializable && ((Serializable) object).id == this.id;
    }
    public static int getId() {
        return id;
    }
}