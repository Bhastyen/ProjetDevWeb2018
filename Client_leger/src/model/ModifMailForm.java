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
			testEmail(email,user);
		} catch(Exception e) {
			erreur = e.getMessage();
		}
		user.setEmail(email);
		return user;
	}
	
	public void testEmail(String email,Utilisateur user) throws java.lang.Exception {
		if(email != null && email.trim().length() != 0) {
			if(!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
				throw new Exception("L'adresse email est invalide !");
			}
			else{

				bdd.Connect.emailUpdate(user.getEmail(), email);
			}
		}
		else {
			throw new Exception("Il faut saisir une adresse mail !");
		}
	}
}
