package client.util;

import client.model.Noeud;
import javafx.scene.Group;
import javafx.scene.shape.Line;

public class Edge extends Group {

	protected Noeud source;
	protected Noeud target;

	Line line;

	public Edge(Noeud source, Noeud target) {

		this.source = source;
		this.target = target;

		source.addCellChild(target);
		target.setPere(source);

		line = new Line();

		line.startXProperty().bind(source.layoutXProperty().add(source.getBoundsInParent().getWidth() / 2.0));
		line.startYProperty().bind(source.layoutYProperty().add(source.getBoundsInParent().getHeight() / 2.0));
		line.endXProperty().bind(target.layoutXProperty().add(target.getBoundsInParent().getWidth() / 2.0));
		line.endYProperty().bind(target.layoutYProperty().add(target.getBoundsInParent().getHeight() / 2.0));

		getChildren().add(line);
		
	}

	public Noeud getSource() {
		return source;
	}

	public Noeud getTarget() {
		return target;
	}

}