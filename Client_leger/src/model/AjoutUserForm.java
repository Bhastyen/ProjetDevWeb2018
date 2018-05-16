package model;

import javax.servlet.http.HttpServletRequest;

public class AjoutUserForm {
	String resultat;
	String erreur;
	
	public String getErreur() {
		return erreur;
	}
	
	public String getResultat() {
        return resultat;
    }
	
	public Utilisateur ajouteUtilisateur(HttpServletRequest request) {
		String nomUser = request.getParameter("userName");
		
		Utilisateur user = new Utilisateur();
		
		try {
			testUserName(nomUser);
		} catch(Exception e) {
			erreur = e.getMessage();
		}
		
		if(erreur.isEmpty()) {
    			resultat = "Inscription réussie !";
		}
		else {
    			resultat = "Inscription échouée !";
		}
		
		user.setUserName(nomUser);
		return user;
		
	}
	
	public void testUserName(String UserName) throws java.lang.Exception {
		if(UserName != null && UserName.trim().length() != 0 ) {
			if(UserName.trim().length() < 3) {
				throw new Exception("Le nom d'utilisateur doit contenir au moins 3 caractères !");
			}	
		}
		else {
			throw new Exception("Il faut indiquer un nom d'utilisateur !");
		}	
	}
	
	
}
