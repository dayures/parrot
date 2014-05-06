package es.ctic.parrot;

import java.io.IOException;
import java.net.MalformedURLException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import es.ctic.parrot.reader.ReaderException;
import es.ctic.parrot.reader.jena.OntResourceAnnotationStrategy;

/**
 * 
 * Unit test for testing the generating of labels automatically.
 * 
 * @author Carlos Tejo Alonso (<a href="http://www.fundacionctic.org">Fundación CTIC</a>)
 * @version 1.0
 * @since 1.0
 *
 */
public class GenerateLabelAutomaticallyTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public GenerateLabelAutomaticallyTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( GenerateLabelAutomaticallyTest.class );
    }

 
    public void test1() throws MalformedURLException, IOException, ReaderException {
    	String reference = "defect-identification-ruleset";
    	String expectedLabel = "defect identification ruleset";
    	String generatedLabel = OntResourceAnnotationStrategy.getLabelAutomatically(reference);
    	assertTrue(expectedLabel.equals(generatedLabel));
    }
    
}
