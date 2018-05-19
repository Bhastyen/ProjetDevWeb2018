package client.vue;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import client.LoginManager;
import client.MainApp;
import client.model.Model;
import client.util.CellType;
import client.util.Graph;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DocumentController implements Initializable {

	@FXML
	private TextField largeur;
	private Model model;
	private Graph graph;
	private MainApp mainApp;

	@FXML
	private void formeCirculaire(ActionEvent event) {
		// panneau.changer(TypeForme.CERCLE);
	}

	@FXML
	private void formeCarrée(ActionEvent event) {
		// panneau.changer(TypeForme.CARRÉ);
	}

	@FXML
	private void formeCarréeArrondi(ActionEvent event) {
		// panneau.changer(TypeForme.CARRÉARRONDI);
	}

	@FXML
	private void changerLargeur(ActionEvent event) {
		// panneau.changer(Double.parseDouble(largeur.getText()));
	}

	@FXML
	private void toutEffacer(ActionEvent event) {
		

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}

	public void setModel(Model model) {
		this.model = model;

	}

	public void setGraph(Graph graph) {
		this.graph = graph;

	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

	}

}