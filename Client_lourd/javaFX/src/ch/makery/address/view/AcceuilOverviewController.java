package ch.makery.address.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import ch.makery.address.LoginManager;
import ch.makery.address.MainApp;
import ch.makery.address.model.Document;
import ch.makery.address.model.Utilisateur;

public class AcceuilOverviewController {

	@FXML
	private TableView<Document> personTable;
	@FXML
	private TableColumn<Document, String> nomDocColumn;
	@FXML
	private TableColumn<Document, String> nomCreateurColumn;

	@FXML
	private Label nomDocLabel;
	@FXML
	private Label nomCreateurLabel;
	@FXML
	private Label descriptionLabel;
	@FXML
	private Label postalCodeLabel;
	@FXML
	private Label cityLabel;
	@FXML
	private Label birthdayLabel;

	// Recuperation des infos
	private MainApp mainApp;

	/**
	 * Constructeur. appelé apres initialize() .
	 */
	public AcceuilOverviewController() {
	}

	/**
	 * Fonction d'initialisation appelée à la lecture du fxml
	 */
	@FXML
	private void initialize() {
		// Initialise le tableau
		nomDocColumn.setCellValueFactory(cellData -> cellData.getValue().nomDocProperty());
		nomCreateurColumn.setCellValueFactory(cellData -> cellData.getValue().nomCreateurProperty());

		showDocDetails(null);

		personTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showDocDetails(newValue));
	}

	/**
	 * Appelée par le main afin de transferer ses infos
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		personTable.setItems(mainApp.getDocData());
	}

	public void handleSeConnecter() {
		Stage stage = new Stage();
		stage.setTitle("Se connecter");
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(mainApp.getPrimaryStage());
		Scene scene = new Scene(new StackPane());

		mainApp.setLoginManager(new LoginManager(scene, stage, mainApp));
		mainApp.getLoginManager().showLoginScreen();

		stage.setScene(scene);
		stage.showAndWait();

	}

	/**
	 * Cette fonction ouvre la page d'inscription
	 *
	 * @return true si l'utilisateur confirme.
	 */
	public boolean handleSinscrire() {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/Inscription.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Inscription");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(mainApp.getPrimaryStage());
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			InscriptionController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setMainApp(mainApp);
			controller.setPerson(mainApp.getUser());

			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	public void editDoc() {
		/*FXMLLoader loader = new FXMLLoader();
		AnchorPane page=new AnchorPane();
		try {
			page = (AnchorPane) loader.load();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Gestion de compte");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(mainApp.getPrimaryStage());
		Scene scene = new Scene(page);
		dialogStage.setScene(scene);*/
		EditDocument editdoc=new EditDocument(mainApp);
			try {
				editdoc.start(mainApp.getPrimaryStage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	private void showDocDetails(Document doc) {
		if (doc != null) {
			nomDocLabel.setText(doc.getNomDoc());
			nomCreateurLabel.setText(doc.getNomCreateur());
			descriptionLabel.setText(doc.getDescription());

		} else {
			nomDocLabel.setText("");
			nomCreateurLabel.setText("");
			descriptionLabel.setText("");

		}
	}
}
