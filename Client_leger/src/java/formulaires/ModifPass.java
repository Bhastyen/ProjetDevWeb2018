package formulaires;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ModifPassForm;
import model.Utilisateur;

/**
 * Servlet implementation class ModifPass
 */
@WebServlet("/ModifPass")
public class ModifPass extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String MODIF_PASS = "/WEB-INF/gestionCompte.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifPass() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(MODIF_PASS).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModifPassForm modifPassForm = new ModifPassForm();
		Utilisateur user = new Utilisateur();
		
		user = modifPassForm.changePass(user, request);
		
		request.setAttribute("user", user);	
		request.setAttribute("modifPassForm", modifPassForm);
		
		this.getServletContext().getRequestDispatcher(MODIF_PASS).forward( request, response );
		
	}

}
