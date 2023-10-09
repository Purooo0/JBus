package AdheliaPutriMaylaniJBusBR;
import java.util.HashMap;

/**
 * Adhelia Putri Maylani [2206814816]
 * Version 5.10.23
 * CS - OOP
 */

public class Serializable implements Comparable<Serializable> {
    final public int id;
    static private HashMap<Class<?>, Integer> mapCounter = new HashMap<>();
    
    protected Serializable(int id){
        Class<?> clazz = this.getClass();
        if (!mapCounter.containsKey(clazz)){
            mapCounter.put(clazz, 0);
        } else {
            int counter = mapCounter.get(clazz);
            mapCounter.put(clazz, counter + 1);
        }
        this.id = mapCounter.get(clazz);
    }

    public static Integer getLastAssignedId(Class<?> clazz){
        return mapCounter.get(clazz);
    }

    static public Integer setLastAssignedId(Class<?> clazz, int lastAssignedId) {
        return mapCounter.put(clazz, lastAssignedId);
    }

    public int compareTo(Serializable other){
        return Integer.compare(this.id, other.id);
    }

    public boolean equals(Object obj){
        if(obj == this){
            return true;
        }
        if(obj instanceof Serializable){
            Serializable other = (Serializable) obj;
            return this.id == other.id;
        }
        return false;
    }

    public boolean equals(Serializable other){
        return this.id == other.id;
    }

}