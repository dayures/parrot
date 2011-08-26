package es.ctic.parrot.eclipse.handlers;


import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import com.ontoprise.ontostudio.owl.gui.navigator.ontology.OntologyTreeElement;
import com.ontoprise.ontostudio.owl.model.OWLModel;
import com.ontoprise.ontostudio.owl.model.OWLModelFactory;

import es.ctic.parrot.eclipse.neontoolkit.ui.Activator;
import es.ctic.parrot.eclipse.neontoolkit.views.DocBrowserView;


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
		
		IWorkbenchPage page = getActiveWorkbenchPage(event);
		IStructuredSelection selection = (IStructuredSelection) HandlerUtil.getCurrentSelectionChecked(event);

		Map <File, String> files = new HashMap<File, String>();
		for (Object element: selection.toList()){

			try{
				OntologyTreeElement ontology = (OntologyTreeElement) element;
	            OWLModel model = OWLModelFactory.getOWLModel(ontology.getOntologyUri(), ontology.getProjectName());
				File file = null;
	
	            if (model != null) {
					file = new File(model.getPhysicalURI().replace("file:", "")); // FIXME update this replace
	            }
	            
				String contentType = "application/rdf+xml"; //TODO Hardcored code
				files.put(file, contentType);
			} catch (Exception e){
				MessageDialog.openInformation(
						HandlerUtil.getActiveWorkbenchWindowChecked(event).getShell(),
						"Ui",
						"Exception="+e);

			}
		}
		
		String documentationPage = Activator.getDefault().parrotCore.exec(files);
		
		// Render HTML output
		getParrotBrowserView(page).setBrowserHTML(documentationPage);		
		
		return null;
	}
	
}
