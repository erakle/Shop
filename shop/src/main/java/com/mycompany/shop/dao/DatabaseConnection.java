package com.mycompany.shop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.mycompany.shop.product.Product;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/shop?useSSL=false";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "1234";
    
    public void addProduct(Product product) throws SQLException, ClassNotFoundException{
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO products" + "  (name, quantity, price) VALUES (?, ?, ?);");
            ps.setString(1, product.getName());
            ps.setInt(2, product.getQuantity());
            ps.setInt(3, product.getPrice());
            System.out.println(ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Product searchProduct(String productName) throws SQLException, ClassNotFoundException {
        Product product = new Product();
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            PreparedStatement ps = con.prepareStatement("SELECT name, quantity, price FROM products WHERE name=?;");
            ps.setString(1, productName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                int quantity = rs.getInt("quantity");
                int price = rs.getInt("price");
                product.setName(name);
                product.setQuantity(quantity);
                product.setPrice(price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
    
    public List<Product> selectAllProducts () throws ClassNotFoundException {
        List<Product> Products = new ArrayList<>();
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            PreparedStatement ps = con.prepareStatement("SELECT * FROM products");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                int quantity = rs.getInt("quantity");
                int price = rs.getInt("price");
                Products.add(new Product(name, quantity, price));
            } 
        } catch (SQLException e) {
                    e.printStackTrace();
                    }
        return Products;
        }
    
    public void deleteProduct (String productName) throws SQLException, ClassNotFoundException {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            PreparedStatement ps = con.prepareStatement("delete from products where name = ?;");
            ps.setString(1, productName);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void editProduct(Product product) throws SQLException, ClassNotFoundException{
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            PreparedStatement ps = con.prepareStatement("update products set name = ?,quantity = ?, price = ? where name = ?;");
            ps.setString(1, product.getName());
            ps.setInt(2, product.getQuantity());
            ps.setInt(3, product.getPrice());
            ps.setString(4, product.getName());
            System.out.println(ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}