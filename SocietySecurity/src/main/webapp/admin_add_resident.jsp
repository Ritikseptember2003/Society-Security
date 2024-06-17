<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
     <%@ include file="header.jsp" %>
    <title>Add Resident Details</title>
    <!-- Link to Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Add your custom CSS file -->
    <link rel="stylesheet" href="your_custom.css">
</head>
<body>
    <div class="container mt-4">
        <h2>Add Resident Details</h2>
        <form method="post" action="admin_add_resident">
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" class="form-control" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <div class="form-group">
                <label for="flatId">Flat Id:</label>
                <input type="text" class="form-control" id="flatId" name="flatId" required>
            </div>
            <div class="form-group">
                <label for="buildingId">Building ID:</label>
                <input type="text" class="form-control" id="buildingId" name="buildingId" required>
            </div>
            <button type="submit" class="btn btn-primary">Add Resident Details</button>
        </form>
    </div>
<%@ include file="footer.jsp" %>
    <!-- Link to Bootstrap JS and jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
