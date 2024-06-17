<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
     <%@ include file="header.jsp" %>
    <title>Admin - Error</title>
    <!-- Link to Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Add your custom CSS file -->
    <link rel="stylesheet" href="your_custom.css">
    <style>
        body {
            background-color: #f5f5f5; /* Background color */
        }
        .container {
            max-width: 500px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
        }
        h2 {
            color: #ff0000; /* Header text color */
        }
        .error-message {
            color: #ff0000; /* Error message text color */
        }
    </style>
</head>
<body>
    <div class="container text-center mt-5">
        <h2 class="error-message mb-4">Error</h2>
        <p class="error-message lead">There was an error during the upload process. Please try again later or contact support.</p>
        <a href="admin_dashboard.jsp" class="btn btn-primary mt-3">Return to Admin Dashboard</a>
    </div>
<%@ include file="footer.jsp" %>
    <!-- Link to Bootstrap JS and jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
