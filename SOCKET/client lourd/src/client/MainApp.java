package client;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import client.model.Document;
import client.model.Groupe;
import client.model.Noeud;
import client.model.Utilisateur;
import client.util.NoeudType;
import client.vue.AcceuilConnectedOverviewController;
import client.vue.AcceuilOverviewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {
	private List<Document> docData = new ArrayList<Document>();
	private Stage primaryStage;
	private BorderPane rootLayout;
	LoginManager loginManager;
	private Utilisateur user = new Utilisateur("jean-claude", "abribuss");
	private Groupe groupe1 = new Groupe(user);
	public Document doc = new Document(new Groupe(new Utilisateur("Affiche", "Pas")), "testdoc");

	public MainApp() {
		// ajout de données
		groupe1.ajoutMembre(user);
		groupe1.ajoutMembre(new Utilisateur("Utilisateur", "numero3"));
		
		Noeud pere=new Noeud("ProjetDevWeb", "Racine",200, 100, 300, 300,true);
		Noeud noeud=new Noeud("Client Lourd", "C'est Beau!", 150, 75, 50, 200,false);
		Noeud fils=new Noeud("Client leger", "C'est beau aussi",150, 75, 500, 100,false);
		Noeud ptifils=new Noeud("Gestionnaire doc", "Pas facile", 200, 50, 50, 400,false);
		
		doc.addNoeud(pere);
		noeud.setPere(pere);
		
		doc.addNoeud(noeud);
		fils.setPere(pere);
		doc.addNoeud(fils);
		ptifils.setPere(noeud);
		doc.addNoeud(ptifils);
		docData.add(doc);
	}

	public List<Document> getDocData() {
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
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("vue/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
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
			// Chargement de la vue principale
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("vue/AcceuilOverview.fxml"));
			AnchorPane acceuilOverview = (AnchorPane) loader.load();

			rootLayout.setCenter(acceuilOverview);

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
			loader.setLocation(MainApp.class.getResource("vue/AcceuilConnectedOverview.fxml"));
		else
			loader.setLocation(MainApp.class.getResource("vue/AcceuilOverview.fxml"));
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

	public void addDoc(Document doc) {
		docData.add(doc);
	}
}