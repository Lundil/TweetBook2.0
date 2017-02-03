package tools;
import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.util.ArrayList;

/**	servlet gérant l'authentification ou l'inscription d'un utilisateur */

@WebServlet("tools/ControlPublication")
public class ControlPublication extends HttpServlet{

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
				model.initialize();

				//créé une publication
				if(request.getParameter("newPublication") != null){
					if(request.getParameter("newPublication").equals("true")){
						model.createPublication(
						(String) request.getParameter("titleNewPublication"), 
						(String) request.getParameter("contentNewPublication"), user.getId());
						model.initialize();
						user.setPublications(model.getPublication(user));
						response.sendRedirect("../profil.jsp");
					}
				}

				//récupère les publications de ses amis
				else if(request.getParameter("getFriendsPublication") != null){
					if(request.getParameter("getFriendsPublication").equals("true")){
						ArrayList<Publication> friendsPublication = model.getFriendsPublication(user);
						session. setAttribute("friendsPublication", friendsPublication);
						response.sendRedirect("../actu.jsp");
					}
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}