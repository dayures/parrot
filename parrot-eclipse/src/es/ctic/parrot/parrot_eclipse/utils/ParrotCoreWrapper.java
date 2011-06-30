package es.ctic.parrot.parrot_eclipse.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;


import es.ctic.parrot.appserv.ParrotAppServ;
import es.ctic.parrot.generators.HtmlOutputGenerator;
import es.ctic.parrot.reader.DocumentReader;
import es.ctic.parrot.reader.ReaderException;
import es.ctic.parrot.reader.StringInput;
import es.ctic.parrot.reader.jena.JenaOWLReader;
import es.ctic.parrot.reader.rifle.RifleXmlReader;
import es.ctic.parrot.transformers.TransformerException;
import es.ctic.parrot.DocumentaryProject;


public class ParrotCoreWrapper {
	
	private ParrotAppServ app;
	private InputStream template;
	
	public ParrotCoreWrapper() {
		
	}
	
	public String exec(String input, String contenttype){
		System.out.println("Exec Parrot plugin"); // DEBUG
		app = new ParrotAppServ();
		JenaOWLReader ontologyWrapper = new JenaOWLReader();
        DocumentReader ruleWrapper = new RifleXmlReader(ontologyWrapper);
        app.setOntologyReader(ontologyWrapper);
        app.setRuleXmlReader(ruleWrapper);
        template = Thread.currentThread().getContextClassLoader().getResourceAsStream("html/template.vm");
		System.out.println("Parrot Core Input:" + input); // DEBUG
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		DocumentaryProject dp = new DocumentaryProject(Locale.ENGLISH);
		HtmlOutputGenerator generator = new HtmlOutputGenerator(outputStream, template);
		dp.addInput(new StringInput(input, contenttype));
		
		try {
			app.createDocumentation(dp, generator);
		} catch (ReaderException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (TransformerException e) {
			throw new RuntimeException(e);
		}
		
		return outputStream.toString();
	}
		
}
