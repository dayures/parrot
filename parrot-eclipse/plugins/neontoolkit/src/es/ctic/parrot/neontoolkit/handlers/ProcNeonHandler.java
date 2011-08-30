package es.ctic.parrot.neontoolkit.handlers;


import java.io.File;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import com.ontoprise.ontostudio.owl.gui.navigator.ontology.OntologyTreeElement;
import com.ontoprise.ontostudio.owl.model.OWLModel;
import com.ontoprise.ontostudio.owl.model.OWLModelFactory;

import es.ctic.parrot.neontoolkit.ui.Activator;
import es.ctic.parrot.neontoolkit.views.DocBrowserView;


public class ProcNeonHandler extends AbstractHandler {

	public ProcNeonHandler() {		
	}

	private DocBrowserView getParrotBrowserView(IWorkbenchPage page) {
		try {
			return (DocBrowserView)page.showView(DocBrowserView.ViewID);
		}
		catch (PartInitException e) {
			throw new RuntimeException(e);
		}
	}

	private IWorkbenchPage getActiveWorkbenchPage(ExecutionEvent event) {
		try {
			return HandlerUtil.getActiveWorkbenchWindowChecked(event).getActivePage();
		} catch (ExecutionException e) {
			throw new RuntimeException(e);
		}
	}

	public Object execute(ExecutionEvent event) throws ExecutionException {

		Shell shell = HandlerUtil.getActiveWorkbenchWindowChecked(event).getShell();
		IWorkbenchPage page = getActiveWorkbenchPage(event);
		IStructuredSelection selection = (IStructuredSelection) HandlerUtil.getCurrentSelectionChecked(event);

		Map <File, String> files = new HashMap<File, String>();
		for (Object element: selection.toList()){

			try{
				OntologyTreeElement ontology = (OntologyTreeElement) element;
	            OWLModel model = OWLModelFactory.getOWLModel(ontology.getOntologyUri(), ontology.getProjectName());

	            // IMPORTANT: model.getPhysicalURI() returns an URI, but in string format (for example uses %20 instead of space).
	            if (model != null) {
	            	File file = new File(new URI(model.getPhysicalURI()));

	            	if ((file == null) || ((file != null) && (file.exists() == false))){
						MessageDialog.openInformation(
								shell,
								"Warning",
								"The file with path "+model.getPhysicalURI()+" cannot be retrieved");
					}
					
	            	if ((file != null) && (file.exists())){
		            	String contentType = "application/rdf+xml"; //TODO Hardcored code
						files.put(file, contentType);
	            	}
					
	            }
			} catch (Exception e){
				MessageDialog.openInformation(
						shell,
						"Exception",
						"Exception="+e);

			}
		}
		
		String documentationPage = Activator.getDefault().parrotCore.exec(files);
		
		// Render HTML output
		getParrotBrowserView(page).setBrowserHTML(documentationPage);		
		
		return null;
	}
	
}
