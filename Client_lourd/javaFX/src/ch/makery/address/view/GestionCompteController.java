package ch.makery.address.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import ch.makery.address.model.Utilisateur;

public class GestionCompteController {
	 private Desktop desktop = Desktop.getDesktop();
	 final FileChooser fileChooser = new FileChooser();
	@FXML
	private TextField pseudoField;
	@FXML
	private TextField emailField;
	@FXML
	private TextField ancienMdpField;
	@FXML
	private TextField nouveauMdpField;
	@FXML
	private TextField confirmMdpField;

	private Stage dialogStage;
	private Utilisateur user;
	private boolean okClicked = false;

	/**
	 * Fonction d'initialisation appelée à la lecture du fxml
	 */
	@FXML
	private void initialize() {
	}

	/**
	 * récupération du stage
	 * 
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/**
	 * Récupération de l'utilisateur à éditer
	 *
	 * @param user
	 */
	public void setPerson(Utilisateur user) {
		this.user = user;

		pseudoField.setText(user.getPseudo());
		emailField.setText(user.getEmail());
		
		ancienMdpField.setText("*********");
		nouveauMdpField.setPromptText("Nouveau mdp");
		confirmMdpField.setPromptText("Confirmez");
	}

	/**
	 * Retourne vrai si l'utilisateur a cliqué, faux sinon
	 *
	 * @return
	 */
	public boolean isOkClicked() {
		return okClicked;
	}

	/**
	 * Action lors de la confirmation
	 */
	@FXML
	private void handleOk() {
		if (isInputValid()) {
			user.setPseudo(pseudoField.getText());
			user.setEmail(emailField.getText());
			user.setMotPasse(nouveauMdpField.getText());

			okClicked = true;
			dialogStage.close();
		}
	}

	/**
	 * Action lors de l'annulation
	 */
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	/**
	 * Vérification des champs
	 *
	 * @return true si les champs sont bons
	 */
	private boolean isInputValid() {
		String errorMessage = "";

		if (pseudoField.getText() == null || pseudoField.getText().length() == 0) {
			errorMessage += "Pseudo non valide\n";
		}
		if (emailField.getText() == null || emailField.getText().length() == 0) {
			errorMessage += "mail non valide\n";
		}
	

		if (!ancienMdpField.getText().equals(user.getMotPasse())) {
			errorMessage += "Vous vous etes trompé de mot de passe!\n";

		}

		if (nouveauMdpField.getText() == null || nouveauMdpField.getText().length() == 0) {
			errorMessage += "Nouveau mdp non valide!\n";
		}

		if (!confirmMdpField.getText().equals(nouveauMdpField.getText())) {
			errorMessage += "Les nouveaux mots de passe ne sont pas identique!\n";
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Show the error message.
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Erreur dans les champs");
			alert.setHeaderText("Corrigez les champs ci-dessous");
			alert.setContentText(errorMessage);

			alert.showAndWait();

			return false;
		}
	}
	 private void openFile(File file) {
	        try {
	            desktop.open(file);
	        } catch (IOException ex) {
	            Logger.getLogger(
	                GestionCompteController.class.getName()).log(
	                    Level.SEVERE, null, ex
	                );
	        }
	    }
	public void handleAvatar(final ActionEvent e) {
        configureFileChooser(fileChooser);
        File file = fileChooser.showOpenDialog(dialogStage);
        if (file != null) {
            openFile(file);
        }
    }
	  private static void configureFileChooser(final FileChooser fileChooser){                           
	        fileChooser.setTitle("View Pictures");
	        fileChooser.setInitialDirectory(
	            new File(System.getProperty("user.home"))
	        ); 
	    }
}