package es.ctic.parrot.docmodel;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Collections;

import es.ctic.parrot.de.Agent;
import es.ctic.parrot.de.Identifier;
import es.ctic.parrot.de.RelatedDocument;

/**
 * An implementation of the VersionableDetailView and DetailView, to serve as a basis for implementing various kinds of detailed views.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public abstract class AbstractVersionableDetailView implements DetailView, VersionableView {
	
	private String anchor;
	private Identifier identifier;
	private String label;

	private String version;
	private String date;
    private String modifiedDate;
    private String issuedDate;
	private Collection<String> creators;
	private Collection<String> contributors;
	private Collection<String> publishers;
	private Collection<Agent> creatorAgents;
	private Collection<Agent> contributorAgents;
	private Collection<Agent> publisherAgents;
	private String rights;
	private String licenseLabel;
	private DocumentableObjectReference isDefinedBy;
	
	private String uri;
	private String uriFragment;
	private String description;
	
	private Collection<RelatedDocument> relatedDocuments;
	private LexicalInformation lexicalInformation;


	public String getLabel() {
		return this.label;
	}

	protected void setLabel(String label) {
		this.label = label;
	}
	
	protected void setAnchor(String anchor) {
		this.anchor = anchor;
	}

	public String getAnchor() {
		return anchor;
	}
	
	protected void setIdentifier(Identifier identifier) {
		this.identifier = identifier;
	}

	public Identifier getIdentifier() {
		return identifier;
	}

	protected void setVersion(String version) {
		this.version = version;
	}

	public String getVersion() {
		return version;
	}
	
	protected void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return date;
	}
	
	protected void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}	
	
	protected void setIssuedDate(String issuedDate) {
		this.issuedDate = issuedDate;
	}

	public String getIssuedDate() {
		return issuedDate;
	}
	
	protected void setCreators(Collection<String> creators) {
		this.creators = creators;
	}

	public Collection<String> getCreators() {
		return Collections.unmodifiableCollection(creators);
	}
	
	protected void setContributors(Collection<String> contributors) {
		this.contributors = contributors;
	}

	public Collection<String> getContributors() {
		return Collections.unmodifiableCollection(contributors);
	}

	protected void setPublishers(Collection<String> publishers) {
		this.publishers = publishers;
	}

	public Collection<String> getPublishers() {
		return Collections.unmodifiableCollection(publishers);
	}
	
	protected void setCreatorAgents(Collection<Agent> creatorAgents) {
		this.creatorAgents = creatorAgents;
	}

	public Collection<Agent> getCreatorAgents() {
		return Collections.unmodifiableCollection(creatorAgents);
	}

	protected void setContributorAgents(Collection<Agent> contributorAgents) {
		this.contributorAgents = contributorAgents;
	}

	public Collection<Agent> getContributorAgents() {
		return Collections.unmodifiableCollection(contributorAgents);
	}
	
	protected void setPublisherAgents(Collection<Agent> publisherAgents) {
		this.publisherAgents = publisherAgents;
	}

	public Collection<Agent> getPublisherAgents() {
		return Collections.unmodifiableCollection(publisherAgents);
	}
	
	protected void setRights(String rights) {
		this.rights = rights;
	}

	public String getRights() {
		return rights;
	}

	protected void setLicenseLabel(String licenseLabel) {
		this.licenseLabel = licenseLabel;
	}

	public String getLicenseLabel() {
		return licenseLabel;
	}

	public String getUri() {
		return uri;
	}

	protected void setUri(String uri) {
		this.uri=uri;
	}

	public String getDescription() {
		return this.description;
	}

	protected void setDescription(String description) {
		this.description = description;
	}

	protected void setRelatedDocuments(Collection<RelatedDocument> relatedDocuments) {
		this.relatedDocuments = relatedDocuments;
	}

	public Collection<RelatedDocument> getRelatedDocuments() {
		return relatedDocuments;
	}
	
	protected void setUriFragment(String uriFragment) {
		this.uriFragment = uriFragment;
	}

	public String getUriFragment() {
		return uriFragment;
	}

	protected void setLexicalInformation(LexicalInformation lexicalInformation) {
		this.lexicalInformation = lexicalInformation;
	}

	public LexicalInformation getLexicalInformation() {
		return lexicalInformation;
	}
	
	protected void setIsDefinedBy(DocumentableObjectReference isDefinedBy) {
		this.isDefinedBy = isDefinedBy;
	}

	public DocumentableObjectReference getIsDefinedBy() {
		return isDefinedBy;
	}
	
	public String getAnchorFromUriMD5(){
		String uri = this.getUri();
		if (uri == null){
			return null;
		} else {
			return "md5-"+getMD5(uri);
		}
	}
	
	/**
	 * Returns the MD5 hash of a string, or <code>null</code> if there is a problem.
	 * @param text the text to obtain the MD5 hash from. 
	 * @return the MD5 hash of a string, or <code>null</code> if there is a problem.
	 */
	static private String getMD5(String text){
		MessageDigest m;
		try {
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}

		m.update(text.getBytes(), 0, text.length());

		return new BigInteger(1, m.digest()).toString(16);
	}

}
