import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HomePage extends JFrame {
    private JButton addCustomerButton;
    private JButton viewOrdersButton;
    private JButton manageMenuButton;
    private JButton manageWaitersButton;
    private JButton manageOrdersButton; // Button for managing orders
    private JButton adminLoginButton;

    public HomePage() {
        // Set frame properties
        setTitle("Home Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400); // Increased height to accommodate new button
        setLocationRelativeTo(null); // Center the window on the screen
        setResizable(false);

        // Set layout manager
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding

        // Add components
        gbc.gridx = 0; gbc.gridy = 0; // First column, first row
        addCustomerButton = new JButton("Add Customer");
        addCustomerButton.setPreferredSize(new Dimension(150, 30)); // Set button size
        add(addCustomerButton, gbc);

        gbc.gridx = 1; // Second column
        viewOrdersButton = new JButton("View Orders");
        viewOrdersButton.setPreferredSize(new Dimension(150, 30)); // Set button size
        add(viewOrdersButton, gbc);

        gbc.gridx = 0; gbc.gridy = 1; // First column, second row
        manageMenuButton = new JButton("Manage Menu");
        manageMenuButton.setPreferredSize(new Dimension(150, 30)); // Set button size
        add(manageMenuButton, gbc);

        gbc.gridx = 1; // Second column
        manageWaitersButton = new JButton("Manage Waiters");
        manageWaitersButton.setPreferredSize(new Dimension(150, 30)); // Set button size
        add(manageWaitersButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2; // First column, third row
        manageOrdersButton = new JButton("Manage Orders"); // Button for managing orders
        manageOrdersButton.setPreferredSize(new Dimension(150, 30)); // Set button size
        add(manageOrdersButton, gbc);
        

        gbc.gridx = 0; gbc.gridy = 3; // First column, fourth row
        adminLoginButton = new JButton("Admin Login");
        adminLoginButton.setPreferredSize(new Dimension(150, 30)); // Set button size
        add(adminLoginButton, gbc);

        // Add action listeners for the buttons
        addCustomerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open AddCustomer JFrame
                AddCustomer addCustomer = new AddCustomer();
                addCustomer.setVisible(true);
            }
        });

        viewOrdersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open ViewOrders JFrame
                ViewOrders viewOrders = new ViewOrders();
                viewOrders.setVisible(true);
            }
        });

        manageMenuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open ManageMenu JFrame
                ManageMenu manageMenu = new ManageMenu();
                manageMenu.setVisible(true);
            }
        });

        manageWaitersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open ManageWaiters JFrame
                ManageWaiters manageWaiters = new ManageWaiters();
                manageWaiters.setVisible(true);
            }
        });

        // Action listener for Manage Orders button
        manageOrdersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open ManageOrders JFrame
                ManageOrders manageOrders = new ManageOrders();
                manageOrders.setVisible(true);
            }
        });

        adminLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open AdminLogin JFrame
                Login adminLogin = new Login();
                adminLogin.setVisible(true);
            }
        });

        // Customize the look and feel
        UIManager.put("Button.background", Color.BLUE);
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Panel.background", Color.WHITE);

        // Set a background color
        getContentPane().setBackground(Color.WHITE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HomePage homePage = new HomePage();
            homePage.setVisible(true);
        });
    }
}