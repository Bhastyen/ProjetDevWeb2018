package model;

import javax.servlet.http.HttpServletRequest;

public class ModifMailForm {
	private String resultat;
	private String erreur;
	
	public String getResultat() {
		return resultat;
	}
	
	public String getErreur() {
		return erreur;
	}
	
	public Utilisateur changeMail(Utilisateur user, HttpServletRequest request) {
		
		String email = request.getParameter("email");
		
		try {
			testEmail(email);
		} catch(Exception e) {
			erreur = e.getMessage();
		}
		user.setEmail(email);
		return user;
	}
	
	public void testEmail(String email) throws java.lang.Exception {
		if(email != null && email.trim().length() != 0) {
			if(!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
				throw new Exception("L'addresse email est invalide !");
			}
		}
		else {
			throw new Exception("Il faut saisir une addresse mail !");
		}
	}
}
