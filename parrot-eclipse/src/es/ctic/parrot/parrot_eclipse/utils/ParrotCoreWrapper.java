package es.ctic.parrot.parrot_eclipse.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Locale;

import es.ctic.parrot.DocumentaryProject;
import es.ctic.parrot.DocumentaryProjectFactory;
import es.ctic.parrot.ParrotAppServ;
import es.ctic.parrot.generators.HtmlOutputGenerator;
import es.ctic.parrot.generators.OutputGenerator.Profile;
import es.ctic.parrot.reader.ReaderException;
import es.ctic.parrot.reader.StringInput;
import es.ctic.parrot.transformers.TransformerException;


public class ParrotCoreWrapper {
	
	private static final String DEFAULT_URI_BASE = "http://ontorule-project.eu/parrot/";
	private static final Locale DEFAULT_LOCALE = Locale.ENGLISH;
	private ParrotAppServ app;
	
	public ParrotCoreWrapper() {
		
	}
	
	public String exec(String input, String contenttype){
		System.out.println("Exec Parrot plugin"); // DEBUG
		app = new ParrotAppServ();
		System.out.println("Parrot Core Input:" + input); // DEBUG
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		DocumentaryProject dp = DocumentaryProjectFactory.createDocumentaryProject(DEFAULT_LOCALE);
		HtmlOutputGenerator generator = new HtmlOutputGenerator.Builder().out(out).uriBase(DEFAULT_URI_BASE).build();
		dp.addInput(new StringInput(input, contenttype));
		
		try {
			app.createDocumentation(dp, generator, Profile.TECHNICAL); // FIXME this should be customize
		} catch (ReaderException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (TransformerException e) {
			throw new RuntimeException(e);
		}
		
		return out.toString();
	}
		
}
