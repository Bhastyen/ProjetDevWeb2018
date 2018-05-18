package client.model;

import java.util.concurrent.atomic.AtomicInteger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Groupe {
	private static final AtomicInteger count = new AtomicInteger(0);
	public final int idGroupe;
	public final Utilisateur CreateurGroupe;
	private ObservableList<Utilisateur> Membres = FXCollections.observableArrayList();

	public Groupe(Utilisateur Createur) {
		this.idGroupe = count.incrementAndGet();
		this.CreateurGroupe = Createur;
		Membres.add(Createur);
	}

	public void ajoutMembre(Utilisateur membre) {
		Membres.add(membre);
	}

	public ObservableList<Utilisateur> getMembres() {
		return Membres;

	}

	public boolean delMembre(Utilisateur membre) {
		return Membres.remove(membre);

	}

	public Utilisateur getCreateur() {
		// TODO Auto-generated method stub
		return CreateurGroupe;
	}
}
