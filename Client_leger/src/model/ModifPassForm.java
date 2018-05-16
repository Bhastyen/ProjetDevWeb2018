package model;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class ModifPassForm {
	
	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();
	
	public Map<String, String> getErreurs() {
        return erreurs;
    }
	
	public String getResultat() {
        return resultat;
    }
	
	public Utilisateur changePass(Utilisateur user, HttpServletRequest request) {
		
		String ancPass = request.getParameter("ancPass");
		String nouvPass = request.getParameter("nouvPass");
		String confPass = request.getParameter("confPass");
		
		try {
			testPass(ancPass, nouvPass, confPass); 		
		} catch(Exception e) {
			erreurs.put("pass", e.getMessage());
		}
		
		user.setPass(nouvPass);
		return user;
	}
	
	public void testPass(String ancPass, String nouvPass, String confPass) throws java.lang.Exception {
		if(nouvPass != null && nouvPass.trim().length() != 0 && confPass != null && confPass.trim().length() != 0) {
			if(!nouvPass.equals(confPass)) {
				throw new Exception("Les mots de passe ne corespondent pas !");
			}
			else if(nouvPass.trim().length() < 5) {
				throw new Exception("Le mot de passe est trop court (au moins 5 caractères requis) !");
			}
		}
		else {
			throw new Exception("Il faut saisir un mot de passe et le confirmer !");
		}
	}

}
