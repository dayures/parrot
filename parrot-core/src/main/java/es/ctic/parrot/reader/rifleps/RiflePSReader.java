package es.ctic.parrot.reader.rifleps;

import java.io.IOException;
import java.net.URL;

import org.antlr.runtime.ANTLRReaderStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.apache.log4j.Logger;
import net.sourceforge.rifle.ast.Document;
import net.sourceforge.rifle.psparser.RIFPRDLexer;
import net.sourceforge.rifle.psparser.RIFPRDParser;

import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.reader.DocumentReader;
import es.ctic.parrot.reader.Input;
import es.ctic.parrot.reader.ReaderException;
import es.ctic.parrot.reader.URLInput;

public class RiflePSReader implements DocumentReader {

    private static final Logger logger = Logger.getLogger(RiflePSReader.class);
    
    private DocumentReader ontologyWrapper;
    private DocumentReader rifXmlReader;
	
	public void readDocumentableObjects(Input input,
			DocumentableObjectRegister register) throws IOException, ReaderException {

		CharStream stream = new ANTLRReaderStream(input.openReader());
		RIFPRDLexer lexer = new RIFPRDLexer(stream);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		RIFPRDParser parser = new RIFPRDParser(tokens);
		try {
			RIFPRDParser.document_return document_return = parser.document();
			
			if (parser.getNumberOfSyntaxErrors() == 0) {
			      logger.info("RIF PS document successfully parsed");
//				  for (String locator : parser.importMap.keySet()) {
//					    logger.info("IMPORT. Locator: " + locator + ", profile: " + parser.importMap.get(locator));
//		                URL url = new URL(locator);
//
//		                logger.info("locator: "+ parser.importMap.get(locator));
//
//		                if(parser.importMap.get(locator) != null){
//			                Input additionalInput = new URLInput(url, "application/rdf+xml"); // FIXME: hardcoded mime type. Profile is not used yet
//			                ontologyWrapper.readDocumentableObjects(additionalInput, register);
//		                }else{
//		                	Input additionalInput = new URLInput(url, "application/rif+xml"); // FIXME: hardcoded mime type. Profile is not used yet
//		                	rifXmlReader.readDocumentableObjects(additionalInput, register);
//		                }
//				  }
			      
			      Document document = document_return.ret_document;
			      RifVisitor visitor = new RifVisitor(register);
			      document.accept(visitor);
			} else {
				// FIXME. This only shows the number of errors, but not the errors.
				throw new ReaderException("RIF PS document " + input + " cannot be parsed. There are " + parser.getNumberOfSyntaxErrors() + " syntax errors");
			}
		} catch (RecognitionException e) {
			// FIXME: use a more specific exception class (maybe org.antlr.runtime.RecognitionException)
			throw new ReaderException("While parsing RIF PS document", e);
		}
		

	}

	public RiflePSReader(DocumentReader ontologyWrapper, DocumentReader rifXmlReader) {
		super();
		this.ontologyWrapper = ontologyWrapper;
		this.rifXmlReader = rifXmlReader;
	}
	

}
