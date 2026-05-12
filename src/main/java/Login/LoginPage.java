package Login;

import jakarta.servlet.RequestDispatcher;
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
import java.sql.SQLException;


@WebServlet("/LoginPage")
public class LoginPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public LoginPage() {
        super();
       
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		@SuppressWarnings("unused")
		PrintWriter out = response.getWriter(); //  to use out in our code 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
					Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/GranthJagat", "root", "2172004Hj");
					String name=request.getParameter("username");  // from the form 
					String password=request.getParameter("password");
					
//					now to check the value entered by user with values in database 
//					and if value matches we need to show login successfully 
					PreparedStatement ps=con.prepareStatement("select username from UserLogin where username=? and password=?"); //here USERNAME is my column name & login is table name 
//					PreparedStatement ps=con.prepareStatement("Select * from UserLogin");
					ps.setString(1, name);  // to replace ? with actual value 
					ps.setString(2, password);
					
				ResultSet rs=ps.executeQuery();
				
//				now if login is succesfull user should be redirect to other page 
				if(rs.next()) 
				{
					RequestDispatcher rd=request.getRequestDispatcher("Mainpage.html"); // it will redirect to welcome.html page
					rd.forward(request, response);                                   // it will redirect to welcome.html page
					
				}
//					if login is unsuccessful then we have to show error message & redirect user to the initial login page (Loginform.jsp or .html)
				else
				{
					RequestDispatcher rd=request.getRequestDispatcher("Error.html");
					rd.forward(request, response);
					
//					out.println("<a href=Loginform.jsp> TRY AGAIN !</a>");      //using anchertag to redirct at iitial page
				}
			
				
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
	}

}
	


