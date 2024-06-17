<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.PreparedStatement, java.sql.ResultSet, java.sql.SQLException" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
      <%@ include file="header1.jsp" %>
    <title>Regular Visitor Details</title>
    <!-- Link to Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="your_custom.css">
    <style>
        body {
            background-color: #f2f2f2;
        }
        .card {
            margin-top: 20px;
        }
        .card-title {
            font-size: 18px;
        }
        .table {
            margin-bottom: 0; /* Remove extra spacing below the table */
        }
        .card-body {
            padding: 0; /* Remove padding from the card body */
        }
        .table th, .table td {
            padding: 0.5rem; /* Adjust table cell padding */
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-md-12 text-center">
                <h2 class="mt-5">Regular Visitor Details</h2>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12 mt-4">
                <div class="card">
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered">
                                <thead class="thead-dark">
                                    <tr>
                                        <th>First Name</th>
                                        <th>Last Name</th>
                                        <th>Phone Number</th>
                                        <th>Security Code</th>
                                        <th>Flat Id</th>
                                        <th>Building Id</th>
                                        <th>Check In Time</th>
                                        <th>Check Out Time</th>
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

                                        // Create a SQL query
                                        String sql = "SELECT * FROM regular_visitor_details";
                                        PreparedStatement statement = connection.prepareStatement(sql);

                                        // Execute the query and process the results
                                        ResultSet resultSet = statement.executeQuery();
                                        while (resultSet.next()) {
                                    %>
                                    <tr>
                                        <td><%= resultSet.getString("first_name") %></td>
                                        <td><%= resultSet.getString("last_name") %></td>
                                        <td><%= resultSet.getString("phone_number") %></td>
                                        <td><%= resultSet.getString("security_code") %></td>
                                        <td><%= resultSet.getInt("flat_id") %></td>
                                        <td><%= resultSet.getInt("building_id") %></td>
                                        <td><%= resultSet.getString("check_in_time") %></td>
                                        <td><%= resultSet.getString("check_out_time") %></td>
                                        <td>
                                            <form method="post" action="CheckoutServlet">
                                                <input type="hidden" name="securityCode" value="<%= resultSet.getString("security_code") %>">
                                                <button type="submit" class="btn btn-primary" <% if (resultSet.getString("check_out_time") != null && !resultSet.getString("check_out_time").isEmpty()) { %>disabled<% } %>
                                                    onclick="disableButton(this);"
                                                >Checkout</button>
                                            </form>
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
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<%@ include file="footer.jsp" %>
    <!-- Link to Bootstrap JS and jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    
    <script>
    function disableButton(button) {
        button.disabled = true;  // Disable the button
        button.form.submit();   // Submit the form
    }
    </script>
</body>
</html>
