package model;

import javax.servlet.http.HttpServletRequest;

public class ModifUserNameForm {
	private String resultat;
	private String erreur;
	
	public String getResultat() {
		return resultat;
	}
	
	public String getErreur() {
		return erreur;
	}
	
	public Utilisateur changeUserName(Utilisateur user, HttpServletRequest request) {
		
		String userName = request.getParameter("userName");
		
		try {
			testUserName(userName,user);
		} catch(Exception e) {
			erreur = e.getMessage();
		}
		user.setEmail(userName);
		return user;		
	}
	
	public void testUserName(String UserName,Utilisateur user) throws java.lang.Exception {
		if(UserName != null && UserName.trim().length() != 0 ) {
			if(UserName.trim().length() < 3) {
				throw new Exception("Le nom d'utilisateur doit contenir au moins 3 caractères !");
			}	
			else{
				bdd.Connect.pseudoUpdate(user.getEmail(), UserName);
			}
		}
		else {
			throw new Exception("Il faut indiquer un nom d'utilisateur !");
		}	
	}
}
