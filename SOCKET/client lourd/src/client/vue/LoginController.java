package client.vue;

import client.LoginManager;
import client.model.Utilisateur;
import client.util.SocketTest;
import client.util.UtilisateurUtil;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
				UtilisateurUtil sessionID = authorize();
				if (sessionID.type==1) {
					loginManager.authenticated(sessionID, user.getText());
				}
			}
		});
	}

	/**
	 * Renvoie true si la connexion est accéptée false sinon
	 */
	private UtilisateurUtil authorize() {
            try {
                SocketTest socket=new SocketTest();
                return socket.TestConnexion(new Utilisateur(user.getText(),password.getText()));
               
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return new UtilisateurUtil("non","non",0);
	}

	private static int sessionID = 0;

	private String generateSessionID() {
		sessionID++;
		return "xyzzy - session " + sessionID;
	}
}
