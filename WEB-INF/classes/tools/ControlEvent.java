package tools;
import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.util.ArrayList;

/**	servlet gérant l'authentification ou l'inscription d'un utilisateur */

@WebServlet("/tools/ControlEvent")
public class ControlEvent extends HttpServlet
{

	HttpSession session = null;
	User user = null;
	public void service( HttpServletRequest request, HttpServletResponse response){
		try{
			response.setContentType("text/html; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			session = request.getSession(true);
			user = (User) session.getAttribute("user");
			if(user != null){

				Model model = new Model();
				/*model.initialize();

				//supprime un utilisateur du groupe
				if(request.getParameter("removeUserFromEvent").equals("true")){
					model.removeUserFromEvent(
					(String) request.getParameter("IDEventToRemove"), user,
					(String) request.getParameter("removeUserFromEventId"));
					user.setEvents(model.getEvent(user));
					response.sendRedirect("event.jsp");
				}

				//ajoute un utilisateur au groupe
				else if(request.getParameter("addUserToGroup").equals("true")){
					model.addUserToGroup((String) request.getParameter("IDGroupToAdd"),
					(String) request.getParameter("addUserToGroupId"));
					user.setGroups(model.getGroup(user));
					response.sendRedirect("group.jsp");
				}

				//création d'un nouveau groupe et ajout de ses utilisateurs
				else if(request.getParameter("newGroup").equals("true")){
					String idGroup = model.createGroup((String) request.getParameter("titleNewGroup"), user);
					model.addUsersToGroup(idGroup, (ArrayList<User>) session.getAttribute("addUsersToGroupIds"));
					user.setGroups(model.getGroup(user));
					response.sendRedirect("group.jsp");
				}

				//suppression d'un groupe
				else if(request.getParameter("deleteGroup").equals("true")){
					model.deleteGroup((String) request.getParameter("IDDeleteGroup"), user);
					user.setGroups(model.getGroup(user));
					response.sendRedirect("group.jsp");
				}
				*/
				//ServletContext servletContext = getServletContext();
				//RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/index.html");
				//dispatcher.forward(request, response);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}