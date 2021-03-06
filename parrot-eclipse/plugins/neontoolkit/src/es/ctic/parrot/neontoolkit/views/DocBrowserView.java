package es.ctic.parrot.neontoolkit.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.browser.*;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.core.runtime.Status;

import es.ctic.parrot.neontoolkit.ui.Activator;


public class DocBrowserView extends ViewPart {
	
	private Browser browser;
	
	public static final String ViewID = "es.ctic.parrot.neontoolkit.browser";

	public DocBrowserView() {	
	}
	
	public void setBrowserHTML(String html){
		if (browser != null) {
			browser.setText(html);
		}
	}
	
	@Override
	public void createPartControl(Composite parent) {
		this.createBrowser(parent);
		this.setLocationListener();
	}

	private void createBrowser(Composite parent){
		try {
//			browser = new Browser(parent, SWT.MOZILLA);
			browser = new Browser(parent, SWT.None);
			String msg = "Create browser instance="+browser.getStyle() + "--"+browser.getBrowserType();
			Status status = new Status(Status.INFO, Activator.PLUGIN_ID, msg);
			Activator.getDefault().getLog().log(status);
		}
		catch(SWTError e){
			e.printStackTrace();
			String msg = "Unable to create browser instance."+e.getMessage();
			Status status = new Status(Status.ERROR, Activator.PLUGIN_ID, msg);
			Activator.getDefault().getLog().log(status);
			return; 
		}
	}
	
	private void setLocationListener() {
	}
	
	@Override
	public void setFocus() {
		if (browser != null) {
			browser.setFocus();
		}
	}

}
