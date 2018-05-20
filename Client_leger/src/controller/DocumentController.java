package controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DocumentPerso;
import model.Documents;

import org.w3c.dom.Document;

import bdd.Connect;
import parser.ParserDOMDoc;

/**
 * Servlet implementation class DocumentController
 */
@WebServlet("/DocumentController")
public class DocumentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DocumentController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// verifie s'il existe un pseudo ou non
		if (request.getSession().getAttribute("sessionUser") == null){
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/connexion.jsp").forward(request, response);  //pas de pseudo = connexion
		}else if (request.getParameter("nouveau") != null){
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/creationDocument.jsp").forward(request, response);
		}
	}
	
	protected void ouverture_fichier(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		// recupere nom du doc
		String nom = (String) request.getParameter("nom");
		String nomDoc = (String) request.getParameter("lien");
		DocumentPerso doc = new DocumentPerso(0, 0, nom);   // a changer
		Document docDOM;
				
		// verifie s'il est deja chargee en memoire
		for (int i = 0; i<Documents.documents.size(); i++){
			if (nomDoc.equals(Documents.documents.get(i).getNom())){
				// indique quel document on doit utilisee
				request.getSession().setAttribute("numero_doc", i);
						 
				// envoie vers la page d'affichage du doc
				getServletContext().getRequestDispatcher("/WEB-INF/jsp/pagePrincipale.jsp").forward(request, response);
			}
		}
				
		// utilise parser DOM pour charge le fichier en memoire sinon
		docDOM = new ParserDOMDoc().ParserDoc("xml/"+nomDoc+"0.xml");
				
		// ajoute le document a la liste des documents utilises
		if (docDOM != null){
			//doc.setDoc(docDOM);
			Documents.documents.add(doc);
		}
				
		// indique quel document on doit utilisee
		request.getSession().setAttribute("numero_doc", Documents.documents.size()-1);
				 
		// envoie vers la page d'affichage du doc
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/pagePrincipale.jsp").forward(request, response);

	}
	
	protected void creation_document(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int id, up = 0, vis;
		String lien;
		DocumentPerso doc;
		Document docDOM;
		
		// recupere nom du doc
		String email = ((model.Utilisateur) request.getSession().getAttribute("sessionUser")).getEmail();
		String nomDoc = (String) request.getParameter("nom");
		String description = (String) request.getParameter("description");
		String visibilite = (String) request.getParameter("visibilite");

		String path = getServletContext().getRealPath("/");
		
		// creer une url menant au fichier XML
		if (visibilite.equals("public")) vis = 1;
		else vis = 2;
		
		// cree le document
		doc = new DocumentPerso(0, 0, nomDoc);  // TODO a changer avec une nouvelle classe
		
		// ajoute le document sans DOM
		Documents.documents.add(doc);
		
		System.out.println("PATH "+path);
        
		// cree un fichier contenant seulement les entetes
		Documents.documents.get(Documents.documents.size()-1).ecrireFichier(0, nomDoc, "0", "", nomDoc+"0.xml");  // TODO methode a implementer dans la nouvelle classe
		// recharge le document apres ecriture
		docDOM = new ParserDOMDoc().ParserDoc("./xml/"+nomDoc+"0.xml");  // TODO plus obligatoire
		//Documents.documents.get(Documents.documents.size()-1).setDoc(docDOM);
		System.out.println("Email "+email);
		
		// recuperation de l'id du createur
		id = Connect.getId(email);
		System.out.println("Id " + id);
		try {
			// enregistrement aupres de la base de donnee
			lien = new File("./xml/"+nomDoc+"0.xml").getAbsolutePath();
			up = Connect.insertDocument(nomDoc, lien, id, vis, description);
			if (up != 0){
				// indique quel document on doit utilisee
				request.getSession().setAttribute("numero_doc", Documents.documents.size()-1);
				
				// envoie vers la page d'affichage du doc
				this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/pagePrincipale.jsp").forward(request, response);
			}else{
				// TODO redirection avec message d'erreur
				
			}
		} catch (NullPointerException e) {
			System.out.println(e);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getSession().getAttribute("sessionUser") == null){
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/connexion.jsp").forward(request, response);  //pas de pseudo = connexion
		}
		
         if (request.getParameter("nouveau") == null || request.getParameter("nouveau").isEmpty()){
        	ouverture_fichier(request, response);
        }else creation_document(request, response);
	}

}
