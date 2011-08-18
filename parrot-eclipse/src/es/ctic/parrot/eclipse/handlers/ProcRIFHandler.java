package es.ctic.parrot.eclipse.handlers;


import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import es.ctic.parrot.eclipse.handlers.ProcHandler;

public class ProcRIFHandler extends ProcHandler {

	public ProcRIFHandler() {		
	}

	public Object execute(ExecutionEvent event) throws ExecutionException {
		return process(event, "application/rif+xml");
	}
}
