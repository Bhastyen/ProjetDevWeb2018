package formulaires;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CreeGroupeForm;
import model.Groupe;

/**
 * Servlet implementation class CreationGroupe
 */
@WebServlet("/CreationGroupe")
public class CreationGroupe extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String C_GROUPE= "/WEB-INF/creerGroupe.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreationGroupe() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(C_GROUPE).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CreeGroupeForm form = new CreeGroupeForm();
		Groupe groupe = form.creerGroupe(request);
	
		
		request.setAttribute("creerGroupeForm", form);
		request.setAttribute("groupe", groupe);
		
		this.getServletContext().getRequestDispatcher(C_GROUPE).forward( request, response );
	}

}
