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
    <c:forEach items="${request.docs}" var="doc">
		<form action="DocumentController" method="post">
			 <fieldset> 
		  	      <legend>${doc[0]}</legend>
		 		  <input type="hidden" value="${doc[0]}" name="nom"/>
				  <input type="hidden" value="${doc[1]}" name="lien"/>
				  <label for="createur">Créateur: ${doc[2]}</label><input type="hidden" value="${doc[2]}" id="createur" name="createur"/>
				  <label for="visibilite">Visibilité: ${doc[3]}</label><input type="hidden" value="${doc[3]}" id="visibilite" name="visibilite"/>
				  <label for="description">Description: ${doc[4]}</label><input type="hidden" value="${doc[4]}" id="description" name="description"/>
				  <input type="submit" value="Ouvrir" id="choixDoc"/>
			 </fieldset>
		</form><br/>
    </c:forEach>
	
	<form action="DocumentController" method="get">
		<fieldset>
			<legend>Nouveau document</legend>
			<input type="hidden" value="1" name="nouveau"/>
			<input type="submit" value="Créer"/>
		</fieldset>
	</form><br/>

</body>

</html>