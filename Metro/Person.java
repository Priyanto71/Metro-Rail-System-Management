package Metro;

// Person.java - abstract base class for people in the system

public abstract class Person {
    protected String username; // common property for persons

    // constructor for subclasses
    public Person(String username) {
        this.username = username;
    }

    // abstract method — subclasses must implement to return their role
    public abstract String getRole();

    // getter for username
    public String getUsername() {
        return username;
    }
}
