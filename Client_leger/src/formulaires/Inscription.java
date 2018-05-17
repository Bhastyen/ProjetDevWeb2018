package formulaires;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.InscForm;
import model.Utilisateur;

/**
 * Servlet implementation class Inscription
 */
@WebServlet("/Inscription")
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INSC = "/WEB-INF/jsp/inscription.jsp";
	public static final String CONNECT = "/WEB-INF/jsp/connexion.jsp";
    
    public Inscription() {
        super();
    }
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 this.getServletContext().getRequestDispatcher(INSC).forward( request, response );
	}

	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		InscForm inscForm = new InscForm();
		Utilisateur user = inscForm.inscUtilisateur(request);
		
		request.setAttribute("inscForm", inscForm);
		request.setAttribute("user", user);
		
		if (inscForm.getResult() != null){
			this.getServletContext().getRequestDispatcher(INSC).forward( request, response );
		}else{
			this.getServletContext().getRequestDispatcher(CONNECT).forward(request, response);
		}
	}
	
}
