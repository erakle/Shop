package com.mycompany.shop.connection;

import com.mycompany.shop.dao.DatabaseConnection;
import com.mycompany.shop.product.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductsServlet extends HttpServlet {
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            if (request.getParameter("new") != null) {
                showNewForm(request, response);
            } else if (request.getParameter("add") != null) {
                addProduct(request, response);
            } else if (request.getParameter("search") != null) {
                searchProduct(request, response);
            } else if (request.getParameter("showAll") != null) {
                listProducts(request, response);
            } else if (request.getParameter("edit") != null) {
                editProduct(request, response);
            } else if (request.getParameter("delete") != null) {
                deleteProduct(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(ProductsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void addProduct (HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        
        if ((request.getParameter("name").length() != 0) && (request.getParameter("quantity").length() != 0)
            && (request.getParameter("price").length() != 0) && request.getParameter("name").matches("[a-zA-Z]+")) {
            try {
                String name = request.getParameter("name");
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                int price = Integer.parseInt(request.getParameter("price"));
                Product product = new Product(name, quantity, price);
                DatabaseConnection db = new DatabaseConnection();
                db.addProduct(product);
            }catch (ClassNotFoundException ex) {
                Logger.getLogger(ProductsServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            listProducts(request, response);
        } else {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Please enter correct product name!');");
            out.println("location='productForm.jsp';");
            out.println("</script>");
        }
    }
    
    private void searchProduct (HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ClassNotFoundException, ServletException {
        
        Product product = new Product();
        String productName = request.getParameter("productName");
        if (productName.matches("[a-zA-Z]+")) {
            if (productName.length() != 0) {
                try {
                    DatabaseConnection db = new DatabaseConnection();
                    product = db.searchProduct(productName);
                    List<Product> listProducts = new ArrayList<>();
                    listProducts.add(product);
                    request.setAttribute("listProducts", listProducts);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                    dispatcher.forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Please enter correct product name!');");
            out.println("location='index.jsp';");
            out.println("</script>");
        }
    }
    
    private void listProducts (HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List<Product> listProducts = new ArrayList<>();
        DatabaseConnection db = new DatabaseConnection();
        try {
            listProducts = db.selectAllProducts();
            request.setAttribute("listProducts", listProducts);
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("productForm.jsp");
	dispatcher.forward(request, response);
    }
    
    private void editProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
        if ((request.getParameter("name").length() != 0) && (request.getParameter("quantity").length() != 0)
            && (request.getParameter("price").length() != 0) && request.getParameter("name").matches("[a-zA-Z]+")) {
            try {
                String name = request.getParameter("name");
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                int price = Integer.parseInt(request.getParameter("price"));
                Product product = new Product(name, quantity, price);
                DatabaseConnection db = new DatabaseConnection();
                db.editProduct(product);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ProductsServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            listProducts(request, response);
        } else {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Please enter correct product name!');");
            out.println("location='productForm.jsp';");
            out.println("</script>");
        }
    }
        

    private void deleteProduct (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        String productName = request.getParameter("productName");
        if (productName.matches("[a-zA-Z]+")) {
            if (productName.length() != 0) {
                try {
                    DatabaseConnection db = new DatabaseConnection();
                    db.deleteProduct(productName);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            listProducts(request, response);
        } else {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Please enter correct product name!');");
            out.println("location='index.jsp';");
            out.println("</script>");
        }
    }
}