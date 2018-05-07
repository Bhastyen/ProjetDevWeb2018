package ch.makery.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDate;

import ch.makery.address.MainApp;
import ch.makery.address.model.Document;
import ch.makery.address.util.LoginManager;

public class AcceuilConnectedOverviewController {

	@FXML
	private TableView<Document> documentsTable;
	@FXML
	private TableColumn<Document, String> nomDocColumn;
	@FXML
	private TableColumn<Document, String> nomCreateurColumn;
	@FXML
	private TableColumn<Document, LocalDate> dateCreationColumn;
	@FXML
	private TableColumn<Document, String> descriptionColumn;
	@FXML
	private Label pseudoLabel;

	// Utile pour les infos
	private MainApp mainApp;

	public AcceuilConnectedOverviewController() {
	}

	/**
	 * Fonction d'initialisation appelée à la lecture du fxml
	 */
	@FXML
	private void initialize() {
		// Initialise le tableau
		nomDocColumn.setCellValueFactory(cellData -> cellData.getValue().nomDocProperty());
		nomCreateurColumn.setCellValueFactory(cellData -> cellData.getValue().nomCreateurProperty());
		dateCreationColumn.setCellValueFactory((cellData -> cellData.getValue().dateCreationProperty()));
		descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());

		showNom();
	}

	/**
	 * appelée par le mail qui se met en parametre
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		documentsTable.setItems(mainApp.getDocData());
	}

	public void handleSeDeconnecter() {
		mainApp.seDeconnecter();

	}

	private void showNom() {

		pseudoLabel.setText("Bienvenue " + LoginManager.getUser() + "!");

	}

	/**
	 * Appelée si on clique sur gestion de compte
	 */
	@FXML
	private void handleGestionCompte() {
		mainApp.gestionCompte();

	}

}
