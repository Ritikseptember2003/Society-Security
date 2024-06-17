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

@WebServlet("/ProcessNormalVisitorServlet")
public class ProcessNormalVisitorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("resource")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form parameters
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phoneNumber = request.getParameter("phoneNumber");
        String buildingIdStr = request.getParameter("buildingId");
        String flatIdStr = request.getParameter("flatId");
        String status = request.getParameter("status");

        // Check if buildingId and flatId are not empty
        if (buildingIdStr == null || buildingIdStr.isEmpty() || flatIdStr == null || flatIdStr.isEmpty()) {
            
            response.sendRedirect("supervisor_visitor_error.jsp"); // Modify to your error page
            return;
        }

        int buildingId = Integer.parseInt(buildingIdStr);
        int flatId = Integer.parseInt(flatIdStr);

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

            // Check if the user with the provided buildingId and flatId exists
            String checkUserQuery = "SELECT * FROM users WHERE building_id = ? AND flat_id = ?";
            preparedStatement = connection.prepareStatement(checkUserQuery);
            preparedStatement.setInt(1, buildingId);
            preparedStatement.setInt(2, flatId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // User exists, so proceed to insert visitor details
                // SQL query to insert visitor details
                String insertQuery = "INSERT INTO normal_visitor_details (first_name, last_name, phone_number, building_id, flat_id, status) VALUES (?, ?, ?, ?, ?, ?)";

                preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                preparedStatement.setString(3, phoneNumber);
                preparedStatement.setInt(4, buildingId);
                preparedStatement.setInt(5, flatId);
                preparedStatement.setString(6, status);

                // Execute the insert query
                preparedStatement.executeUpdate();

                // Redirect to a success page or display a success message
                response.sendRedirect("supervisor_visitor_success.jsp");
            } else {
                // User doesn't exist, so redirect to an error page
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
