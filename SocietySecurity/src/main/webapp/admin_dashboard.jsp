<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    
     <%@ include file="header.jsp" %>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Add your custom CSS file -->
    <link rel="stylesheet" href="your_custom.css">
    <style>
        body {
            background-color: #f2f2f2;
        }
        .card {
            margin-top: 20px;
            border: 1px solid #ddd;
            border-radius: 10px;
        }
        .card-title {
            font-size: 24px;
            background-color: #007BFF;
            color: #fff;
            padding: 10px;
            border-radius: 10px 10px 0 0;
        }
        .list-group-item {
            text-align: center;
            background-color: #fff;
            border: 1px solid #ddd;
            margin: 5px;
            padding: 10px;
            border-radius: 10px;
        }
        .list-group-item a {
            text-decoration: none;
            color: #007BFF;
            font-weight: bold;
            transition: color 0.3s;
        }
        .list-group-item a:hover {
            color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-md-12 text-center">
                <h2 class="mt-5">Admin Dashboard</h2>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4">
                <div class="card">
                    <div class="card-title">Admin Actions</div>
                    <div class="list-group">
                        <li class="list-group-item">
                            <a href="admin_add_building.jsp">Add Building Details</a>
                        </li>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card">
                    <div class="card-title">Admin Actions</div>
                    <div class="list-group">
                        <li class="list-group-item">
                            <a href="admin_add_flat.jsp">Add Flat Details</a>
                        </li>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card">
                    <div class="card-title">Admin Actions</div>
                    <div class="list-group">
                        <li class="list-group-item">
                            <a href="admin_add_resident.jsp">Add Resident Details</a>
                        </li>
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
        $(document).ready(function() {
            // Add smooth scrolling to all links
            $("a").on('click', function(event) {
                if (this.hash !== "") {
                    event.preventDefault();
                    var hash = this.hash;
                    $('html, body').animate({
                        scrollTop: $(hash).offset().top
                    }, 800, function(){
                        window location.hash = hash;
                    });
                }
            });
        });
    </script>
   
    
</body>
</html>
