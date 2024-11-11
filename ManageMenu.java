import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManageMenu extends JFrame {
    private JTable menuTable;
    private DefaultTableModel tableModel;
    private JTextField menuIdField;
    private JTextField menuNameField;
    private JTextField priceField;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;

    public ManageMenu() {
        // Set frame properties
        setTitle("Manage Menu");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); // Center the window on the screen
        setResizable(false);

        // Set layout manager
        setLayout(new BorderLayout());

        // Create table model and JTable
        tableModel = new DefaultTableModel(new String[]{"Menu ID", "Menu Name", "Price"}, 0);
        menuTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(menuTable);
        add(scrollPane, BorderLayout.CENTER);

        // Create input fields for menu item details
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Menu ID:"));
        menuIdField = new JTextField();
        inputPanel.add(menuIdField);

        inputPanel.add(new JLabel("Menu Name:"));
        menuNameField = new JTextField();
        inputPanel.add(menuNameField);

        inputPanel.add(new JLabel("Price:"));
        priceField = new JTextField();
        inputPanel.add(priceField);

        add(inputPanel, BorderLayout.NORTH);

        // Create buttons
        addButton = new JButton("Add Menu Item");
        updateButton = new JButton("Update Menu Item");
        deleteButton = new JButton("Delete Menu Item");

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Load menu items when the frame is initialized
        loadMenuItems();

        // Add action listeners for the buttons
        addButton.addActionListener(e -> addMenuItem());
        updateButton.addActionListener(e -> updateMenuItem());
        deleteButton.addActionListener(e -> deleteMenuItem());

        // Table row selection listener to fill input fields
        menuTable.getSelectionModel().addListSelectionListener(event -> {
            int selectedRow = menuTable.getSelectedRow();
            if (selectedRow != -1) {
                menuIdField.setText(tableModel.getValueAt(selectedRow, 0).toString());
                menuNameField.setText(tableModel.getValueAt(selectedRow, 1).toString());
                priceField.setText(tableModel.getValueAt(selectedRow, 2).toString());
            }
        });
    }

    private void loadMenuItems() {
        // Clear existing rows
        tableModel.setRowCount(0);

        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/restaurant"; // Replace with your database URL
        String user = "root"; // Replace with your database username
        String password = "Vishal@4983"; // Replace with your database password

        String sql = "SELECT Menu_ID, Menu_Name, Price FROM Menu";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            // Populate the table model with data from the result set
            while (rs.next()) {
                int menuId = rs.getInt("Menu_ID");
                String menuName = rs.getString("Menu_Name");
                int price = rs.getInt("Price");
                tableModel.addRow(new Object[]{menuId, menuName, price});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error loading menu items: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addMenuItem() {
        // Gather data from input fields
        int menuId = Integer.parseInt(menuIdField.getText());
        String menuName = menuNameField.getText();
        int price = Integer.parseInt(priceField.getText());

        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/restaurant"; // Replace with your database URL
        String user = "root"; // Replace with your database username
        String password = "Vishal@4983"; // Replace with your database password

        String sql = "INSERT INTO Menu (Menu_ID, Menu_Name, Price) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, menuId);
            pstmt.setString(2, menuName);
            pstmt.setInt(3, price);
            pstmt.executeUpdate();

            // Clear input fields
            menuIdField.setText("");
            menuNameField.setText("");
            priceField.setText("");

            // Reload menu items
            loadMenuItems();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error adding menu item: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateMenuItem() {
        // Gather data from input fields
        int menuId = Integer.parseInt(menuIdField.getText());
        String menuName = menuNameField.getText();
        int price = Integer.parseInt(priceField.getText());

        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/restaurant"; // Replace with your database URL
        String user = "root"; // Replace with your database username
        String password = "Vishal@4983"; // Replace with your database password

        String sql = "UPDATE Menu SET Menu_Name = ?, Price = ? WHERE Menu_ID = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, menuName);
            pstmt.setInt(2, price);
            pstmt.setInt(3, menuId);
            pstmt.executeUpdate();

            // Clear input fields
            menuIdField.setText("");
            menuNameField.setText("");
            priceField.setText("");

            // Reload menu items
            loadMenuItems();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error updating menu item: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteMenuItem() {
        // Gather data from input fields
        int menuId = Integer.parseInt(menuIdField.getText());

        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/restaurant"; // Replace with your database URL
        String user = "root"; // Replace with your database username
        String password = "Vishal@4983"; // Replace with your database password

        String sql = "DELETE FROM Menu WHERE Menu_ID = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, menuId);
            pstmt.executeUpdate();

            // Clear input fields
            menuIdField.setText("");
            menuNameField.setText("");
            priceField.setText("");

            // Reload menu items
            loadMenuItems();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error deleting menu item: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}