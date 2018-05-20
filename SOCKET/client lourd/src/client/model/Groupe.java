package client.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Groupe {
	private static final AtomicInteger count = new AtomicInteger(0);
	public final int idGroupe;
	public final Utilisateur CreateurGroupe;
	private List<Utilisateur> Membres = new ArrayList<Utilisateur>();

	public Groupe(Utilisateur Createur) {
		this.idGroupe = count.incrementAndGet();
		this.CreateurGroupe = Createur;
		Membres.add(Createur);
	}

	public void ajoutMembre(Utilisateur membre) {
		Membres.add(membre);
	}

	public List<Utilisateur> getMembres() {
		return Membres;

	}

	public boolean delMembre(Utilisateur membre) {
		return Membres.remove(membre);

	}

	public Utilisateur getCreateur() {

		return CreateurGroupe;
	}
}
