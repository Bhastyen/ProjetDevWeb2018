package client.vue;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import client.util.Graph;
import client.util.Layout;
import client.model.Model;
import client.vue.RandomLayout;
import client.LoginManager;
import client.MainApp;
import client.model.Document;
import client.model.Utilisateur;
import client.util.CellType;

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
	Graph graph ;
	Model model;
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
			loader.setLocation(MainApp.class.getResource("vue/Inscription.fxml"));
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

		// Load root layout from fxml file.
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("vue/Documents.fxml"));
		BorderPane root;
		try {
			root = (BorderPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edition");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(mainApp.getPrimaryStage());
			// Show the scene containing the root layout.

			graph = new Graph(mainApp);

			root.setCenter(graph.getScrollPane());

			Scene scene = new Scene(root, 1024, 768);

			dialogStage.setScene(scene);
			dialogStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addGraphComponents();
		DocumentController controller = loader.getController();
		controller.setModel(this.model);
		controller.setGraph(this.graph);
		controller.setMainApp(this.mainApp);
		Layout layout = new RandomLayout(graph);
		layout.execute();

	}

	private void addGraphComponents() {

		graph.beginUpdate();
		model = graph.getModel();
		model.addCell("A", "Noeud", CellType.RECTANGLE, 50, 100, 0, 0);
		model.addCell("B", "Noeud2", CellType.RECTANGLE, 50, 100, 100, 200);
		model.addEdge("A", "B");

		graph.endUpdate();

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
