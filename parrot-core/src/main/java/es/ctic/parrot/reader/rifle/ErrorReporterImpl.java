package es.ctic.parrot.reader.rifle;

import net.sourceforge.rifle.psparser.ErrorReporter;

import org.apache.log4j.Logger;

/**
 * An implementation of the error reporter interface. 
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 * 
 */
public class ErrorReporterImpl implements ErrorReporter {

    private static final Logger logger = Logger.getLogger(ErrorReporterImpl.class);
    
    private String firstError = null;
    
    /**
     * Logs an error during the parsing and sets this as first error if it has not been set before.
     * @param msg the message.
     */
    public void reportError(String msg) {
        logger.error("While parsing RIF PS document: " + msg);
        if (getFirstError() == null) {
            this.firstError = msg;
        }
    }

    /**
     * Returns the first error reported.
     * @return the first error reported.
     */
    public String getFirstError() {
        return firstError;
    }

}
