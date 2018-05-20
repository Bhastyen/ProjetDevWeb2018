package client.vue;

import client.LoginManager;
import client.model.Document;
import client.model.Model;
import client.model.Noeud;
import client.util.Graph;
import client.util.NoeudType;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/** Ecran de connexion */
public class AjoutNoeudController {
	@FXML
	private TextField titre;
	@FXML
	private TextField nomPere;
	@FXML
	private TextField longueur;
	@FXML
	private TextField largeur;
	@FXML
	private TextField type;
	@FXML
	private TextField description;
	@FXML
	private Model model;
	private Graph graph;
	private Stage stage;
	private int y = 10;
	private int x = 10;
	private Document doc;

	public void initialize() {
	}

	public void initManager(final LoginManager loginManager) {

	}

	private String recupLongueur() {
		if (longueur.getText() != null)
			return longueur.getText();
		return "100";
	}

	private String recupTitre() {
		return titre.getText();
	}

	private String recupNomPere() {
		return nomPere.getText();
	}

	private String recupLargeur() {
		if (largeur.getText() != null)
			return largeur.getText();
		return "50";
	}

	public void ajout() {
		int intLongueur = Integer.parseInt(recupLongueur());
		int intLargeur = Integer.parseInt(recupLargeur());
		String sTitre = recupTitre();
		String sNomPere = recupNomPere();
		String sDescription = recupDescription();
		Noeud test = null;
		if (intLongueur > 1 && intLargeur > 1) {
			for (Noeud noeud : model.getAllnoeuds())
				if (noeud.getTitre().equals(sNomPere))
					test = noeud;

			if (test!=null) {
				Noeud newNoeud=new Noeud(sTitre, sDescription,  intLongueur, intLargeur, x, y,false);
				newNoeud.setPere(test);
				this.doc.addNoeud(newNoeud);
				graph.beginUpdate();
				
				model.addlien(sTitre, sNomPere);

				graph.endUpdate();
				stage.close();
			}
		}

	}

	private String recupDescription() {

		return description.getText();
	}

	public void setModel(Model model) {
		this.model = model;

	}

	public void setGraph(Graph graph) {
		this.graph = graph;

	}
	public void setDoc(Document doc) {
		this.doc = doc;

	}
	public void setStage(Stage stage) {
		this.stage = stage;

	}

	public void setY(int y) {
		this.y = y;

	}

	public void setX(int x) {
		this.x = x;

	}
}
