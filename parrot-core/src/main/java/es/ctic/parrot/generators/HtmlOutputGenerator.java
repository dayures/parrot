package es.ctic.parrot.generators;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Properties;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import es.ctic.parrot.docmodel.Document;

/**
 * Generates <code>HTML</code> content from a documentary model.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public class HtmlOutputGenerator implements OutputGenerator {

    private static final String PROFILE = "profile";
	private static final String DOCUMENT = "document";
	private static final String REPORTURL_NOLANG = "reportUrl_NoLang";
	
	private final OutputStream out;
    private InputStream template;

    /**
     * Constructs a generator of <code>HTML</code> content
     * @param out Stream where the output will be written.
     * @param template The template for the output report.
     */
    public HtmlOutputGenerator(OutputStream out, InputStream template) {
        this.out = out;
        this.template = template;
    }

    /**
     * Constructs a generator of <code>HTML</code> content
     * @param out Stream where the output will be written.
     */
    public HtmlOutputGenerator(OutputStream out) {
        this.out = out;
        this.template = getDefaultTemplate();
    }
    
    private InputStream getDefaultTemplate() {
		InputStream template = Thread.currentThread().getContextClassLoader().getResourceAsStream("html/template.vm");
		if (template == null) {
			throw new RuntimeException("Failed to load resource");
		}
		return template;
	}

	/**
     * Generates an output.
     * @param document The source document.
     * @param profile The profile of the user that will read the report.
     */
    public void generateOutput(Document document, Profile profile) {
        VelocityContext ctx = new VelocityContext();
        ctx.put(DOCUMENT, document);
        ctx.put(PROFILE, profile);
        ctx.put(REPORTURL_NOLANG, getNoLangURL(document.getReportURL()));
        fillTemplate(out, ctx, template);
    }

    /**
     * Returns a URL without the <code>language</code> parameter (if exists).
     * @param url the URL to treat.
     * @return a URL without the <code>language</code> parameter (if exists).
     */
	private String getNoLangURL(String url) {
		if (url != null){
			if (url.matches(".*&language=.*?(?=[&]).*")){
	        	url = url.replaceFirst("&language=.*?(?=[&])", "");
	        } else if (url.matches(".*&language=.*$")) {
	        	url = url.replaceFirst("&language=.*$", "");
	        } else {
	        	//url = url ;
	        }
		}
		return url;
	}

    
    /**
     * Fills the template using the context given and writes the result into an output stream.
     * @param out Stream where the output will be written.
     * @param ctx The velocity context that contains the information to fill the template.
     * @param template The template for the output report.
     * 
     */
    private void fillTemplate(OutputStream out, VelocityContext ctx, InputStream template) {
        // FIXME: this code is not reentrant!
        try {
            Properties p = new Properties();
            p.setProperty("resource.loader", "class,file");
            p.setProperty("class.resource.loader.class","org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            p.setProperty("file.resource.loader.class","org.apache.velocity.runtime.resource.loader.FileResourceLoader");
            p.setProperty(Velocity.EVENTHANDLER_REFERENCEINSERTION, "org.apache.velocity.app.event.implement.EscapeHtmlReference");
            p.setProperty("eventhandler.escape.html.match", "/^(?!\\$unescapehtml_).*/");
            p.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.NullLogSystem");
            Velocity.init(p);

            OutputStreamWriter osw = new OutputStreamWriter(out, (new InputStreamReader(template)).getEncoding());
            Velocity.evaluate(ctx, osw, "parrot", new BufferedReader(new InputStreamReader(template))); 
            osw.flush();
            osw.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }		
    }

}
