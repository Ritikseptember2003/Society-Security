import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProcessStaffDetailsServlet")
public class ProcessStaffDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data from the request
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phoneNumber = request.getParameter("phoneNumber");
        String salary = request.getParameter("salary");
        String occupation = request.getParameter("occupation");
        int buildingId = Integer.parseInt(request.getParameter("buildingId"));

        // Database connection information
        String jdbcUrl = "jdbc:mysql://localhost:3306/societysecurity";
        String dbUser = "root";
        String dbPassword = "sonu9939@";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            // Define the SQL query to insert staff details into the database
            String insertQuery = "INSERT INTO staff_details (first_name, last_name, phone_number, salary, occupation, building_id) VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                preparedStatement.setString(3, phoneNumber);
                preparedStatement.setString(4, salary);
                preparedStatement.setString(5, occupation);
                preparedStatement.setInt(6, buildingId);

                // Execute the SQL query to insert data into the database
                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("supervisor_visitor_error.jsp"); // Handle error and redirect
            return; // Return to avoid further processing
        }

        // Redirect the user to a confirmation page or the original form
        response.sendRedirect("supervisor_visitor_success.jsp");
    }
}
