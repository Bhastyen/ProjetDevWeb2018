package client.util;

//Basé sur un post sur le forum http://stackoverflow.com
import client.model.Noeud;
import javafx.scene.Group;
import javafx.scene.shape.Line;

public class Liens extends Group {

	protected Noeud source;
	protected Noeud target;

	Line line;

	public Liens(Noeud source, Noeud target) {
System.out.println(source.toString()
		+target.toString());
		this.source = source;
		this.target = target;

		source.addnoeudChild(target);
		target.setPere(source);

		line = new Line();

		line.startXProperty().bind(source.layoutXProperty().add(source.getBoundsInParent().getWidth() / 2.0));
		line.startYProperty().bind(source.layoutYProperty().add(source.getBoundsInParent().getHeight() / 2.0));
		line.endXProperty().bind(target.layoutXProperty().add(target.getBoundsInParent().getWidth() / 2.0));
		line.endYProperty().bind(target.layoutYProperty().add(target.getBoundsInParent().getHeight() / 2.0));

		getChildren().add(line);

	}

	public void dellien() {
		getChildren().clear();
	}

	public Noeud getSource() {
		return source;
	}

	public Noeud getTarget() {
		return target;
	}

}