package tools;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.util.ArrayList;

/**	servlet gérant l'authentification ou l'inscription d'un utilisateur */

@WebServlet("/connexion")
public class connexion extends HttpServlet
{
	public void service( HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		response.sendRedirect("./profil.jsp");
	}
}