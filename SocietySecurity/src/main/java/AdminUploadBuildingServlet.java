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

@WebServlet("/admin_add_building")
public class AdminUploadBuildingServlet extends HttpServlet {
    
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve building details from the form
        String buildingName = request.getParameter("buildingName");
        String buildingAddress = request.getParameter("buildingAddress");
        int numberoffloors = Integer.parseInt(request.getParameter("numberoffloors"));

        // Database connection parameters
        String jdbcUrl = "jdbc:mysql://localhost:3306/SocietySecurity";
        String dbUser = "root";
        String dbPassword = "sonu9939@";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

            // Check if the building_id already exists
            String checkQuery = "SELECT COUNT(*) FROM building_details WHERE building_name = ?";
            PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
            checkStatement.setString(1, buildingName);

            int count = 0;
            try (ResultSet resultSet = checkStatement.executeQuery()) {
                if (resultSet.next()) {
                    count = resultSet.getInt(1);
                }
            }

            if (count == 0) {
                // Building does not exist, proceed with the insertion
                String buildingQuery = "INSERT INTO building_details (building_name, building_address, number_of_floors) VALUES (?, ?, ?)";
                PreparedStatement buildingStatement = connection.prepareStatement(buildingQuery);
                buildingStatement.setString(1, buildingName);
                buildingStatement.setString(2, buildingAddress);
                buildingStatement.setInt(3, numberoffloors);
                buildingStatement.executeUpdate();
                buildingStatement.close();
                response.sendRedirect("admin_success_building.jsp");
            } else {
                // Building with the same name already exists
                response.sendRedirect("admin_error.jsp");
            }

            // Close connections
            checkStatement.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("admin_error.jsp"); // Handle error
        }
    }
}
