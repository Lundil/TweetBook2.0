package tools;
import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.util.ArrayList;

/**	servlet gérant l'authentification ou l'inscription d'un utilisateur */

@WebServlet("/tools/ControlGroup")
public class ControlGroup extends HttpServlet{

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
				if(request.getParameter("removeUserFromGroup").equals("true")){
					model.removeUserFromGroup(
					Integer.parseInt(request.getParameter(request.getParameter("IDGroupToRemove"))), user,
					Integer.parseInt(request.getParameter(request.getParameter("removeUserFromGroupId"))));
					user.setGroups(model.getGroup(user));
					response.sendRedirect("group.jsp");
				}

				//ajoute un utilisateur au groupe
				else if(request.getParameter("addUserToGroup").equals("true")){
					model.addUserToGroup(Integer.parseInt(request.getParameter(request.getParameter("IDGroupToAdd"))),
					Integer.parseInt(request.getParameter(request.getParameter("addUserToGroupId"))));
					user.setGroups(model.getGroup(user));
					response.sendRedirect("group.jsp");
				}

				//création d'un nouveau groupe et ajout de ses utilisateurs
				else if(request.getParameter("newGroup").equals("true")){
					String idGroup = model.createGroup((String) request.getParameter("titleNewGroup"), user);
					model.addUsersToGroup(Integer.parseInt(idGroup), (ArrayList<User>) session.getAttribute("addUsersToGroupIds"));
					user.setGroups(model.getGroup(user));
					response.sendRedirect("group.jsp");
				}

				//suppression d'un groupe
				else if(request.getParameter("deleteGroup").equals("true")){
					model.deleteGroup(Integer.parseInt(request.getParameter(request.getParameter("IDDeleteGroup"))), user);
					user.setGroups(model.getGroup(user));
					response.sendRedirect("group.jsp");
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