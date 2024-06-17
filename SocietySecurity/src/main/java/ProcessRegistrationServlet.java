import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProcessRegistrationServlet")
public class ProcessRegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form parameters
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phoneNumber = request.getParameter("phoneNumber");

        String flatIdStr = request.getParameter("flatId");
        String buildingIdStr = request.getParameter("buildingId");

        // Check if flatId and buildingId are not empty
        if (flatIdStr == null || flatIdStr.isEmpty() || buildingIdStr == null || buildingIdStr.isEmpty()) {
            // Handle the case where flatId or buildingId is missing or empty
            response.sendRedirect("supervisor_visitor_error.jsp"); // Modify to your error page
            return;
        }

        int flatId = Integer.parseInt(flatIdStr);
        int buildingId = Integer.parseInt(buildingIdStr);

        // Check if the buildingId and flatId exist in the flat_details table
        if (!checkBuildingFlatExistence(buildingId, flatId)) {
            response.sendRedirect("supervisor_visitor_error.jsp"); // BuildingId and flatId do not exist
            return;
        }

        // Automatically generate the check-in time
        Timestamp checkInTime = new Timestamp(System.currentTimeMillis());

        // Set check-out time to null initially
        Timestamp checkOutTime = null;

        // Generate a random security code (you can adjust the length as needed)
        String securityCode = generateRandomSecurityCode(6);

        // Database connection details (modify these according to your database configuration)
        String jdbcURL = "jdbc:mysql://localhost:3306/societysecurity";
        String dbUser = "root";
        String dbPassword = "sonu9939@";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Create a database connection
            Class.forName("com.mysql.cj.jdbc.Driver"); // Use the modern MySQL driver
            connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            // Insert the registration details into the database
            String insertQuery = "INSERT INTO regular_visitor_details (first_name, last_name, phone_number, security_code, flat_id, building_id, check_in_time, check_out_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, phoneNumber);
            preparedStatement.setString(4, securityCode);
            preparedStatement.setInt(5, flatId);
            preparedStatement.setInt(6, buildingId);
            preparedStatement.setTimestamp(7, checkInTime);
            preparedStatement.setTimestamp(8, checkOutTime);

            // Execute the insert query
            preparedStatement.executeUpdate();

            // Close resources
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }

            // Redirect to a success page or display a success message
            response.sendRedirect("supervisor_visitor_success.jsp");
        } catch (ClassNotFoundException | SQLException e) {
            // Handle exceptions
            e.printStackTrace();
            // Redirect to an error page or display an error message
            response.sendRedirect("supervisor_visitor_error.jsp");
        }
    }

    // Method to generate a random security code
    private String generateRandomSecurityCode(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder code = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            code.append(characters.charAt(index));
        }

        return code.toString();
    }

    // Method to check if buildingId and flatId exist in the flat_details table
    private boolean checkBuildingFlatExistence(int buildingId, int flatId) {
        // Database connection details (modify these according to your database configuration)
        String jdbcURL = "jdbc:mysql://localhost:3306/societysecurity";
        String dbUser = "root";
        String dbPassword = "sonu9939@";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Create a database connection
            Class.forName("com.mysql.cj.jdbc.Driver"); // Use the modern MySQL driver
            connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            // Check if the specified buildingId and flatId exist in the flat_details table
            String checkQuery = "SELECT COUNT(*) FROM flat_details WHERE building_id = ? AND flat_id = ?";
            preparedStatement = connection.prepareStatement(checkQuery);
            preparedStatement.setInt(1, buildingId);
            preparedStatement.setInt(2, flatId);
            resultSet = preparedStatement.executeQuery();

            return resultSet.next() && resultSet.getInt(1) > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
