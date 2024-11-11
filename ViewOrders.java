import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewOrders extends JFrame {
    private JTable ordersTable;
    private DefaultTableModel tableModel;
    private JButton refreshButton;

    public ViewOrders() {
        // Set frame properties
        setTitle("View Orders");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Center the window on the screen
        setResizable(false);

        // Set layout manager
        setLayout(new BorderLayout());

        // Create table model and JTable
        tableModel = new DefaultTableModel(new String[]{"Customer Name", "Menu ID"}, 0);
        ordersTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(ordersTable);
        add(scrollPane, BorderLayout.CENTER);

        // Create and add refresh button
        refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> loadOrders());
        add(refreshButton, BorderLayout.SOUTH);

        // Load orders when the frame is initialized
        loadOrders();
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
                String custName = rs.getString("Cust_name");
                int menuId = rs.getInt("Menu_ID");
                tableModel.addRow(new Object[]{custName, menuId});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error loading orders: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ViewOrders viewOrdersFrame = new ViewOrders();
            viewOrdersFrame.setVisible(true);
        });
    }
}