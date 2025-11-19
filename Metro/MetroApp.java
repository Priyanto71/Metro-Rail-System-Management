package Metro;

// MetroApp.java - Main GUI (depends on User, UserDatabase, MRTService, Schedule, BackgroundImagePanel)

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class MetroApp extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;

    private UserDatabase db;
    private User currentUser;
    private MRTService mrtService;

    public MetroApp() {
        super("Metro Rail System Management");

        db = new UserDatabase();
        mrtService = new MRTService();

        

        // Use BackgroundImagePanel as content pane to show background image across whole frame
        try {
            mainPanel = new BackgroundImagePanel(new CardLayout(), "Metro/background.jpg");
        } catch (Exception ex) {
            // Fallback to plain panel if background panel fails
            mainPanel = new JPanel(new CardLayout());
        }
        cardLayout = (CardLayout) mainPanel.getLayout(); //layout manager from mainPanel

        initUI(); //to build ui

        setContentPane(mainPanel); //window to main panel
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setSize(780, 520);
        setLocationRelativeTo(null); //center the window
    }

    // Make any component's text white
    private void makeWhite(Component c) {
        if (c == null) return;
        c.setForeground(Color.WHITE);
        // For buttons, also make background transparent for better look on image
        if (c instanceof JButton) {
            // keep default L&F button background but ensure text is white
            ((JButton) c).setOpaque(false);
            ((JButton) c).setContentAreaFilled(false);
            ((JButton) c).setBorderPainted(true);
        }
    }

    // initialize UI and cards
    private void initUI() {
        JPanel login = createLoginPanel();
        JPanel app = createAppPanel();

        mainPanel.add(login, "LOGIN");
        mainPanel.add(app, "APP");

        cardLayout.show(mainPanel, "LOGIN"); // show login first
    }

    // Login panel
    private JPanel createLoginPanel() {
        JPanel panel;
        try {
            panel = new BackgroundImagePanel(new GridBagLayout(), "Metro/background.jpg");
        } catch (Exception ex) {
            panel = new JPanel(new GridBagLayout());
            panel.setBackground(Color.DARK_GRAY);
        }

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);

        // Title
        JLabel lblTitle = new JLabel("Dhaka Metro Rail - Login / Sign Up");
        lblTitle.setFont(lblTitle.getFont().deriveFont(18f));
        makeWhite(lblTitle);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(lblTitle, gbc);

        gbc.gridwidth = 1;

        // Username Label
        JLabel lblUser = new JLabel("Username:");
        makeWhite(lblUser);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lblUser, gbc);

        // Username Field
        JTextField tfUser = new JTextField(15);
        makeWhite(tfUser);
        tfUser.setOpaque(false);
        tfUser.setCaretColor(Color.WHITE);
        tfUser.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(tfUser, gbc);

        // Password Label
        JLabel lblPass = new JLabel("Password:");
        makeWhite(lblPass);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblPass, gbc);

        // Password Field
        JPasswordField pf = new JPasswordField(15);
        makeWhite(pf);
        pf.setOpaque(false);
        pf.setCaretColor(Color.WHITE);
        pf.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(pf, gbc);

        // Login Button
        JButton btnLogin = new JButton("Login");
        makeWhite(btnLogin);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(btnLogin, gbc);

        // Signup Button
        JButton btnSign = new JButton("Sign Up");
        makeWhite(btnSign);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(btnSign, gbc);

        // Login Action
        btnLogin.addActionListener(e -> {
            String u = tfUser.getText().trim();
            String p = new String(pf.getPassword()).trim();
            if (u.isEmpty() || p.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter username and password.");
                return;
            }
            User user = db.authenticate(u, p);
            if (user != null) {
                currentUser = user;
                setTitle("Dhaka Metro Rail System Management - " + currentUser.getUsername());
                cardLayout.show(mainPanel, "APP");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid password or user not found.");
            }
        });

        // Sign Up Action
        btnSign.addActionListener(e -> {
            String u = tfUser.getText().trim();
            String p = new String(pf.getPassword()).trim();
            if (u.isEmpty() || p.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter username and password.");
                return;
            }
            if (db.exists(u)) {
                JOptionPane.showMessageDialog(this, "Username already exists. Choose another.");
                return;
            }
            User nu = new User(u, p);
            db.addUser(nu);
            db.save();
            JOptionPane.showMessageDialog(this, "Sign up successful. You can log in now.");
        });

        return panel;
    }

    // Main app panel (tabs)
    private JPanel createAppPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JTabbedPane tabs = new JTabbedPane();

        String[] stations = {"Uttara North", "Uttara Central", "Uttara South", "Pallabi", "Mirpur 11", "Mirpur 10", "Kazipara", "Shewrapara", "Agargaon", "Bijoy Shoroni", "Farmgate", "Karwan Bazar", "Shahbagh", "Dhaka University", "Bangladesh Secretariat", "Motijheel"};

        // --- Schedule tab ---
        JPanel schedulePanel = new JPanel(new BorderLayout());
        JPanel top = new JPanel();
        top.add(new JLabel("Station:"));
        JComboBox<String> cbStations = new JComboBox<>(stations);
        top.add(cbStations);
        JButton btnShow = new JButton("Show Schedule");
        top.add(btnShow);
        schedulePanel.add(top, BorderLayout.NORTH);

        JTextArea taSchedule = new JTextArea(12, 40);
        taSchedule.setEditable(false);
        schedulePanel.add(new JScrollPane(taSchedule), BorderLayout.CENTER);

        // Multithreaded schedule loading when button clicked
        btnShow.addActionListener(e -> {
            String st = (String) cbStations.getSelectedItem();
            taSchedule.setText("Loading schedule...\n");

            // load schedule on a background thread to prevent UI freeze
            new Thread(() -> {
                try {
                    Thread.sleep(700); // simulate short load delay
                } catch (InterruptedException ex) { /* ignore */ }

                // update UI on EDT
                SwingUtilities.invokeLater(() -> {
                    taSchedule.setText("");
                    taSchedule.append("Trains towards Uttara North:\n");
                    for (String t : Schedule.getTowardsUttara(st)) {
                        taSchedule.append("• " + t + "\n");
                    }
                    taSchedule.append("\nTrains towards Motijheel:\n");
                    for (String t : Schedule.getTowardsMotijheel(st)) {
                        taSchedule.append("• " + t + "\n");
                    }
                });
            }).start();
        });

        tabs.addTab("Schedule", schedulePanel);

        // --- MRT Pass / Balance tab ---
        JPanel passPanel = new JPanel(new GridBagLayout());
        GridBagConstraints g2 = new GridBagConstraints();
        g2.insets = new Insets(6, 6, 6, 6);

        g2.gridx = 0;
        g2.gridy = 0;
        passPanel.add(new JLabel("MRT/Rapid Pass No:"), g2);

        JTextField tfPass = new JTextField(15);
        g2.gridx = 1;
        g2.gridy = 0;
        passPanel.add(tfPass, g2);

        JButton btnSavePass = new JButton("Save Pass");
        g2.gridx = 0;
        g2.gridy = 1;
        passPanel.add(btnSavePass, g2);

        JLabel lblBalance = new JLabel("Balance: -");
        g2.gridx = 1;
        g2.gridy = 1;
        passPanel.add(lblBalance, g2);

        JButton btnRecharge = new JButton("Recharge");
        g2.gridx = 0;
        g2.gridy = 2;
        passPanel.add(btnRecharge, g2);

        JButton btnHistory = new JButton("Journey History");
        g2.gridx = 1;
        g2.gridy = 2;
        passPanel.add(btnHistory, g2);

        JButton btnRefresh = new JButton("Refresh");
        g2.gridx = 1;
        g2.gridy = 3;
        passPanel.add(btnRefresh, g2);

        // Save pass
        btnSavePass.addActionListener(e -> {
            if (currentUser == null) {
                JOptionPane.showMessageDialog(this, "Log in first.");
                return;
            }
            String passNo = tfPass.getText().trim();
            if (passNo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter pass number.");
                return;
            }
            currentUser.setMrtNumber(passNo);
            db.save();
            lblBalance.setText("Balance: " + String.format("%.2f BDT", currentUser.getBalance()));
            JOptionPane.showMessageDialog(this, "MRT / Rapid Pass saved.");
        });

        // Refresh
        btnRefresh.addActionListener(e -> {
            if (currentUser == null) {
                JOptionPane.showMessageDialog(this, "Please login first.");
                return;
            }

            // Reload user from DB
            User updated = db.getUser(currentUser.getUsername());
            if (updated != null) {
                currentUser = updated;
            }

            // Refresh fields
            tfPass.setText(currentUser.getMrtNumber() == null ? "" : currentUser.getMrtNumber());
            lblBalance.setText("Balance: " + String.format("%.2f BDT", currentUser.getBalance()));

            JOptionPane.showMessageDialog(this, "Data refreshed!");
        });

        // Recharge
        btnRecharge.addActionListener(e -> {
            if (currentUser == null) {
                JOptionPane.showMessageDialog(this, "Log in first.");
                return;
            }
            String inp = JOptionPane.showInputDialog(this, "Enter recharge amount (BDT):");
            if (inp == null) return;
            try {
                double amt = Double.parseDouble(inp);
                if (amt <= 0) {
                    JOptionPane.showMessageDialog(this, "Enter positive amount.");
                    return;
                }
                currentUser.addBalance(amt);
                db.save();
                lblBalance.setText("Balance: " + String.format("%.2f BDT", currentUser.getBalance()));
                JOptionPane.showMessageDialog(this, "Recharged successfully.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number.");
            }
        });

        // History
        btnHistory.addActionListener(e -> {
            if (currentUser == null) {
                JOptionPane.showMessageDialog(this, "Log in first.");
                return;
            }
            StringBuilder sb = new StringBuilder();
            for (String s : currentUser.getJourneys()) sb.append(s).append("\n");
            if (sb.length() == 0) sb.append("No journeys yet.");
            JOptionPane.showMessageDialog(this, sb.toString(), "Journey History", JOptionPane.INFORMATION_MESSAGE);
        });

        tabs.addTab("MRT Pass", passPanel);

        // --- Travel tab ---
        JPanel travelPanel = new JPanel(new GridBagLayout());
        GridBagConstraints g3 = new GridBagConstraints();
        g3.insets = new Insets(6, 6, 6, 6);

        g3.gridx = 0;
        g3.gridy = 0;
        travelPanel.add(new JLabel("From:"), g3);
        JComboBox<String> cbFrom = new JComboBox<>(stations);
        g3.gridx = 1;
        g3.gridy = 0;
        travelPanel.add(cbFrom, g3);

        g3.gridx = 0;
        g3.gridy = 1;
        travelPanel.add(new JLabel("To:"), g3);
        JComboBox<String> cbTo = new JComboBox<>(stations);
        g3.gridx = 1;
        g3.gridy = 1;
        travelPanel.add(cbTo, g3);

        JButton btnCalc = new JButton("Calculate Fare");
        g3.gridx = 0;
        g3.gridy = 2;
        travelPanel.add(btnCalc, g3);

        JLabel lblFare = new JLabel("Fare: -");
        g3.gridx = 1;
        g3.gridy = 2;
        travelPanel.add(lblFare, g3);

        JButton btnStartTravel = new JButton("Start Travel");
        g3.gridx = 0;
        g3.gridy = 3;
        travelPanel.add(btnStartTravel, g3);

        tabs.addTab("Travel", travelPanel);

        // Calculate fare action (demonstrates method overloading: calculateFare(user,from,to))
        btnCalc.addActionListener(e -> {
            if (currentUser == null) {
                JOptionPane.showMessageDialog(this, "Log in first.");
                return;
            }
            String from = Objects.toString(cbFrom.getSelectedItem(), "");
            String to = Objects.toString(cbTo.getSelectedItem(), "");
            if (from.equals(to)) {
                JOptionPane.showMessageDialog(this, "Choose different stations.");
                return;
            }

            double fareForUser = mrtService.calculateFare(currentUser, from, to); // overloaded
            double fareBase = mrtService.calculateFare(from, to); // base version

            String info = String.format("Base fare: %.2f BDT\nYour fare (after discount if any): %.2f BDT",
                    fareBase, fareForUser);
            lblFare.setText("Fare: " + String.format("%.2f BDT", fareForUser));
            JOptionPane.showMessageDialog(this, info);
        });

        // Start travel: use MRTService.travel which deducts balance and adds journey
        btnStartTravel.addActionListener(e -> {
            if (currentUser == null) {
                JOptionPane.showMessageDialog(this, "Log in first.");
                return;
            }
            String from = Objects.toString(cbFrom.getSelectedItem(), "");
            String to = Objects.toString(cbTo.getSelectedItem(), "");
            if (from.equals(to)) {
                JOptionPane.showMessageDialog(this, "Choose different stations.");
                return;
            }

            double fareForUser = mrtService.calculateFare(currentUser, from, to);
            if (currentUser.getBalance() < fareForUser) {
                JOptionPane.showMessageDialog(this, "Insufficient balance. Please recharge.");
                return;
            }

            boolean ok = mrtService.travel(currentUser, from, to);
            if (ok) {
                db.save();
                JOptionPane.showMessageDialog(this, "Travel complete! Fare: " + String.format("%.2f BDT", fareForUser));
                if (currentUser.getBalance() < 20) {
                    JOptionPane.showMessageDialog(this, "Low balance (" + String.format("%.2f", currentUser.getBalance()) + " BDT). Please recharge.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Travel failed (deduction error).");
            }
        });

        // Bottom logout button
        JButton btnLogout = new JButton("Logout");
        btnLogout.addActionListener(e -> {
            currentUser = null;
            setTitle("Metro Rail System Management");
            cardLayout.show(mainPanel, "LOGIN");
        });

        panel.add(tabs, BorderLayout.CENTER);
        panel.add(btnLogout, BorderLayout.SOUTH);

        return panel;
    }
}
