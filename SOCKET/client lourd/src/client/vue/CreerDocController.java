package client.vue;

import client.LoginManager;
import client.MainApp;
import client.model.Document;
import client.model.Groupe;
import client.model.Model;
import client.util.NoeudType;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/** Ecran de connexion */
public class CreerDocController {
	@FXML
	private TextField titre;

	@FXML
	private TextField description;
	@FXML
	private Model model;
	private Stage stage;

	private MainApp mainApp;

	public void initialize() {
	}

	public void initManager(final LoginManager loginManager) {

	}

	private String recupTitre() {
		return titre.getText();
	}

	public void ajout() {
		if (recupTitre() != null && recupDescription() != null) {
			Document doc = new Document(new Groupe(mainApp.getUser()), recupTitre(), recupDescription());

			doc.getContenu().addnoeud("Racine", "Noeud racine", NoeudType.RECTANGLE, 150, 100, 250, 250,true);
			mainApp.addDoc(doc);
			stage.close();
		}
	}

	private String recupDescription() {

		return description.getText();
	}

	public void setModel(Model model) {
		this.model = model;

	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

	}

	public void setStage(Stage stage) {
		this.stage = stage;

	}

}
