package ch.makery.address;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import ch.makery.address.model.Document;
import ch.makery.address.model.Groupe;
import ch.makery.address.model.Noeud;
import ch.makery.address.model.Utilisateur;
import ch.makery.address.view.AcceuilConnectedOverviewController;
import ch.makery.address.view.AcceuilOverviewController;
import ch.makery.address.view.EditDocument;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {
	private ObservableList<Document> docData = FXCollections.observableArrayList();
	private Stage primaryStage;
	private BorderPane rootLayout;
	private Document monDoc;
	LoginManager loginManager;
	private Utilisateur user = new Utilisateur("jean-claude", "abribuss");
	private Groupe groupe1 = new Groupe(user);
	 private Desktop desktop = Desktop.getDesktop();
	public	Document doc=new Document(new Groupe(new Utilisateur("jean","herve")),"testdoc");
    	
	public MainApp() {
		// Add some sample data
		groupe1.ajoutMembre(new Utilisateur("Utilisateur", "numero2"));
		groupe1.ajoutMembre(new Utilisateur("Utilisateur", "numero3"));
		monDoc = new Document(groupe1, "MonDoc!");
		docData.add(monDoc);
		Noeud noeud=new Noeud(200,80,doc.getNomDoc(),50);
    	doc.addNoeud(noeud);
    	Noeud noeud2=new Noeud(400,400,"fils1",25);
    	noeud2.setPere(noeud);
    	doc.addNoeud(noeud2);
    	Noeud noeud3=new Noeud(400,400,"fils2",25);
    	noeud3.setPere(noeud2);
    	doc.addNoeud(noeud3);
		System.out.println(user.getPseudo());
	}

	public ObservableList<Document> getDocData() {
		return docData;
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Client Lourd");

		initRootLayout();

		showAcceuilOverview();
	}

	/**
	 * Initialise le RootLayout
	 */
	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Affiche la page d'acceuil dans le root layout.
	 */
	public void showAcceuilOverview() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/AcceuilOverview.fxml"));
			AnchorPane acceuilOverview = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(acceuilOverview);

			// Give the controller access to the main app.
			AcceuilOverviewController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Renvoie le Stage principal.
	 * 
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void connected(boolean verif) {
		FXMLLoader loader = new FXMLLoader();
		if (verif)
			loader.setLocation(MainApp.class.getResource("view/AcceuilConnectedOverview.fxml"));
		else
			loader.setLocation(MainApp.class.getResource("view/AcceuilOverview.fxml"));
		AnchorPane acceuilOverview = null;
		try {
			acceuilOverview = (AnchorPane) loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		rootLayout.setCenter(acceuilOverview);

		// Choisit un controlleur en fonction de l'etat de connexion
		if (verif) {
			AcceuilConnectedOverviewController controller;

			controller = loader.getController();

			controller.setMainApp(this);
		} else {
			AcceuilOverviewController controller;

			controller = loader.getController();

			controller.setMainApp(this);

		}

	}
	
	 private void openFile(File file) {
	        try {
	            desktop.open(file);
	        } catch (IOException ex) {
	            Logger.getLogger(
	                MainApp.class.getName()).log(
	                    Level.SEVERE, null, ex
	                );
	        }
	    }

	public Utilisateur getUser() {

		return this.user;
	}

	public void setUser(Utilisateur user) {

		this.user = user;
	}

	public LoginManager getLoginManager() {
		// TODO Auto-generated method stub
		return this.loginManager;
	}

	public void setLoginManager(LoginManager loginManager) {
		// TODO Auto-generated method stub
		this.loginManager = loginManager;
	}
}