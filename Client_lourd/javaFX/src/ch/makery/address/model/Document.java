package ch.makery.address.model;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Document {
	Groupe groupe;
	private final StringProperty NomDoc;
	private final StringProperty Description;
	private final ObjectProperty<LocalDate> dateCreation;

	public Document(Groupe groupe, String nom) {
		this.groupe = groupe;
		this.NomDoc = new SimpleStringProperty(nom);
		this.Description = new SimpleStringProperty("");
		this.dateCreation = new SimpleObjectProperty<LocalDate>(LocalDate.now());
		// groupe.getMembres().forEach(U->Utilisateur.addDocument(this));
	}

	public Groupe getGroupe() {
		return groupe;
	}

	public String getNomDoc() {
		return NomDoc.get();
	}

	public LocalDate getDateCreation() {
		return dateCreation.get();
	}

	public ObjectProperty<LocalDate> dateCreationProperty() {
		return dateCreation;
	}

	public void setNomDoc(String NomDoc) {
		this.NomDoc.set(NomDoc);
	}

	public StringProperty nomDocProperty() {
		return NomDoc;
	}

	public String getDescription() {
		return Description.get();
	}

	public void setDescription(String Description) {
		this.Description.set(Description);
	}

	public StringProperty descriptionProperty() {
		return Description;
	}

	public StringProperty nomCreateurProperty() {

		return this.getGroupe().getCreateur().pseudoProperty();
	}

	public String getNomCreateur() {
		return this.nomCreateurProperty().get();
	}

}