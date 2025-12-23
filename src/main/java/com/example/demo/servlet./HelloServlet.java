package com.example.demo.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Requirement 1: Servlet Requirement
 * - Create class com.example.demo.servlet.HelloServlet extending HttpServlet.
 * - Annotate it with @WebServlet(urlPatterns = "/hello-servlet").
 * - Must have a protected void doGet(...) method.
 */
@WebServlet(urlPatterns = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        // Setting the response content type
        resp.setContentType("text/plain");
        
        // Writing the response body
        resp.getWriter().write("Servlet Response");
    }
}