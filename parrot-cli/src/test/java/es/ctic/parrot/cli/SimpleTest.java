package es.ctic.parrot.cli;

import org.junit.Test;


public class SimpleTest {

	@Test
	public void input() throws Exception {

		String[] args = {"--input", "http://ontorule-project.eu/resources/steel-30.owl" +
						 " -i", 	"http://ontorule-project.eu/resources/m24dem/CAx_M24_rules_parrot.rifps" };
		
		Parrot.main(args);
		
	}
}
