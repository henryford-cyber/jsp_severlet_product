package AdminServlet.Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import AdminServlet.DAO.AdUserDao;
import AdminServlet.Model.AdUser;
 
@WebServlet(urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }
 
     
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html;charset=UTF-8");
		 PrintWriter out=response.getWriter();
		 try {
			 String name =request.getParameter("txtname");
			 String password =request.getParameter("txtpassword");
			 AdUserDao dao =new AdUserDao();
			 AdUser a=dao.checkLogin(name, password);
			 if(a==null) {
				 response.sendRedirect("Admin/login.jsp");
			 }else {
				 response.sendRedirect("Admin/thanhcong.jsp");
			 }
			 
		 } catch (Exception e) {
			 
		}
	}
	 

	 
	

}
