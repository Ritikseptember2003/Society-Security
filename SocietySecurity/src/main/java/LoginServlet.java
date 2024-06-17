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
import javax.servlet.http.*; 
import ritik.User;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Display the login form for GET requests
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();

        // Database connection parameters
        String jdbcUrl = "jdbc:mysql://localhost:3306/societysecurity";
        String dbUser = "root";
        String dbPassword = "sonu9939@";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

            if ("Ram".equals(username) && "ram@123".equals(password)) {
                // Redirect to the admin dashboard
                response.sendRedirect("admin_dashboard.jsp");
            } else if ("Shyam".equals(username) && "shyam@123".equals(password)) {
                // Redirect to the supervisor dashboard
                response.sendRedirect("supervisor_dashboard.jsp");
            } else {
                // Check credentials against the database for other users
                String query = "SELECT * FROM users WHERE username = ? AND password = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    String role = resultSet.getString("Role");
                    if ("Flat Resident".equals(role)) {
                        // Retrieve user details
                        int buildingId = resultSet.getInt("building_id");
                        int flatId = resultSet.getInt("flat_id");

                        // Create a User object
                        User user = new User(username, buildingId, flatId);

                        // Store the User object in the session
                        session.setAttribute("user", user);

                        // Redirect to the resident dashboard
                        response.sendRedirect("resident_dashboard.jsp");
                    } else {
                        // Invalid credentials for other users
                        response.sendRedirect("login.jsp?error=1");
                    }
                } else {
                    // Invalid credentials; display an error message
                    response.sendRedirect("login.jsp?error=1");
                }

                resultSet.close();
                preparedStatement.close();
            }

            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
