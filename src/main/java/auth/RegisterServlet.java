package auth;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.ConnectionDetails;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Connection con = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) throws ServletException {
    	if(con == null) {
    		try {
				con = ConnectionDetails.getConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block	
				e.printStackTrace();
			}
    	}
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String fullname = request.getParameter("fullname");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		String sql = "insert into users (fullname, username, password) values (?, ?, ?)";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, fullname);
			ps.setString(2, username);
			ps.setString(3, password);
			
			int res = ps.executeUpdate();
			PrintWriter pw = response.getWriter();
			pw.println("<html>");
			pw.println("<head><link rel=\"stylesheet\" href=\"./style.css\" /></head>");
			pw.println("<body><div class=\"container\">");
			if(res == 1) {
				pw.println("<p>Register successfull...</p>");
				pw.println("<a href=\"login.html\">Login here</p>");
			} else {
				pw.println("<div class=\"input-wrapper\"><p class=\"error\">There was an error registering the user...</p>");
				pw.println("<a href=\"register.html\">Register again here</p></div>");
			}
			pw.println("</div></body></html>");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con = null;
		}
	}
}
