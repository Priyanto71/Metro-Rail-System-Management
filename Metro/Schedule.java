package Metro;

// Schedule.java - provides simple schedule lists

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Schedule {

    private static Map<String, List<String>> towardsUttara = new HashMap<>();
    private static Map<String, List<String>> towardsMotijheel = new HashMap<>();

    static {
        // schedules
        // towardsUttara
        List<String> t1 = new ArrayList<>();
        t1.add("07:30");
        t1.add("07:50");
        t1.add("08:10");
        t1.add("08:30");
        t1.add("08:50");
        t1.add("09:10");
        t1.add("09:30");
        t1.add("09:50");
        t1.add("10:10");
        t1.add("10:30");
        t1.add("10:50");
        t1.add("11:10");
        t1.add("11:30");
        t1.add("11:50");
        // towardsMotijheel
        List<String> t2 = new ArrayList<>();
        t2.add("07:40");
        t2.add("08:00");
        t2.add("08:20");
        t2.add("08:40");
        t2.add("09:00");
        t2.add("09:20");
        t2.add("09:40");
        t2.add("10:00");
        t2.add("10:20");
        t2.add("10:40");
        t2.add("11:00");
        t2.add("11:20");
        t2.add("11:40");
        t2.add("12:00");

        // assign to stations
        String[] stations = {"Uttara North", "Uttara Central", "Uttara South", "Pallabi", "Mirpur 11", "Mirpur 10", "Kazipara", "Shewrapara", "Agargaon", "Bijoy Shoroni", "Farmgate", "Karwan Bazar", "Shahbagh", "Dhaka University", "Bangladesh Secretariat", "Motijheel"};
        for (String s : stations) {
            towardsUttara.put(s, t1);
            towardsMotijheel.put(s, t2);
        }
    }

    // getters
    public static List<String> getTowardsUttara(String station) {
        return towardsUttara.getOrDefault(station, new ArrayList<>());
    }

    public static List<String> getTowardsMotijheel(String station) {
        return towardsMotijheel.getOrDefault(station, new ArrayList<>());
    }
}
