package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class redirection
 */
@WebServlet("/redirection")
public class redirection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public redirection() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int choix = Integer.parseInt(request.getParameter("choix"));
		if(choix == 1){
			this.getServletContext().getRequestDispatcher("/InitDocument").forward(request, response);
		}else if(choix == 2){
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/gestionCompte.jsp").forward(request, response);
		}else if(choix == 3){
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/pagePrincipale.jsp").forward(request, response);
		}else if(choix == 4){
			this.getServletContext().getRequestDispatcher("/deconnexion").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
