<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.PreparedStatement, java.sql.ResultSet, java.sql.SQLException, java.sql.Timestamp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
      <%@ include file="header1.jsp" %>
    <title>Maintenance Details</title>
    <!-- Add your CSS and Bootstrap links here -->
    <link rel="stylesheet" href="your_custom.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h2 class="text-center">Maintenance Details</h2>
        <div class="table-responsive">
            <form method="post" action="UpdatePaymentServlet">
                <table class="table table-bordered">
                    <thead class="thead-dark">
                        <tr>
                            
                            <th>User Name</th>
                            <th>Building ID</th>
                            <th>Flat ID</th>
                            <th>Status</th>
                            <th>Maintenance Charge (in Rupees)</th>
                            <th>Payment Date and Time</th>
                            <th>Payment Mode</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                        Connection connection = null;
                        try {
                            // Establish a database connection
                            String jdbcUrl = "jdbc:mysql://localhost:3306/societysecurity";
                            String dbUser = "root";
                            String dbPassword = "sonu9939@";
                            Class.forName("com.mysql.cj.jdbc.Driver"); // Load the MySQL JDBC driver

                            connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

                            // Create a SQL query to fetch maintenance details
                            String sql = "SELECT * FROM maintenance_details";
                            PreparedStatement statement = connection.prepareStatement(sql);

                            // Execute the query and process the results
                            ResultSet resultSet = statement.executeQuery();
                            while (resultSet.next()) {
                        %>
                        <tr>
                            
                            <td><%= resultSet.getString("user_name") %></td>
                            <td><%= resultSet.getInt("building_id") %></td>
                            <td><%= resultSet.getInt("flat_id") %></td>
                            <td>
                                <select name="status<%= resultSet.getRow() %>" 
                                    <% if ("Paid".equals(resultSet.getString("status"))) { %>disabled<% } %>
                                >
                                    <option value="Due" <%= "Due".equals(resultSet.getString("status")) ? "selected" : "" %>>Due</option>
                                    <option value="Paid" <%= "Paid".equals(resultSet.getString("status")) ? "selected" : "" %>>Paid</option>
                                </select>
                            </td>
                            <td><%= resultSet.getDouble("maintenance_charge") %></td>
                            <td><%= resultSet.getTimestamp("payment_datetime") %></td>
                            <td>
                                <%
                                String selectedMode = resultSet.getString("mode_of_payment");
                                String[] paymentModes = {"Card", "UPI", "Cash", "Cheque"};
                                %>
                                <select name="payment_mode<%= resultSet.getRow() %>"
                                    <% if ("Paid".equals(resultSet.getString("status"))) { %>disabled<% } %>
                                >
                                    <option value="null">Select Payment Mode</option>
                                    <%
                                    for (String mode : paymentModes) {
                                    %>
                                    <option value="<%= mode %>" <%= mode.equals(selectedMode) ? "selected" : "" %>><%= mode %></option>
                                    <%
                                    }
                                    %>
                                </select>
                            </td>
                            <td>
                                <input type="hidden" name="id<%= resultSet.getRow() %>" value="<%= resultSet.getInt("id") %>">
                                <button type="submit" class="btn btn-primary" name="updateButton<%= resultSet.getRow() %>"
                                    <% if ("Paid".equals(resultSet.getString("status"))) { %>disabled<% } %>
                                >Update</button>
                            </td>
                        </tr>
                        <%
                            }
                        } catch (SQLException e) {
                            out.println("Database Error: " + e.getMessage());
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            out.println("JDBC Driver Error: " + e.getMessage());
                            e.printStackTrace();
                        } finally {
                            // Close the database connection in a finally block
                            if (connection != null) {
                                try {
                                    connection.close();
                                } catch (SQLException e) {
                                    out.println("Connection Closing Error: " + e.getMessage());
                                    e.printStackTrace();
                                }
                            }
                        }
                        %>
                    </tbody>
                </table>
            </form>
        </div>
    </div>
<%@ include file="footer.jsp" %>
    <!-- Add your JavaScript and Bootstrap JS links here -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
