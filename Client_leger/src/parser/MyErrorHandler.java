package parser;

import org.w3c.dom.DOMErrorHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;



public class MyErrorHandler implements ErrorHandler{

	@Override
	public void error(SAXParseException e) throws SAXException {
		System.out.println("Erreur de grammaire: "+e.getMessage());
	}

	@Override
	public void fatalError(SAXParseException e) throws SAXException {
		System.out.println("Erreur de syntaxe: "+e.getMessage());
	}

	@Override
	public void warning(SAXParseException e) throws SAXException {
		System.out.println("Warning: "+e.getMessage());
	}

}
