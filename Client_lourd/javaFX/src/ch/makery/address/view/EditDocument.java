package ch.makery.address.view;
import javafx.scene.shape.Line;
import ch.makery.address.MainApp;
import ch.makery.address.model.Document;
import ch.makery.address.model.Groupe;
import ch.makery.address.model.Noeud;
import ch.makery.address.model.Utilisateur;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
 
/**
 * A sample that demonstrates various mouse and scroll events and their usage.
 * Click the circles and drag them across the screen. Scroll the whole screen.
 * All events are logged to the console.
 *
 * @see javafx.scene.Cursor
 * @see javafx.scene.input.MouseEvent
 * @see javafx.event.EventHandler
 */
public class EditDocument extends Application {

    //variables for storing initial position before drag of circle
 private  MainApp mainApp;

    public EditDocument(MainApp mainApp) {
	this.mainApp=mainApp;
}

	private void init(Stage primaryStage) {
    	
    	
    	
        Group root = new Group();
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 700,500));
        // create circle with method listed below: paramethers: name of the circle, color of the circle, radius
      
        
        // and a second, bigger circle
 //       final Circle circleSmall = createCircle("Orange circle", Color.CORAL, 40);
         // we can set mouse event to any node, also on the rectangle
        for(int i=0;i<mainApp.doc.getContenu().size();i++) {
        	Line ligne=new Line();
        	Label titre = new Label();
        	int j=i;
        mainApp.doc.getContenu().get(i).getDessinNoeud().setOnMouseMoved(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
            	root.getChildren().remove(titre);
            	titre.setText(mainApp.doc.getContenu().get(j).getTitre());
            	titre.setTranslateX(mainApp.doc.getContenu().get(j).getInitX()-10);
            	titre.setTranslateY(mainApp.doc.getContenu().get(j).getInitY()-10);
            	 root.getChildren().add(titre);

           	 if( mainApp.doc.getContenu().get(j).getPere()!=null) {
           		root.getChildren().remove(ligne);
           	
           		 Noeud pere=mainApp.doc.getContenu().get(j).getPere();
           		 ligne.setStroke(Color.BLACK);
           		 ligne.setStartX(pere.getInitX());
           		 ligne.setStartY(pere.getInitY());
           		 ligne.setEndX(mainApp.doc.getContenu().get(j).getInitX());
           		 ligne.setEndY(mainApp.doc.getContenu().get(j).getInitY());
           		 root.getChildren().addAll(ligne);
           	 }
            }
        }
);
        root.getChildren().addAll(mainApp.doc.getContenu().get(i).getDessinNoeud());  
    
        }
          // show all the circle , rectangle and console
       
    }
 
    /*private void showOnConsole(String text) {
         //if there is 8 items in list, delete first log message, shift other logs and  add a new one to end position
         if (consoleObservableList.size()==8) {
            consoleObservableList.remove(0);
         }
         consoleObservableList.add(text);
    }*/

    @Override public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
    }
 
}