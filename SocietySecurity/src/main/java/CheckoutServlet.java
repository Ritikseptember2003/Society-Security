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

@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the unique identifier for the visitor (e.g., security code)
        String securityCode = request.getParameter("securityCode");

        // Database connection details
        String jdbcURL = "jdbc:mysql://localhost:3306/societysecurity";
        String dbUser = "root";
        String dbPassword = "sonu9939@";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Create a database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            // Update the checkout time for the visitor with the given security code
            String updateQuery = "UPDATE regular_visitor_details SET check_out_time = ? WHERE security_code = ?";
            preparedStatement = connection.prepareStatement(updateQuery);
            
            // Set the current timestamp as the checkout time
            Timestamp checkoutTime = new Timestamp(System.currentTimeMillis());
            preparedStatement.setTimestamp(1, checkoutTime);
            
            preparedStatement.setString(2, securityCode);

            // Execute the update query
            preparedStatement.executeUpdate();

            // Redirect to a success page or display a success message
            response.sendRedirect("supervisor_visitor_success.jsp");
        } catch (ClassNotFoundException | SQLException e) {
            // Handle exceptions
            e.printStackTrace();
            // Redirect to an error page or display an error message
            response.sendRedirect("supervisor_visitor_error.jsp");
        } finally {
            // Close resources
            if (preparedStatement != null) {
                try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            if (connection != null) {
                try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }
    }
}
