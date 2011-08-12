package es.ctic.parrot.parrot_eclipse.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.browser.*;
import org.eclipse.swt.widgets.Composite;
//import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.core.runtime.Status;

import es.ctic.parrot.parrot_eclipse.ParrotEclipsePlugin;


public class DocBrowserView extends ViewPart {
	
	private Browser browser;
	//private String initialHTML = "<html><head></head><body>Click Parrot -> Generate</body></html>";
	
	public static final String ViewID = "es.ctic.parrot.parrot-eclipse.browser";

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
			browser = new Browser(parent, SWT.None);
		}
		catch(SWTError e){
			String msg = "Unable to create browser instance";
			Status status = new Status(Status.ERROR, ParrotEclipsePlugin.PLUGIN_ID, msg);
			ParrotEclipsePlugin.getDefault().getLog().log(status);
			return; 
		}
//		this.setBrowserHTML(initialHTML);
	}
	
	private void setLocationListener() {
	}
	
	@Override
	public void setFocus() {
		browser.setFocus();
	}

}
