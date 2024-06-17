<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="ritik.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    
    <!-- Add Bootstrap CSS link and any custom CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="your_custom.css">
    <style>
        .navbar {
            background-color: #007BFF;
        }
        .navbar-brand {
            margin: 0 auto;
        }
        .navbar-brand a {
            color: #fff;
            font-weight: bold;
            text-align: center;
        }
        .navbar-nav .nav-item a {
            color: #fff;
            text-decoration: none;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light">
        <div class="navbar-brand">
            <a href="admin_dashboard.jsp">Society Security System</a>
        </div>
        <div class="navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a  href="login.jsp">Logout</a>
                </li>
            </ul>
        </div>
    </nav>
</body>
</html>
