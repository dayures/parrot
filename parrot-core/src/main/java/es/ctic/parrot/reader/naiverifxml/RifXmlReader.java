package es.ctic.parrot.reader.naiverifxml;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathException;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.reader.DocumentReader;
import es.ctic.parrot.reader.Input;
import es.ctic.parrot.reader.ReaderException;
import es.ctic.parrot.reader.URLInput;

public class RifXmlReader implements DocumentReader {

    private static final Logger logger = Logger.getLogger(RifXmlReader.class);
    
    private DocumentReader ontologyWrapper;
    
    public RifXmlReader(DocumentReader ontologyWrapper) {
        this.ontologyWrapper = ontologyWrapper;
    }
        
    private Document parseDocument(Reader in) throws IOException {
        logger.info("Parsing RIF XML file");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(false); // FIXME: namespace
        try {
            return dbf.newDocumentBuilder().parse(new InputSource(in));
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public void readDocumentableObjects(Input input, DocumentableObjectRegister register) throws IOException, ReaderException {
        Document document = parseDocument(input.openReader());
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();
        try {
            XPathExpression importXPathExpr = xpath.compile("//Import/location/text()"); // FIXME: namespace
            NodeList importNodeList = (NodeList) importXPathExpr.evaluate(document, XPathConstants.NODESET);
            logger.info("Found " + importNodeList.getLength() + " import sentences in the RIF file");
            for (int i = 0; i<importNodeList.getLength() ; i++) {
                logger.info("The RIF file imports location: " + importNodeList.item(i).getNodeValue());
                URL url = new URL(importNodeList.item(i).getNodeValue());
                Input additionalInput = new URLInput(url, "application/rdf+xml"); // FIXME: hardcoded mime type
                ontologyWrapper.readDocumentableObjects(additionalInput, register);
                //FIXME import could be another RIF document
            }

            XPathExpression sentenceXPathExpr = xpath.compile("//sentence"); // FIXME: namespace
            NodeList sentenceNodeList = (NodeList) sentenceXPathExpr.evaluate(document, XPathConstants.NODESET);
            logger.info("Found " + sentenceNodeList.getLength() + " rules (sentences)");
            for (int i = 0; i<sentenceNodeList.getLength() ; i++) {
                register.registerDocumentableObject(new RuleXmlImpl((Element) sentenceNodeList.item(i), i, register));
            }
        } catch (XPathException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (DOMException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
