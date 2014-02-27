package es.ctic.parrot.reader.rifle;

import java.io.IOException;

import net.sourceforge.rifle.ast.Document;
import net.sourceforge.rifle.psparser.RIFPRDLexer;
import net.sourceforge.rifle.psparser.RIFPRDParser;
import net.sourceforge.rifle.psparser.exceptions.UndefinedPrefixException;

import org.antlr.runtime.ANTLRReaderStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.apache.log4j.Logger;

import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.reader.Input;
import es.ctic.parrot.reader.ReaderException;
import es.ctic.parrot.reader.jena.JenaOWLReader;

/**
 * A reader for RIF Presentation Syntax (PS) input documents. 
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 * 
 */
public class RiflePSReader extends AbstractRifleReader {

    private static final Logger logger = Logger.getLogger(RiflePSReader.class);

    /**
     * Constructs a RIF PS reader.
     * @param ontologyReader the ontology reader.
     * @param rifXmlReader the RIF XML reader for the import resolver.
     */
    public RiflePSReader(JenaOWLReader ontologyReader, RifleXMLReader rifXmlReader) {
        setOntologyReader(ontologyReader);	
        setRIFImportResolver(new RIFImportResolver(ontologyReader, rifXmlReader));
    }
    
	public void readDocumentableObjects(Input input,
			DocumentableObjectRegister register) throws IOException, ReaderException {

		CharStream stream = new ANTLRReaderStream(input.openReader());
		RIFPRDLexer lexer = new RIFPRDLexer(stream);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		RIFPRDParser parser = new RIFPRDParser(tokens);
        ErrorReporterImpl errorReporter = new ErrorReporterImpl();
        parser.setErrorReporter(errorReporter);
		try {
			RIFPRDParser.document_return document_return = parser.document();
			
			if (parser.getNumberOfSyntaxErrors() == 0) {
				  logger.trace("RIF PS document read without errors");
			      Document document = document_return.ret_document;
			      getRIFImportResolver().resolveImports(document, register);
			      RifleASTVisitor visitor = new RifleASTVisitor(register, getOntologyReader().getAnnotationStrategy(), getOntologyReader().getOntModel());
			      document.accept(visitor);
			} else {
                throw new ReaderException("RIF PS document " + input + " cannot be parsed. There are " + parser.getNumberOfSyntaxErrors() + " syntax errors. First error is: " + errorReporter.getFirstError());
			}
		}
		catch (UndefinedPrefixException e) {
			throw new ReaderException(e.getMessage() + " while parsing RIF PS document", e);
		}
		catch (RecognitionException e) {
			throw new ReaderException("While parsing RIF PS document", e);
		}
		

	}

}
