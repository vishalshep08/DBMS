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

public class ManageWaiters extends JFrame {
    private JTable waitersTable;
    private DefaultTableModel tableModel;
    private JTextField waiterIdField;
    private JTextField waiterNameField;
    private JTextField salaryField;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;

    public ManageWaiters() {
        // Set frame properties
        setTitle("Manage Waiters");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); // Center the window on the screen
        setResizable(false);

        // Set layout manager
        setLayout(new BorderLayout());

        // Create table model and JTable
        tableModel = new DefaultTableModel(new String[]{"Waiter ID", "Waiter Name", "Salary"}, 0);
        waitersTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(waitersTable);
        add(scrollPane, BorderLayout.CENTER);

        // Create input fields for waiter details
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Waiter ID:"));
        waiterIdField = new JTextField();
        inputPanel.add(waiterIdField);

        inputPanel.add(new JLabel("Waiter Name:"));
        waiterNameField = new JTextField();
        inputPanel.add(waiterNameField);

        inputPanel.add(new JLabel("Salary:"));
        salaryField = new JTextField();
        inputPanel.add(salaryField);

        add(inputPanel, BorderLayout.NORTH);

        // Create buttons
        addButton = new JButton("Add Waiter");
        updateButton = new JButton("Update Waiter");
        deleteButton = new JButton("Delete Waiter");

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Load waiter records when the frame is initialized
        loadWaiters();

        // Add action listeners for the buttons
        addButton.addActionListener(e -> addWaiter());
        updateButton.addActionListener(e -> updateWaiter());
        deleteButton.addActionListener(e -> deleteWaiter());

        // Table row selection listener to fill input fields
        waitersTable.getSelectionModel().addListSelectionListener(event -> {
            int selectedRow = waitersTable.getSelectedRow();
            if (selectedRow != -1) {
                waiterIdField.setText(tableModel.getValueAt(selectedRow, 0).toString());
                waiterNameField.setText(tableModel.getValueAt(selectedRow, 1).toString());
                salaryField.setText(tableModel.getValueAt(selectedRow, 2).toString());
            }
        });
    }

    private void loadWaiters() {
        // Clear existing rows
        tableModel.setRowCount(0);

        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/restaurant"; // Replace with your database URL
        String user = "root"; // Replace with your database username
        String password = "Vishal@4983"; // Replace with your database password

        String sql = "SELECT Waiter_ID, Waiter_Name, Salary FROM Waiter";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            // Populate the table model with data from the result set
            while (rs.next()) {
                int waiterId = rs.getInt("Waiter_ID");
                String waiterName = rs.getString("Waiter_Name");
                int salary = rs.getInt("Salary");
                tableModel.addRow(new Object[]{waiterId, waiterName, salary});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error loading waiters: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addWaiter() {
        // Gather data from input fields
        int waiterId = Integer.parseInt(waiterIdField.getText());
        String waiterName = waiterNameField.getText();
        int salary = Integer.parseInt(salaryField.getText());

        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/restaurant"; // Replace with your database URL
        String user = "root"; // Replace with your database username
        String password = "Vishal@4983"; // Replace with your database password

        String sql = "INSERT INTO Waiter (Waiter_ID, Waiter_Name, Salary) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, waiterId);
            pstmt.setString(2, waiterName);
            pstmt.setInt(3, salary);
            pstmt.executeUpdate();

            // Clear input fields
            waiterIdField.setText("");
            waiterNameField.setText("");
            salaryField.setText("");

            // Reload waiters
            loadWaiters();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error adding waiter: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateWaiter() {
        // Gather data from input fields
        int waiterId = Integer.parseInt(waiterIdField.getText());
        String waiterName = waiterNameField.getText();
        int salary = Integer.parseInt(salaryField.getText());

        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/restaurant"; // Replace with your database URL
        String user = "root"; // Replace with your database username
        String password = "Vishal@4983"; // Replace with your database password

        String sql = "UPDATE Waiter SET Waiter_Name = ?, Salary = ? WHERE Waiter_ID = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, waiterName);
            pstmt.setInt(2, salary);
            pstmt.setInt(3, waiterId);
            pstmt.executeUpdate();

            // Clear input fields
            waiterIdField.setText("");
            waiterNameField.setText("");
            salaryField.setText("");

            // Reload waiters
            loadWaiters();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error updating waiter: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteWaiter() {
        // Gather data from input fields
        int waiterId = Integer.parseInt(waiterIdField.getText());

        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/restaurant"; // Replace with your database URL
        String user = "root"; // Replace with your database username
        String password = "Vishal@4983"; // Replace with your database password

        String sql = "DELETE FROM Waiter WHERE Waiter_ID = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, waiterId);
            pstmt.executeUpdate();

            // Clear input fields
            waiterIdField.setText("");
            waiterNameField.setText("");
            salaryField.setText("");

            // Reload waiters
            loadWaiters();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error deleting waiter: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}