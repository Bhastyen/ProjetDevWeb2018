package client.vue;

import java.io.IOException;
import java.time.LocalDate;

import client.MainApp;
import client.model.Document;
import client.model.Model;
import client.model.Utilisateur;
import client.util.Graph;
import client.util.Layout;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AcceuilConnectedOverviewController {

	@FXML
	private TableView<Document> documentsTable;
	@FXML
	private ImageView avatar;
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
	private Document doc;
	Graph graph;
	Model model;

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
		documentsTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> setDoc(newValue));

	}

	/**
	 * appelée par le main qui se met en parametre
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		showNom();
		documentsTable.setItems(FXCollections.observableArrayList(mainApp.getDocData()));
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
	 * Appelée si on clique sur gestion de compte Cette fonction ouvre la page de
	 * gestion de compte
	 *
	 * @param user
	 *            L'utilisateur à editer
	 * @return true si l'utilisateur confirme.
	 */
	@FXML
	private boolean handleGestionCompte() {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("vue/GestionCompte.fxml"));
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

	public void voirMesDocs() {
		documentsTable.getItems().clear();
		ObservableList<Document> liste = FXCollections.observableArrayList();

		for (Document doc : mainApp.getDocData())
			if (doc.getGroupe().getCreateur().equals(mainApp.getUser()))
				liste.add(doc);
		documentsTable.setItems(liste);
		documentsTable.refresh();

	}

	public void voirDocsAsso() {

		documentsTable.getItems().clear();

		ObservableList<Document> liste = FXCollections.observableArrayList();
		for (Document doc : mainApp.getDocData())
			for (Utilisateur user : doc.getGroupe().getMembres())
				if (user.equals(mainApp.getUser()))
					liste.add(doc);
		documentsTable.setItems(liste);
		documentsTable.refresh();

	}

	public void creerDoc() {
		Stage stage = new Stage();
		stage.setTitle("Creer Docment");
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(mainApp.getPrimaryStage());
		Scene scene = new Scene(new StackPane());
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../vue/CreerDoc.fxml"));
		try {
			scene.setRoot((Parent) loader.load());
			CreerDocController controller = loader.getController();
			controller.setModel(model);
			controller.setMainApp(mainApp);
			controller.setStage(stage);
		} catch (IOException err) {
			// TODO Auto-generated catch block
			err.printStackTrace();
		}

		stage.setScene(scene);
		stage.showAndWait();
	}

	public void editDoc() {
		if (doc == null)
			return;
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("vue/Documents.fxml"));
		BorderPane root;
		try {
			root = (BorderPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edition");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(mainApp.getPrimaryStage());
			DocumentController controller = loader.getController();
			graph = new Graph(mainApp, controller, true,doc);
			controller.setMainApp(this.mainApp);

			root.setCenter(graph.getScrollPane());

			Scene scene = new Scene(root, 1024, 768);

			dialogStage.setScene(scene);
			dialogStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addGraphComponents();

		Layout layout = new InitLayout(graph);
		layout.execute();

	}

	private void addGraphComponents() {

		graph.beginUpdate();
		graph.chargeDoc(doc);

		graph.endUpdate();

	}

	private void setDoc(Document doc) {
		this.doc = doc;
	}

}
