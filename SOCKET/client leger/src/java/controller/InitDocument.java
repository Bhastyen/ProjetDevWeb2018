package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InitDocument
 */
@WebServlet("/InitDocument")
public class InitDocument extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitDocument() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("sessionUser") == null){
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/connexion.jsp").forward(request, response);  //pas de pseudo = connexion
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String[]> docs;
		
		if (request.getSession().getAttribute("sessionUser") == null){
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/connexion.jsp").forward(request, response);  //pas de pseudo = connexion
		}
		
    	// recupere les donnees de la bdd
    	docs = bdd.Connect.getDonneeDocument(((model.Utilisateur) request.getSession().getAttribute("sessionUser")).getEmail());
    	// on definit la liste a afficher dans la jsp
    	request.setAttribute("docs", docs);
    	System.out.println(docs);
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/choixDocument.jsp").forward(request, response);  //pas de pseudo = connexion
	}

}
