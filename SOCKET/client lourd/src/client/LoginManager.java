package client;

//basé sur le tutoriel http://code.makery.ch/library/javafx-8-tutorial/fr/
import client.model.Utilisateur;
import client.util.UtilisateurUtil;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import client.vue.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** Verificateur de connexion */
public class LoginManager {
	private Scene scene;
	private Stage stage;
	private static String pseudo;
	private String sessionId;
	private MainApp mainApp;

	public LoginManager(Scene scene, Stage stage, MainApp mainApp) {
		this.scene = scene;
		this.stage = stage;
		this.mainApp = mainApp;
	}

	/**
	 * Recuperation des informations
	 */
	public void authenticated(UtilisateurUtil sessionID, String user) {
		this.sessionId = "okay";
		this.pseudo = user;
                mainApp.setUser(new Utilisateur(sessionID));
		showMainView(sessionId);
	}

	/**
	 * Deconnexion
	 */
	public void logout() {
		mainApp.connected(false);
	}

	public void showLoginScreen() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("vue/login.fxml"));
			scene.setRoot((Parent) loader.load());
			LoginController controller = loader.<LoginController>getController();
			controller.initManager(this);
		} catch (IOException ex) {
			Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void showMainView(String sessionID) {

		stage.close();
		mainApp.connected(true);
	}

	public static String getUser() {
		return pseudo;
	}

}
