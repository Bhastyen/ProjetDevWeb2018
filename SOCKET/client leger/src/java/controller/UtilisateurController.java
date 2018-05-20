package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import encrypt.cryptText;
import bdd.Connect;

/**
 * Servlet implementation class UtilisateurController
 */
@WebServlet("/UtilisateurController")
public class UtilisateurController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UtilisateurController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO lancer la socket?
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.sendRedirect("/WEB-INF/jsp/connexion.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Date date;
		ResultSet r;
		String pseudo, email;
		String mdp = cryptText.crypt((String) request.getAttribute("mdp"));

		
		// verifie le cas dans lequel on est
		if ((boolean) request.getAttribute("connexion")){
			// recupere les donnees pour verifier l'id
			pseudo = (String) request.getAttribute("pseudo");
			
			// verification
		    r = Connect.processSelect("SELECT Pseudo FROM utilisateurs WHERE Pseudo = "+pseudo+" AND Mdp = "+mdp);
		    if (r != null){
		    	// ajout de l'utilisateur a la liste des utilisateurs connectes TODO a discuter
		    	//Utilisateur.utilisateurs.add(new Utilisateur(pseudo));
		    	
		    	// ajout de l'utilisateur a l'objet session
		    	//request.getSession().setAttribute("pseudo", pseudo);
		    	
		    	// envoie vers la page de choix des documents
		    	//getServletContext().getRequestDispatcher("/WEF-INF/jsp/choixDoc.jsp").forward(request, response);
		    }else{
		    	//response.sendRedirect("/WEF-INF/jsp/connexion.jsp");
		    }
		}else{
			// recupere les donnees pour creation du compte
			pseudo = (String) request.getAttribute("pseudo");
			email = (String) request.getAttribute("email");
			date = new Date();
			
			// verifie si l'adresse email n'est pas deja utilisee
			r = Connect.processSelect("SELECT Pseudo FROM utilisateurs WHERE email = "+email);
			if (r != null){
				// renvoie vers la page d'inscription avec un code signifiant le probleme
				//request.setAttribute("ERREUR", 1);
				//getServletContext().getRequestDispatcher("/WEF-INF/jsp/inscription.jsp").forward(request, response);
			}
			
			// ajout a la bdd
			Connect.processUpdate("INSERT INTO utilisateurs(Pseudo, Mdp, Date_inscription, email) VALUES ("+pseudo+","+mdp+","+date+","+email+")");
			
			// envoie vers la page de connexion
			//response.sendRedirect("/WEF-INF/jsp/connexion.jsp");
		}
	}

}
