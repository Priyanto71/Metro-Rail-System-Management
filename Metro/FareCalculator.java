package Metro;
// FareCalculator.java - interface for fare calculation

public interface FareCalculator {

    // calculate base fare between two stations (no user)
    double calculateFare(String from, String to);

    // overloaded: calculate fare for a given user (may apply discounts)
    double calculateFare(User user, String from, String to);
}
