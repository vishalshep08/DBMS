import java.sql.*;

public class Menu {
    private int menuId;
    private String menuName;
    private int price;

    public Menu(int menuId, String menuName, int price) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.price = price;
    }

    public void addMenu() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant", "root", "Vishal@4983");
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Menu (Menu_ID, Menu_name, Price) VALUES (?, ?, ?)");
            pstmt.setInt(1, menuId);
            pstmt.setString(2, menuName);
            pstmt.setInt(3, price);
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error adding menu: " + e.getMessage());
        }
    }

    public void viewMenu() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant", "root", "Vishal@4983");
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Menu WHERE Menu_ID = ?");
            pstmt.setInt(1, menuId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("Menu ID: " + rs.getInt("Menu_ID"));
                System.out.println("Menu Name: " + rs.getString("Menu_name"));
                System.out.println("Price: " + rs.getInt("Price"));
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error viewing menu: " + e.getMessage());
        }
    }
}