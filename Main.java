// Main.java - launcher

import Metro.MetroApp;

public class Main {
    public static void main(String[] args) {
        // launch Swing UI on the Event Dispatch Thread
        javax.swing.SwingUtilities.invokeLater(() -> {
            MetroApp app = new MetroApp();
            app.setVisible(true);
        });
    }
}
