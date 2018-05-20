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
	public static final String MODIF_MAIL = "/WEB-INF/jsp/gestionCompte.jsp";
       
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
		ModifMailForm modifMailForm = new ModifMailForm();
		Utilisateur user = new Utilisateur();
		//user.setMail("pierre@pierre.fr");
		user = (Utilisateur) request.getSession().getAttribute("sessionUser");

		user = modifMailForm.changeMail(user, request);
	//	request.setAttribute("modifMailForm", modifMailForm);
		
		request.setAttribute("sessionUser", user);
		
		this.getServletContext().getRequestDispatcher(MODIF_MAIL).forward( request, response );
		
	}

}
