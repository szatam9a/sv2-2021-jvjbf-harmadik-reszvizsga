package vehiclerental;

import java.time.LocalTime;
import java.util.Objects;

public class Bike implements Rentable {
    private String ID;
    private LocalTime rentingTime;

    public Bike(String ID) {
        this.ID = ID;
    }

    @Override
    public LocalTime getRentingTime() {
        return rentingTime;
    }

    @Override
    public int compareTo(Rentable o1, Rentable o2) {
        return Rentable.super.compareTo(o1, o2);
    }

    @Override
    public int calculateSumPrice(long minutes) {
        return (int) (minutes * 15);
    }

    @Override
    public void rent(LocalTime time) {
        this.rentingTime = time;
    }

    @Override
    public void closeRent() {
        this.rentingTime = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bike)) return false;
        Bike bike = (Bike) o;
        return Objects.equals(ID, bike.ID);
    }

    @Override
    public int compareTo(Rentable o) {
        return this.rentingTime.compareTo(o.getRentingTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}
