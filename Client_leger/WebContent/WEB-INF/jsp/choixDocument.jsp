<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Choix du document</title>
</head>

<body>
	<% 
	    int vis;
	    String nom;
	    String lien;
	    String date;
	    String createur;
	    String visibilite;
	    String description;
	    
		java.sql.ResultSet result = bdd.Connect.processSelect("SELECT documents.Nom,groupe.Nom_groupe,documents.Visibilite,documents.Description"+
				                                               " FROM utilisateurs,membres,groupes,documents"+
		                                                       " WHERE utilisateurs.Numero = membres.Num_utilisateur"+
				                                               " AND membres.Num_groupe = groupe.Numero_groupe"+
		                                                       " AND groupe.Numero_doc = documents.Numero_doc"+
				                                               " AND utilisateurs.Pseudo = ${session.utilisateur.pseudo}");
	                                                                                                                                                                              
	   if (result != null){
		   while (result.next()){
		       nom = result.getString("Nom");
		       lien = result.getString("Lien_document");
		       createur = result.getString("Nom_groupe");
		       //date = result.getString("DateCreation");
		       description = result.getString("Description");
               vis = result.getInt("Visibilite");
		       if (vis == 1) 
		    	   visibilite = "Public";
		       else if (vis == 2) 
		           visibilite = "Prive";
		   %>
			   <form action="/DocumentController" method="post">
			       <fieldset> 
			           <legend>${nom}</legend>
				   	   <input type="hidden" value="${nom}" name="nom"/>
				   	   <input type="hidden" value="${lien}" name="lien"/>
				   	   <label for="createur">Créateur: ${createur}</label><input type="hidden" value="${createur}" id="createur" name="createur"/>
				   	   <label for="visibilite">Visibilité: ${visibilite}</label><input type="hidden" value="${visibilite}" id="visibilite" name="visibilite"/>
				   	   <label for="description">Description: ${description}</label><input type="hidden" value="${description}" id="description" name="description"/>
				       <input type="submit" value="Valider" id="choixDoc"/>
			       </fieldset>
			   </form><br/>
	<% 	   }
	   }
	%>
	
	
	<form action="/WEB-INF/jsp/creationDocument.jsp" method="get">
		<fieldset> 
			<legend>Nouveau document</legend>
			<input type="hidden" value="1" name="nouveau"/>
			<input type="submit" value="Valider"/>
		</fieldset>
	</form><br/>

</body>

</html>