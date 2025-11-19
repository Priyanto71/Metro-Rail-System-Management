package Metro;

// User.java - concrete user class that extends Person and is serializable

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User extends Person implements Serializable { //inheritance
    private static final long serialVersionUID = 1L; // for serialization

    public String password;            // user's password
    public double balance = 0.0;       // user's MRT/Rapid pass balance
    private String mrtNumber = null;   // optional stored MRT pass number
    private List<String> journeys;     // textual journey history

    // constructor calls parent abstract class constructor
    public User(String username, String password) {
        super(username);              // initialize Person part
        this.password = password;
        this.balance = 100.0;         // starting balance
        this.journeys = new ArrayList<>();
    }

    // method overriding: implementation of abstract getRole()
    @Override
    public String getRole() {
        return "Regular Metro User";
    }

    // override toString() for easy display
    @Override
    public String toString() {
        return "User(" + username + ") - Balance: " + String.format("%.2f", balance) + " BDT";
    }

    // MRT pass number getter/setter Encapsulation
    public String getMrtNumber() {
        return mrtNumber;
    }

    public void setMrtNumber(String mrtNumber) {
        this.mrtNumber = mrtNumber;
    }

    // balance helpers
    public double getBalance() {
        return balance;
    }

    public void addBalance(double amt) {
        this.balance += amt;
    }

    public boolean deduct(double amt) {
        if (amt <= balance) {
            balance -= amt;
            return true;
        }
        return false;
    }

    // journey history
    public void addJourney(String j) {
        journeys.add(j);
    }

    public List<String> getJourneys() {
        return journeys;
    }
}
