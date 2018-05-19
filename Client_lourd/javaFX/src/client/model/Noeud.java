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
	private String description="";
	List<Noeud> children = new ArrayList<>();
	Noeud pere;


	

	public Noeud(String titre,String description,int longueur,int largeur,int x,int y) {
		this.description=description;
		this.titre=titre;
		this.largeur=largeur;
		this.longueur=longueur;
		this.pos_X=x;
		this.pos_Y=y;
		
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
public void remove(){
       getChildren().clear();
}
	public void setTitre(String titre) {
            Text titreText=new Text(longueur/3,largeur/3,titre);
		this.titre = titre;
                getChildren().remove(1);
                getChildren().add(titreText);
	}

	public void addCellChild(Noeud cell) {
		children.add(cell);
	}

	public List<Noeud> getCellChildren() {
		return children;
	}

	public void setPere(Noeud cell) {
		this.pere = cell;
	}

	public Noeud getPere() {
		return pere;
	}

	public void removeCellChild(Noeud cell) {
		children.remove(cell);
	}

	public void setView(Node view) {
		Text titreText=new Text(longueur/3,largeur/3,titre);
		this.view = view;
		getChildren().addAll(view,titreText);

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
		return this.titre+
				" "+this.description+" "
				+this.longueur+" "+
				this.largeur+" "+this.pos_X+" "+this.pos_Y;
	}
}