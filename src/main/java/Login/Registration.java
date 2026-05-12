package Login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve form data
        String name= request.getParameter("username");
        String pass= request.getParameter("password");
        String email = request.getParameter("email");

        try {
            // Load MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/GranthJagat", "root", "2172004Hj"
            );

            // Insert query
            String query = "INSERT INTO UserLogin (username, password, email) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            // Set parameters
            ps.setString(1, name);
            ps.setString(2, pass);
            ps.setString(3, email);

            // Execute update
            int status = ps.executeUpdate();
            if (status > 0) {
            	request.getRequestDispatcher("LoginPage.html").include(request, response);
            } 
          
            else {
                out.print("<p style='color:red;'>Error adding record.</p>");
            }

//            // Redirect to a page (optional)
//            request.getRequestDispatcher("Home.html").include(request, response);

        } catch (Exception e) {
            out.print("<p style='color:red;'>Error: " + e.getMessage() + "</p>");
        }
    }
}