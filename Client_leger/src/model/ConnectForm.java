package model;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;




public class ConnectForm {
	
	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();

	public Map<String, String> getErreurs() {
        return erreurs;
    }
	
	public String getResultat() {
        return resultat;
    }
	
	public Utilisateur connectUtilisateur(HttpServletRequest request) {
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
        
        Utilisateur user = new Utilisateur();
        
        try {
    			testEmail(email);
        } catch(Exception e) {
    			erreurs.put("email", e.getMessage());
        } 
        user.setEmail(email);
        
        // recupere le pseudo
        user.setPseudo(bdd.Connect.getPseudo(email));
      
        try {
    			testPass(pass); 		
        } catch(Exception e) {
    			erreurs.put("pass", e.getMessage());
        } 		   
        user.setMotPasse(pass);
        
        // connection a la base de donn�es
        if (email != null && pass != null){
        	System.out.println("Probleme "+bdd.Connect.valideUtilisateur(email, pass));
		    if (!bdd.Connect.valideUtilisateur(email, pass)){
				erreurs.put("bdd", "Identifiant inconnu");
		    }
        }
       
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
	
	public void testPass(String pass) throws java.lang.Exception {
		if(pass == null || pass.trim().length() == 0) {
			throw new Exception("Il faut saisir un mot de passe !");
		}
	}
}
