package parser;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;




public class ParserSAX {
    private SAXParser parser;
	
	
    public ParserSAX() {
    	SAXParserFactory factory = SAXParserFactory.newInstance();
    	factory.setValidating(true);
    	try {
			parser = factory.newSAXParser();
		} catch (ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		}
    }
    
	
	public void monParsing(DefaultHandler h, String xmlDoc) {
		try {
			parser.parse(xmlDoc, h);
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/*public static List<Noeud> parserSAX(String docXML) {
         ParserSAX parser = new ParserSAX();
         MyParserSAXDocument handler = new MyParserSAXDocument();
         
         parser.monParsing(handler, docXML);
         
         return parser.getNoeuds();
	}*/

}
