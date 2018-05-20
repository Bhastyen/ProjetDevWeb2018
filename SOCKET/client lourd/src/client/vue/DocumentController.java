package client.vue;

import java.net.URL;
import java.util.ResourceBundle;

import client.MainApp;
import client.model.Noeud;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class DocumentController implements Initializable {

	private MainApp mainApp;
	@FXML
	private Label nomDocLabel;
	@FXML
	private Label nomCreateurLabel;
	@FXML
	private Label descriptionLabel;
	@FXML
	private Label datcreaLabel;
	@FXML
	private Label nomNoeudLabel;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		nomDocLabel.setText(mainApp.doc.getNomDoc());
		nomCreateurLabel.setText(mainApp.doc.getNomCreateur());
		descriptionLabel.setText(mainApp.doc.getDescription());
		datcreaLabel.setText(mainApp.doc.getDateCreation().toString());

	}

	public void setNoeud(Node node) {
		descriptionLabel.setText(((Noeud) node).getDescription());
		nomNoeudLabel.setText(((Noeud) node).getTitre());

	}

}