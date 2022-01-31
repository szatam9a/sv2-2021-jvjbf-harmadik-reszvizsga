package vehiclerental;

import java.time.LocalTime;
import java.util.*;

public class RentService {
    private Set<User> users = new HashSet<>();
    private Set<Rentable> rentables = new HashSet<>();
    private Map<Rentable, User> actualRenting = new TreeMap<>();

    public void registerUser(User user) {
        if (users.contains(user)) throw new UserNameIsAlreadyTakenException("Username is taken!");
        users.add(user);
    }

    public void addRentable(Rentable rentable) {
        rentables.add(rentable);
    }

    public void rent(User user, Rentable rentable, LocalTime time) {
        validateUserForRent(user, rentable, time);
        if (validateRentableForRent(rentable, time)) {
            rentable.rent(time);
            actualRenting.put(rentable, user);
        }
    }

    private boolean validateRentableForRent(Rentable rentable, LocalTime time) {
        if (rentingTimeInMinutes(time) > 180) {
            throw new IllegalStateException("cant rent for more than 180minutes");
        }
        if (actualRenting.containsKey(rentable)) {
            for (Map.Entry<Rentable, User> actual : actualRenting.entrySet()) {
                if (actual.getKey().equals(rentable)) {
                    Rentable rent = actual.getKey();
                    if (makeItMinutes(rent.getRentingTime()) + makeItMinutes(time) > 180)
                        throw new IllegalStateException("cant rent for more than 180minutes");
                    rent.rent(time);
                    return false;
                }
            }
        }
        return true;
    }

    private void validateUserForRent(User user, Rentable rentable, LocalTime time) {
        if (user.getBalance() < rentable.calculateSumPrice((makeItMinutes(time) - makeItMinutes(LocalTime.now())))) {
            throw new IllegalStateException("no balajnce for rent");
        }
    }

    public void closeRent(Rentable rentable, int minutes) {
        if (actualRenting.containsKey(rentable)) {
            User user = actualRenting.entrySet().stream().filter(e -> e.getKey().equals(rentable)).findFirst().get().getValue();
            user.minusBalance(rentable.calculateSumPrice(minutes));

        }
    }

    private int makeItMinutes(LocalTime time) {
        return (time.getMinute() + time.getHour() * 60);
    }

    private int rentingTimeInMinutes(LocalTime renting) {
        return makeItMinutes(renting) - makeItMinutes(LocalTime.now());
    }

    public Map<Rentable, User> getActualRenting() {
        return actualRenting;
    }

    public Set<User> getUsers() {
        return users;
    }

    public Set<Rentable> getRentables() {
        return rentables;
    }
}
