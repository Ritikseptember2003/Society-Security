<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <%@ include file="header1.jsp" %>
    <title>Upload Regular Visitor Details</title>
    
    <!-- Link to Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    
    <!-- Add your custom CSS file (if needed) -->
    <link rel="stylesheet" href="your_custom.css">
    
    <style>
        /* Your existing styles here */
    </style>
</head>
<body>
    <div class="container">
        <h2 class="mt-4">Regular Visitor or Vendor</h2>
        <div>
            <h3>Choose an option:</h3>
            <ul>
                <li>
                    <a href="sign_in.jsp" class="btn btn-primary btn-lg">Sign In</a>
                </li>
                <li>
                    <a href="registration.jsp" class="btn btn-primary btn-lg">Register as New Visitor or Vendor</a>
                </li>
            </ul>
        </div>
    </div>
    
    <%@ include file="footer.jsp" %>
    
    <!-- Link to Bootstrap JS and jQuery (if needed) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
