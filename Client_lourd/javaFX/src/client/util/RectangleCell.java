package client.util;

import client.model.Noeud;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RectangleCell extends Noeud {

	public RectangleCell(String titre,String description, int hauteur, int largeur,int x,int y) {
		super(titre,description,hauteur,largeur,x,y);

		Rectangle view = new Rectangle(hauteur, largeur);
//view.setY(y);
//view.setX(x);
		view.setStroke(Color.DODGERBLUE);
		view.setFill(Color.DODGERBLUE);

		setView(view);

	}

}