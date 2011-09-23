package es.ctic.parrot.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * The class <code>MapUtils</code> includes methods to load Map from files.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public final class MapUtils {

	/**
	 * Suppress default constructor for noninstantiability.
	 */
	private MapUtils() {
		throw new AssertionError();
	}
	
	/**
	 * Reads a <code>properties</code> file, and returns it as a Map 
	 * 
	 * @param filename The filename to read.
	 * @param delimiter The string (one or more characters) that separates the key from the value in the properties file.
	 * @return the Map generated.
	 * @throws Exception if there is any problem during the load process.
	 */
	public static Map<String, String> readPropertiesFileAsMap(String filename, String delimiter) throws Exception {
	  Map<String, String> map = new HashMap<String, String>();
	  InputStream is = MapUtils.class.getClassLoader().getResourceAsStream(filename);
	  InputStreamReader isr = new InputStreamReader(is);
	  BufferedReader reader = new BufferedReader(isr);	  
	  String line;
	  while ((line = reader.readLine()) != null)  {
	    if (line.trim().length()==0) continue;
	    int delimPosition = line.indexOf(delimiter);
	    String value = line.substring(0, delimPosition).trim();
	    String key = line.substring(delimPosition+1).trim();
	    map.put(key, value); // Change the order to feed tha map!
	  }
	  reader.close();
	  return map;
	}

}
