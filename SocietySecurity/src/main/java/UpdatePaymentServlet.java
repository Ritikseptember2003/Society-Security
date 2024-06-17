import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@WebServlet("/UpdatePaymentServlet")
public class UpdatePaymentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Database connection details (modify these according to your database configuration)
        String jdbcURL = "jdbc:mysql://localhost:3306/societysecurity";
        String dbUser = "root";
        String dbPassword = "sonu9939@";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Create a database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            // Iterate through the form parameters and look for "updateButton" to identify the row to update
            for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
                String paramName = entry.getKey();
                if (paramName.startsWith("updateButton")) {
                    int rowNumber = Integer.parseInt(paramName.substring("updateButton".length()));
                    int id = Integer.parseInt(request.getParameter("id" + rowNumber));
                    String status = request.getParameter("status" + rowNumber);
                    String paymentMode = request.getParameter("payment_mode" + rowNumber);

                    // Update the payment date and time to the current date and time
                    Timestamp paymentDateTime = new Timestamp(System.currentTimeMillis());

                    // Update the maintenance details in the database for the specified row
                    String updateQuery = "UPDATE maintenance_details SET status=?, mode_of_payment=?, payment_datetime=? WHERE id=?";
                    preparedStatement = connection.prepareStatement(updateQuery);
                    preparedStatement.setString(1, "Paid"); // Set the status to 'Paid'
                    preparedStatement.setString(2, paymentMode);
                    preparedStatement.setTimestamp(3, paymentDateTime);
                    preparedStatement.setInt(4, id);

                    // Execute the update query
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("SQL Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new ServletException("JDBC Driver Error: " + e.getMessage());
        } finally {
            // Close the database connection in a finally block
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new ServletException("Connection Closing Error: " + e.getMessage());
                }
            }
        }

        // Redirect back to the JSP page after the update
        response.sendRedirect("maintenance_details.jsp");
    }
}
