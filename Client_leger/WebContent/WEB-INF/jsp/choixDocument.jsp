<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link type="text/css" rel="stylesheet" href="choixDocument.css" />
	<link type="text/css" rel="stylesheet" href="bandeau.css" />
	<title>Choix du document</title>
</head>

<body>

    <div class = "bandeau">
    	<div class ="onglet"><a href="/ProjetWeb2018/redirection?choix=1" >Choix document</a></div>
    	<div class ="onglet"><a href="/ProjetWeb2018/redirection?choix=2" >Gestion compte</a></div>
    	<div class ="onglet"><a href="/ProjetWeb2018/redirection?choix=3" >Gestion groupe</a></div>
    	<div class ="onglet"><a href="/ProjetWeb2018/redirection?choix=4" >Déconnexion</a></div>   
    </div>
    <div class ="contenant">
	    <div class ="listeDocuments">
		    <c:forEach items="${docs}" var="doc">
		        <div class ="doc">
					<form action="DocumentController" method="post">
						 <fieldset> 
					  	      <legend>${doc[0]}</legend>
					 		  <input class="champ" type="hidden" value="${doc[0]}" name="nom"/> </br>
							  <input class="champ" type="hidden" value="${doc[1]}" name="lien"/> </br>
							  <label class="champ" for="createur">Créateur: ${doc[2]}</label><input type="hidden" value="${doc[2]}" id="createur" name="createur"/> <br>
							  <label class="champ" for="visibilite">Visibilité: ${doc[3]}</label><input type="hidden" value="${doc[3]}" id="visibilite" name="visibilite"/> <br>
							  <label class="champ" for="description">Description: ${doc[4]}</label><input type="hidden" value="${doc[4]}" id="description" name="description"/> <br>
							  <input class="champ" type="submit" value="Ouvrir" id="choixDoc"/><br>
						 </fieldset>
					</form><br/>
				</div>
		    </c:forEach>
		</div>
			<form class="creerDoc" action="DocumentController" method="get">
				<fieldset>
					<legend>Nouveau document</legend>
					<input type="hidden" value="1" name="nouveau"/>
					<input type="submit" value="Créer"/>
				</fieldset>
			</form><br/>
   </div>
</body>

</html>