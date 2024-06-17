<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <%@ include file="header1.jsp" %>
    <title>Visitor Registration</title>
    <!-- Link to Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Add your custom CSS file (if needed) -->
    <link rel="stylesheet" href="your_custom.css">
</head>
<body>
    <div class="container">
        <h2 class="text-center">Visitor Registration</h2>
        <form method="post" action="ProcessRegistrationServlet">
            <div class="form-group">
                <label for="firstName">First Name:</label>
                <input type="text" id="firstName" name="firstName" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="lastName">Last Name:</label>
                <input type="text" id="lastName" name="lastName" class="form-control" required>
            </div>
            <div class "form-group">
                <label for="phoneNumber">Phone Number:</label>
                <input type="text" id="phoneNumber" name="phoneNumber" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="flatNumber">Flat Id:</label>
                <input type="text" id="flatNumber" name="flatId" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="buildingNumber">Building Id:</label>
                <input type="text" id="buildingNumber" name="buildingId" class="form-control" required>
            </div>
            <button type="submit" class="btn btn-primary">Register</button>
        </form>
    </div>
<%@ include file="footer.jsp" %>
    <!-- Link to Bootstrap JS and jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
