import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MaintenanceDetailsServlet")
public class MaintenanceDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form parameters
        
        String userName = request.getParameter("user_name");
        String buildingIdStr = request.getParameter("building_id");
        String flatIdStr = request.getParameter("flat_id");
        String status = request.getParameter("status");
        String maintenanceChargeStr = request.getParameter("maintenance_charge");
        String paymentMode = request.getParameter("payment_mode"); // Added to get payment mode

        // Check if the buildingId and flatId are not empty
        if (buildingIdStr == null || buildingIdStr.isEmpty() || flatIdStr == null || flatIdStr.isEmpty()) {
            // Handle the case where buildingId or flatId is missing or empty
            response.sendRedirect("supervisor_visitor_error.jsp"); // Modify to your error page
            return;
        }

        int buildingId = Integer.parseInt(buildingIdStr);
        int flatId = Integer.parseInt(flatIdStr);
        double maintenanceCharge = Double.parseDouble(maintenanceChargeStr);

        // Initialize paymentDateTime as null
        Timestamp paymentDateTime = null;

        // Database connection details (modify these according to your database configuration)
        String jdbcURL = "jdbc:mysql://localhost:3306/societysecurity";
        String dbUser = "root";
        String dbPassword = "sonu9939@";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet checkResultSet = null;

        try {
            // Create a database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            // Check if the specified buildingId and flatId exist in the flat_details table
            String checkQuery = "SELECT * FROM flat_details WHERE building_id = ? AND flat_id = ?";
            PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
            checkStatement.setInt(1, buildingId);
            checkStatement.setInt(2, flatId);

            checkResultSet = checkStatement.executeQuery();

            if (!checkResultSet.next()) {
                // The specified buildingId and flatId do not exist in the flat_details table
                response.sendRedirect("supervisor_visitor_error.jsp"); // Modify to your error page
                return;
            }

            // Check if a row with the same buildingId and flatId exists in the maintenance_details table
            String existingRowQuery = "SELECT * FROM maintenance_details WHERE building_id = ? AND flat_id = ?";
            PreparedStatement existingRowStatement = connection.prepareStatement(existingRowQuery);
            existingRowStatement.setInt(1, buildingId);
            existingRowStatement.setInt(2, flatId);

            ResultSet existingRowResultSet = existingRowStatement.executeQuery();

            if (existingRowResultSet.next()) {
                // A row with the same buildingId and flatId already exists in the maintenance_details table
                response.sendRedirect("supervisor_visitor_error.jsp"); // Modify to your error page
                return;
            }

            // If status is "Paid," update paymentDateTime to the current date and time
            if ("Paid".equals(status)) {
                paymentDateTime = new Timestamp(System.currentTimeMillis());
            }

            // Insert the maintenance details into the database
            String insertQuery = "INSERT INTO maintenance_details (user_name, building_id, flat_id, status, maintenance_charge, payment_datetime, mode_of_payment) VALUES ( ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, userName);
           
            preparedStatement.setInt(2, buildingId);
            preparedStatement.setInt(3, flatId);
            preparedStatement.setString(4, status);
            preparedStatement.setDouble(5, maintenanceCharge);
            preparedStatement.setTimestamp(6, paymentDateTime);
            preparedStatement.setString(7, paymentMode); // Set mode_of_payment based on user selection

            // Execute the insert query
            preparedStatement.executeUpdate();

            // Close resources
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (checkStatement != null) {
                checkStatement.close();
            }
            if (existingRowStatement != null) {
                existingRowStatement.close();
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
        } finally {
            // Close resources in a finally block
            if (checkResultSet != null) {
                try {
					checkResultSet.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }
    }
}
