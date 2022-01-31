package vehiclerental;

import java.time.LocalTime;

public class Bike implements Rentable {
    private String ID;
    private LocalTime rentingTime;

    @Override
    public int calculateSumPrice(long minutes) {
        return (int) minutes * 15;
    }

    public Bike(String ID) {
        this.ID = ID;
    }

    public void rent(LocalTime time) {
        this.rentingTime = time;
    }

    public void closeRent() {
        this.rentingTime = null;
    }

    public String getID() {
        return ID;
    }

    @Override
    public LocalTime getRentingTime() {
        return rentingTime;
    }
}
