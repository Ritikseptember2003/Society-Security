<%@ page import="java.sql.Connection,java.sql.DriverManager,java.sql.PreparedStatement,java.sql.ResultSet,ritik.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <%@ include file="header2.jsp" %>
    <title>User Details</title>

    <!-- Include Bootstrap CSS for styling -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    
    <!-- Add your custom CSS file -->
    <link rel="stylesheet" href="your_custom.css">
    
    <style>
        body {
            background-color: #f2f2f2;
        }
        .container {
            padding: 20px;
        }
        h2 {
            font-size: 24px;
            margin-top: 20px;
        }
        .user-details {
            background-color: #fff;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
            margin-top: 20px;
        }
        .no-requests {
            font-size: 18px;
            margin-top: 10px;
        }
        .card {
            border: 1px solid #ddd;
            margin-top: 10px;
            border-radius: 5px;
        }
        .card-title {
            font-size: 20px;
            background-color: #007BFF;
            color: #fff;
            padding: 10px;
            border-radius: 5px 5px 0 0;
        }
        .card-body {
            padding: 20px;
        }
        .action-buttons {
            margin: 5px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2 class="mt-4">User Details</h2>
        <div class="user-details">
            <%
                User user1 = (User) session.getAttribute("user");
                if (user1 != null) {
            %>
            <p class="lead">Welcome <%= user1.getUsername() %> !!</p>
            <p>Building ID: <%= user1.getBuildingId() %></p>
            <p>Flat ID: <%= user1.getFlatId() %></p>
            <%
                } else {
            %>
            <p>User not found</p>
            <%
                }
            %>
        </div>
        
        <div class="visitor-requests">
            <h2 class="mt-4">Visitor Requests</h2>
            <%
                User visitorUser = (User) session.getAttribute("user");
                if (visitorUser != null) {
                    String jdbcUrl = "jdbc:mysql://localhost:3306/SocietySecurity";
                    String dbUser = "root";
                    String dbPassword = "sonu9939@";

                    try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
                        String selectQuery = "SELECT first_name, last_name, phone_number, status FROM normal_visitor_details WHERE building_id = ? AND flat_id = ? AND status = 'Pending'";
                        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                            preparedStatement.setInt(1, visitorUser.getBuildingId());
                            preparedStatement.setInt(2, visitorUser.getFlatId());
                            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                                boolean requestsFound = false; // Flag to check if any requests are found
                                while (resultSet.next()) {
                                    requestsFound = true; // Set the flag to true if requests are found
                                    String firstName = resultSet.getString("first_name");
                                    String lastName = resultSet.getString("last_name");
                                    String phoneNumber = resultSet.getString("phone_number");
                                    String status = resultSet.getString("status");
                            %>
                          
                            <div class="card">
                                
                                <div class="card-body">
                                	<p>Name: <%= firstName %> <%= lastName %></p>
                                    <p>Phone Number: <%= phoneNumber %></p>
                                    <p>Status: <%= status %></p>
                                    <form action="process_request" method="post">
                                        <button type="submit" name="action" value="approve" class="btn btn-success action-buttons">Approve</button>
                                        <button type="submit" name="action" value="decline" class="btn btn-danger action-buttons">Decline</button>
                                    </form>
                                </div>
                            </div>
                            <%
                                }
                                if (!requestsFound) {
                            %>
                            <p class="no-requests">No Visitor Requests Found</p>
                            <%
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            %>
        </div>
        
        <!-- Maintenance Details Section -->
        <%
            User maintenanceUser = (User) session.getAttribute("user");
            if (maintenanceUser != null) {
                String jdbcUrl = "jdbc:mysql://localhost:3306/SocietySecurity";
                String dbUser = "root";
                String dbPassword = "sonu9939@";

                try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
                    String selectMaintenanceQuery = "SELECT maintenance_charge, status, mode_of_payment, payment_datetime FROM maintenance_details WHERE building_id = ? AND flat_id = ?";
                    try (PreparedStatement maintenanceStatement = connection.prepareStatement(selectMaintenanceQuery)) {
                        maintenanceStatement.setInt(1, maintenanceUser.getBuildingId());
                        maintenanceStatement.setInt(2, maintenanceUser.getFlatId());

                        try (ResultSet maintenanceResultSet = maintenanceStatement.executeQuery()) {
                            if (maintenanceResultSet.next()) {
                                double price = maintenanceResultSet.getDouble("maintenance_charge");
                                String status = maintenanceResultSet.getString("status");
                                String modeofPayment = maintenanceResultSet.getString("mode_of_payment");
                                String paymentDate = maintenanceResultSet.getString("payment_datetime");

                                %>
                                <div class="maintenance-details">
                                    <h2 class="mt-4">Maintenance Details</h2>
                                    <div class="card">
                                        
                                        <div class="card-body">
                                            <p>Price: Rs<%= price %></p>
                                            <p>Status: <%= status %></p>
                                            <p>Mode Of Payment: <%= modeofPayment %></p>
                                            <p>Payment Date and Time: <%= paymentDate %></p>
                                        </div>
                                    </div>
                                    
                                </div>
                                <%
                            } else {
                                %>
                                <p class="no-maintenance">No Maintenance Details Found</p>
                                <%
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        %>
    </div>
    <%@ include file="footer.jsp" %>
    <!-- Include Bootstrap JS and jQuery if needed -->
    <script src="https://ajax.googleapis.com/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
