package parser;

import java.io.File;
import java.io.IOException;

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
    
    public Document ParserDoc(String fichierXml){
    	factory = DocumentBuilderFactory.newInstance();
    	factory.setValidating(true);
    	
    	try {
			builder = factory.newDocumentBuilder();
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
