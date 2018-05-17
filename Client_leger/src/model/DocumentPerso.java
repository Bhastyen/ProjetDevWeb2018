package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;




public class DocumentPerso implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4544716438287060576L;
	
	private int id;
	private String nom;
	private int version;
	private Document doc;
	
	
	public DocumentPerso(){
		
	}
	
	public DocumentPerso(int id, int version, String nom){
		this.id = id;
		this.version = version;
		this.nom = new String(nom);
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}
    
    public String getIdMax() {
    	if (doc != null){
	    	Element elem;
	    	NodeList nodes = doc.getElementsByTagName("document");
	    	
	    	if (nodes != null){
	    		elem = (Element) nodes.item(0);
	    		return elem.getAttribute("id_max");
	    	}

	    	return null;
    	}
    	
    	return null;
    }
    
    public String[] getInfoNoeud(int id){
    	String[] infos = new String[7+5];
    	NodeList nodes;
    	Element element = doc.getElementById("n"+new Integer(id).toString());
    	
    	if (element != null){
	    	// recupere les informations sur le noeud
	    	infos[0] = element.getAttribute("parent");
	    	infos[1] = element.getAttribute("x");
	    	infos[2] = element.getAttribute("y");
	    	infos[3] = element.getAttribute("taille");
	    	infos[4] = element.getAttribute("couleur1");
	    	infos[5] = element.getAttribute("couleur2");
	    	infos[6] = element.getAttribute("couleur3");
	    	
	    	// recupere le contenu
	    	nodes = element.getChildNodes(); 
	    	for (int i = 0; i<nodes.getLength(); i++){
	    		if (nodes.item(i).getNodeName().equals("idee")){
	    			element = (Element) nodes.item(i);
	    			break;
	    		}
	    	}
	    	
	    	// recupere les informations sur le contenu
	    	infos[7] = element.getTextContent();
	    	infos[8] = element.getAttribute("taille");
	    	infos[9] = element.getAttribute("couleur1");
	    	infos[10] = element.getAttribute("couleur2");
	    	infos[11] = element.getAttribute("couleur3");
	    	
	    	return infos;
    	}
    	
    	return null; 
    }
    
    public void setInfoNoeud(int id, String[] infos){
    	NodeList nodes;
    	Element element = doc.getElementById("n"+new Integer(id).toString());
    	
    	if (element != null){
	    	// recupere les informations sur le noeud
    		element.setAttribute("parent", infos[0]); 
	    	element.setAttribute("x", infos[1]); 
	    	element.setAttribute("y", infos[2]); 
	    	element.setAttribute("taille", infos[3]); 
	    	element.setAttribute("couleur1", infos[4]);
	    	element.setAttribute("couleur2", infos[5]);
	    	element.setAttribute("couleur3", infos[6]); 
	    	
	    	// recupere le contenu
	    	nodes = element.getChildNodes(); 
	    	for (int i = 0; i<nodes.getLength(); i++){
	    		if (nodes.item(i).getNodeName().equals("idee")){
	    			element = (Element) nodes.item(i);
	    			break;
	    		}
	    	}
	    	
	    	// recupere les informations sur le contenu
	    	element.setTextContent(infos[7]);
	    	element.setAttribute("taille", infos[8]);
	    	element.setAttribute("couleur1", infos[9]);
	    	element.setAttribute("couleur2", infos[10]);
	    	element.setAttribute("couleur3", infos[11]); 
	    }
    	 
    }
    
    public void ajoutNoeud(int parent, String[] infos){
    	// on commence en creant un nouvel element
    	Element fils;
    	Element element = doc.getElementById("n"+new Integer(parent).toString());
    	fils = doc.createElement("noeud");
    	 
    	// recupere le prochain id a cree et le met a jour dans la balise document
    	String id_max = ((Element) doc.getElementsByTagName("document").item(0)).getAttribute("id_max");
    	((Element) doc.getElementsByTagName("document").item(0)).setAttribute("id_max", new Integer(Integer.parseInt(id_max)+1).toString());
    	 
    	// defini les infos du nouveau noeud puis l'ajoute
    	fils.setAttribute("id", new Integer(Integer.parseInt(id_max)+1).toString());
    	element.appendChild(fils);
    	setInfoNoeud(Integer.parseInt(id_max)+1, infos);
    }

    public void suppressionNoeud(int id){
    	Element element = doc.getElementById("n"+new Integer(id).toString());
    	doc.removeChild(element);
    }

    public void replacerNoeud(int id, int parent){
    	Element newparent = doc.getElementById("n"+new Integer(parent).toString());
    	Element fils = doc.getElementById("n"+new Integer(id).toString());
    	doc.replaceChild(newparent, fils);
    }
    
    public void ecrireNoeud(Element element, FileOutputStream out) throws NumberFormatException, IOException{
    
    	if (element == null)
    		return ;
    	
    	NodeList nodes = element.getChildNodes();
    	
    	if (element.getNodeName().equals("noeud")){
    		// ecriture du noeud
    		out.write(new Byte("<"+element.getNodeName()+" id="+element.getAttribute("id")+" x="+element.getAttribute("x")+" y="+element.getAttribute("y")
    				           +" taille="+element.getAttribute("taille")+" couleur1="+element.getAttribute("couleur1")+" couleur2="+element.getAttribute("couleur2")
    				           +" couleur3="+element.getAttribute("couleur3")+">\n"));
    		// on recurre sur les fils
    		for (int i = 0; i<nodes.getLength(); i++){
    			ecrireNoeud((Element) nodes.item(i), out);
    		}
    		// fermeture de la balise noeud
    		out.write(new Byte("</noeud>\n"));
    	}else if (element.getNodeName().equals("idee")){
    		// ecriture de l'idee et de son texte
    		out.write(new Byte("<"+element.getNodeName()+" taille="+element.getAttribute("taille")+" couleur1="
    		                   +element.getAttribute("couleur1")+" couleur2="
    		                   +element.getAttribute("couleur2")+" couleur3="
    		                   +element.getAttribute("couleur3")+">"
    		                   +element.getTextContent()+"</idee>\n"));
    		
    	}
    }
    
    public void ecrireFichier(int version, String nomDoc, String id_max){
    	FileOutputStream out;
    	
		try {
			// cree le fichier et ecrit l'entete des fichiers xml + la premiere balise document unique par fichier
			out = new FileOutputStream(new File("/WEB_INF/xml/"+nomDoc+"/"+nomDoc+version));
			out.write(new Byte("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n"));
			out.write(new Byte("<!DOCTYPE MindMap SYSTEM \"/WEB-INF/DTD/MindMap.dtd\">\n\n"));
			out.write(new Byte("<document nom=\""+nomDoc+"\" id_max=\""+id_max+"\">\n"));
			
			// recupere les enfants du document s'il existe
			if (doc != null){
				NodeList nodes = doc.getChildNodes();
	
				// ecrit les noeuds et leurs contenu par recurrence
				for (int i = 0; i<nodes.getLength(); i++)
					ecrireNoeud((Element) nodes.item(i), out);
			}
			// ecrit la fin du document
			out.write(new Byte("</document>\n"));
	
		    // fermeture du stream out
			out.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
