package Metro;

// UserDatabase.java - simple user store with serialization to keep data between runs

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserDatabase {
    private static final String DB_FILE = "users.ser";
    private Map<String, User> users;

    // Constructor: loads existing DB from disk or creates new map
    @SuppressWarnings("unchecked")
    public UserDatabase() {
        users = new HashMap<>();
        File f = new File(DB_FILE);
        if (f.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
                Object obj = ois.readObject();
                if (obj instanceof Map) {
                    users = (Map<String, User>) obj;
                }
            } catch (Exception ex) {
                System.out.println("Warning: could not load users.ser: " + ex.getMessage());
            }
        }
    }

    // persist map to disk
    public void save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DB_FILE))) {
            oos.writeObject(users);
        } catch (Exception ex) {
            System.out.println("Failed to save user database: " + ex.getMessage());
        }
    }

    // add user and persist
    public void addUser(User u) {
        users.put(u.getUsername(), u);
        save();
    }

    // check existence
    public boolean exists(String username) {
        return users.containsKey(username);
    }

    // authenticate
    public User authenticate(String username, String password) {
        User u = users.get(username);
        if (u != null && u.password.equals(password)) return u;
        return null;
    }

    // get user by username
    public User getUser(String username) {
        return users.get(username);
    }
}
