package es.ctic.parrot.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Buffer for errors.
 * 
 * @author Carlos Tejo Alonso (<a href="http://www.fundacionctic.org">Fundación CTIC</a>)
 * @version 1.0
 * @since 1.0
 *
 */
public class ErrorBuffer {
	private List<String> errorsGeneral;
	private Map<String, String> errorsUri;
	
	public ErrorBuffer() {
		this.errorsGeneral = new ArrayList<String>();
		this.errorsUri = new HashMap<String, String>();
	}
	
	public boolean addError(String msg){
		return errorsGeneral.add(msg);
	}
	
	public boolean addError(String uri, String msg){
		if (errorsUri.put(uri, msg) == null){
			return true;
		} else {
			return false;			
		}
	}
	
	public String getErrorsForUri(String uri){
		return errorsUri.get(uri);
	}

	public Collection<String> getErrorsNotAssociated(){
		return errorsGeneral;
	}
	
	public Map<String, String> getErrorsUri(){
		return errorsUri;
	}
	
	public boolean isEmpty(){
		return (errorsUri.isEmpty() && errorsGeneral.isEmpty());
	}

}
