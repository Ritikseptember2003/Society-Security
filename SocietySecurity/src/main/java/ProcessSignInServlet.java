import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProcessSignInServlet")
public class ProcessSignInServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the security code from the form
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

            // Query to check if the security code exists
            String checkCodeQuery = "SELECT * FROM regular_visitor_details WHERE security_code = ?";
            preparedStatement = connection.prepareStatement(checkCodeQuery);
            preparedStatement.setString(1, securityCode);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Security code exists, simply redirect to the success page
                response.sendRedirect("supervisor_visitor_success.jsp");
            } else {
                // Security code not found
                response.sendRedirect("supervisor_visitor_error.jsp");
            }
        } catch (ClassNotFoundException | SQLException e) {
            // Handle exceptions
            e.printStackTrace();
            // Redirect to an error page or display an error message
            response.sendRedirect("supervisor_visitor_error.jsp");
        } finally {
            // Close resources
            try {
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
