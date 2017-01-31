package tools;
import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.util.ArrayList;

/**	servlet gérant l'authentification ou l'inscription d'un utilisateur */

@WebServlet("tools/ControlFriend")
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

				//ajoute un ami
				if(request.getParameter("addFriend") != null){
					if(request.getParameter("addFriend").equals("true")){
						model.addFriend(Integer.valueOf(request.getParameter("addFriendId")), user);
						model.initialize();
						user.setFriends(model.getFriend(user));
						response.sendRedirect("../amis.jsp");
					}
				}

				//récupère la liste des amis potentiels
				else if(request.getParameter("getPotentialFriends") != null){
					if(request.getParameter("getPotentialFriends").equals("true")){
						ArrayList<User> potentialFriends = model.getPotientialFriend(user);
						//response.setParameter(("potentialFriends"), potentialFriends);
						response.sendRedirect("../amis.jsp");
					}
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}