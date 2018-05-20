package client.util;

//Basé sur un post sur le forum http://stackoverflow.com
import client.MainApp;
import client.model.Document;
import client.model.Model;
import client.model.Noeud;
import client.vue.DocumentController;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

public class Graph {

	private Model model;

	private Group canvas;

	private PlanTravail scrollPane;

	GestionSouris mouseGestures;

	/**
	 * Plan de travail pour l'utilisateur
	 */
	NoeudLayer noeudLayer;

	private DocumentController controller;

	public Graph(MainApp mainApp, DocumentController controller, boolean i,Document doc) {
		this.controller = controller;

		canvas = new Group();
		noeudLayer = new NoeudLayer();

		canvas.getChildren().add(noeudLayer);
		mouseGestures = new GestionSouris(this, mainApp, doc, i);
		scrollPane = new PlanTravail(canvas);

		scrollPane.setFitToWidth(true);
		scrollPane.setFitToHeight(true);

	}

	public DocumentController getController() {
		return controller;
	}

	public ScrollPane getScrollPane() {
		return this.scrollPane;
	}

	public void addNoeud(Noeud noeud) {
		model.addnoeud(noeud);
		model.addlien(noeud.getTitre(), noeud.getPere().getTitre());
	}

	public Pane getnoeudLayer() {
		return this.noeudLayer;
	}

	public Model getModel() {
		return model;
	}

	public void beginUpdate() {
	}

	public void chargeDoc(Document doc) {
		
		this.model = doc.getContenu();
 for(Noeud noeud : doc.getNoeuds()) {
	 if(!noeud.isRoot()) {
		 model.addlien(noeud.getTitre(),noeud.getPere().getTitre());
	 }
 }
	 
		getnoeudLayer().getChildren().addAll(model.getAllliens());
		getnoeudLayer().getChildren().addAll(model.getAllnoeuds());
		getnoeudLayer().getChildren().addAll(model.getAddedliens());
		getnoeudLayer().getChildren().addAll(model.getAddednoeuds());

		// remove components from graph pane
		getnoeudLayer().getChildren().removeAll(model.getRemovednoeuds());
		getnoeudLayer().getChildren().removeAll(model.getRemovedliens());

		// enable dragging of noeuds
		for (Noeud noeud : model.getAllnoeuds()) {
			mouseGestures.makeDraggable(noeud);
		}
		for (Noeud noeud : model.getAddednoeuds()) {
			mouseGestures.makeDraggable(noeud);
		}

		// every noeud must have a parent, if it doesn't, then the graphParent is
		// the parent
		getModel().attachOrphansToGraphParent(model.getAddednoeuds());

		// remove reference to graphParent
		getModel().disconnectFromGraphParent(model.getRemovednoeuds());

		// merge added & removed noeuds with all noeuds
		getModel().merge();
	}

	public void endUpdate() {

		// add components to graph pane
		getnoeudLayer().getChildren().addAll(model.getAddedliens());
		getnoeudLayer().getChildren().addAll(model.getAddednoeuds());

		// remove components from graph pane
		getnoeudLayer().getChildren().removeAll(model.getRemovednoeuds());
		getnoeudLayer().getChildren().removeAll(model.getRemovedliens());

		// enable dragging of noeuds
		for (Noeud noeud : model.getAddednoeuds()) {
			mouseGestures.makeDraggable(noeud);
		}

		// every noeud must have a parent, if it doesn't, then the graphParent is
		// the parent
		getModel().attachOrphansToGraphParent(model.getAddednoeuds());

		// remove reference to graphParent
		getModel().disconnectFromGraphParent(model.getRemovednoeuds());

		// merge added & removed noeuds with all noeuds
		getModel().merge();

	}

	public double getScale() {
		return this.scrollPane.getScaleValue();
	}

	public GestionSouris getMouse() {
		return this.mouseGestures;
	}
}