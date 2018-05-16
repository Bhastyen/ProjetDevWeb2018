<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gestion de compte</title>
</head>
<body>
	<form method="post" action="ModifMail">
		<fieldset>
		<legend>Modifier l'adresse email</legend>
		<label for="email">Nouvelle adresse mail</label> 
		<input type="text" id="email" name="email" value="<c:out value="${user.email}"/>" />
		<span class="erreur">${modifMailForm.erreurs}</span>
		<br/>			
		<input type="submit" value="Modifier mon email" class="boutonSubmit" />
        <br />
		</fieldset>
	</form>
	
	<form method="post" action="ModifPass">
		<fieldset>
		<legend>Modifier le mot de passe</legend>
		<label for="pass">Ancien mot de passe</label>
		<input type="password" id="ancPass" name="pass" value="" /> 
		<span class="erreur">${modifPassFrom.erreurs['pass']}</span>
		<br/>
		<label for="pass">Nouveau mot de passe</label>
		<input type="password" id="nouvPass" name="pass" value="" /> 
		<span class="erreur">${modifPassForm.erreurs['pass']}</span>
		<br/>	
		<label for="pass">Confirmation du nouveau mot de passe</label>
		<input type="password" id="confPass" name="pass" value="" /> 
		<span class="erreur">${modifPassForm.erreurs['pass']}</span>
		<br/>	
		<input type="submit" value="Modifier mon mot de passe" class="boutonSubmit" />
        <br />
		</fieldset>
	</form>
	
	<form method="post" action="ModifUserName">
		<fieldset>
		<legend>Modifier le nom d'utilisateur</legend>
		<label for="userName">Nom d'utilisateur</label> 
		<input type="text" id="userName" name="userName" value="<c:out value="${user.userName}"/>" />
		<span class="erreur">${modifUserNameForm.erreurs['userName']}</span>
		<br/>
		<input type="submit" value="Modifier mon nom d'utilisateur" class="boutonSubmit" />
        <br />
		</fieldset>
	</form>
	
	

</body>
</html>