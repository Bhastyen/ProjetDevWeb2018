package client.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import client.util.NoeudType;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Document {
	Groupe groupe;
	private final StringProperty NomDoc;
	private final StringProperty Description;
	private Model model;
	private List<Noeud> listeNoeuds=new ArrayList<Noeud>();;

	private final ObjectProperty<LocalDate> dateCreation;

	public Document(Groupe groupe, String nom) {
		this.groupe = groupe;
		this.NomDoc = new SimpleStringProperty(nom);
		this.Description = new SimpleStringProperty("");
		this.dateCreation = new SimpleObjectProperty<LocalDate>(LocalDate.now());
		this.model = new Model();
		// groupe.getMembres().forEach(U->Utilisateur.addDocument(this));
	}

	public Document(Groupe groupe, String nom, String Description) {
		this(groupe, nom);
		this.Description.set(Description);
	}

	public Model getContenu() {
		return model;
	}

	public void setContenu(Model contenu) {
		this.model = contenu;
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
	public void addNoeud(Noeud noeud) {
		this.listeNoeuds.add(noeud);
		model.addnoeud(noeud.getTitre(), noeud.getDescription(), NoeudType.RECTANGLE,
				 noeud.getLongueur(), noeud.getLargeur(), (int)noeud.getPosX(), (int)noeud.getPosY(),noeud.isRoot());
		 
	}
	public void delNoeud(Noeud noeud) {
		this.listeNoeuds.remove(noeud);
	}
	public List<Noeud> getNoeuds(){
		return this.listeNoeuds;
	}
}