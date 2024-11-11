import java.sql.*;

public class Customer {
    private String custName;
    private int phoneNo;
    private int pincode;
    private int waiterId;

    public Customer(String custName, int phoneNo, int pincode, int waiterId) {
        this.custName = custName;
        this.phoneNo = phoneNo;
        this.pincode = pincode;
        this.waiterId = waiterId;
    }

    public void addCustomer() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant", "root", "Vishal@4983");
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Customer (Cust_name, Phone_no, Pincode, Waiter_id) VALUES (?, ?, ?, ?)");
            pstmt.setString(1, custName);
            pstmt.setInt(2, phoneNo);
            pstmt.setInt(3, pincode);
            pstmt.setInt(4, waiterId);
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error adding customer: " + e.getMessage());
        }
    }

    public void viewCustomer() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant", "username", "password");
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Customer WHERE Cust_name = ?");
            pstmt.setString(1, custName);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("Customer Name: " + rs.getString("Cust_name"));
                System.out.println("Phone No: " + rs.getInt("Phone_no"));
                System.out.println("Pincode: " + rs.getInt("Pincode"));
                System.out.println("Waiter ID: " + rs.getInt("Waiter_id"));
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error viewing customer: " + e.getMessage());
        }
    }
}