package ch.makery.address.model;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Noeud {

private Point2D dragAnchor;
Line ligne=new Line();
	 
private Noeud pere;
private double initX;
private double initY;
private String titre;
private String description;
private Circle dessinNoeud;
public Noeud(double initX,double initY, String titre,int taille){
	dessinNoeud=createCircle("Blue circle", Color.DODGERBLUE, taille);
	this.initX=initX;
	this.initY=initY;
	this.titre=titre;
	this.description="";
	dessinNoeud.setTranslateX(initX);
	dessinNoeud.setTranslateY(initY);
}

public Circle getDessinNoeud() {
	return dessinNoeud;
}

public void setDessinNoeud(Circle dessinNoeud) {
	this.dessinNoeud = dessinNoeud;
}

public Noeud getPere() {
	return pere;
}
public void setPere(Noeud pere) {
	this.pere = pere;
}
public double getInitX() {
	return initX;
}
public void setInitX(double initX) {
	this.initX = initX;
}
public double getInitY() {
	return initY;
}
public void setInitY(double initY) {
	this.initY = initY;
}
public String getTitre() {
	return titre;
}
public void setTitre(String titre) {
	this.titre = titre;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
private Circle createCircle(final String name, final Color color, int radius) {
    //create a circle with desired name,  color and radius
    final Circle circle = new Circle(radius, new RadialGradient(0, 0, 0.2, 0.3, 1, true, CycleMethod.NO_CYCLE, new Stop[] {
        new Stop(0, Color.rgb(250,250,255)),
        new Stop(1, color)
    }));
    //add a shadow effect
    circle.setEffect(new InnerShadow(7, color.darker().darker()));
    //change a cursor when it is over circle
    circle.setCursor(Cursor.HAND);
    //add a mouse listeners
 
    circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
        public void handle(MouseEvent me) {
            //the event will be passed only to the circle which is on front
            me.consume();
        }
    });

    circle.setOnMouseDragged(new EventHandler<MouseEvent>() {
        public void handle(MouseEvent me) {
            double dragX = me.getSceneX() - dragAnchor.getX();
            double dragY = me.getSceneY() - dragAnchor.getY();
            //calculate new position of the circle
            double newXPosition = initX + dragX;
            double newYPosition = initY + dragY;
      
            //if new position do not exceeds borders of the rectangle, translate to this position
            if ((newXPosition>=circle.getRadius()) && (newXPosition<=500-circle.getRadius())) {
                circle.setTranslateX(newXPosition);
                
            }
            if ((newYPosition>=circle.getRadius()) && (newYPosition<=300-circle.getRadius())){
                circle.setTranslateY(newYPosition);
            }
           
        }
    });
    circle.setOnMouseEntered(new EventHandler<MouseEvent>() {
        public void handle(MouseEvent me) {
            //change the z-coordinate of the circle
            circle.toFront();
        }
    });
    circle.setOnMouseExited(new EventHandler<MouseEvent>() {
        public void handle(MouseEvent me) {
        	
        }
    });
    circle.setOnMousePressed(new EventHandler<MouseEvent>() {
        public void handle(MouseEvent me) {
             //when mouse is pressed, store initial position
            initX = circle.getTranslateX();
            initY = circle.getTranslateY();
            dragAnchor = new Point2D(me.getSceneX(), me.getSceneY());
        }
    });
    circle.setOnMouseReleased(new EventHandler<MouseEvent>() {
        public void handle(MouseEvent me) {
        }
    });

    return circle;
}

}
