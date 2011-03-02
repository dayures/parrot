package es.ctic.parrot.utils;

import java.util.Map;

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
