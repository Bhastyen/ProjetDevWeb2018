package ch.makery.address.view;

import ch.makery.address.util.LoginManager;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/** Ecran de connexion */
public class LoginController {
	@FXML
	private TextField user;
	@FXML
	private TextField password;
	@FXML
	private Button loginButton;

	public void initialize() {
	}

	public void initManager(final LoginManager loginManager) {
		loginButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String sessionID = authorize();
				if (sessionID != null) {
					loginManager.authenticated(sessionID, user.getText());
				}
			}
		});
	}

	/**
	 * Renvoie true si la connexion est accéptée false sinon
	 */
	private String authorize() {
		return "open".equals(user.getText()) && "sesame".equals(password.getText()) ? generateSessionID() : null;
	}

	private static int sessionID = 0;

	private String generateSessionID() {
		sessionID++;
		return "xyzzy - session " + sessionID;
	}
}
