package tools;
import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.util.ArrayList;

/**	servlet gérant l'authentification ou l'inscription d'un utilisateur */

@WebServlet("/tools/ControlFriend")
public class ControlFriend extends HttpServlet{

	HttpSession session = null;
	User user = null;
	public void service( HttpServletRequest request, HttpServletResponse response){
		try{

			session = request.getSession(true);
			user = (User) session.getAttribute("user");
			if(user != null){

				Model model = new Model();
				model.initialize();

				//supprime un utilisateur du groupe
				if(request.getParameter("removeFriend") != null){
					model.removeFriend(
					Integer.parseInt(request.getParameter("removeFriendId")), user);
					model.initialize();
					user.setFriends(model.getFriend(user));
					response.sendRedirect("../amis.jsp");
				}

				//récupère les infos de l'utilisateur pour afficher son profil
				else if(request.getParameter("friendToDisplayProfil") != null){
					User friend = model.getFriend(Integer.valueOf(request.getParameter(request.getParameter("friendToDisplayProfilId"))));
					session.setAttribute(("friendToDisplay"), friend);
					response.sendRedirect("friendProfil.jsp");
				}

				//ajoute un ami
				else if(request.getParameter("addFriend") != null){
					model.addFriend(Integer.valueOf(request.getParameter("addFriendId")), user);
					model.initialize();
					user.setFriends(model.getFriend(user));
					response.sendRedirect("../amis.jsp");
				}

				//récupère la liste des amis potentiels
				else if(request.getParameter("getPotentialFriends").equals("true")){
					ArrayList<User> potentialFriends = model.getPotientialFriend(user);
					session.setAttribute(("potentialFriends"), potentialFriends);
					response.sendRedirect("../amis.jsp");
				}

				//ServletContext servletContext = getServletContext();
				//RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/index.html");
				//dispatcher.forward(request, response);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}