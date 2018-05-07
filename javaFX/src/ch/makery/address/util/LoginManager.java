package ch.makery.address.util;

import java.io.IOException;
import java.util.logging.*;

import ch.makery.address.MainApp;
import ch.makery.address.view.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
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
	public void authenticated(String sessionID, String user) {
		this.sessionId = sessionID;
		this.pseudo = user;
		showMainView(sessionID);
	}

	/**
	 * Deconnexion
	 */
	public void logout() {
		showLoginScreen();
	}

	public void showLoginScreen() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/login.fxml"));
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
