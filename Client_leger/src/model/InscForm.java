package model;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class InscForm {
	
    private String result;
    private Map<String, String> erreurs = new HashMap<String, String>();
    
    public String getResult(){
        return result;
    }

    public Map<String, String> getErreurs(){
        return erreurs;
    }
    
   public Utilisateur inscUtilisateur(HttpServletRequest request) {
    	
    		String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        String confirmPass = request.getParameter("confPass");
        String UserName = request.getParameter("userName"); 
        
        Utilisateur user = new Utilisateur();
        
        try {
        		testEmail(email);
        } catch(Exception e) {
        		erreurs.put("email", e.getMessage());
        } 
        user.setMail(email);
        
        try {
        		testPass(pass, confirmPass); 		
        } catch(Exception e) {
        		erreurs.put("pass", e.getMessage());
        }    
        user.setPass(pass);
        
        try {
        		testUserName(UserName);
        } catch(Exception e) {
        		erreurs.put("userName", e.getMessage());
        }   
        user.setUserName(UserName);
        
        if(erreurs.isEmpty()) {
        		result = "Inscription réussie !";
        }
        else {
        		result = "Inscription échouée !";
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
	
	public void testPass(String pass, String passConfirm) throws java.lang.Exception {
		if(pass != null && pass.trim().length() != 0 && passConfirm != null && passConfirm.trim().length() != 0) {
			if(!pass.equals(passConfirm)) {
				throw new Exception("Les mots de passe ne corespondent pas !");
			}
			else if(pass.trim().length() < 5) {
				throw new Exception("Le mot de passe est trop court (au moins 5 caractères requis) !");
			}
		}
		else {
			throw new Exception("Il faut saisir un mot de passe et le confirmer !");
		}
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
