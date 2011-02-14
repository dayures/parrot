package es.ctic.parrot.parrot_eclipse.delegates;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

import es.ctic.parrot.parrot_eclipse.views.DocBrowserView;

public class BackActionDelegate implements IViewActionDelegate {

	DocBrowserView view;
	
	public void init(IViewPart view) {
		// TODO Auto-generated method stub
		this.view = (DocBrowserView)view;
	}

	public void run(IAction action) {
		view.browserBack();
		// TODO Auto-generated method stub

	}

	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

}
