package client.model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class Noeud extends Pane {
	Node view;
	String titre;
	private int pos_X;
	private int pos_Y;
	private int longueur;
	private int largeur;
	private String description = "";
	List<Noeud> children = new ArrayList<>();
	Noeud pere=null;
boolean root;
	public boolean isRoot() {
	return root;
}

public void setRoot(boolean root) {
	this.root = root;
}

	public Noeud(String titre, String description, int longueur, int largeur, int x, int y,boolean root) {
		this.description = description;
		this.titre = titre;
		this.largeur = largeur;
		this.longueur = longueur;
		this.pos_X = x;
		this.pos_Y = y;
		this.root=root;
	

	}

	public int getLongueur() {
		return longueur;
	}

	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}

	public int getLargeur() {
		return largeur;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	public double getPosX() {
		return pos_X;
	}

	public void setX(int x) {
		this.pos_X = x;
	}

	public double getPosY() {
		return pos_Y;
	}

	public void setPosY(int y) {
		this.pos_Y = y;
	}

	public String getTitre() {
		return titre;
	}

	public void remove() {
		getChildren().clear();
	}

	public void setTitre(String titre) {
		Text titreText = new Text(longueur / 3, largeur / 3, titre);
		this.titre = titre;
		getChildren().remove(1);
		getChildren().add(titreText);
	}

	public void addnoeudChild(Noeud noeud) {
		children.add(noeud);
	}

	public List<Noeud> getnoeudChildren() {
		return children;
	}

	public void setPere(Noeud noeud) {
		this.pere = noeud;
	}

	public Noeud getPere() {
		return pere;
	}

	public void removenoeudChild(Noeud noeud) {
		children.remove(noeud);
	}

	public void setView(Node view) {
		Text titreText = new Text(longueur / 3, largeur / 2, titre);
		this.view = view;
		getChildren().addAll(view, titreText);

	}

	public Node getView() {
		return this.view;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String toString() {
		return this.titre + " " + this.description + " " + this.longueur + " " + this.largeur + " " + this.pos_X + " "
				+ this.pos_Y;
	}
}