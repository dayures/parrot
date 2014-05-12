<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"> 

<jsp:include page="head.jsp">
	<jsp:param name="title" value="About" />
</jsp:include>


<body>

<div class="all">

<jsp:include page="header.jsp" />


<h2 id="what-is">What is PARROT</h2>
<p>PARROT is a <a href="http://www.w3.org/TR/rif-overview/">RIF</a> and <a href="http://www.w3.org/TR/owl-ref/">OWL</a> documentation service developed <a href="http://www.fundacionctic.org/">Fundación CTIC</a>.
<p>PARROT software modules are licensed under <a href="http://www.eclipse.org/legal/epl-v10.html">Eclipse Public License - v 1.0</a>, a business-friendly OSS license.</p>

<h2 id="publications">Publications</h2>
<p>PARROT has been the subject of the following academic publication:</p>

<p class="publication-title">Current practices and perspectives for metadata on web ontologies and rules.</p>
<p class="publication-authors">Carlos Tejo-Alonso, Diego Berrueta, Luis Polo and Sergio Fernández</p>
<blockquote class="publication-abstract">The Semantic Web contains a number of knowledge artifacts, including
OWL ontologies, RIF rule sets and RDF datasets. Effective exchange and management
of these artifacts demands the use of metadata and prompt availability of
accurate reference documentation. In this article, we analyze the current practices
in metadata usage for OWL ontologies, and we propose a vocabulary for annotating
RIF rules. We also introduce a software tool --Parrot-- that exploits these 
annotations and produces reference documentation for combinations of ontologies 
and rules.</blockquote>

<p>Carlos Tejo-Alonso, Diego Berrueta, Luis Polo and Sergio Fernández: Current practices and perspectives for metadata on web ontologies and rules. In: <a href="http://www.inderscience.com/info/inarticle.php?artid=50016">International Journal of Metadata, Semantics and Ontologies (IJMSO)</a>.
<a href="https://bitbucket.org/fundacionctic/parrot/downloads/2012-Article%20PARROT-IJMSO.pdf">Download the article in PDF</a>.</p>


<h2>Team</h2>
PARROT has been developed thanks to the work of of the contributing developers:
<ul>
<li>Carlos Tejo Alonso</li>
<li>Diego Berrueta</li>
<li>Sergio Fernández</li>
<li>Luis Polo</li>
</ul>

<jsp:include page="footer.jsp" />

</div>

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.5.1/jquery.min.js"></script>
<script type="text/javascript" src="report/js/jquery.corner.js"></script>
<script type="text/javascript" src="javascript/help-scripts.js"></script>

<jsp:include page="google-analytics.jsp" />

</body>
</html>
