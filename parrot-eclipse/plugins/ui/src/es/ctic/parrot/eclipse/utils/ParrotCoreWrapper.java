package es.ctic.parrot.eclipse.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import es.ctic.parrot.DocumentaryProject;
import es.ctic.parrot.DocumentaryProjectFactory;
import es.ctic.parrot.ParrotAppServ;
import es.ctic.parrot.generators.HtmlOutputGenerator;
import es.ctic.parrot.generators.OutputGenerator.Profile;
import es.ctic.parrot.reader.FileInput;
import es.ctic.parrot.reader.ReaderException;
import es.ctic.parrot.transformers.TransformerException;


public class ParrotCoreWrapper {
	
	private static final String CUSTOMIZE_CSS_URL = "http://ontorule-project.eu/parrot/report/css/eclipse.css";
	private static final String DEFAULT_URI_BASE = "http://ontorule-project.eu/parrot/";
	private static final Locale DEFAULT_LOCALE = Locale.ENGLISH;
	private ParrotAppServ app;
	
	public ParrotCoreWrapper() {
		
	}

	public String exec(Map<File, String> mapFileContentType) {
		System.out.println("Exec Parrot plugin for a list of files"); // DEBUG
		app = new ParrotAppServ();
		for (File f : mapFileContentType.keySet()){
			try {
				System.out.println("Documenting file:" + f.getCanonicalPath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException(e);
				
			}
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		DocumentaryProject dp = DocumentaryProjectFactory.createDocumentaryProject(DEFAULT_LOCALE, CUSTOMIZE_CSS_URL);
		HtmlOutputGenerator generator = new HtmlOutputGenerator.Builder().out(out).uriBase(DEFAULT_URI_BASE).build();
		try {
			for (File file : mapFileContentType.keySet()){
				dp.addInput(new FileInput(file, mapFileContentType.get(file)));
			}
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
