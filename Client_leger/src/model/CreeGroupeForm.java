package model;

import javax.servlet.http.HttpServletRequest;

public class CreeGroupeForm {
	private String resultat;
	private String erreur;
	
	public String getErreur() {
		return erreur;
	}
	
	public String getResultat() {
		return resultat;
	}
	
	public Groupe creerGroupe(HttpServletRequest request) {
		String nomGroupe = request.getParameter("nomGroupe");
		Groupe groupe = new Groupe();
		
		try {
			testNomGroupe(nomGroupe);
		} catch(Exception e) {
			erreur = e.getMessage();
			resultat = "Création de groupe échouée !";
		}
		resultat = "Création de groupe réussie !";
		groupe.setNomGroupe(nomGroupe);
		return groupe;
	}
	
	public void testNomGroupe(String nomGroupe) throws java.lang.Exception {
		if(nomGroupe == null || nomGroupe.trim().length() == 0) {
			throw new Exception("Il faut indiquer un nom de groupe !");
		}
		
	}
}
