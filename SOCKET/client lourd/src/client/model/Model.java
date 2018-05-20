package client.model;

//Basé sur un post sur le forum http://stackoverflow.com
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import client.util.Liens;
import client.util.NoeudType;
import client.util.RectangleNoeud;
//import client.util.Trianglenoeud;

public class Model {

	Noeud graphParent;

	List<Noeud> allnoeuds;
	List<Noeud> addednoeuds;
	List<Noeud> removednoeuds;

	List<Liens> allliens;
	List<Liens> addedliens;
	List<Liens> removedliens;

	Map<String, Noeud> noeudMap; // <id,noeud>

	public Model() {

		graphParent = new Noeud("_ROOT_", "salut", 0, 0, 0, 0,false);

		clear();
	}

	public void clear() {

		allnoeuds = new ArrayList<>();
		addednoeuds = new ArrayList<>();
		removednoeuds = new ArrayList<>();

		allliens = new ArrayList<>();
		addedliens = new ArrayList<>();
		removedliens = new ArrayList<>();

		noeudMap = new HashMap<>(); // <id,noeud>

	}

	public void clearAddedLists() {
		addednoeuds.clear();
		addedliens.clear();
	}

	public List<Noeud> getAddednoeuds() {
		return addednoeuds;
	}

	public List<Noeud> getRemovednoeuds() {
		return removednoeuds;
	}

	public List<Noeud> getAllnoeuds() {
		return allnoeuds;
	}

	public List<Liens> getAddedliens() {
		return addedliens;
	}

	public List<Liens> getRemovedliens() {
		return removedliens;
	}

	public List<Liens> getAllliens() {
		return allliens;
	}

	public void addnoeud(String id, String description, NoeudType type, int longueur, int largeur, int posx, int posy,boolean root) {

		switch (type) {

		case RECTANGLE:
			RectangleNoeud rectanglenoeud = new RectangleNoeud(id, description, longueur, largeur, posx, posy,root);
			rectanglenoeud.relocate(posx, posy);
			addnoeud(rectanglenoeud);
			break;

		case TRIANGLE:
			// Trianglenoeud circlenoeud = new Trianglenoeud(id);
			// addnoeud(circlenoeud);
			break;

		default:
			throw new UnsupportedOperationException("Unsupported type: " + type);
		}
	}

	public void addnoeud(Noeud noeud) {

		addednoeuds.add(noeud);
		noeud.relocate(noeud.getPosX(), noeud.getPosY());
		
		noeudMap.put(noeud.getTitre(),noeud);

	}

	public void addlien(String sourceId, String targetId) {

		Noeud sourcenoeud = noeudMap.get(sourceId);
		Noeud targetnoeud = noeudMap.get(targetId);
		Liens lien = new Liens(sourcenoeud,targetnoeud);

		addedliens.add(lien);

	}

	/**
	 * Attache les noeuds orphelins
	 * 
	 * @param noeudList
	 */
	public void attachOrphansToGraphParent(List<Noeud> noeudList) {

		for (Noeud noeud : noeudList) {
			if (noeud.getPere() == null) {
				graphParent.addnoeudChild(noeud);
			}
		}

	}

	/**
	 * 
	 * Deconnecte les noeuds des parents
	 * 
	 * @param noeudList
	 */
	public void disconnectFromGraphParent(List<Noeud> noeudList) {

		for (Noeud noeud : noeudList) {
			graphParent.removenoeudChild(noeud);
		}
	}

	public void merge() {

		allnoeuds.addAll(addednoeuds);
		allnoeuds.removeAll(removednoeuds);

		addednoeuds.clear();
		removednoeuds.clear();

		allliens.addAll(addedliens);
		allliens.removeAll(removedliens);

		addedliens.clear();
		removedliens.clear();

	}


}