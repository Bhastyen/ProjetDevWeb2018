<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
</head>

<body>

	<form action="/DocumentController" method="post">
		<fieldset> 
			<legend>Nouveau Document</legend>
			<label for="nom">Nom: <input type="text" placeholder="Veuillez indiquer le nom de votre document" id = "nom" name="nom"/></label>
			<label for="visibilite">Visibilité: 
				<select id="visibilite" name="visibilite">
				  <option value="1">public</option>
				  <option value="2">prive</option>
				</select>
			</label>
			<label for="description">Description:
				<textarea cols="50" rows="6" maxlength="300" placeholder="Entrez votre description ici" id="description" name="description"></textarea>
			</label>
			<input type="submit" value="Valider" id="choixDoc"/>
		</fieldset>
	</form><br/>

</body>

</html>