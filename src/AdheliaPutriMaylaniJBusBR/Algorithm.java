package AdheliaPutriMaylaniJBusBR;
import java.util.Iterator;
import java.util.*;
import java.lang.reflect.Array;

/**
 * Adhelia Putri Maylani [2206814816]
 * Version 6.10.23
 * CS - OOP
 */
    public class Algorithm {
        public static <T> int count(Iterator<T> iterator, Predicate<T> pred) {
            int counter = 0;

            while (iterator.hasNext()) {
                T current = iterator.next();
                if (pred.Predicate(current)) {
                    counter++;
                }
            }
            return counter;
        }

        public static <T> int count(T[] array, T value) {
            return count(Arrays.asList(array).iterator(), (Predicate<T>) obj -> obj.equals(value));
        }

        public static <T> int count(Iterator<T> iterator, T value) {
            return count(iterator, (Predicate<T>) obj -> obj.equals(value));
        }

        public static <T> int count(T[] array, Predicate<T> pred) {
            return count(Arrays.asList(array).iterator(), pred);
        }

        public static <T> int count(Iterable<T> iterable, T value) {
            return count(iterable.iterator(), (Predicate<T>) obj -> obj.equals(value));
        }

        public static <T> int count(Iterable<T> iterable, Predicate<T> pred) {
            return count(iterable.iterator(), pred);
        }

        public static <T> T find(Iterable<T> iterable, Predicate<T> pred) {
            for (T item : iterable) {
                if (pred.Predicate(item)) {
                    return item;
                }
            }
            return null;
        }

        public static <T> T find(T[] array, T value) {
            return find((Iterable<T>) Arrays.asList(array), (Predicate<T>) obj -> obj.equals(value));
        }

        public static <T> T find(T[] array, Predicate<T> pred){
            return find((Iterable<T>) Arrays.asList(array), pred);
        }

        public static <T> T find(Iterator<T> iterator, T value) {
            return find((Iterable<T>) () -> iterator, (Predicate<T>) obj -> obj.equals(value));
        }

        public static <T> T find(Iterable<T> iterable, T value) {
            return find(iterable, (Predicate<T>) obj -> obj.equals(value));
        }

        public static <T> T find(Iterator<T> iterator, Predicate<T> pred) {
            while (iterator.hasNext()) {
                T current = iterator.next();
                if (pred.Predicate(current)) {
                return current;
                }
            }
            return null;
        }


    public static <T> List<T> collect(Iterable<T> iterable, Predicate<T> pred){
        List<T> result = new ArrayList<>();
        for (T current : iterable) {
            if (pred.Predicate(current)) {
                result.add(current);
            }
        }
        return result;
    }
    public static <T> List<T> collect(Iterable<T> iterable, T value){
        for(T current : iterable){
            if(current == value){
                return (List<T>) current;
            }
        }
        return null;
    }
    public static <T> List<T> collect(T[] array, T value){
        final Iterator<T> it = Arrays.stream(array).iterator();
        return collect(it, value);
    }
    public static <T> List<T> collect(T[] array, Predicate<T> pred){
        List<T> result = new ArrayList<>();
        for (T current : array) {
            if (pred.Predicate(current)) {
                result.add(current);
            }
        }
        return result;
    }
    public static <T> List<T> collect(Iterator<T> iterator, T value){
        List<T> result = new ArrayList<>();
        while (iterator.hasNext()) {
            T current = iterator.next();
            if (value.equals(current)) {
                result.add(current);
            }
        }
        return result;
    }
    public static <T> List<T> collect(Iterator<T> iterator, Predicate<T> pred){
        List<T> result = new ArrayList<>();
        while (iterator.hasNext()) {
            T current = iterator.next();
            if (pred.Predicate(current)) {
                result.add(current);
            }
        }
        return result;
    }

    public static <T> boolean exists(T[] array, T value) {
        final Iterator<T> it = java.util.Arrays.stream(array).iterator();
        return exists(it, value);
    }

    public static <T> boolean exists(T[] array, Predicate<T> pred) {
        final Iterator<T> it = java.util.Arrays.stream(array).iterator();
        return exists(it, pred);
    }

    public static <T> boolean exists(Iterable<T> iterable, T value) {
        final Iterator<T> it = iterable.iterator();
        return exists(it, value);
    }

    public static <T> boolean exists(Iterable<T> iterable, Predicate<T> pred) {
        final Iterator<T> it = iterable.iterator();
        return exists(it, pred);
    }
    public static <T> boolean exists(Iterator<T> iterator, T value) {
        while (iterator.hasNext()) {
            T current = iterator.next();
            if (current.equals(value)) {
                return true;
            }
        }
        return false;
    }
    public static <T> boolean exists(Iterator<T> iterator, Predicate<T> pred) {
        while (iterator.hasNext()) {
            T current = iterator.next();
            if (pred.Predicate(current)) {
                return true;
            }
        }
        return false;
    }
}
