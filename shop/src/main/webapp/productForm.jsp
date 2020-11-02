<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shop</title>
        <style>
            input[type=submit] {
                width: 145px;
                background-color: #4CAF50;
                border: none;
                color: white;
                padding: 16px 32px;
                text-decoration: none;
                margin: 4px 2px;
                cursor: pointer;
                border-radius: 4px;
                }
            input[type=text], input[type=number] {
                width: 300px;
                padding: 12px 20px;
                margin: 8px 0;
                box-sizing: border-box;
                border: 3px solid #555;
                border-radius: 4px;
                }
            body{
                display: flex;
                align-items: center;
                flex-direction: column;
                }
        </style>
    </head>
    <body>
        <form action="ProductsServlet" method="get">
            <label for="name">Product name:</label><br>
            <input type="text" name="name"><br>
            <label for="quantity">Quantity:</label><br>
            <input type="number" name="quantity"><br>
            <label for="price">Price:</label><br>
            <input type="number" name="price"><br>
            <input type="submit" name="add" value="Add product">
            <input type="submit" name="edit" value="Edit product">
        </form>
    </body>
</html>