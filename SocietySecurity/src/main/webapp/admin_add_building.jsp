<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
     <%@ include file="header.jsp" %>
    <title>Add Building Details</title>
    <!-- Link to Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Add your custom CSS file -->
    <link rel="stylesheet" href="your_custom.css">
</head>
<body>
    <div class="container">
        <h2 class="mt-4">Add Building Details</h2>
        <form method="post" action="admin_add_building">
            <div class="form-group">
                <label for="buildingName">Building Name:</label>
                <input type="text" class="form-control" id="buildingName" name="buildingName" required>
            </div>
            <div class="form-group">
                <label for="buildingAddress">Building Address:</label>
                <input type="text" class="form-control" id="buildingAddress" name="buildingAddress" required>
            </div>
            <div class="form-group">
                <label for="numberoffloors">Number Of Floors:</label>
                <input type="text" class="form-control" id="numberoffloors" name="numberoffloors" required>
            </div>
            <button type="submit" class="btn btn-primary">Add Building Details</button>
        </form>
    </div>

<%@ include file="footer.jsp" %>
    <!-- Link to Bootstrap JS and jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
