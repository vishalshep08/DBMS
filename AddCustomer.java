import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddCustomer extends JFrame {
    private JTextField nameField;
    private JTextField phoneField;
    private JTextField pincodeField;
    private JTextField waiterIdField;
    private JButton submitButton;

    public AddCustomer() {
        // Set frame properties
        setTitle("Add Customer");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 250);
        setLocationRelativeTo(null); // Center the window on the screen
        setResizable(false);

        // Set layout manager
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding

        // Add name label and text field
        gbc.gridx = 0; gbc.gridy = 0; // First column, first row
        add(new JLabel("Name:"), gbc);
        nameField = new JTextField(15);
        gbc.gridx = 1; // Second column
        add(nameField, gbc);

        // Add phone label and text field
        gbc.gridx = 0; gbc.gridy = 1; // First column, second row
        add(new JLabel("Phone No:"), gbc);
        phoneField = new JTextField(15);
        gbc.gridx = 1; // Second column
        add(phoneField, gbc);

        // Add pincode label and text field
        gbc.gridx = 0; gbc.gridy = 2; // First column, third row
        add(new JLabel("Pincode:"), gbc);
        pincodeField = new JTextField(15);
        gbc.gridx = 1; // Second column
        add(pincodeField, gbc);

        // Add waiter ID label and text field
        gbc.gridx = 0; gbc.gridy = 3; // First column, fourth row
        add(new JLabel("Waiter ID:"), gbc);
        waiterIdField = new JTextField(15);
        gbc.gridx = 1; // Second column
        add(waiterIdField, gbc);

        // Add submit button
        gbc.gridx = 0; gbc.gridy = 4; // First column, fifth row
        submitButton = new JButton("Submit");
        add(submitButton, gbc);

        // Add action listener for the submit button
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle the submission of customer data
                String name = nameField.getText();
                String phone = phoneField.getText();
                String pincode = pincodeField.getText();
                String waiterId = waiterIdField.getText();

                // Insert customer data into the database
                insertCustomer(name, phone, pincode, waiterId);
            }
        });
    }

    private void insertCustomer(String name, String phone, String pincode, String waiterId) {
        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/restaurant"; // Replace with your database URL
        String user = "root"; // Replace with your database username
        String password = "Vishal@4983"; // Replace with your database password

        String sql = "INSERT INTO Customer (Cust_name, Phone_no, Pincode, Waiter_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, Integer.parseInt(phone)); // Assuming phone is an integer
            pstmt.setInt(3, Integer.parseInt(pincode)); // Assuming pincode is an integer
            pstmt.setInt(4, Integer.parseInt(waiterId)); // Assuming waiter ID is an integer

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this,
                        "Customer added successfully!",
                        "Confirmation",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error adding customer: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Please enter valid numbers for Phone No, Pincode, and Waiter ID.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        // Clear the fields after submission
        nameField.setText("");
        phoneField.setText("");
        pincodeField.setText("");
        waiterIdField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AddCustomer addCustomerFrame = new AddCustomer();
            addCustomerFrame.setVisible(true);
        });
    }
}