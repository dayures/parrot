package es.ctic.parrot.cli;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Locale;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.log4j.Logger;

import es.ctic.parrot.DocumentaryProject;
import es.ctic.parrot.DocumentaryProjectFactory;
import es.ctic.parrot.ParrotAppServ;
import es.ctic.parrot.generators.HtmlOutputGenerator;
import es.ctic.parrot.generators.OutputGenerator;
import es.ctic.parrot.generators.OutputGenerator.Profile;
import es.ctic.parrot.reader.FileInput;
import es.ctic.parrot.reader.URLInput;

public class Parrot {

	private static final Logger logger = Logger.getLogger(Parrot.class);
    
    private static final OutputStream DEFAULT_OUT = System.out;
    private static final Locale DEFAULT_LOCALE = Locale.ENGLISH;
    private static final String DEFAULT_TEMPLATE = "classpath:html/template.vm";
    private static final String TECHNICAL_PROFILE = "technical";
    private static final String BUSINESS_PROFILE = "business";
    private static final String DEFAULT_PROFILE = TECHNICAL_PROFILE;
	private static final String DEFAULT_PARROT_URI_BASE = "http://ontorule-project.eu/parrot/"; 

    
    public static void main( String[] args ) throws Exception {
    	
        Options options = createOptions();
        CommandLine cmd = parseCommandLine(args, options);

        // default values
        OutputStream out = DEFAULT_OUT;
        Locale locale = DEFAULT_LOCALE;
        String template = DEFAULT_TEMPLATE;
        Profile profile = Profile.TECHNICAL;
        String customizeCssUrl = null;
        String base = DEFAULT_PARROT_URI_BASE;
        
        // process options
        if (cmd.hasOption("h")) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp(320,"java -jar target/parrot-jar-with-dependencies.jar","options" ,options, "\nExample of usage: \n" +
            		"java -jar parrot-jar-with-dependencies.jar " +
            		"-i http://ontorule-project.eu/resources/m24dem/CAx_M24_ontology.owl " +
            		"-i http://ontorule-project.eu/resources/m24dem/CAx_M24_rules_parrot.rifps " +
            		"-o documentation-generated.html " +
            		"-s http://idi.fundacionctic.org/semantica/parrot/style.css " +
            		"-b http://ontorule-project.eu/parrot/", true); 
            // EXAMPLE java -jar target/parrot-jar-with-dependencies.jar -i http://ontorule-project.eu/resources/m24dem/CAx_M24_ontology.owl -i http://ontorule-project.eu/resources/m24dem/CAx_M24_rules_parrot.rifps -s http://idi.fundacionctic.org/semantica/parrot/style.css -o a.html
            return;
        }
        
        if (cmd.hasOption("o")) {
            out = new FileOutputStream(cmd.getOptionValue("o"));
        }
        
        if (cmd.hasOption("l")) {
            String lang = cmd.getOptionValue("l");
            locale = new Locale(lang);
        }
        
        if (cmd.hasOption("t")) {
            template = cmd.getOptionValue("t");
        }
        
        if (cmd.hasOption("s")) {
        	customizeCssUrl = cmd.getOptionValue("s");
        }
        
        // profile
        if (cmd.hasOption("p")) {
            String profileStr = cmd.getOptionValue("p", DEFAULT_PROFILE).toLowerCase();
		
			if (profileStr.equals(BUSINESS_PROFILE)){
				profile = Profile.BUSINESS;
			} 
			
			if (profileStr.equals(TECHNICAL_PROFILE)){
				profile = Profile.TECHNICAL;
			}
            
        }
        
        if (cmd.getOptionValues('i') == null || cmd.getOptionValues('i').length == 0){
            printError("Please specify at least one input");
            return;
        }

        // URI base
        if (cmd.hasOption("b")) {
        	base = cmd.getOptionValue("b");
        }

        ParrotAppServ app = new ParrotAppServ();

        try {
            InputStream templateInputStream = openTemplateInputStream(template);
            DocumentaryProject dp = DocumentaryProjectFactory.createDocumentaryProject(locale, customizeCssUrl);
            for ( String inputFilename : cmd.getOptionValues('i') ) {
                if (inputFilename.startsWith("http:")) {
                    dp.addInput(new URLInput(new URL(inputFilename)));
                } else {
                    dp.addInput(new FileInput(new File(inputFilename)));
                }
            }
            if (dp.getInputs().isEmpty()) {
            	printError("Please specify at least one input");
            } else {
                OutputGenerator outputGenerator = new HtmlOutputGenerator.Builder().out(out).template(templateInputStream).uriBase(base).build();
                app.createDocumentation(dp, outputGenerator, profile);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printError(String message) {
		//logger.error(message);
		System.err.println("\n>>> ERROR: " + message + "\n");
	}

	private static InputStream openTemplateInputStream(String template) throws FileNotFoundException {
        if (template.startsWith("classpath:")) {
            String templateSuffix = template.substring("classpath:".length());
            logger.info("Reading template " + templateSuffix + " from classpath");
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(templateSuffix);
            if (is == null) {
                throw new FileNotFoundException(template);
            }
            return is;
        } else {
            logger.info("Reading template " + template + " from filesystem");
            return new FileInputStream(template);
        }
    }

    private static CommandLine parseCommandLine(String[] args, Options options)
            throws ParseException {
        CommandLineParser parser = new PosixParser();
        CommandLine cmd = parser.parse(options, args);
        return cmd;
    }

    @SuppressWarnings("static-access")
	private static Options createOptions() {
        Options options = new Options();
        options.addOption("o", "output", true, "output file");
        options.addOption("h", "help", false, "print help");
        options.addOption("p", "profile", true, "profile ( valid vules: 'technical' [default] or 'business')");        
        options.addOption("l", "lang", true, "language using language subtag registry from IANA (default: " + DEFAULT_LOCALE.getLanguage() + ")");
        options.addOption("s", "css", true, "customize Cascading Style Sheet URL");
        options.addOption("b", "base", true, "URI base to resolve the external resources");
        Option inputFile   = OptionBuilder.withArgName("file")
        .hasArg(true)
        .withDescription("input document")
        .create( 'i' );
        
        inputFile.setLongOpt("input");

        options.addOption(inputFile);
        
        options.addOption("t", "template", true, "template (default: " + DEFAULT_TEMPLATE + ")");
        return options;
    }

}
