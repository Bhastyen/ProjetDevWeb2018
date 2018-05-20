package client.util;

import client.model.Noeud;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RectangleNoeud extends Noeud {

	public RectangleNoeud(String titre, String description, int hauteur, int largeur, int x, int y,boolean root) {
		super(titre, description, hauteur, largeur, x, y,root);

		Rectangle view = new Rectangle(hauteur, largeur);
		// view.setY(y);
		// view.setX(x);
		view.setStroke(Color.DODGERBLUE);
		view.setFill(Color.DODGERBLUE);

		setView(view);

	}

}