package client.util;


import model.Utilisateur;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class UtilisateurUtil implements Serializable{
   static final long serialVersionUID = -687991492884005033L;
	private static final AtomicInteger count = new AtomicInteger(0);
	private String pseudo;
	private String email;
	private String avatar;
	private String motPasse;
    public int type;

	public UtilisateurUtil() {
		this(null, null,0);
	}

	/**
	 * Constructeur.
	 * 
	 * @param pŝeudo
	 * @param mdp
	 */
	public UtilisateurUtil(String pŝeudo, String mdp,int type) {

		this.pseudo = pŝeudo;
		this.motPasse = mdp;

		// Some initial dummy data, just for convenient testing.
		this.avatar = "laulle";
		this.email = "MonMail";
                this.type=type;

	}
        public UtilisateurUtil(Utilisateur user,int type){
            this.pseudo=user.getPseudo();
            this.motPasse=user.getMotPasse();
            this.email=user.getEmail();
            this.type=type;
        }

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo=pseudo;
	}

	

	public String getMotPasse() {
		return motPasse;
	}

	public void setMotPasse(String MotPasse) {
		this.motPasse=MotPasse;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String Email) {
		this.email=Email;
	}

	

}