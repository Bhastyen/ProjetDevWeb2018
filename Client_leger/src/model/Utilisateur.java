package model;

import java.util.concurrent.atomic.AtomicInteger;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class Utilisateur {
	private static final AtomicInteger count = new AtomicInteger(0);
	private final StringProperty pseudo;
	private final StringProperty email;
	private final StringProperty avatar;
	private final StringProperty motPasse;


	public Utilisateur() {
		this(null, null);
	}

	/**
	 * Constructeur.
	 * 
	 * @param pŝeudo
	 * @param mdp
	 */
	public Utilisateur(String pŝeudo, String mdp) {
		
		this.pseudo = new SimpleStringProperty(pŝeudo);
		this.motPasse = new SimpleStringProperty(mdp);

		// Some initial dummy data, just for convenient testing.
		this.avatar = new SimpleStringProperty("laulle");
		this.email = new SimpleStringProperty("MonMail");

	}

	public String getPseudo() {
		return pseudo.get();
	}

	public void setPseudo(String pseudo) {
		this.pseudo.set(pseudo);
	}

	public StringProperty pseudoProperty() {
		return pseudo;
	}

	public String getMotPasse() {
		return motPasse.get();
	}

	public void setMotPasse(String MotPasse) {
		this.motPasse.set(MotPasse);
	}

	public StringProperty motPasseProperty() {
		return motPasse;
	}

	public String getEmail() {
		return email.get();
	}

	public void setEmail(String Email) {
		this.email.set(Email);
	}

	public StringProperty emailProperty() {
		return email;
	}

	public String getAvatar() {
		return avatar.get();
	}

	public void setAvatar(String Avatar) {
		this.avatar.set(Avatar);
	}

	public StringProperty avatarProperty() {
		return avatar;
	}

	

}