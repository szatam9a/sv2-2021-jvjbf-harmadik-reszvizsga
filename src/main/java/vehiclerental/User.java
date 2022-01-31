package vehiclerental;

import java.util.Objects;

public class User {
    private String userName;
    private String email;
    private int balance;

    public User(String userName, String emailAddress, int balance) {
        this.userName = userName;
        this.email = emailAddress;
        this.balance = balance;
    }

    public void minusBalance(int minus) {
        this.balance -= minus;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getUserName(), user.getUserName()) && Objects.equals(getEmail(), user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserName(), getEmail());
    }
}
