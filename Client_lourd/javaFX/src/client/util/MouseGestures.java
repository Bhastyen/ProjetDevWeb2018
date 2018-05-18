package client.util;

import java.io.IOException;

import client.MainApp;
import client.model.Model;
import client.model.Noeud;
import client.vue.AjoutNoeudController;
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

public class MouseGestures {

	final DragContext dragContext = new DragContext();
	final ContextMenu contextMenu = new ContextMenu();
	MenuItem item1 = new MenuItem("Modifier Titre");
	MenuItem item2 = new MenuItem("Modifier Description");
	MenuItem item3 = new MenuItem("Modifier Longueur");
	MenuItem item4 = new MenuItem("Modifier Largeur");
	MenuItem item5 = new MenuItem("Modifier Père");
	MenuItem item6 = new MenuItem("Ajouter noeud");

	Graph graph;
	Noeud noeud;
	Model model;
MainApp mainApp;
	public MouseGestures(Graph graph,MainApp mainApp,Model model) {
		this.graph = graph;
		this.mainApp=mainApp;
		this.model=model;
	}

	public void makeDraggable(final Node node) {
	graph.getScrollPane().addEventHandler(ContextMenuEvent.CONTEXT_MENU_REQUESTED, event -> {
		item6.setOnAction(new EventHandler<ActionEvent>() {
		    public void handle(ActionEvent e) {
		    	Stage stage = new Stage();
				stage.setTitle("Ajout Noeud");
				stage.initModality(Modality.WINDOW_MODAL);
				stage.initOwner(mainApp.getPrimaryStage());
				Scene scene = new Scene(new StackPane());
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../vue/AjoutNoeud.fxml"));
				try {
					scene.setRoot((Parent) loader.load());
					AjoutNoeudController controller = loader.getController();
					controller.setModel(model);
					controller.setGraph(graph);
					controller.setStage(stage);
					System.out.println(event.getX());
					controller.setX((int)event.getX());
					controller.setY((int)event.getY());
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
					FXMLLoader loader = new FXMLLoader(getClass().getResource("../vue/ModifierNoeud.fxml"));
					try {
						scene.setRoot((Parent) loader.load());
						ModifierNoeudController controller = loader.getController();
						controller.setModel(model);
						controller.setNoeud(node);
						controller.setStage(stage);
						controller.setType(0);
					} catch (IOException err) {
						// TODO Auto-generated catch block
						err.printStackTrace();
					}

					stage.setScene(scene);
					stage.showAndWait();
					System.out.println(((Noeud)node).toString());
			    }
			});
			item2.setOnAction(new EventHandler<ActionEvent>() {
			    public void handle(ActionEvent e) {
			    	Stage stage = new Stage();
					stage.setTitle("Modifier Noeud");
					stage.initModality(Modality.WINDOW_MODAL);
					stage.initOwner(mainApp.getPrimaryStage());
					Scene scene = new Scene(new StackPane());
					FXMLLoader loader = new FXMLLoader(getClass().getResource("../vue/ModifierNoeud.fxml"));
					try {
						scene.setRoot((Parent) loader.load());
						ModifierNoeudController controller = loader.getController();
						controller.setModel(model);
						controller.setNoeud(node);
						controller.setStage(stage);
						controller.setType(1);
					} catch (IOException err) {
						// TODO Auto-generated catch block
						err.printStackTrace();
					}

					stage.setScene(scene);
					stage.showAndWait();
					System.out.println(((Noeud)node).toString());
			    }
			});
			item3.setOnAction(new EventHandler<ActionEvent>() {
			    public void handle(ActionEvent e) {
			    	Stage stage = new Stage();
					stage.setTitle("Modifier Noeud");
					stage.initModality(Modality.WINDOW_MODAL);
					stage.initOwner(mainApp.getPrimaryStage());
					Scene scene = new Scene(new StackPane());
					FXMLLoader loader = new FXMLLoader(getClass().getResource("../vue/ModifierNoeud.fxml"));
					try {
						scene.setRoot((Parent) loader.load());
						ModifierNoeudController controller = loader.getController();
						controller.setModel(model);
						controller.setNoeud(node);
						controller.setStage(stage);
						controller.setType(2);
					} catch (IOException err) {
						// TODO Auto-generated catch block
						err.printStackTrace();
					}

					stage.setScene(scene);
					stage.showAndWait();
					System.out.println(((Noeud)node).toString());
			    }
			});
			item4.setOnAction(new EventHandler<ActionEvent>() {
			    public void handle(ActionEvent e) {
			    	Stage stage = new Stage();
					stage.setTitle("Modifier Noeud");
					stage.initModality(Modality.WINDOW_MODAL);
					stage.initOwner(mainApp.getPrimaryStage());
					Scene scene = new Scene(new StackPane());
					FXMLLoader loader = new FXMLLoader(getClass().getResource("../vue/ModifierNoeud.fxml"));
					try {
						scene.setRoot((Parent) loader.load());
						ModifierNoeudController controller = loader.getController();
						controller.setModel(model);
						controller.setNoeud(node);
						controller.setStage(stage);
						controller.setType(3);
					} catch (IOException err) {
						// TODO Auto-generated catch block
						err.printStackTrace();
					}

					stage.setScene(scene);
					stage.showAndWait();
					System.out.println(((Noeud)node).toString());
			    }
			});
			item5.setOnAction(new EventHandler<ActionEvent>() {
			    public void handle(ActionEvent e) {
			    	Stage stage = new Stage();
					stage.setTitle("Modifier Noeud");
					stage.initModality(Modality.WINDOW_MODAL);
					stage.initOwner(mainApp.getPrimaryStage());
					Scene scene = new Scene(new StackPane());
					FXMLLoader loader = new FXMLLoader(getClass().getResource("../vue/ModifierNoeud.fxml"));
					try {
						scene.setRoot((Parent) loader.load());
						ModifierNoeudController controller = loader.getController();
						controller.setModel(model);
						controller.setNoeud(node);
						controller.setStage(stage);
						controller.setType(4);
					} catch (IOException err) {
						// TODO Auto-generated catch block
						err.printStackTrace();
					}

					stage.setScene(scene);
					stage.showAndWait();
					System.out.println(((Noeud)node).toString());
			    }
			});
			contextMenu.getItems().clear();
			contextMenu.getItems().addAll(item1, item2,item3,item4,item5);
			contextMenu.show(node, event.getScreenX(), event.getScreenY());
			event.consume();
		});
		node.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
			contextMenu.hide();
		
		});
		node.setOnMouseDragged(onMouseDraggedEventHandler);
		node.setOnMouseReleased(onMouseReleasedEventHandler);

	}

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