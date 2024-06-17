import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() {
        Connection connection = null;

        String jdbcUrl = "jdbc:mysql://localhost:3306/societysecurity";  // Replace with your database URL
        String username = "root";  // Replace with your database username
        String password = "sonu9939@";  // Replace with your database password

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  // Load the MySQL JDBC driver
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Database connection successful!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Database connection failed.");
        }

        return connection;
    }
}
