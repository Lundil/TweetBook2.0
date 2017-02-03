package tools;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.util.ArrayList;

/**	servlet gérant l'authentification ou l'inscription d'un utilisateur */

@WebServlet("/deconnexion")
public class deconnexion extends HttpServlet
{
	public void service( HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		//Récupère session actuelle
    	HttpSession session = request.getSession();
    	//Kill la session
    	session.invalidate();
    	//Redirige vers la page d'acceuil
    	response.sendRedirect("./index.html");
    }
}