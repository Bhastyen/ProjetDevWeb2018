<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Connexion</title>
<link type="text/css" rel="stylesheet" href="connexion.css" />
</head>
<body>
	<form method="post" action="Connexion">
		<fieldset>
			<legend>Connexion</legend>
			
			<div>
				<label for="email">Adresse mail</label> 
				<input type="text" id="email" name="email" value="<c:out value="${user.email}"/>" />
				<span class="erreur">${form.erreurs['email']}</span>
				<br/>
			</div>
			
			<div>
				<label for="pass">Mot de passe</label>
				<input type="password" id="pass" name="pass" value="" />
				<span class="erreur">${form.erreurs['pass']}</span>
				<br/>
			</div>
			
			<div>
				<input type="submit" value="Connexion" class="boutonSubmit" />
				<a href="/ProjetWeb2018/Inscription" >Créer un compte</a>
	        		<br/>
        	</div>
        	</fieldset>
     </form>
</body>
</html>