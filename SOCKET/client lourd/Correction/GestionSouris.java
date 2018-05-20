package client.util;

import java.io.IOException;

import client.MainApp;
import client.MainApp;
import client.model.Document;
import client.model.Model;
import client.model.Noeud;
import client.util.Graph;
import client.util.Liens;
import client.vue.AjoutNoeudController;
import client.vue.DocumentController;
import client.vue.ModifierNoeudController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GestionSouris {

	final DragContext dragContext = new DragContext();
	final ContextMenu contextMenu = new ContextMenu();
	MenuItem item1 = new MenuItem("Modifier Titre");
	MenuItem item2 = new MenuItem("Modifier Description");
	MenuItem item3 = new MenuItem("Modifier Longueur");
	MenuItem item4 = new MenuItem("Modifier Largeur");
	MenuItem item5 = new MenuItem("Modifier Père");
	MenuItem item6 = new MenuItem("Ajouter noeud");
	MenuItem item7 = new MenuItem("Supprimer noeud");

	Graph graph;
	public Noeud noeud;
	MainApp mainApp;
	DocumentController docControl;
	private boolean bool;
	private Document doc;

	public GestionSouris(Graph graph, MainApp mainApp, Document doc, boolean bool) {
		this.graph = graph;
		this.mainApp = mainApp;
		this.doc = doc;
		this.docControl = graph.getController();
		this.bool = bool;
	}

	public void makeDraggable(final Node node) {
		if (bool) {
			graph.getScrollPane().addEventHandler(ContextMenuEvent.CONTEXT_MENU_REQUESTED, event -> {
				item6.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {
						Stage stage = new Stage();
						stage.setTitle("Ajout Noeud");
						stage.initModality(Modality.WINDOW_MODAL);
						stage.initOwner(mainApp.getPrimaryStage());
						Scene scene = new Scene(new StackPane());
						FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("vue/AjoutNoeud.fxml"));
						try {
							scene.setRoot((Parent) loader.load());
							AjoutNoeudController controller = loader.getController();
							controller.setModel(doc.getContenu());
							controller.setGraph(graph);
							controller.setStage(stage);
							controller.setDoc(doc);
							controller.setX((int) event.getX());
							controller.setY((int) event.getY());
						} catch (IOException err) {
							// TODO Auto-generated catch block
							err.printStackTrace();
						}

						stage.setScene(scene);
						stage.showAndWait();
					}
				});

				contextMenu.getItems().clear();
				contextMenu.getItems().addAll(item6);
				contextMenu.show(node, event.getScreenX(), event.getScreenY());
				event.consume();
			});
			graph.getScrollPane().addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
				contextMenu.hide();

			});

			node.setOnMousePressed(onMousePressedEventHandler);
			node.addEventHandler(ContextMenuEvent.CONTEXT_MENU_REQUESTED, event -> {
				item1.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {

						Stage stage = new Stage();
						stage.setTitle("Modifier Noeud");
						stage.initModality(Modality.WINDOW_MODAL);
						stage.initOwner(mainApp.getPrimaryStage());
						Scene scene = new Scene(new StackPane());
						FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("vue/ModifierNoeud.fxml"));
						try {
							scene.setRoot((Parent) loader.load());
							ModifierNoeudController controller = loader.getController();
							controller.setModel(doc.getContenu());
							controller.setNoeud(node);
							controller.setStage(stage);
							controller.setType(0);
						} catch (IOException err) {
							// TODO Auto-generated catch block
							err.printStackTrace();
						}

						stage.setScene(scene);
						stage.showAndWait();
					}
				});
				item2.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {
						Stage stage = new Stage();
						stage.setTitle("Modifier Noeud");
						stage.initModality(Modality.WINDOW_MODAL);
						stage.initOwner(mainApp.getPrimaryStage());
						Scene scene = new Scene(new StackPane());
						FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("vue/ModifierNoeud.fxml"));
						try {
							scene.setRoot((Parent) loader.load());
							ModifierNoeudController controller = loader.getController();
							controller.setModel(doc.getContenu());
							controller.setNoeud(node);
							controller.setStage(stage);
							controller.setType(1);
						} catch (IOException err) {
							// TODO Auto-generated catch block
							err.printStackTrace();
						}

						stage.setScene(scene);
						stage.showAndWait();
					}
				});
				item3.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {
						Stage stage = new Stage();
						stage.setTitle("Modifier Noeud");
						stage.initModality(Modality.WINDOW_MODAL);
						stage.initOwner(mainApp.getPrimaryStage());
						Scene scene = new Scene(new StackPane());
						FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("vue/ModifierNoeud.fxml"));
						try {
							scene.setRoot((Parent) loader.load());
							ModifierNoeudController controller = loader.getController();
							controller.setModel(doc.getContenu());
							controller.setNoeud(node);
							controller.setStage(stage);
							controller.setType(2);
						} catch (IOException err) {
							// TODO Auto-generated catch block
							err.printStackTrace();
						}

						stage.setScene(scene);
						stage.showAndWait();
					}
				});
				item4.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {
						Stage stage = new Stage();
						stage.setTitle("Modifier Noeud");
						stage.initModality(Modality.WINDOW_MODAL);
						stage.initOwner(mainApp.getPrimaryStage());
						Scene scene = new Scene(new StackPane());
						FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("vue/ModifierNoeud.fxml"));
						try {
							scene.setRoot((Parent) loader.load());
							ModifierNoeudController controller = loader.getController();
							controller.setModel(doc.getContenu());
							controller.setNoeud(node);
							controller.setStage(stage);
							controller.setType(3);
						} catch (IOException err) {
							// TODO Auto-generated catch block
							err.printStackTrace();
						}

						stage.setScene(scene);
						stage.showAndWait();
					}
				});
				item5.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {
						Stage stage = new Stage();
						stage.setTitle("Modifier Noeud");
						stage.initModality(Modality.WINDOW_MODAL);
						stage.initOwner(mainApp.getPrimaryStage());
						Scene scene = new Scene(new StackPane());
						FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("vue/ModifierNoeud.fxml"));
						try {
							scene.setRoot((Parent) loader.load());
							ModifierNoeudController controller = loader.getController();
							controller.setModel(doc.getContenu());
							controller.setNoeud(node);
							controller.setStage(stage);
							controller.setGraph(graph);
							controller.setType(4);
						} catch (IOException err) {
							// TODO Auto-generated catch block
							err.printStackTrace();
						}

						stage.setScene(scene);
						stage.showAndWait();
					}
				});
				item7.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {
						for (Liens lien : doc.getContenu().getAllliens()) {
							if (lien.getSource().equals((Noeud) node) || lien.getTarget().equals((Noeud) node))
								lien.dellien();

						}
						doc.delNoeud(((Noeud) node));
						((Noeud) node).remove();
						
					}
				});
				contextMenu.getItems().clear();
				contextMenu.getItems().addAll(item1, item2, item3, item4, item5, item7);
				contextMenu.show(node, event.getScreenX(), event.getScreenY());
				event.consume();
			});
			node.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
				contextMenu.hide();

			});
			node.setOnMouseDragged(onMouseDraggedEventHandler);
			node.setOnMouseReleased(onMouseReleasedEventHandler);

		}
		node.setOnMouseMoved(onMouseMovedEventHandler);
	}

	EventHandler<MouseEvent> onMouseMovedEventHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {

			Node node = (Node) event.getSource();
			docControl.setNoeud(node);
		}
	};
	EventHandler<MouseEvent> onMousePressedEventHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {

			Node node = (Node) event.getSource();

			double scale = graph.getScale();

			dragContext.x = node.getBoundsInParent().getMinX() * scale - event.getScreenX();
			dragContext.y = node.getBoundsInParent().getMinY() * scale - event.getScreenY();

		}
	};

	EventHandler<MouseEvent> onMouseDraggedEventHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {

			Node node = (Node) event.getSource();

			double offsetX = event.getScreenX() + dragContext.x;
			double offsetY = event.getScreenY() + dragContext.y;

			// adjust the offset in case we are zoomed
			double scale = graph.getScale();

			offsetX /= scale;
			offsetY /= scale;

			node.relocate(offsetX, offsetY);

		}
	};

	EventHandler<MouseEvent> onMouseReleasedEventHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {

		}
	};

	class DragContext {

		double x;
		double y;

	}

}