package ch.makery.address.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

import ch.makery.address.MainApp;
import ch.makery.address.model.Document;

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
	}

	/**
	 * appelée par le mail qui se met en parametre
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		showNom();
		documentsTable.setItems(mainApp.getDocData());
	}
	/**
	 * Action effectuée lors d'un clique sur le bouton se deconnecter
	 */
	public void handleSeDeconnecter() {
		mainApp.getLoginManager().logout();
		mainApp.connected(false);

	}

	private void showNom() {

		pseudoLabel.setText("Bienvenue " + mainApp.getUser().getPseudo() + "!");

	}

	/**
	 * Appelée si on clique sur gestion de compte
	 * Cette fonction ouvre la page de gestion de compte
		 *
		 * @param user
		 *            L'utilisateur à editer
		 * @return true si l'utilisateur confirme.
	 */
	@FXML
	private boolean handleGestionCompte() {

		
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(MainApp.class.getResource("view/GestionCompte.fxml"));
				AnchorPane page = (AnchorPane) loader.load();

				Stage dialogStage = new Stage();
				dialogStage.setTitle("Gestion de compte");
				dialogStage.initModality(Modality.WINDOW_MODAL);
				dialogStage.initOwner(mainApp.getPrimaryStage());
				Scene scene = new Scene(page);
				dialogStage.setScene(scene);

				GestionCompteController controller = loader.getController();
				controller.setDialogStage(dialogStage);
				controller.setPerson(mainApp.getUser());

				dialogStage.showAndWait();

				return controller.isOkClicked();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		


	

}
