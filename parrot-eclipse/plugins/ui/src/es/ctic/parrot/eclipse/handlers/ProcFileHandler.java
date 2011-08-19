package es.ctic.parrot.eclipse.handlers;


import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import es.ctic.parrot.eclipse.Activator;
import es.ctic.parrot.eclipse.views.DocBrowserView;

public class ProcFileHandler extends AbstractHandler {

    private static final Map<String,String> MAP_EXTENSION_MIMETYPE = createMapExtensionMimeytpe();
    private static Map<String, String> createMapExtensionMimeytpe() {
        Map<String, String> result = new HashMap<String, String>();
        result.put("es.ctic.parrot.rdf.xml.content-type","application/rdf+xml");
        result.put("es.ctic.parrot.rif.xml.content-type", "application/rif+xml");
        result.put("es.ctic.parrot.rifps.text.content-type", "text/x-rif-ps");
//        result.put("xhtml", "application/xhtml+xml");
//        result.put("n3", "text/n3");
//        result.put("html", "text/html");
        return Collections.unmodifiableMap(result);
    }
	
	public ProcFileHandler() {		
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
			IFile ifile = (IFile) element;	
			String contentType;
			try {
				contentType = MAP_EXTENSION_MIMETYPE.get(ifile.getContentDescription().getContentType().getId());
			} catch (CoreException e) {
				throw new RuntimeException(e);
			}
			File file = ifile.getLocation().toFile();
			files.put(file, contentType);
		}
		
		String documentationPage = Activator.getDefault().parrotCore.exec(files);
		
		// Render HTML output
		getParrotBrowserView(page).setBrowserHTML(documentationPage);		
		
		return null;
	}
	
}
