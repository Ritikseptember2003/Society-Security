import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ritik.User;


@WebServlet("/process_request")
public class ProcessRequestServlet extends HttpServlet {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        User user = (User) request.getSession().getAttribute("user");

        String jdbcUrl = "jdbc:mysql://localhost:3306/SocietySecurity";
        String dbUser = "root";
        String dbPassword = "sonu9939@";

        if (user != null) {
            try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
                if ("approve".equals(action)) {
                    // Update the status to "Approved" in the database for the current user
                    String updateQuery = "UPDATE normal_visitor_details SET status = 'Approved' WHERE building_id = ? AND flat_id = ? AND status = 'Pending'";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                        preparedStatement.setInt(1, user.getBuildingId());
                        preparedStatement.setInt(2, user.getFlatId());
                        preparedStatement.executeUpdate();
                    }
                } else if ("decline".equals(action)) {
                    // Update the status to "Declined" in the database for the current user
                    String updateQuery = "UPDATE normal_visitor_details SET status = 'Declined' WHERE building_id = ? AND flat_id = ? AND status = 'Pending'";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                        preparedStatement.setInt(1, user.getBuildingId());
                        preparedStatement.setInt(2, user.getFlatId());
                        preparedStatement.executeUpdate();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Redirect the user back to the original page
        response.sendRedirect("resident_dashboard.jsp");
    }
}
