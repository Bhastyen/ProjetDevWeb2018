package client.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import client.util.CellType;
import client.util.Edge;
import client.util.RectangleCell;
//import client.util.TriangleCell;

public class Model {

	Noeud graphParent;

	List<Noeud> allCells;
	List<Noeud> addedCells;
	List<Noeud> removedCells;

	List<Edge> allEdges;
	List<Edge> addedEdges;
	List<Edge> removedEdges;

	Map<String, Noeud> cellMap; // <id,cell>

	public Model() {

		graphParent = new Noeud("_ROOT_","salut",0,0,0,0);

		// clear model, create lists
		clear();
	}

	public void clear() {

		allCells = new ArrayList<>();
		addedCells = new ArrayList<>();
		removedCells = new ArrayList<>();

		allEdges = new ArrayList<>();
		addedEdges = new ArrayList<>();
		removedEdges = new ArrayList<>();

		cellMap = new HashMap<>(); // <id,cell>

	}

	public void clearAddedLists() {
		addedCells.clear();
		addedEdges.clear();
	}

	public List<Noeud> getAddedCells() {
		return addedCells;
	}

	public List<Noeud> getRemovedCells() {
		return removedCells;
	}

	public List<Noeud> getAllCells() {
		return allCells;
	}

	public List<Edge> getAddedEdges() {
		return addedEdges;
	}

	public List<Edge> getRemovedEdges() {
		return removedEdges;
	}

	public List<Edge> getAllEdges() {
		return allEdges;
	}

	public void addCell(String id, String description,CellType type, int longueur, int largeur,int posx,int posy) {

		switch (type) {

		case RECTANGLE:
			RectangleCell rectangleCell = new RectangleCell(id,description, longueur, largeur,posx,posy);
			rectangleCell.relocate(posx, posy);
			addCell(rectangleCell);
			break;

		case TRIANGLE:
			//TriangleCell circleCell = new TriangleCell(id);
			//addCell(circleCell);
			break;

		default:
			throw new UnsupportedOperationException("Unsupported type: " + type);
		}
	}

	private void addCell(Noeud cell) {

		addedCells.add(cell);

		cellMap.put(cell.getTitre(), cell);

	}

	public void addEdge(String sourceId, String targetId) {

		Noeud sourceCell = cellMap.get(sourceId);
		Noeud targetCell = cellMap.get(targetId);
		Edge edge = new Edge(sourceCell, targetCell);

		addedEdges.add(edge);

	}

	/**
	 * Attach all cells which don't have a parent to graphParent
	 * 
	 * @param cellList
	 */
	public void attachOrphansToGraphParent(List<Noeud> cellList) {

		for (Noeud cell : cellList) {
			if (cell.getPere()==null) {
				graphParent.addCellChild(cell);
			}
		}

	}

	/**
	 * Remove the graphParent reference if it is set
	 * 
	 * @param cellList
	 */
	public void disconnectFromGraphParent(List<Noeud> cellList) {

		for (Noeud cell : cellList) {
			graphParent.removeCellChild(cell);
		}
	}

	public void merge() {

		// cells
		allCells.addAll(addedCells);
		allCells.removeAll(removedCells);

		addedCells.clear();
		removedCells.clear();

		// edges
		allEdges.addAll(addedEdges);
		allEdges.removeAll(removedEdges);

		addedEdges.clear();
		removedEdges.clear();

	}

	public void addCell(String sTitre, String sDescription, CellType rectangle, double intLongueur, double intLargeur,
			double x, double y) {
		// TODO Auto-generated method stub
		
	}
}