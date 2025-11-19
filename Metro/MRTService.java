package Metro;
// MRTService.java - implements FareCalculator, contains travel helper methods

public class MRTService implements FareCalculator {

    // INTERFACE METHOD: compute fare without considering user's discounts
    @Override
    public double calculateFare(String from, String to) {
        return MetroFare.getFare(from, to);
        }

    // OVERLOADED METHOD: compute fare for a user (apply 10% discount if they have an MRT number)
    @Override
    public double calculateFare(User user, String from, String to) {
        double base = calculateFare(from, to);
        if (user != null && user.getMrtNumber() != null && !user.getMrtNumber().isEmpty()) {
            return base * 0.90; // 10% discount
        }
        return base;
    }

    // perform travel: calculate fare for user and deduct if possible — returns true on success
    public boolean travel(User user, String from, String to) {
        double fare = calculateFare(user, from, to); // uses overloaded version
        if (fare < 0) return false;
        if (user.deduct(fare)) {
            user.addJourney("From " + from + " to " + to + " | Fare: " + String.format("%.2f", fare));
            return true;
        } else {
            return false;
        }
    }
}
