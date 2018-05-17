package controller;

import java.io.IOException;
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
		List<String[]> docs = new ArrayList<>(); 
		String[] tab;
		int vis;
		String nom; String lien; String createur; String visibilite = ""; String description;
		java.sql.Date date;
		
		System.out.println("Probleme 10");
		// verifie s'il existe un pseudo ou non
		if (request.getSession().getAttribute("user") == null || ((String) request.getSession().getAttribute("user")).isEmpty()){
			this.getServletContext().getRequestDispatcher("/WEF-INF/jsp/connexion.jsp").forward(request, response);  //pas de pseudo = connexion
		}else{
		    // recupere les donnees de la bdd
			java.sql.ResultSet result = bdd.Connect.getDonneeDocument(((model.Utilisateur) request.getSession().getAttribute("user")).getEmail());
			
		    if (result != null){
			 try {
				while (result.next()){
					   // on stocke ses donnees		   
				       nom = result.getString("Nom");
				       lien = result.getString("Lien_document");
				       createur = result.getString("Nom_groupe");
				       date = result.getDate("DateCreation");
				       description = result.getString("Description");
				       vis = result.getInt("Visibilite");
				       if (vis == 1) visibilite = "Public";
				       else visibilite = "Prive";
				       
				       tab = new String[7];
				       tab[0] = nom; tab[1] = lien; tab[2] = createur; tab[3] = date.toString(); tab[4] = description; tab[5] = visibilite; //tab[6] = nom; 
				       docs.add(tab);
				   }
			 } catch (SQLException e) {
				e.printStackTrace();
		   	 }
		    }
		    
		    // on definit la liste a afficher dans la jsp
		    request.setAttribute("docs", docs);
			this.getServletContext().getRequestDispatcher("/WEF-INF/jsp/choixDocument.jsp").forward(request, response);  // pseudo = choix du document a ouvrir
		}
	}
	
	protected void ouverture_fichier(HttpServletRequest request, HttpServletResponse response){
		//int numero = 0;
		
		// recupere nom du doc
		String nom = (String) request.getAttribute("nom");
		String nomDoc = (String) request.getAttribute("lien");
		DocumentPerso doc = new DocumentPerso(0, 0, nom);
		Document docDOM;
				
		// verifie s'il est deja chargee en memoire
		for (int i = 0; i<Documents.documents.size(); i++){
			if (nomDoc.equals(Documents.documents.get(i).getNom())){
				// indique quel document on doit utilisee
				request.getSession().setAttribute("numero_doc", i);
						 
				// envoie vers la page d'affichage du doc
				//getServletContext().getRequestDispatcher("/WEF-INF/jsp/pagePrincipale.jsp").forward(request, response);
			}
		}
				
		// utilise parser DOM pour charge le fichier en memoire sinon
		docDOM = new ParserDOMDoc().ParserDoc("/WEF-INF/xml/"+nomDoc+"/"+nomDoc+".xml");
				
		// ajoute le document a la liste des documents utilises
		if (docDOM != null){
			doc.setDoc(docDOM);
			Documents.documents.add(doc);
		}
				
		// indique quel document on doit utilisee
		request.getSession().setAttribute("numero_doc", Documents.documents.size()-1);
				 
		// envoie vers la page d'affichage du doc
		//getServletContext().getRequestDispatcher("/WEF-INF/jsp/pagePrincipale.jsp").forward(request, response);

	}
	
	protected void creation_document(HttpServletRequest request, HttpServletResponse response){
		int id, up;
		ResultSet result;
		DocumentPerso doc;
		Document docDOM;
		
		// recupere nom du doc
		String pseudo = (String) request.getSession().getAttribute("pseudo");
		String nomDoc = (String) request.getAttribute("nom");
		String description = (String) request.getAttribute("description");
		String visibilite = (String) request.getAttribute("visibilite");
		
		// cree le document
		doc = new DocumentPerso(0, 0, nomDoc);
		
		// ajoute le document sans DOM
		Documents.documents.add(doc);
		
		// cree un fichier contenant seulement les entetes
		Documents.documents.get(Documents.documents.size()-1).ecrireFichier(0, nomDoc, "0");
		
		// recharge le document apres ecriture
		docDOM = new ParserDOMDoc().ParserDoc("/WEF-INF/xml/"+nomDoc+"/"+nomDoc+"0.xml");
		Documents.documents.get(Documents.documents.size()-1).setDoc(docDOM);
		
		// recuperation de l'id du createur
		result = Connect.processSelect("SELECT Numero FROM utilisateurs WHERE Pseudo="+pseudo);
		try {
			id = result.getInt("Numero");

			// enregistrement aupres de la base de donnee
			up = Connect.processUpdate("INSERT INTO document(Nom, Lien_document, Createur, Visibilite, Description) VALUES ("+nomDoc+","+"/WEF-INF/xml/"+nomDoc+"/"+nomDoc+"0.xml+"+","+id+","+visibilite+","+description+")");
			if (up != 0){
				// indique quel document on doit utilisee
				request.getSession().setAttribute("numero_doc", Documents.documents.size()-1);
				
				// envoie vers la page d'affichage du doc
				//getServletContext().getRequestDispatcher("/WEF-INF/jsp/pagePrincipale.jsp").forward(request, response);
			}else{
				// TODO redirection avec message d'erreur
				
			}
		} catch (SQLException | NullPointerException e) {
			System.out.println(e);
            // TODO redirection avec message d'erreur
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("nouveau") == null || request.getParameter("nouveau").isEmpty())
        	ouverture_fichier(request, response);
        else creation_document(request, response);
	}

}
