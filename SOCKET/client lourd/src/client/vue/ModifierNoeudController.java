package client.vue;

import client.LoginManager;
import client.model.Model;
import client.model.Noeud;
import client.util.Graph;
import client.util.Liens;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/** Ecran de connexion */
public class ModifierNoeudController {

	@FXML
	private TextField champ;
	private Node noeud;
	private Stage stage;
	private Model model;
	private int type;
	private Graph graph;
	@FXML
	private Label typeLabel;

	public void initialize() {
	}

	public void initManager(final LoginManager loginManager) {

	}

	/**
	 * Renvoie true si la connexion est accéptée false sinon
	 */
	private String recupChamp() {
		return champ.getText();
	}

	public void modifier() {
		Noeud test = null;
		switch (type) {
		case 0:
			if (recupChamp() != null)
				((Noeud) noeud).setTitre(recupChamp());
			break;
		case 1:
			if (recupChamp() != null)
				((Noeud) noeud).setDescription(recupChamp());
			break;
		case 2:
			if (recupChamp() != null) {
				((Noeud) noeud).setLongueur(Integer.parseInt(recupChamp()));
				Rectangle view = new Rectangle(((Noeud) noeud).getLargeur(), ((Noeud) noeud).getLongueur());

				view.setStroke(Color.DODGERBLUE);
				view.setFill(Color.DODGERBLUE);

				((Noeud) noeud).setView(view);
			}
			break;
		case 3:
			if (recupChamp() != null) {
				((Noeud) noeud).setLargeur(Integer.parseInt(recupChamp()));
				Rectangle view = new Rectangle(((Noeud) noeud).getLargeur(), ((Noeud) noeud).getLongueur());

				view.setStroke(Color.DODGERBLUE);
				view.setFill(Color.DODGERBLUE);

				((Noeud) noeud).setView(view);

			}
			break;
		case 4:
			if (recupChamp() != null)
				for (Noeud noeud : model.getAllnoeuds())
					if (noeud.getTitre().equals(recupChamp()))
						test = noeud;
			if (test != null) {
				((Noeud) noeud).setPere(test);
				for (Liens lien : model.getAllliens()) {
					if (lien.getSource().equals((Noeud) noeud) || lien.getTarget().equals((Noeud) noeud))
						lien.dellien();

				}
				graph.beginUpdate();
				model.addlien(((Noeud) noeud).getTitre(), test.getTitre());
				graph.endUpdate();
			}
			break;
		}
		stage.close();
	}

	public void setNoeud(Node node) {
		this.noeud = node;

	}

	public void setStage(Stage stage) {
		this.stage = stage;

	}

	public void setModel(Model model) {
		this.model = model;

	}

	public void setGraph(Graph graph) {
		this.graph = graph;

	}

	public void setType(int type) {
		this.type = type;
		switch (type) {
		case 0:
			typeLabel.setText("Nouveau Titre:");
			break;
		case 1:
			typeLabel.setText("Nouvelle description:");
			break;
		case 2:
			typeLabel.setText("Nouvelle longueur");
			break;
		case 3:
			typeLabel.setText("Nouvelle largeur");
			break;
		case 4:
			typeLabel.setText("Nouveu père");
			break;

		}

	}
}
