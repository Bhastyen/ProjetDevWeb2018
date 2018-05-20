package model;


import client.util.UtilisateurUtil;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class Utilisateur implements Serializable{
	private static final AtomicInteger count = new AtomicInteger(0);
	private String pseudo;
	private String email;
	private String avatar;
	private String motPasse;

	public Utilisateur() {
		this(null, null);
	}
        public Utilisateur(UtilisateurUtil user){
            this.pseudo=user.getPseudo();
            this.motPasse=user.getMotPasse();
            this.email=user.getEmail();
        }

	/**
	 * Constructeur.
	 * 
	 * @param pŝeudo
	 * @param mdp
	 */
	public Utilisateur(String pŝeudo, String mdp) {

		this.pseudo = pŝeudo;
		this.motPasse = mdp;

		// Some initial dummy data, just for convenient testing.
		this.avatar = "laulle";
		this.email = "MonMail";

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