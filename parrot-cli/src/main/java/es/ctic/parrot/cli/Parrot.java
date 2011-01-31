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
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.log4j.Logger;

import es.ctic.parrot.DocumentaryProject;
import es.ctic.parrot.appserv.ParrotAppServ;
import es.ctic.parrot.generators.HtmlOutputGenerator;
import es.ctic.parrot.generators.OutputGenerator;
import es.ctic.parrot.reader.FileInput;
import es.ctic.parrot.reader.URLInput;

public class Parrot {

    private static final Logger logger = Logger.getLogger(Parrot.class);
    
    private static final String DEFAULT_LANG = "EN";
    private static final String DEFAULT_TEMPLATE = "classpath:html/template.vm";
    
    public static void main( String[] args ) throws Exception {
        Options options = createOptions();
        CommandLine cmd = parseCommandLine(args, options);
        // default values
        OutputStream out = System.out;
        String lang = DEFAULT_LANG;
        String template = DEFAULT_TEMPLATE;
        // process options
        if (cmd.hasOption("h")) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("parrot", options);
            return;
        }
        if (cmd.hasOption("o")) {
            out = new FileOutputStream(cmd.getOptionValue("o"));
        }
        if (cmd.hasOption("l")) {
            lang = cmd.getOptionValue("l");
        }
        if (cmd.hasOption("t")) {
            template = cmd.getOptionValue("t");
        }

        ParrotAppServ app = new ParrotAppServ();

        try {
            InputStream templateInputStream = openTemplateInputStream(template);
            DocumentaryProject dp = new DocumentaryProject(new Locale(lang));
            for ( String inputFilename : cmd.getArgs() ) {
                if (inputFilename.startsWith("http:")) {
                    dp.addInput(new URLInput(new URL(inputFilename)));
                } else {
                    dp.addInput(new FileInput(new File(inputFilename)));
                }
            }
            if (dp.getInputs().isEmpty()) {
                System.err.println("Please specify at least one input");
            } else {
                OutputGenerator outputGenerator = new HtmlOutputGenerator(out, templateInputStream);
                app.createDocumentation(dp, outputGenerator);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
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

    private static Options createOptions() {
        Options options = new Options();
        options.addOption("o", true, "output file");
        options.addOption("h", false, "print help");
        options.addOption("l", true, "language (default: " + DEFAULT_LANG + ")");
        options.addOption("t", true, "template (default: " + DEFAULT_TEMPLATE + ")");
        return options;
    }

}
