package es.ctic.parrot.eclipse.neontoolkit.ui.popup.actions;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.ontoprise.ontostudio.owl.gui.navigator.ontology.OntologyTreeElement;
import com.ontoprise.ontostudio.owl.model.OWLModel;
import com.ontoprise.ontostudio.owl.model.OWLModelFactory;

import es.ctic.parrot.eclipse.neontoolkit.ui.Activator;
import es.ctic.parrot.eclipse.neontoolkit.views.DocBrowserView;

public class Action implements IObjectActionDelegate {

	private Shell shell;
	private ISelection selection;

    public static final String ID = "es.ctic.parrot.eclipse.neontoolkit.ui.Action";

	/**
	 * Constructor for Action1.
	 */
	public Action() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		
		IWorkbench wb = PlatformUI.getWorkbench();
		IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
	    IWorkbenchPage page = win.getActivePage();

		try{

			Map <File, String> files = new HashMap<File, String>();
			IStructuredSelection s = (IStructuredSelection) this.selection;
			for (Object element: s.toList()){

				
				OntologyTreeElement ontology = (OntologyTreeElement) element;
	            OWLModel model = OWLModelFactory.getOWLModel(ontology.getOntologyUri(), ontology.getProjectName());
				File file = null;

	            if (model != null) {
					file = new File(model.getPhysicalURI().replace("file:", "")); // FIXME update this replace
	            }
	            
				String contentType = "application/rdf+xml";
				files.put(file, contentType);
			}
			
			String documentationPage = Activator.getDefault().parrotCore.exec(files);
			
			// Render HTML output
			getParrotBrowserView(page).setBrowserHTML(documentationPage);
			
		} catch (Exception e){
			MessageDialog.openInformation(
					shell,
					"Ui",
					"Exception="+e);

		}

	}
	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	    this.selection = selection;
	}

	private DocBrowserView getParrotBrowserView(IWorkbenchPage page) {
		try {
			return (DocBrowserView)page.showView(DocBrowserView.ViewID);
		}
		catch (PartInitException e) {
			throw new RuntimeException(e);
		}
	}
}
