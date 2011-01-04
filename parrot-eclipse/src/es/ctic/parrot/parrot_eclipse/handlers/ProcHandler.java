package es.ctic.parrot.parrot_eclipse.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;

import es.ctic.parrot.parrot_eclipse.ParrotEclipsePlugin;
import es.ctic.parrot.parrot_eclipse.views.DocBrowserView;

public abstract class ProcHandler extends AbstractHandler {

	private IWorkbenchPage getActiveWorkbenchPage(ExecutionEvent event) {
		try {
			return HandlerUtil.getActiveWorkbenchWindowChecked(event).getActivePage();
		} catch (ExecutionException e) {
			throw new RuntimeException(e);
		}
	}
	
	private DocBrowserView getParrotBrowserView(IWorkbenchPage page) {
		try {
			return (DocBrowserView)page.showView(DocBrowserView.ViewID);
		}
		catch (PartInitException e) {
			throw new RuntimeException(e);
		}
	}
	
	private IEditorPart getActiveEditor(IWorkbenchPage page) {
		return page.getActiveEditor();
	}
	
	protected Object process(ExecutionEvent event, String contenttype) {
		IWorkbenchPage page = getActiveWorkbenchPage(event);
		IEditorPart part = getActiveEditor(page);
		
		if(part != null) {
			// TODO: Verify casting
			ITextEditor editor = (ITextEditor)part;
			IDocumentProvider dp = editor.getDocumentProvider();
			IDocument doc = dp.getDocument(editor.getEditorInput());
			
			// Ask Parrot Core
	//		System.out.println("Antes de llamar al Parrot Core");
			String documentationPage = ParrotEclipsePlugin.getDefault().parrotCore.exec(doc.get(), 
					contenttype);
		//	System.out.println(documentationPage);
			
			// Render HTML output
			getParrotBrowserView(page).setBrowserHTML(documentationPage);
		}
		else {
			String msg = "No active editor, please select one";
			Status status = new Status(Status.ERROR, ParrotEclipsePlugin.PLUGIN_ID, msg);
			ParrotEclipsePlugin.getDefault().getLog().log(status);
		}
		
		return null;
	}
}
