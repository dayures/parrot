<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${param.mimetypeSimple eq 'true'}">
	<option value="application/rdf+xml">It is a OWL ontology</option>
	<option value="text/n3">It is a N3 ontology</option>
	<option value="text/turtle">It is a Turtle ontology</option>
	<option value="application/n-triples">It is a N-triples ontology</option>								
	<option value="application/xhtml+xml">It is a XHTML+RDFa document</option>
	<option value="text/html">It is a HTML+RDFa document</option>
	<option value="application/rif+xml">It is a RIF XML document</option>
	<option value="text/x-rif-ps">It is a RIF PS document</option>
</c:if>

<c:if test="${param.mimetypeSimple eq 'false'}">
	<option value="application/rdf+xml"   <c:if test="${param.mimetypeObtained eq 'application/rdf+xml'}">selected="selected"</c:if>>It is a OWL ontology</option>
	<option value="text/n3"				  <c:if test="${param.mimetypeObtained eq 'text/n3'}">selected="selected"</c:if>>It is a N3 ontology</option>
	<option value="text/turtle" 		  <c:if test="${param.mimetypeObtained eq 'text/turtle'}">selected="selected"</c:if>>It is a Turtle ontology</option>
	<option value="application/n-triples" <c:if test="${param.mimetypeObtained eq 'application/n-triples'}">selected="selected"</c:if>>It is a N-triples ontology</option>
	<option value="application/xhtml+xml" <c:if test="${param.mimetypeObtained eq 'application/xhtml+xml'}">selected="selected"</c:if>>It is a XHTML+RDFa document</option>
	<option value="text/html" 			  <c:if test="${param.mimetypeObtained eq 'text/html'}">selected="selected"</c:if>>It is a HTML+RDFa document</option>
	<option value="application/rif+xml"   <c:if test="${param.mimetypeObtained eq 'application/rif+xml'}">selected="selected"</c:if>>It is a RIF XML document</option>
	<option value="text/x-rif-ps" 		  <c:if test="${param.mimetypeObtained eq 'text/x-rif-ps'}">selected="selected"</c:if>>It is a RIF PS document</option>
</c:if>
