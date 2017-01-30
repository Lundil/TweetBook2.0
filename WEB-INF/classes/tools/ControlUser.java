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
				model.initialize();
				model.createUser(
				(String) request.getParameter("inFirstName"), (String) request.getParameter("inLastName"),
				(String) request.getParameter("inMail"), (String) request.getParameter("inPhoneNumber"),
				(String) request.getParameter("inDate"), (String) request.getParameter("inPlace"),
				(String) request.getParameter("inAddress"), (String) request.getParameter("inLogin"),
				(String) request.getParameter("inPassword"));
				System.out.println("inscription réussie");
				response.sendRedirect("../signup.html");
			}
			
			//authentification
			else if(request.getParameter("signup") != null){
				model.initialize();
				user = model.getUser((String) request.getParameter("upLogin"), (String) request.getParameter("upPassword"));
				if(user != null){

					//ajout des publications et des amis au profil
					model.initialize();
					user.setPublications(model.getPublication(user));
					model.initialize();
					user.setFriends(model.getFriend(user));

					//création de la session
					session = request.getSession(true);
					session.setAttribute("user", user);
					response.sendRedirect("../profil.jsp");
				}
				else
					response.sendRedirect("../ysufiudyufqiuyqyd.html");
			}
			//ServletContext servletContext = getServletContext();
			//RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/index.html");
			//dispatcher.forward(request, response);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}