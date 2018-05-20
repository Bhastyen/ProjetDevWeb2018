package parser;

import java.util.ArrayList;
import java.util.List;

import model.Noeud;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;




public class MyParserSAXDocument extends DefaultHandler{
	private List<Noeud> noeuds = new ArrayList<Noeud>();
	private int niveau = 0;

	public void startDocument(){
		System.out.println("Liste des etudiants:");
	}
	
	public void endDocument(){
		System.out.println();
	}
	
	public void startElement(String uri, String localName, String qName, Attributes att){
		// hierarchisation de l'affichage grace aux identations + affiche le nom des elements
		if (qName == "racine")
			//noeuds.add(new Noeud())
			System.out.println(qName);
		else if (qName == "etudiant")
			System.out.println("\t"+qName);
		else System.out.print("\t\t"+qName+": ");
	} 
	
	public void endElement(String uri, String localName, String qName){
		
		
	}
	
	public void characters(char[] caracteres, int depart, int longueur){
		// affichage des mots contenu dans les balises si le fichier est correctement fait
		String mot = new String(caracteres, depart, longueur);
		System.out.println(mot);
	}
	
	public List<Noeud> getNoeuds(){
		return noeuds; 
	}

}
