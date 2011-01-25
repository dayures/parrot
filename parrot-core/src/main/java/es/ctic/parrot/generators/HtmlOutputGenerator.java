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

public class HtmlOutputGenerator implements OutputGenerator {

    private final OutputStream out;
    private InputStream template;

    /**
     * @param out Stream where the output will be written
     * @param template The template for the output report
     */
    public HtmlOutputGenerator(OutputStream out, InputStream template) {
        this.out = out;
        this.template = template;
    }

    public void generateOutput(Document document) {
        VelocityContext ctx = new VelocityContext();
        ctx.put("document", document);
        fillTemplate(out, ctx, template);
    }

    private void fillTemplate(OutputStream out, VelocityContext ctx, InputStream template) {
        // FIXME: this code is not reentrant!
        try {
            Properties p = new Properties();
            p.setProperty("resource.loader", "class,file");
            p.setProperty("class.resource.loader.class","org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            p.setProperty("file.resource.loader.class","org.apache.velocity.runtime.resource.loader.FileResourceLoader");
            p.setProperty(Velocity.EVENTHANDLER_REFERENCEINSERTION, "org.apache.velocity.app.event.implement.EscapeHtmlReference");

            Velocity.init(p);

            OutputStreamWriter osw = new OutputStreamWriter(out, (new InputStreamReader(template)).getEncoding());
            //Velocity.mergeTemplate("html.vm","utf-8",ctx, osw);
            Velocity.evaluate(ctx, osw, "parrot", new BufferedReader(new InputStreamReader(template))); 
            osw.flush();
            osw.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }		
    }

}
