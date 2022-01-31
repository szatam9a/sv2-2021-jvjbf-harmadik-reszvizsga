package vehiclerental;

import java.time.LocalTime;
import java.util.Comparator;

public interface Rentable extends Comparable<Rentable> {
    int calculateSumPrice(long minutes);

    LocalTime getRentingTime();

    void rent(LocalTime time);

    void closeRent();

    default int compareTo(Rentable o1, Rentable o2) {
        return o1.getRentingTime().compareTo(o2.getRentingTime());
    }

}
