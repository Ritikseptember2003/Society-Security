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
import javax.servlet.http.HttpSession;

import ritik.User;

@WebServlet("/resident_dashboard")
public class UserDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection parameters
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/societysecurity";
    private static final String dbUser = "root";
    private static final String dbPassword = "sonu9939@";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username"); // Get the username from the session
        User user = getUserDetails(username);

        if (user != null) {
            
            request.setAttribute("user", user);
            
            request.getRequestDispatcher("/resident_dashboard.jsp").forward(request, response);
     
            
        } else {
            response.getWriter().write("User not found");
        }
    }

    private User getUserDetails(String username) {
        User user = null;

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String selectQuery = "SELECT username, building_id, flat_id FROM users WHERE username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setString(1, username);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String fetchedUsername = resultSet.getString("username");
                        int buildingId = resultSet.getInt("building_id");
                        int flatId = resultSet.getInt("flat_id");
                        user = new User(fetchedUsername, buildingId, flatId);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}