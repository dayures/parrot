package es.ctic.parrot.utils;

import java.util.Map;

/**
 * Taglibs.
 * 
 * @author Carlos Tejo Alonso (<a href="http://www.fundacionctic.org">Fundación CTIC</a>)
 * @version 1.0
 * @since 1.0
 *
 */
public class Taglib {
   @SuppressWarnings("rawtypes")
public static boolean contains(Map map, Object o) {
	   if (map == null)
		   return false;
	   else
		   return map.containsKey(o);
   }
   
   @SuppressWarnings("rawtypes")
public static String getMessage(Map map, Object o) {
	      return (String) map.get(o);
   }
}
