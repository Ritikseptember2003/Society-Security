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

@WebServlet("/admin_add_resident")
public class AdminUploadResidentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve resident details from the form
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int flatId = Integer.parseInt(request.getParameter("flatId"));
        int buildingId = Integer.parseInt(request.getParameter("buildingId"));

        // Database connection parameters
        String jdbcUrl = "jdbc:mysql://localhost:3306/SocietySecurity";
        String dbUser = "root";
        String dbPassword = "sonu9939@";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

            // Check if the specified buildingId and flatId exist in the flat_details table
            String checkQuery = "SELECT COUNT(*) FROM flat_details WHERE building_id = ? AND flat_id = ?";
            PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
            checkStatement.setInt(1, buildingId);
            checkStatement.setInt(2, flatId);
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next() && resultSet.getInt(1) > 0) {
                // BuildingId and flatId exist in flat_details, proceed to check in users table
                String userCheckQuery = "SELECT COUNT(*) FROM users WHERE building_id = ? AND flat_id = ?";
                PreparedStatement userCheckStatement = connection.prepareStatement(userCheckQuery);
                userCheckStatement.setInt(1, buildingId);
                userCheckStatement.setInt(2, flatId);
                ResultSet userResultSet = userCheckStatement.executeQuery();

                if (userResultSet.next() && userResultSet.getInt(1) > 0) {
                    // A row already exists in the users table with the same buildingId and flatId, show an error message
                    response.sendRedirect("admin_error.jsp");
                } else {
                    // No existing row found in users table, proceed to insert resident details
                    String insertQuery = "INSERT INTO users (username, password, role, flat_id, building_id) VALUES (?, ?, 'Flat Resident', ?, ?)";
                    PreparedStatement insertStatement = connection.prepareStatement(insertQuery);

                    insertStatement.setString(1, username);
                    insertStatement.setString(2, password);
                    insertStatement.setInt(3, flatId);
                    insertStatement.setInt(4, buildingId);
                    insertStatement.executeUpdate();

                    // Close connections and redirect to a success page
                    insertStatement.close();
                    userCheckStatement.close();
                    checkStatement.close();
                    connection.close();
                    response.sendRedirect("admin_success_resident.jsp");
                }

                // Close the user result set
                userResultSet.close();
            } else {
                // BuildingId and flatId not found in flat_details, show an error message
                response.sendRedirect("admin_error.jsp");
            }

            // Close the result set
            resultSet.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("admin_error.jsp"); // Handle error
        }
    }
}
