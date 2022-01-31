package vehiclerental;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RentService {
    private Set<User> users = new HashSet<>();
    private Set<Rentable> rentables = new HashSet<>();
    private Map<Rentable,User> actualRenting = new HashMap<>();

    public void registerUser(User user){
        if (users.contains(user)){
            throw new UserNameIsAlreadyTakenException("username already taken");
        }
    }
    public void AddRentable(Rentable rentable){
        rentables.add(rentable);
    }
    void rent(User user, Rentable rentable, LocalTime time){

    }
    void closeRent(Rentable rentable, int minutes){

    }
}
