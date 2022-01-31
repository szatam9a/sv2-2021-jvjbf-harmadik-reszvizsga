package vehiclerental;

import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Car implements Rentable {
    private String id;
    private LocalTime rentingTime;
    private int pricePerMin;

    public Car(String id, int pricePerMin) {
        this.id = id;
        this.pricePerMin = pricePerMin;
    }

    @Override
    public int calculateSumPrice(long minutes) {
        return (int) (this.pricePerMin * minutes);
    }
    public void rent(LocalTime time) {
        this.rentingTime = time;
    }

    public void closeRent() {
        this.rentingTime = null;
    }

    public String getId() {
        return id;
    }

    @Override
    public LocalTime getRentingTime() {
        return rentingTime;
    }

    public int getPricePerMin() {
        return pricePerMin;
    }
}
