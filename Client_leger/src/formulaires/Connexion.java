package formulaires;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ConnectForm;
import model.Utilisateur;

/**
 * Servlet implementation class Connexion
 */
@WebServlet("/Connexion")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String CONNECT = "/WEB-INF/jsp/connexion.jsp";
	public static final String CONNECTED = "/WEB-INF/jsp/connexion.jsp";
	
	
    
    public Connexion() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 this.getServletContext().getRequestDispatcher(CONNECT).forward( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ConnectForm form = new ConnectForm();

        Utilisateur utilisateur = form.connectUtilisateur(request);

        HttpSession session = request.getSession();

        request.setAttribute( "form", form );
        request.setAttribute( "utilisateur", utilisateur );
        
        if (form.getErreurs().isEmpty()){
        	System.out.println("Probleme1 "+form.getErreurs().isEmpty());
            session.setAttribute("sessionUser", utilisateur);
            this.getServletContext().getRequestDispatcher("/DocumentController").forward( request, response );
        } else {        	
        	session.setAttribute("sessionUser", null);
        	this.getServletContext().getRequestDispatcher(CONNECT).forward( request, response );
        }
    }
	
}
