package es.ctic.parrot.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * Utils for HTTP requests. 
 * 
 * @author Carlos Tejo Alonso (<a href="http://www.fundacionctic.org">Fundación CTIC</a>)
 * @version 1.0
 * @since 1.0
 *
 */
public class HttpUtils {
	
	private static final org.apache.log4j.Logger logger = Logger.getLogger(HttpUtils.class);

	public static void applyIENastyPatch(HttpServletRequest req,
			HttpServletResponse res) {
		// java implementation of http://www.wikier.org/blog/serving-xhtml-to-ie
		String accept = req.getHeader("Accept");
		if (!StringUtils.contains(accept, "application/xhtml+xml")
				&& (StringUtils.contains(accept, "text/html") || StringUtils
						.contains(accept, "*/*"))) {
			logger.debug("Applying nasty patch for IE");
			res.setContentType("text/html");
		}
	}

}
