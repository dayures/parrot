package es.ctic.parrot.project;

import java.util.Collection;
import java.util.Locale;

import es.ctic.parrot.reader.Input;

public interface DocumentaryProject { 
	/**
	 * Adds the specified input to this set if it is not already present (optional operation).
	 * @param input input to be added.
	 * @return <code>true</code> if the input has been added. 
	 */
	public abstract boolean addInput(Input input);

	/**
	 * Adds the specified collection of inputs to this set if it is not already present (optional operation).
	 * @param inputs collection of inputs to be added.
	 * @return <code>true</code> if the collection of inputs has been added. 
	 */
	public abstract boolean addAllInput(Collection<Input> inputs);

	/**
	 * Returns the inputs .
	 * @return the collection of inputs.
	 */
	public abstract Collection<Input> getInputs();

	/**
	 * Returns the locale.
	 * @return the locale.
	 */
	public abstract Locale getLocale();

	/**
	 * Returns the URL where is the prologue.
	 * @return the URL where is the prologue.
	 */
	public abstract String getPrologueURL();

	/**
	 * Returns the URL where is the appendix.
	 * @return the URL where is the appendix.
	 */
	public abstract String getAppendixURL();

	/**
	 * Returns the generated report URL.
	 * @return the generated report URL.
	 */
	public abstract String getGeneratedReportURL();

	/**
	 * Returns the customize CSS URL.
	 * @return the customize CSS URL.
	 */
	public abstract String getCustomizeCssURL();
	
	/**
	 * Returns the profile.
	 * @return the profile.
	 */
	public abstract Profile getProfile();


}