package es.ctic.parrot.parrot_eclipse.handlers;


import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import es.ctic.parrot.parrot_eclipse.handlers.ProcHandler;

public class ProcOWLHandler extends ProcHandler {

	public ProcOWLHandler() {		
	}

	public Object execute(ExecutionEvent event) throws ExecutionException {
		return process(event, "application/rdf+xml");
	}
}
