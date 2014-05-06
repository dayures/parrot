package es.ctic.parrot;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.hp.hpl.jena.rdf.model.Model;

import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.reader.Input;
import es.ctic.parrot.reader.ReaderException;
import es.ctic.parrot.reader.URLInput;
import es.ctic.parrot.reader.jena.JenaOWLReader;

/**
 * 
 * Unit tests.
 * 
 * @author Carlos Tejo Alonso (<a href="http://www.fundacionctic.org">Fundación CTIC</a>)
 * @version 1.0
 * @since 1.0
 *
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

 
    public void testHeaders() throws MalformedURLException, IOException, ReaderException {
    	JenaOWLReader reader = new JenaOWLReader();
    	Input input = new URLInput(new URL("http://richard.cyganiak.de/2008/03/rdfbugs/accept.php"));
    	DocumentableObjectRegister register = new DocumentableObjectRegister();
    	reader.readDocumentableObjects(input, register);
    	Model model = reader.getOntModel();
		model.write(System.out, "RDF/XML-ABBREV");
    	assertTrue(model.size() > 0);
    }
    
    public void testContentLocation() throws MalformedURLException, IOException, ReaderException {
    	JenaOWLReader reader = new JenaOWLReader();
    	Input input = new URLInput(new URL("http://purl.org/NET/scovo#"));
    	DocumentableObjectRegister register = new DocumentableObjectRegister();
    	reader.readDocumentableObjects(input, register);
    	Model model = reader.getOntModel();
		model.write(System.out, "RDF/XML-ABBREV");
    	assertTrue(model.size() > 0);
    }
    
    
}
