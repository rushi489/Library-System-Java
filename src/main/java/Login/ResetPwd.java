package Login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class Update
 */
@WebServlet("/ResetPwd")
public class ResetPwd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetPwd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve form data
        String name = request.getParameter("username");
        String pass = request.getParameter("password");
        String newpass = request.getParameter("Newpassword");

        try {
            // Load MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/GranthJagat", "root", "2172004Hj"
            );

            // Check if the ID exists in the database
            String checkQuery = "SELECT * FROM UserLogin WHERE username = ? and password = ?";
            PreparedStatement checkPs = con.prepareStatement(checkQuery);
            checkPs.setString(1, name);
            checkPs.setString(2, pass);
            @SuppressWarnings("unused")
			ResultSet rs = checkPs.executeQuery();

           
                // If ID exists, update the record
                String updateQuery = "UPDATE UserLogin SET password = ? WHERE username = ?";
                PreparedStatement updatePs = con.prepareStatement(updateQuery);
                updatePs.setString(1, newpass);
                updatePs.setString(2, name);

                int status = updatePs.executeUpdate();
                if (status > 0) {
                    out.print("<p style='color:green;'>Record updated successfully.</p>");
                } 
                else {
                    out.print("<p style='color:red;'>Error updating record.</p>");
                }
           

            // Redirect to a page (optional)
           request.getRequestDispatcher("LoginPage.html").include(request, response);

        } catch (Exception e) {
            out.print("<p style='color:red;'>Error: " + e.getMessage() + "</p>");
        }
    }

}