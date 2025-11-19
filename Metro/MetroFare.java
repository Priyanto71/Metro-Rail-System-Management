package Metro; // package declaration

import java.util.HashMap; // import HashMap for fare table
import java.util.Map; // import Map

// MetroFare provides static fare lookup between two stations.
public class MetroFare { // class begins
    private static Map<String, Integer> fareTable = new HashMap<>(); // map to hold fares

    static { // static initializer to populate fare table with sample fares
        // fares are symmetric: "StationA|StationB" -> fare
        // base fares
        fareTable.put(key("Uttara North","Uttara Central"), 20); // fare sample
        fareTable.put(key("Uttara Central","Uttara North"), 20); // symmetric entry
        fareTable.put(key("Uttara Central","Uttara South"), 20); // fare sample
        fareTable.put(key("Uttara South","Uttara Central"), 20); // symmetric entry
        fareTable.put(key("Uttara South","Pallabi"), 20); // fare sample
        fareTable.put(key("Pallabi","Uttara South"), 20); // symmetric entry
        fareTable.put(key("Pallabi","Mirpur 11"), 20); // fare sample
        fareTable.put(key("Mirpur 11","Pallabi"), 20); // symmetric entry
        fareTable.put(key("Mirpur 11","Mirpur 10"), 20); // fare sample
        fareTable.put(key("Mirpur 10","Mirpur 11"), 20); // symmetric entry
        fareTable.put(key("Mirpur 10","Kazipara"), 20); // fare sample
        fareTable.put(key("Kazipara","Mirpur 10"), 20); // symmetric entry
        fareTable.put(key("Kazipara","Shewrapara"), 20); // fare sample
        fareTable.put(key("Shewrapara","Kazipara"), 20); // symmetric entry
        fareTable.put(key("Shewrapara","Agargaon"), 20); // fare sample
        fareTable.put(key("Agargaon","Shewrapara"), 20); // symmetric entry
        fareTable.put(key("Agargaon","Bijoy Shoroni"), 20); // sample fare
        fareTable.put(key("Bijoy Shoroni","Agargaon"), 20); // symmetric entry
        fareTable.put(key("Bijoy Shoroni","Farmgate"), 20); // sample long route fare
        fareTable.put(key("Farmgate","Bijoy Shoroni"), 20); // symmetric entry
        fareTable.put(key("Farmgate","Karwan Bazar"), 20); // sample fare
        fareTable.put(key("Karwan Bazar","Farmgate"), 20); // symmetric entry
        fareTable.put(key("Karwan Bazar","Shahbagh"), 20); // fare sample
        fareTable.put(key("Shahbagh","Karwan Bazar"), 20); // symmetric entry
        fareTable.put(key("Shahbagh","Dhaka University"), 20); // fare sample
        fareTable.put(key("Dhaka University","Shahbagh"), 20); // symmetric entry
        fareTable.put(key("Dhaka University","Bangladesh Secretariat"), 20); // fare sample
        fareTable.put(key("Bangladesh Secretariat","Dhaka University"), 20); // symmetric entry
        fareTable.put(key("Bangladesh Secretariat","Motijheel"), 20); // fare sample
        fareTable.put(key("Motijheel","Bangladesh Secretariat"), 20); // symmetric entry

        // Uttara North to others
        fareTable.put(key("Uttara North","Uttara South"), 20); // fare sample
        fareTable.put(key("Uttara South","Uttara North"), 20); // symmetric entry
        fareTable.put(key("Uttara North","Pallabi"), 30); // fare sample
        fareTable.put(key("Pallabi","Uttara North"), 30); // symmetric entry
        fareTable.put(key("Uttara North","Mirpur 11"), 30); // fare sample
        fareTable.put(key("Mirpur 11","Uttara North"), 30); // symmetric entry
        fareTable.put(key("Uttara North","Mirpur 10"), 40); // fare sample
        fareTable.put(key("Mirpur 10","Uttara North"), 40); // symmetric entry
        fareTable.put(key("Uttara North","Kazipara"), 40); // sample fare
        fareTable.put(key("Kazipara","Uttara North"), 40); // symmetric entry
        fareTable.put(key("Uttara North","Shewrapara"), 50); // sample fare
        fareTable.put(key("Shewrapara","Uttara North"), 50); // symmetric entry
        fareTable.put(key("Uttara North","Agargaon"), 60); // sample fare
        fareTable.put(key("Agargaon","Uttara North"), 60); // symmetric entry
        fareTable.put(key("Uttara North","Bijoy Shoroni"), 60); // fare sample
        fareTable.put(key("Bijoy Shoroni","Uttara North"), 60); // symmetric entry
        fareTable.put(key("Uttara North","Farmgate"), 70); // fare sample
        fareTable.put(key("Farmgate","Uttara North"), 70); // symmetric entry
        fareTable.put(key("Uttara North","Karwan Bazar"), 80); // fare sample
        fareTable.put(key("Karwan Bazar","Uttara North"), 80); // symmetric entry
        fareTable.put(key("Uttara North","Shahbagh"), 80); // fare sample
        fareTable.put(key("Shahbagh","Uttara North"), 80); // symmetric entry
        fareTable.put(key("Uttara North","Dhaka University"), 90); // fare sample
        fareTable.put(key("Dhaka University","Uttara North"), 90); // symmetric entry
        fareTable.put(key("Uttara North","Bangladesh Secretariat"), 90); // sample fare
        fareTable.put(key("Bangladesh Secretariat","Uttara North"), 90); // symmetric entry
        fareTable.put(key("Uttara North","Motijheel"), 100); // sample fare
        fareTable.put(key("Motijheel","Uttara North"), 100); // symmetric entry

        // Uttara Central to others
        fareTable.put(key("Uttara Central","Pallabi"), 20); // fare sample
        fareTable.put(key("Pallabi","Uttara Central"), 20); // symmetric entry
        fareTable.put(key("Uttara Central","Mirpur 11"), 30); // fare sample
        fareTable.put(key("Mirpur 11","Uttara Central"), 30); // symmetric entry
        fareTable.put(key("Uttara Central","Mirpur 10"), 30); // fare sample
        fareTable.put(key("Mirpur 10","Uttara Central"), 30); // symmetric entry
        fareTable.put(key("Uttara Central","Kazipara"), 40); // sample fare
        fareTable.put(key("Kazipara","Uttara Central"), 40); // symmetric entry
        fareTable.put(key("Uttara Central","Shewrapara"), 40); // sample fare
        fareTable.put(key("Shewrapara","Uttara Central"), 40); // symmetric entry
        fareTable.put(key("Uttara Central","Agargaon"), 50); // sample fare
        fareTable.put(key("Agargaon","Uttara Central"), 50); // symmetric entry
        fareTable.put(key("Uttara Central","Bijoy Shoroni"), 60); // fare sample
        fareTable.put(key("Bijoy Shoroni","Uttara Central"), 60); // symmetric entry
        fareTable.put(key("Uttara Central","Farmgate"), 60); // fare sample
        fareTable.put(key("Farmgate","Uttara Central"), 60); // symmetric entry
        fareTable.put(key("Uttara Central","Karwan Bazar"), 70); // fare sample
        fareTable.put(key("Karwan Bazar","Uttara Central"), 70); // symmetric entry
        fareTable.put(key("Uttara Central","Shahbagh"), 80); // fare sample
        fareTable.put(key("Shahbagh","Uttara Central"), 80); // symmetric entry
        fareTable.put(key("Uttara Central","Dhaka University"), 80); // fare sample
        fareTable.put(key("Dhaka University","Uttara Central"), 80); // symmetric entry
        fareTable.put(key("Uttara Central","Bangladesh Secretariat"), 90); // sample fare
        fareTable.put(key("Bangladesh Secretariat","Uttara Central"), 90); // symmetric entry
        fareTable.put(key("Uttara Central","Motijheel"), 90); // sample fare
        fareTable.put(key("Motijheel","Uttara Central"), 90); // symmetric entry

        // Uttara South to others
        fareTable.put(key("Uttara South","Mirpur 11"), 20); // fare sample
        fareTable.put(key("Mirpur 11","Uttara South"), 20); // symmetric entry
        fareTable.put(key("Uttara South","Mirpur 10"), 30); // fare sample
        fareTable.put(key("Mirpur 10","Uttara South"), 30); // symmetric entry
        fareTable.put(key("Uttara South","Kazipara"), 30); // sample fare
        fareTable.put(key("Kazipara","Uttara South"), 30); // symmetric entry
        fareTable.put(key("Uttara South","Shewrapara"), 40); // sample fare
        fareTable.put(key("Shewrapara","Uttara South"), 40); // symmetric entry
        fareTable.put(key("Uttara South","Agargaon"), 40); // sample fare
        fareTable.put(key("Agargaon","Uttara South"), 40); // symmetric entry
        fareTable.put(key("Uttara South","Bijoy Shoroni"), 50); // fare sample
        fareTable.put(key("Bijoy Shoroni","Uttara South"), 50); // symmetric entry
        fareTable.put(key("Uttara South","Farmgate"), 60); // fare sample
        fareTable.put(key("Farmgate","Uttara South"), 60); // symmetric entry
        fareTable.put(key("Uttara South","Karwan Bazar"), 60); // fare sample
        fareTable.put(key("Karwan Bazar","Uttara South"), 60); // symmetric entry
        fareTable.put(key("Uttara South","Shahbagh"), 70); // fare sample
        fareTable.put(key("Shahbagh","Uttara South"), 70); // symmetric entry
        fareTable.put(key("Uttara South","Dhaka University"), 70); // fare sample
        fareTable.put(key("Dhaka University","Uttara South"), 70); // symmetric entry
        fareTable.put(key("Uttara South","Bangladesh Secretariat"), 80); // sample fare
        fareTable.put(key("Bangladesh Secretariat","Uttara South"), 80); // symmetric entry
        fareTable.put(key("Uttara South","Motijheel"), 90); // sample fare
        fareTable.put(key("Motijheel","Uttara South"), 90); // symmetric entry

        // Pallabi to others
        fareTable.put(key("Pallabi","Mirpur 10"), 20); // fare sample
        fareTable.put(key("Mirpur 10","Pallabi"), 20); // symmetric entry
        fareTable.put(key("Pallabi","Kazipara"), 20); // sample fare
        fareTable.put(key("Kazipara","Pallabi"), 20); // symmetric entry
        fareTable.put(key("Pallabi","Shewrapara"), 30); // sample fare
        fareTable.put(key("Shewrapara","Pallabi"), 30); // symmetric entry
        fareTable.put(key("Pallabi","Agargaon"), 30); // sample fare
        fareTable.put(key("Agargaon","Pallabi"), 30); // symmetric entry
        fareTable.put(key("Pallabi","Bijoy Shoroni"), 40); // fare sample
        fareTable.put(key("Bijoy Shoroni","Pallabi"), 40); // symmetric entry
        fareTable.put(key("Pallabi","Farmgate"), 50); // fare sample
        fareTable.put(key("Farmgate","Pallabi"), 50); // symmetric entry
        fareTable.put(key("Pallabi","Karwan Bazar"), 50); // fare sample
        fareTable.put(key("Karwan Bazar","Pallabi"), 50); // symmetric entry
        fareTable.put(key("Pallabi","Shahbagh"), 60); // fare sample
        fareTable.put(key("Shahbagh","Pallabi"), 60); // symmetric entry
        fareTable.put(key("Pallabi","Dhaka University"), 60); // fare sample
        fareTable.put(key("Dhaka University","Pallabi"), 60); // symmetric entry
        fareTable.put(key("Pallabi","Bangladesh Secretariat"), 70); // sample fare
        fareTable.put(key("Bangladesh Secretariat","Pallabi"), 70); // symmetric entry
        fareTable.put(key("Pallabi","Motijheel"), 80); // sample fare
        fareTable.put(key("Motijheel","Pallabi"), 80); // symmetric entry

        // Mirpur 11 to others
        fareTable.put(key("Mirpur 11","Kazipara"), 20); // sample fare
        fareTable.put(key("Kazipara","Mirpur 11"), 20); // symmetric entry
        fareTable.put(key("Mirpur 11","Shewrapara"), 20); // sample fare
        fareTable.put(key("Shewrapara","Mirpur 11"), 20); // symmetric entry
        fareTable.put(key("Mirpur 11","Agargaon"), 30); // sample fare
        fareTable.put(key("Agargaon","Mirpur 11"), 30); // symmetric entry
        fareTable.put(key("Mirpur 11","Bijoy Shoroni"), 40); // fare sample
        fareTable.put(key("Bijoy Shoroni","Mirpur 11"), 40); // symmetric entry
        fareTable.put(key("Mirpur 11","Farmgate"), 40); // fare sample
        fareTable.put(key("Farmgate","Mirpur 11"), 40); // symmetric entry
        fareTable.put(key("Mirpur 11","Karwan Bazar"), 50); // fare sample
        fareTable.put(key("Karwan Bazar","Mirpur 11"), 50); // symmetric entry
        fareTable.put(key("Mirpur 11","Shahbagh"), 60); // fare sample
        fareTable.put(key("Shahbagh","Mirpur 11"), 60); // symmetric entry
        fareTable.put(key("Mirpur 11","Dhaka University"), 60); // fare sample
        fareTable.put(key("Dhaka University","Mirpur 11"), 60); // symmetric entry
        fareTable.put(key("Mirpur 11","Bangladesh Secretariat"), 70); // sample fare
        fareTable.put(key("Bangladesh Secretariat","Mirpur 11"), 70); // symmetric entry
        fareTable.put(key("Mirpur 11","Motijheel"), 70); // sample fare
        fareTable.put(key("Motijheel","Mirpur 11"), 70); // symmetric entry

        // Mirpur 10 to others
        fareTable.put(key("Mirpur 10","Shewrapara"), 20); // sample fare
        fareTable.put(key("Shewrapara","Mirpur 10"), 20); // symmetric entry
        fareTable.put(key("Mirpur 10","Agargaon"), 20); // sample fare
        fareTable.put(key("Agargaon","Mirpur 10"), 20); // symmetric entry
        fareTable.put(key("Mirpur 10","Bijoy Shoroni"), 30); // fare sample
        fareTable.put(key("Bijoy Shoroni","Mirpur 10"), 30); // symmetric entry
        fareTable.put(key("Mirpur 10","Farmgate"), 40); // fare sample
        fareTable.put(key("Farmgate","Mirpur 10"), 40); // symmetric entry
        fareTable.put(key("Mirpur 10","Karwan Bazar"), 40); // fare sample
        fareTable.put(key("Karwan Bazar","Mirpur 10"), 40); // symmetric entry
        fareTable.put(key("Mirpur 10","Shahbagh"), 50); // fare sample
        fareTable.put(key("Shahbagh","Mirpur 10"), 50); // symmetric entry
        fareTable.put(key("Mirpur 10","Dhaka University"), 50); // fare sample
        fareTable.put(key("Dhaka University","Mirpur 10"), 50); // symmetric entry
        fareTable.put(key("Mirpur 10","Bangladesh Secretariat"), 60); // sample fare
        fareTable.put(key("Bangladesh Secretariat","Mirpur 10"), 60); // symmetric entry
        fareTable.put(key("Mirpur 10","Motijheel"), 60); // sample fare
        fareTable.put(key("Motijheel","Mirpur 10"), 60); // symmetric entry

        // Kazipara to others
        fareTable.put(key("Kazipara","Agargaon"), 20); // sample fare
        fareTable.put(key("Agargaon","Kazipara"), 20); // symmetric entry
        fareTable.put(key("Kazipara","Bijoy Shoroni"), 20); // fare sample
        fareTable.put(key("Bijoy Shoroni","Kazipara"), 20); // symmetric entry
        fareTable.put(key("Kazipara","Farmgate"), 30); // fare sample
        fareTable.put(key("Farmgate","Kazipara"), 30); // symmetric entry
        fareTable.put(key("Kazipara","Karwan Bazar"), 40); // fare sample
        fareTable.put(key("Karwan Bazar","Kazipara"), 40); // symmetric entry
        fareTable.put(key("Kazipara","Shahbagh"), 40); // fare sample
        fareTable.put(key("Shahbagh","Kazipara"), 40); // symmetric entry
        fareTable.put(key("Kazipara","Dhaka University"), 50); // fare sample
        fareTable.put(key("Dhaka University","Kazipara"), 50); // symmetric entry
        fareTable.put(key("Kazipara","Bangladesh Secretariat"), 50); // sample fare
        fareTable.put(key("Bangladesh Secretariat","Kazipara"), 50); // symmetric entry
        fareTable.put(key("Kazipara","Motijheel"), 60); // sample fare
        fareTable.put(key("Motijheel","Kazipara"), 60); // symmetric entry

        // Shewrapara to others
        fareTable.put(key("Shewrapara","Bijoy Shoroni"), 20); // fare sample
        fareTable.put(key("Bijoy Shoroni","Shewrapara"), 20); // symmetric entry
        fareTable.put(key("Shewrapara","Farmgate"), 20); // fare sample
        fareTable.put(key("Farmgate","Shewrapara"), 20); // symmetric entry
        fareTable.put(key("Shewrapara","Karwan Bazar"), 30); // fare sample
        fareTable.put(key("Karwan Bazar","Shewrapara"), 30); // symmetric entry
        fareTable.put(key("Shewrapara","Shahbagh"), 40); // fare sample
        fareTable.put(key("Shahbagh","Shewrapara"), 40); // symmetric entry
        fareTable.put(key("Shewrapara","Dhaka University"), 40); // fare sample
        fareTable.put(key("Dhaka University","Shewrapara"), 40); // symmetric entry
        fareTable.put(key("Shewrapara","Bangladesh Secretariat"), 50); // sample fare
        fareTable.put(key("Bangladesh Secretariat","Shewrapara"), 50); // symmetric entry
        fareTable.put(key("Shewrapara","Motijheel"), 50); // sample fare
        fareTable.put(key("Motijheel","Shewrapara"), 50); // symmetric entry

        // Agargaon to others
        fareTable.put(key("Agargaon","Farmgate"), 20); // fare sample
        fareTable.put(key("Farmgate","Agargaon"), 20); // symmetric entry
        fareTable.put(key("Agargaon","Karwan Bazar"), 20); // fare sample
        fareTable.put(key("Karwan Bazar","Agargaon"), 20); // symmetric entry
        fareTable.put(key("Agargaon","Shahbagh"), 30); // fare sample
        fareTable.put(key("Shahbagh","Agargaon"), 30); // symmetric entry
        fareTable.put(key("Agargaon","Dhaka University"), 30); // fare sample
        fareTable.put(key("Dhaka University","Agargaon"), 30); // symmetric entry
        fareTable.put(key("Agargaon","Bangladesh Secretariat"), 40); // sample fare
        fareTable.put(key("Bangladesh Secretariat","Agargaon"), 40); // symmetric entry
        fareTable.put(key("Agargaon","Motijheel"), 50); // sample fare
        fareTable.put(key("Motijheel","Agargaon"), 50); // symmetric entry

        // Bijoy Shoroni to others
        fareTable.put(key("Bijoy Shoroni","Karwan Bazar"), 20); // fare sample
        fareTable.put(key("Karwan Bazar","Bijoy Shoroni"), 20); // symmetric entry
        fareTable.put(key("Bijoy Shoroni","Shahbagh"), 20); // fare sample
        fareTable.put(key("Shahbagh","Bijoy Shoroni"), 20); // symmetric entry
        fareTable.put(key("Bijoy Shoroni","Dhaka University"), 30); // fare sample
        fareTable.put(key("Dhaka University","Bijoy Shoroni"), 30); // symmetric entry
        fareTable.put(key("Bijoy Shoroni","Bangladesh Secretariat"), 40); // sample fare
        fareTable.put(key("Bangladesh Secretariat","Bijoy Shoroni"), 40); // symmetric entry
        fareTable.put(key("Bijoy Shoroni","Motijheel"), 40); // sample fare
        fareTable.put(key("Motijheel","Bijoy Shoroni"), 40); // symmetric entry

        // Farmgate to others
        fareTable.put(key("Farmgate","Shahbagh"), 20); // fare sample
        fareTable.put(key("Shahbagh","Farmgate"), 20); // symmetric entry
        fareTable.put(key("Farmgate","Dhaka University"), 20); // fare sample
        fareTable.put(key("Dhaka University","Farmgate"), 20); // symmetric entry
        fareTable.put(key("Farmgate","Bangladesh Secretariat"), 30); // sample fare
        fareTable.put(key("Bangladesh Secretariat","Farmgate"), 30); // symmetric entry
        fareTable.put(key("Farmgate","Motijheel"), 30); // sample fare
        fareTable.put(key("Motijheel","Farmgate"), 30); // symmetric entry

        // Karwan Bazar to others
        fareTable.put(key("Karwan Bazar","Dhaka University"), 20); // fare sample
        fareTable.put(key("Dhaka University","Karwan Bazar"), 20); // symmetric entry
        fareTable.put(key("Karwan Bazar","Bangladesh Secretariat"), 20); // sample fare
        fareTable.put(key("Bangladesh Secretariat","Karwan Bazar"), 20); // symmetric entry
        fareTable.put(key("Karwan Bazar","Motijheel"), 30); // sample fare
        fareTable.put(key("Motijheel","Karwan Bazar"), 30); // symmetric entry

        // Shahbagh to others
        fareTable.put(key("Shahbagh","Bangladesh Secretariat"), 20); // sample fare
        fareTable.put(key("Bangladesh Secretariat","Shahbagh"), 20); // symmetric entry
        fareTable.put(key("Shahbagh","Motijheel"), 20); // sample fare
        fareTable.put(key("Motijheel","Shahbagh"), 20); // symmetric entry

        // Dhaka University to others
        fareTable.put(key("Dhaka University","Motijheel"), 20); // sample fare
        fareTable.put(key("Motijheel","Dhaka University"), 20); // symmetric entry
    }

    // Helper to generate consistent keys for the fare table
    private static String key(String a, String b) { // method signature
        return a + "|" + b; // return concatenated key
    }

    // Public method to get fare between two stations; returns -1 if unknown
    public static int getFare(String from, String to) { // method signature
        Integer f = fareTable.get(key(from, to)); // lookup fare
        return f == null ? -1 : f; // return -1 if null else fare value
    }
}
