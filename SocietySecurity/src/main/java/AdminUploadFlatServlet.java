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

@WebServlet("/admin_add_flat")
public class AdminUploadFlatServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve flat details from the form
        int flatId = Integer.parseInt(request.getParameter("flatId"));
        String flatType = request.getParameter("flatType");
        int buildingId = Integer.parseInt(request.getParameter("buildingId"));

        // Database connection parameters
        String jdbcUrl = "jdbc:mysql://localhost:3306/SocietySecurity";
        String dbUser = "root";
        String dbPassword = "sonu9939@";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

            // Check for existing rows with the same buildingId and flatId
            String checkQuery = "SELECT COUNT(*) FROM flat_details WHERE building_id = ? AND flat_id = ?";
            PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
            checkStatement.setInt(1, buildingId);
            checkStatement.setInt(2, flatId);
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next() && resultSet.getInt(1) > 0) {
                // Rows with the same buildingId and flatId exist, so show an error message
                response.sendRedirect("admin_error.jsp");
            } else {
                // No existing rows found, proceed to insert the flat details
                String flatQuery = "INSERT INTO flat_details (flat_id, flat_type, building_id) VALUES (?, ?, ?)";
                PreparedStatement flatStatement = connection.prepareStatement(flatQuery);

                flatStatement.setInt(1, flatId);
                flatStatement.setString(2, flatType);
                flatStatement.setInt(3, buildingId);
                flatStatement.executeUpdate();

                // Close connections and redirect to a success page
                flatStatement.close();
                connection.close();
                response.sendRedirect("admin_success_flat.jsp");
            }

            // Close the result set and checkStatement
            resultSet.close();
            checkStatement.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("admin_error.jsp"); // Handle error
        }
    }
}
