package es.ctic.parrot.parrot_eclipse;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import es.ctic.parrot.parrot_eclipse.utils.*;

/**
 * The activator class controls the plug-in life cycle
 */
public class ParrotEclipsePlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "es.ctic.parrot.parrot_eclipse";

	// The shared instance
	private static ParrotEclipsePlugin plugin;
	
	// Parrot core binding
	public ParrotCoreWrapper parrotCore;
	
	/**
	 * The constructor
	 */
	public ParrotEclipsePlugin() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		parrotCore = new ParrotCoreWrapper();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static ParrotEclipsePlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
}
