<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ajouter un utilisateur</title>
<link type="text/css" rel="stylesheet" href="style.css" />
</head>
<body>
	<form method="post" action="	AjoutUser">
		<fieldset>
		<legend>Ajouter un utilisateur</legend>
		
		<label for="userName">Nom d'utilisateur</label> 
		<input type="text" id="userName" name="userName" value="<c:out value="${user.userName}"/>" />
		<span class="erreur">${form.erreur}</span>
		<br/>
		
		<input type="submit" value="Ajouter" class="boutonSubmit" />
        <br />
        <p class="${empty form.erreur ? 'succes' : 'erreur'}">${form.resultat}</p>
		
		</fieldset>
	</form>
</body>
</html>