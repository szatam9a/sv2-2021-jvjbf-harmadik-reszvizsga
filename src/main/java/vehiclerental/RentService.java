package vehiclerental;

import java.time.LocalTime;
import java.util.*;

public class RentService {
    private Set<User> users = new HashSet<>();
    private Set<Rentable> rentables = new HashSet<>();
    private Map<Rentable, User> actualRenting = new HashMap<>();

    public RentService() {
    }

    public void registerUser(User user) {
        if (users.contains(user)) {
            throw new UserNameIsAlreadyTakenException("Username is taken!");
        }
        users.add(user);
    }

    public void addRentable(Rentable rentable) {
        rentables.add(rentable);
    }

    public void rent(User user, Rentable rentable, LocalTime time) {
        hasTheUserMoneyForTheRent(user, isItMoreThan3Hours(time), rentable);
        if (!actualRenting.keySet().contains(rentable)) {
            rentable.rent(time);
            actualRenting.put(rentable, user);
        } else {
            throw new IllegalStateException("Rentable already rented");
        }
    }

    private void hasTheUserMoneyForTheRent(User user, int minutes, Rentable rentable) {
        if (user.getBalance() < rentable.calculateSumPrice(minutes)) {
            throw new IllegalStateException("no money for the rent");
        }
    }

    private int isItMoreThan3Hours(LocalTime time) {
        if (makeItMinutes(time) - makeItMinutes(LocalTime.now()) > 180) {
            throw new IllegalStateException("more than 3 hours");
        } else return makeItMinutes(time) - makeItMinutes(LocalTime.now());
    }

    private int makeItMinutes(LocalTime time) {
        return time.getHour() * 60 + time.getMinute();
    }

    public void closeRent(Rentable rentable, int minutes) {
        actualRenting.get(rentable).minusBalance(rentable.calculateSumPrice(minutes));
        actualRenting.remove(rentable);
        rentable.closeRent();
    }

    public Set<User> getUsers() {
        return users;
    }

    public Set<Rentable> getRentables() {
        return rentables;
    }

    public Map<Rentable, User> getActualRenting() {
        return actualRenting;
    }
}
