package jdbcproject.crud.resultsettypes.vulnerable;
import java.sql.*;
import java.util.Scanner;

public class SQLInjectionDemo {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";  // Change to your database
        String user = "root";  // Change to your DB username
        String password = "password";  // Change to your DB password
        
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             Scanner scanner = new Scanner(System.in)) {

            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            
            System.out.print("Enter password: ");
            String userPassword = scanner.nextLine();
   
            //USE SPACE AFTER COMMENT
        
            //' OR '1'='1' -- 
            // admin'-- 
            
            
            // **Vulnerable SQL Query using Statement**
            String sql = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + userPassword + "'";

            System.out.println("Executing query: " + sql);  // Shows the generated SQL query
            
            ResultSet rs = stmt.executeQuery(sql);

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
