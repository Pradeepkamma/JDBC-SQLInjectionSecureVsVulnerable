package jdbcproject.crud.resultsettypes.vulnerable
import java.sql.*;
import java.util.Scanner;

public class SecureLoginDemo {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";  // Change to your database
        String user = "root";  // Change to your DB username
        String password = "password";  // Change to your DB password
        
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Scanner scanner = new Scanner(System.in)) {

            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            
            System.out.print("Enter password: ");
            String userPassword = scanner.nextLine();
            
            // Use ' OR '1'='1 -- 
            // admin'-- 
            

            // **Secure Query using PreparedStatement**
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            System.out.println("SQL: "+sql);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, userPassword);

            System.out.println("Executing secure query...");
            
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("Login successful! Welcome, " + rs.getString("username"));
            } else {
                System.out.println("Login failed! Invalid credentials.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
