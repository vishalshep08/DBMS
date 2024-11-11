import java.sql.*;

public class Orders {
    private String custName;
    private int menuId;

    public Orders(String custName, int menuId) {
        this.custName = custName;
        this.menuId = menuId;
    }

    public void addOrder() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant", "root", "Vishal@4983");
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Orders (Cust_name, Menu_ID) VALUES (?, ?)");
            pstmt.setString(1, custName);
            pstmt.setInt(2, menuId);
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error adding order: " + e.getMessage());
        }
    }

    public void viewOrder() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant", "root", "Vishal@4983");
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Orders WHERE Cust_name = ?");
            pstmt.setString(1, custName);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("Customer Name: " + rs.getString("Cust_name"));
                System.out.println("Menu ID: " + rs.getInt("Menu_ID"));
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error viewing order: " + e.getMessage());
        }
    }
}