package formulaires;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ModifMailForm;
import model.Utilisateur;

/**
 * Servlet implementation class ModifMail
 */
@WebServlet("/ModifMail")
public class ModifMail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String MODIF_MAIL = "/WEB-INF/gestionCompte.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifMail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(MODIF_MAIL).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModifMailForm modifPassForm = new ModifMailForm();
		Utilisateur user = new Utilisateur();
		user.setEmail("pierre@pierre.fr");
		
		request.setAttribute("user", user);
		
		user = modifPassForm.changeMail(user, request);
		
		request.setAttribute("modifPassForm", modifPassForm);
		
		this.getServletContext().getRequestDispatcher(MODIF_MAIL).forward( request, response );
		
	}

}
