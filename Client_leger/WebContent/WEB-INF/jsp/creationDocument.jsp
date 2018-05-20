<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link type="text/css" rel="stylesheet" href="choixDocument.css" />
		<link type="text/css" rel="stylesheet" href="bandeau.css" />
	<title>Insert title here</title>
</head>

<body>
  <div class = "bandeau">
    	<div class ="onglet"><a href="/ProjetWeb2018/redirection?choix=1" >Choix document</a></div>
    	<div class ="onglet"><a href="/ProjetWeb2018/redirection?choix=2" >Gestion compte</a></div>
    	<div class ="onglet"><a href="/ProjetWeb2018/redirection?choix=3" >Gestion groupe</a></div>
    	<div class ="onglet"><a href="/ProjetWeb2018/redirection?choix=4" >Déconnexion</a></div>   
    </div>
	<form class="formNouveauDoc" action="DocumentController" method="post">
		<fieldset> 
			<legend>Nouveau Document</legend>
			<input type="hidden" value="1" name="nouveau"/>
			<label for="nom">Nom: <input type="text" placeholder="Veuillez indiquer le nom de votre document" id = "nom" name="nom"/></label><br/>
			<label for="visibilite">Visibilité: 
				<select id="visibilite" name="visibilite">
				  <option value="1">public</option>
				  <option value="2">prive</option>
				</select>
			</label><br/>
			<label for="description">Description:
				<textarea cols="50" rows="6" maxlength="300" placeholder="Entrez votre description ici" id="description" name="description"></textarea>
			</label><br/>
			<input type="submit" value="Valider" id="choixDoc"/>
		</fieldset>
	</form><br/>

</body>

</html>