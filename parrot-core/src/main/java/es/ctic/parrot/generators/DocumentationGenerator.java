package es.ctic.parrot.generators;

import java.io.InputStream;
import java.io.OutputStream;

public interface DocumentationGenerator {
	
	public void generate(InputStream template,OutputStream out);

}
