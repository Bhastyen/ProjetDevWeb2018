package parser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;




public class ParserDOMDoc {
    DocumentBuilderFactory factory;
    DocumentBuilder builder;
    
    
    public ParserDOMDoc() {
    	factory = null;
    	builder = null;
    }
    
    public static boolean copier(File source, File dest) {   /* code source venant de l'url: https://java.developpez.com/faq/javaio?page=Gestion-des-fichiers#LANGAGE_FICHIER_copier */
        try (InputStream sourceFile = new FileInputStream(source);  
                OutputStream destinationFile = new FileOutputStream(dest)) { 
            // Lecture par segment de 0.5Mo  
            byte buffer[] = new byte[512 * 1024]; 
            int nbLecture; 
            while ((nbLecture = sourceFile.read(buffer)) != -1){ 
                destinationFile.write(buffer, 0, nbLecture); 
            } 
        } catch (IOException e){ 
            e.printStackTrace(); 
            return false; // Erreur 
        } 
        return true; // Résultat OK   
    }

	public static void creerDTD(String dest){
		FileWriter f;
		BufferedWriter out;
		
		// cree le fichier
		try {
			f = new FileWriter(dest);
			out = new BufferedWriter(f);
            
			out.write("<!ELEMENT document (noeud*)>\n");
			out.write("<!ATTLIST document nom CDATA #REQUIRED id_max CDATA #REQUIRED>\n");
			out.write("<!ELEMENT noeud (idee,noeud*)>\n");
			out.write("<!ATTLIST noeud id ID #REQUIRED parent IDREF #REQUIRED x CDATA #REQUIRED y CDATA #REQUIRED taille CDATA #REQUIRED couleur1 CDATA #REQUIRED couleur2 CDATA #REQUIRED couleur3 CDATA #REQUIRED>\n");
			out.write("<!ELEMENT idee (#PCDATA)>\n");
			out.write("<!ATTLIST idee taille CDATA #REQUIRED couleur1 CDATA #REQUIRED couleur2 CDATA #REQUIRED couleur3 CDATA #REQUIRED>\n");
		
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
    
    public Document ParserDoc(String fichierXml){
    	MyErrorHandler error = new MyErrorHandler();
    	factory = DocumentBuilderFactory.newInstance();
    	factory.setValidating(true);
    	
    	try {
			builder = factory.newDocumentBuilder();
			builder.setErrorHandler(error);
			return builder.parse(new File(fichierXml));
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	return null;
    }
	
}
