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
            table{
                border-collapse: collapse;
                margin: 25px 0;
                font-size: 0.9em;
                font-family: sans-serif;
                min-width: 300px;
                box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
            }
            th{
                background-color: #009879;
                color: #ffffff;
                text-align: left;
            }
        </style>
    </head>
    <body>
        <form action="ProductsServlet" method="get">
            <input type="submit" name="new" value="Add/Edit product">
            <input type="submit" name="showAll" value="Show all products"><br>
            <label for="productName">Product name:</label><br>
            <input type="text" name="productName"><br>
            <input type="submit" name="search" value="Search product">
            <input type="submit" name="delete" value="Delete product">
        </form>
        <table border="1" cellpadding="5">
            <caption><h2>List of Products</h2></caption>
            <tr>
                <th>Name</th>
                <th>Quantity</th>
                <th>Price</th>
            </tr>
            <c:forEach var="product" items="${listProducts}">
                <tr>
                    <td><c:out value="${product.name}" /></td>
                    <td><c:out value="${product.quantity}" /></td>
                    <td><c:out value="${product.price}" /></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>