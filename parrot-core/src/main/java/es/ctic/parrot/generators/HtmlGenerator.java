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

public class HtmlGenerator implements DocumentationGenerator{
	private Document document;
	
	public HtmlGenerator(Document document){
		this.document=document;
	}

	public void generate(InputStream template,OutputStream out) {
		VelocityContext ctx = new VelocityContext();
		ctx.put("document", document);
		fillTemplate(out, ctx, template);
	}
	
	private void fillTemplate(OutputStream out, VelocityContext ctx, InputStream is) {
		try {
			Properties p=new Properties();
			p.setProperty("resource.loader", "class,file");
			p.setProperty("class.resource.loader.class","org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
			p.setProperty("file.resource.loader.class","org.apache.velocity.runtime.resource.loader.FileResourceLoader");
			p.setProperty(Velocity.EVENTHANDLER_REFERENCEINSERTION, "org.apache.velocity.app.event.implement.EscapeHtmlReference");

			Velocity.init(p);
			 
			OutputStreamWriter osw = new OutputStreamWriter(out, (new InputStreamReader(is)).getEncoding());
			//Velocity.mergeTemplate("html.vm","utf-8",ctx, osw);
			Velocity.evaluate(ctx, osw, "parrot", new BufferedReader(new InputStreamReader(is))); 
			osw.flush();
			osw.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}		
	}



}
