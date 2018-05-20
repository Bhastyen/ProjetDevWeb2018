package formulaires;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ModifUserNameForm;
import model.Utilisateur;

/**
 * Servlet implementation class ModifUserName
 */
@WebServlet("/ModifUserName")
public class ModifUserName extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String MODIF_USER_NAME= "/WEB-INF/gestionCompte.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifUserName() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(MODIF_USER_NAME).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModifUserNameForm modifUserNameForm = new ModifUserNameForm();
		Utilisateur user = new Utilisateur();
		user.setPseudo("lajante");
		
		request.setAttribute("user", user);
		
		user = modifUserNameForm.changeUserName(user, request);
		
		request.setAttribute("modifUserNameForm", modifUserNameForm);
		
		this.getServletContext().getRequestDispatcher(MODIF_USER_NAME).forward( request, response );
	}

}
