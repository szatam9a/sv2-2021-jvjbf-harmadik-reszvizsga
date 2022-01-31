package vehiclerental;

import java.time.LocalTime;
import java.util.Objects;

public class Car implements Rentable {
    private String ID;
    private LocalTime rentingTime;
    private int pricePerMinute;

    public Car(String ID, int pricePerMinute) {
        this.ID = ID;
        this.pricePerMinute = pricePerMinute;
    }

    @Override
    public int calculateSumPrice(long minutes) {
        return (int) (pricePerMinute * minutes);
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
    public int compareTo(Rentable o) {
        return this.rentingTime.compareTo(o.getRentingTime());
    }

    @Override
    public LocalTime getRentingTime() {
        return rentingTime;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return Objects.equals(ID, car.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}
