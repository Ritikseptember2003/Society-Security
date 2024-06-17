<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <%@ include file="header1.jsp" %>
    <title>Upload Maintenance Details</title>
    <!-- Add your CSS and Bootstrap links here -->
    <link rel="stylesheet" href="your_custom.css">
    
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h2 class="text-center">Upload Maintenance Details</h2>
        <form method="post" action="MaintenanceDetailsServlet">
            <div class="form-group">
                <label for="userName">User Name:</label>
                <input type="text" id="userName" name="user_name" class="form-control" required>
            </div>
            
            <div class="form-group">
                <label for="buildingId">Building ID:</label>
                <input type="text" id="buildingId" name="building_id" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="flatId">Flat ID:</label>
                <input type="text" id="flatId" name="flat_id" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="status">Status:</label>
                <input type="text" id="status" name="status" class="form-control" value="Due" readonly>
            </div>
            <div class="form-group">
                <label for="maintenanceCharge">Maintenance Charge (in Rupees):</label>
                <input type="text" id="maintenanceCharge" name="maintenance_charge" class="form-control" required>
            </div>
            <div class="form-group">
    <label for="paymentMode">Payment Mode:</label>
    <select id="paymentMode" name="payment_mode" class="form-control" disabled>
        <option value="null" selected>Select Payment Mode</option>
        <option value="card">Card</option>
        <option value="upi">UPI</option>
        <option value="cash">Cash</option>
        <option value="cheque">Cheque</option>
    </select>
</div>
            <button type="submit" class="btn btn-primary">Upload Maintenance Details</button>
        </form>
    </div>
<%@ include file="footer.jsp" %>
    <!-- Add your JavaScript and Bootstrap JS links here -->
    <script src="your-custom-script.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
