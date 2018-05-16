<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Creer un groupe</title>
<link type="text/css" rel="stylesheet" href="style.css" />
</head>
<body>
	<body>
	<form method="post" action="CreationGroupe">
		<fieldset>
			<legend>Creer un groupe</legend>
	
			<label for="email">Nom du groupe</label> 
			<input type="text" id="nomGroupe" name="nomGroupe" value="<c:out value="${groupe.nomGroupe}"/>" />
			<span class="erreur">${creerGroupeForm.erreur}</span>
			<br/>
			
			<input type="submit" value="Creer" class="boutonSubmit" />
        		<br/>
        		<p class="${empty creerGroupeForm.erreur ? 'succes' : 'erreur'}">${creerGroupeForm.resultat}</p>
			
		</fieldset>
     </form>
</body>
</html>