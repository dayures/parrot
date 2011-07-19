package es.ctic.parrot.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public List<String> getErrorsNotAssociated(){
		return errorsGeneral;
	}
	
	public Map<String, String> getErrorsUri(){
		return errorsUri;
	}
	
	public boolean isEmpty(){
		return (errorsUri.isEmpty() && errorsGeneral.isEmpty());
	}

}
