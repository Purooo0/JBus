package AdheliaPutriMaylaniJBusBR;
import java.util.Iterator;

/**
 * Adhelia Putri Maylani [2206814816]
 * Version 5.10.23
 * CS - OOP
 */

public class Algorithm {
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
            if (pred.test(current)) {
                return true;
            }
        }
        return false;
    }
}
