<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Inscription</title>
<link type="text/css" rel="stylesheet" href="inscription.css" />
</head>
<body>
	<form method="post" action="Inscription">
		<fieldset>
		<legend>Inscription</legend>
		<div>
			<label for="email">Adresse mail</label> 
			<input type="text" id="email" name="email" value="<c:out value="${user.email}"/>" />
			<span class="erreur">${inscForm.erreurs['email']}</span>
			<br/>
		</div>
		
		<div>
			<label for="pass">Mot de passe</label>
			<input type="password" id="pass" name="pass" value="" /> 
			<span class="erreur">${inscForm.erreurs['pass']}</span>
			<br/>
		</div>
		
		<div>
			<label for="confPass">Confirmation du mot de passe</label> 
			<input type="password" id="confPass" name="confPass" value="" />
			<br/>
		</div>
		
		<div>
			<label for="userName">Nom d'utilisateur</label> 
			<input type="text" id="userName" name="userName" value="<c:out value="${user.userName}"/>" />
			<span class="erreur">${inscForm.erreurs['userName']}</span>
			<br/>
		</div>
		
		<div>
			<input type="submit" value="Inscription" class="boutonSubmit" />
	        <br />
	        <p class="${empty inscForm.erreurs ? 'succes' : 'erreur'}">${inscForm.result}</p>
        </div>
        </fieldset>
	</form>
</body>
</html>