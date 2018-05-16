package formulaires;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AjoutUserForm;
import model.Utilisateur;

/**
 * Servlet implementation class AjoutUser
 */
@WebServlet("/AjoutUser")
public class AjoutUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String AJOUT = "/WEB-INF/ajoutUser.jsp";
       
    public AjoutUser() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(AJOUT).forward( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AjoutUserForm form = new AjoutUserForm();

        Utilisateur user = form.ajouteUtilisateur(request);
        
        request.setAttribute("form", form);
		request.setAttribute("user", user);
		
		this.getServletContext().getRequestDispatcher(AJOUT).forward( request, response );

	}

}
