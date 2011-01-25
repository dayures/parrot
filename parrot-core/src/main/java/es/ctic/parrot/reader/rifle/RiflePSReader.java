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
import es.ctic.parrot.reader.DocumentReader;
import es.ctic.parrot.reader.Input;
import es.ctic.parrot.reader.ReaderException;

public class RiflePSReader extends ImportResolver implements DocumentReader {

    private static final Logger logger = Logger.getLogger(RiflePSReader.class);
    
    public RiflePSReader(DocumentReader ontologyReader, DocumentReader rifXmlReader) {
        super(ontologyReader, rifXmlReader);
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
			      Document document = document_return.ret_document;
                  resolveImports(document, register);
			      RifleASTVisitor visitor = new RifleASTVisitor(register);
			      document.accept(visitor, null); // it's null because it is the root node
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
