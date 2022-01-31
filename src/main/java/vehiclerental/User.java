package vehiclerental;

import java.util.Objects;

public class User {
    private String name;
    private String email;
    private int balance;

    public User(String name, String email, int balance) {
        this.name = name;
        this.email = email;
        this.balance = balance;
    }

    public void minus(int minus) {
        this.balance -= minus;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getBalance() {
        return balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getName(), user.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
