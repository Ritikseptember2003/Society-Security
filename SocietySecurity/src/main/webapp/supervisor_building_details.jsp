<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.PreparedStatement, java.sql.ResultSet, java.sql.SQLException" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <%@ include file="header1.jsp" %>
    <title>Building Details</title>
    <!-- Link to Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Add your custom CSS file -->
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
    </style>
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-md-12 text-center">
                <h2 class="mt-5">Building Details</h2>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 offset-md-3 mt-4">
                <div class="card">
                    <div class="card-body">
                     
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Building ID</th>
                                    <th>Building Name</th>
                                    <th>Building Address</th>
                                    <th>Number of Floors</th>
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
                                    Class.forName("com.mysql.cj.jdbc.Driver"); // Load the MySQL JDBC driver (Note: changed the driver class)

                                    connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

                                    // Create a SQL query
                                    String sql = "SELECT * FROM building_details";
                                    PreparedStatement statement = connection.prepareStatement(sql);

                                    // Execute the query and process the results
                                    ResultSet resultSet = statement.executeQuery();
                                    while (resultSet.next()) {
                                %>
                                <tr>
                                    <td><%= resultSet.getInt("building_id") %></td>
                                    <td><%= resultSet.getString("building_name") %></td>
                                    <td><%= resultSet.getString("building_address") %></td>
                                    <td><%= resultSet.getInt("number_of_floors") %></td>
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
<%@ include file="footer.jsp" %>
    <!-- Link to Bootstrap JS and jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    
</body>
</html>