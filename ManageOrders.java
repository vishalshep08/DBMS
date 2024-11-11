import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManageOrders extends JFrame {
    private JTable ordersTable;
    private DefaultTableModel tableModel;
    private JTextField customerNameField;
    private JTextField menuIdField;
    private JButton addButton;
    private JButton deleteButton;

    public ManageOrders() {
        // Set frame properties
        setTitle("Manage Orders");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); // Center the window on the screen
        setResizable(false);

        // Set layout manager
        setLayout(new BorderLayout());

        // Create table model and JTable
        tableModel = new DefaultTableModel(new String[]{"Customer Name", "Menu ID"}, 0);
        ordersTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(ordersTable);
        add(scrollPane, BorderLayout.CENTER);

        // Create input fields for order details
        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        inputPanel.add(new JLabel("Customer Name:"));
        customerNameField = new JTextField();
        inputPanel.add(customerNameField);

        inputPanel.add(new JLabel("Menu ID:"));
        menuIdField = new JTextField();
        inputPanel.add(menuIdField);

        add(inputPanel, BorderLayout.NORTH);

        // Create buttons
        addButton = new JButton("Add Order");
        deleteButton = new JButton("Delete Order");

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Load order records when the frame is initialized
        loadOrders();

        // Add action listeners for the buttons
        addButton.addActionListener(e -> addOrder());
        deleteButton.addActionListener(e -> deleteOrder());

        // Table row selection listener to fill input fields
        ordersTable.getSelectionModel().addListSelectionListener(event -> {
            int selectedRow = ordersTable.getSelectedRow();
            if (selectedRow != -1) {
                customerNameField.setText(tableModel.getValueAt(selectedRow, 0).toString());
                menuIdField.setText(tableModel.getValueAt(selectedRow, 1).toString());
            }
        });
    }

    private void loadOrders() {
        // Clear existing rows
        tableModel.setRowCount(0);

        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/restaurant"; // Replace with your database URL
        String user = "root"; // Replace with your database username
        String password = "Vishal@4983"; // Replace with your database password

        String sql = "SELECT Cust_name, Menu_ID FROM Orders";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            // Populate the table model with data from the result set
            while (rs.next()) {
                String customerName = rs.getString("Cust_name");
                int menuId = rs.getInt("Menu_ID");
                tableModel.addRow(new Object[]{customerName, menuId});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error loading orders: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addOrder() {
        // Gather data from input fields
        String customerName = customerNameField.getText();
        int menuId = Integer.parseInt(menuIdField.getText());

        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/restaurant"; // Replace with your database URL
        String user = "root"; // Replace with your database username
        String password = "Vishal@4983"; // Replace with your database password

        String sql = "INSERT INTO Orders (Cust_name, Menu_ID) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1 , customerName);
            pstmt.setInt(2, menuId);
            pstmt.executeUpdate();

            // Clear input fields
            customerNameField.setText("");
            menuIdField.setText("");

            // Reload orders
            loadOrders();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error adding order: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteOrder() {
        // Get the selected row
        int selectedRow = ordersTable.getSelectedRow();
        if (selectedRow != -1) {
            // Gather data from the selected row
            String customerName = tableModel.getValueAt(selectedRow, 0).toString();
            int menuId = Integer.parseInt(tableModel.getValueAt(selectedRow, 1).toString());

            // Database connection parameters
            String url = "jdbc:mysql://localhost:3306/restaurant"; // Replace with your database URL
            String user = "root"; // Replace with your database username
            String password = "Vishal@4983"; // Replace with your database password

            String sql = "DELETE FROM Orders WHERE Cust_name = ? AND Menu_ID = ?";

            try (Connection conn = DriverManager.getConnection(url, user, password);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, customerName);
                pstmt.setInt(2, menuId);
                pstmt.executeUpdate();

                // Remove the selected row from the table model
                tableModel.removeRow(selectedRow);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this,
                        "Error deleting order: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ManageOrders manageOrders = new ManageOrders();
            manageOrders.setVisible(true);
        });
    }
}