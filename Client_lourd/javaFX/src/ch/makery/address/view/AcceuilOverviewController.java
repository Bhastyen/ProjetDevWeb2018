package ch.makery.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ch.makery.address.MainApp;
import ch.makery.address.model.Document;

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

	// Reference to the main application.
	private MainApp mainApp;

	/**
	 * The constructor. The constructor is called before the initialize() method.
	 */
	public AcceuilOverviewController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		// Initialize the person table with the two columns.
		nomDocColumn.setCellValueFactory(cellData -> cellData.getValue().nomDocProperty());
		nomCreateurColumn.setCellValueFactory(cellData -> cellData.getValue().nomCreateurProperty());

		// Clear person details.
		showDocDetails(null);

		// Listen for selection changes and show the person details when changed.
		personTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showDocDetails(newValue));
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		// Add observable list data to the table
		personTable.setItems(mainApp.getDocData());
	}

	public void handleSeConnecter() {
		mainApp.seConnecter();

	}

	private void showDocDetails(Document doc) {
		if (doc != null) {
			// Fill the labels with info from the person object.
			nomDocLabel.setText(doc.getNomDoc());
			nomCreateurLabel.setText(doc.getNomCreateur());
			descriptionLabel.setText(doc.getDescription());

			// TODO: We need a way to convert the birthday into a String!
			// birthdayLabel.setText(...);
		} else {
			// Person is null, remove all the text.
			nomDocLabel.setText("");
			nomCreateurLabel.setText("");
			descriptionLabel.setText("");

		}
	}
}
