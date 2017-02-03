package tools;

import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

/**	servlet gérant l'authentification ou l'inscription d'un utilisateur */

@WebServlet("tools/ControlUser")
public class ControlUser extends HttpServlet{

	User user = null;
	HttpSession session = null;
	public void service(HttpServletRequest request, HttpServletResponse response){
		try{

			Model model = new Model();
			//inscription
			if(request.getParameter("signin") != null){
				if(request.getParameter("signin").equals("true")){
					model.initialize();
					model.createUser(
					(String) request.getParameter("inFirstName"), (String) request.getParameter("inLastName"),
					(String) request.getParameter("inMail"), (String) request.getParameter("inPhoneNumber"),
					(String) request.getParameter("inDate"), (String) request.getParameter("inPlace"),
					(String) request.getParameter("inAddress"), (String) request.getParameter("inLogin"),
					(String) request.getParameter("inPassword"));
					System.out.println("inscription réussie");
					response.setContentType("text/html; charset=UTF-8");
					response.setCharacterEncoding("UTF-8");
					response.sendRedirect("../signup.html");
				}
			}
			else
				response.sendRedirect("../error.html");

		} catch (Exception e){
			e.printStackTrace();
		}
	}
}