package es.ctic.parrot.utils;

import java.util.Map;

public class Taglib {
   public static boolean contains(Map map, Object o) {
	   if (map == null)
		   return false;
	   else
		   return map.containsKey(o);
   }
   
   public static String getMessage(Map map, Object o) {
	      return (String) map.get(o);
   }
}
