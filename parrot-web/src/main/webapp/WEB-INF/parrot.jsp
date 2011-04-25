<%@ page language="java" contentType="application/xhtml+xml; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="custom-functions.tld" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"> 
<head> 
<title>Parrot, RIF and OWL documentation service</title> 
<meta http-equiv="Content-Type" content="application/xhtml+xml; charset=UTF-8" /> 
<meta name="description" content="parrot" /> 
<meta name="keywords" content="parrot, documentation, tool, rif, rdf" /> 
<link rel="shortcut icon" type="image/png" href="images/favicon.ico"  />
<link rel="stylesheet" type="text/css" media="screen,projection" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.10/themes/cupertino/jquery-ui.css" /> 
<link rel="stylesheet" type="text/css" href="css/style.css"	media="screen,projection,print" /> 
</head> 

<body>
	<div class="all"> 

	<div id="header">
	<h1><a href="http://ontorule-project.eu/parrot"
		title="go to parrot project home page">Parrot </a></h1>
	<h2>a RIF and OWL documentation service (alpha version)</h2>
	</div>

		<c:if test="${ ! empty errorsGeneral}" >    	
    	<div class="inputData" id="inputDataReport">
			<h2 class="error">Errors</h2>
			<div class="inputDataReport">
		    	<ul class="error">
					<c:forEach var="line" items="${errorsGeneral}">
		         		<li class="error"><span><c:out value="${line}"/></span></li>
		      		</c:forEach>
				</ul> 
				<c:if test="${ ! empty advices}">
		    	<ul class="advice">
					<c:forEach var="line" items="${advices}">
		         		<li class="advice"><span><c:out value="${line}"/></span></li>
		      		</c:forEach>
				</ul>
				</c:if>
			</div>
		</div>
		</c:if>

		<div class="inputData"> 
		<div id="tabs"> 
		    <ul> 
		        <li><a href="#tabs-1">by URI</a></li> 
		        <li><a href="#tabs-2">by direct input</a></li> 
		        <li><a href="#tabs-3">by file upload</a></li>
		        <li><a href="#tabs-4">Existing report</a></li>  
		    </ul>            
		        <div id="tabs-1"> 
		        	<h2>By URI</h2> 
					<form method="get" action="">
		        	<c:choose>
    					<c:when test='${not empty paramValues.documentUri}'>
				        	<%-- For every String[] item of paramValues... --%>
		      				<c:forEach var='uri' items='${paramValues.documentUri}' varStatus='uriStatus'> 
				        	<p <c:if test="${fn:contains(errorsUri, uri)}">class="error"</c:if>>
								<label <c:if test='${uriStatus.first}'>for="documentUri"</c:if> title="URL of the page containing the OWL/RIF document" class="uri">URI: </label>
				        		<input <c:if test='${uriStatus.first}'>id="documentUri"</c:if> name="documentUri" value="<c:out value="${uri}" escapeXml="true"/>" type="text" size="100" />
							<select name="mimetype">
								<option value="default" <c:if test="${paramValues.mimetype[uriStatus.index] eq 'default'}">selected="selected"</c:if>>Allow content negotiation</option>
								<option value="application/owl+xml" <c:if test="${paramValues.mimetype[uriStatus.index] eq 'application/owl+xml'}">selected="selected"</c:if>>It is a OWL ontology</option>
								<option value="text/n3" <c:if test="${paramValues.mimetype[uriStatus.index] eq 'text/n3'}">selected="selected"</c:if>>It is a N3 ontology</option>
								<option value="application/xhtml+xml" <c:if test="${paramValues.mimetype[uriStatus.index] eq 'application/xhtml+xml'}">selected="selected"</c:if>>It is a XHTML+RDFa document</option>
								<option value="text/html" <c:if test="${paramValues.mimetype[uriStatus.index] eq 'text/html'}">selected="selected"</c:if>>It is a HTML+RDFa document</option>
								<option value="application/rif+xml" <c:if test="${paramValues.mimetype[uriStatus.index] eq 'application/rif+xml'}">selected="selected"</c:if>>It is a RIF XML document</option>
								<option value="text/x-rif-ps" <c:if test="${paramValues.mimetype[uriStatus.index] eq 'text/x-rif-ps'}">selected="selected"</c:if>>It is a RIF PS document</option>
							</select>							 
							<br /> 
							<c:if test='${uriStatus.first}'>
								<%-- Only for the first input --%>
								<span class="uriHint">(for example: <tt id="example">http://ontorule-project.eu/resources/steel-30.owl</tt>)</span>
							</c:if>
							<c:if test="${fn:contains(errorsUri,uri)}">
								<span class="error">${fn:getMessage(errorsUri, uri)}</span>
							</c:if>
							</p>
		 			        </c:forEach>
					    </c:when>
					    <c:otherwise>
				        	<p><label title="URL of the page containing the OWL/RIF document"
							for="documentUri" class="uri">URI: </label><input id="documentUri" name="documentUri" value="http://" type="text" size="100" />
							<select name="mimetype">
								<option value="default" selected="selected">Allow content negotiation</option>
								<option value="application/owl+xml">It is a OWL ontology</option>
								<option value="text/n3">It is a N3 ontology</option>
								<option value="application/xhtml+xml">It is a XHTML+RDFa document</option>
								<option value="text/html">It is a HTML+RDFa document</option>
								<option value="application/rif+xml">It is a RIF XML document</option>
								<option value="text/x-rif-ps">It is a RIF PS document</option>
							</select>
							<span class="removeURI">remove</span>
							<span class="uriHint">(for example: <tt id="example">http://ontorule-project.eu/resources/steel-30.owl</tt>)</span>
							</p>
						</c:otherwise>
					</c:choose>
					<p id="addURI"><span id="addURILink">add another URI</span></p>
					<fieldset class="moreoptions">
						<legend id="extra_opt_uri" class="more-options-closed">More Options</legend>
						<div id="options">
							<input type="radio" name="profile" id="business_profile" value="business" checked="checked" /><label for="business_profile">Business report</label> <input type="radio" name="profile" id="technical_profile" value="technical" /><label for="technical_profile">Technical report</label>
						</div>
						<hr />
					</fieldset>
					<div class="buttons">
						<button type="submit" class="positive"><img src="images/tick.png" alt=""/>Generate documentation</button>
					</div>

					
					</form>
		        </div> 
		        <!-- 
		        <div class="language">
		        <label>Select preferred language: </label>
       			<select name="language">
					<option value="bg">български</option>
					<option value="cs">čeština</option>
					<option value="da">dansk</option>
					<option value="de">Deutsch</option>
					<option value="et">eesti</option>
					<option value="el">ελληνικά</option>
					<option value="en" selected="selected">English</option>
					<option value="es">español</option>
					<option value="fr">français</option>
					<option value="ga">Gaeilge</option>
					<option value="it">italiano</option>
					<option value="lv">latviešu</option>
					<option value="lt">lietuvių</option>
					<option value="hu">magyar</option>
					<option value="mt">Malti</option>
					<option value="nl">Nederlands</option>
					<option value="pl">polski</option>
					<option value="pt">português</option>
					<option value="ro">română</option>
					<option value="sk">slovenčina</option>
					<option value="sl">slovenščina</option>
					<option value="fi">suomi</option>
					<option value="sv">svenska</option>
				</select>
		        </div>
		         -->
				
			        <div id="tabs-2"> 
			        	<h2>By direct input</h2> 
						<form method="post" action="">
			        	<p class="direct-input"><label title="Text of the document" for="documentText" class="text">Enter your document:</label><br />
			        	<textarea id="documentText" name="documentText" cols="150" rows="15" style="width:80%"><c:if test='${not empty param.documentText}'><c:out value="${param.documentText}" escapeXml="true"/></c:if></textarea></p>
						<p class="direct-input">This is an : 
						  <select name="mimetypeText">
					        <option value="application/owl+xml" <c:if test="${param.mimetypeText eq 'application/owl+xml'}">selected="selected"</c:if>>OWL ontology</option>
					        <option value="text/n3" <c:if test="${param.mimetypeText eq 'text/n3'}">selected="selected"</c:if>>N3 ontology</option>
					        <option value="application/xhtml+xml" <c:if test="${param.mimetypeText eq 'application/xhtml+xml'}">selected="selected"</c:if>>XHTML+RDFa document</option>
					        <option value="text/html" <c:if test="${param.mimetypeText eq 'text/html'}">selected="selected"</c:if>>HTML+RDFa document</option>
					        <option value="application/rif+xml" <c:if test="${param.mimetypeText eq 'application/rif+xml'}">selected="selected"</c:if>>RIF XML document</option>
					        <option value="text/x-rif-ps" <c:if test="${param.mimetypeText eq 'text/x-rif-ps'}">selected="selected"</c:if>>RIF PS document</option>
					      </select>
					    </p> 
						<div class="buttons">
							<button type="submit" class="positive"><img src="images/tick.png" alt=""/>Generate documentation</button>
						</div>
						</form>
			        </div> 
			         <!-- 
			        <div class="language">
		        		<label>Select preferred language: </label>
       					<select name="language">
							<option value="bg">български</option>
							<option value="cs">čeština</option>
							<option value="da">dansk</option>
							<option value="de">Deutsch</option>
							<option value="et">eesti</option>
							<option value="el">ελληνικά</option>
							<option value="en" selected="selected">English</option>
							<option value="es">español</option>
							<option value="fr">français</option>
							<option value="ga">Gaeilge</option>
							<option value="it">italiano</option>
							<option value="lv">latviešu</option>
							<option value="lt">lietuvių</option>
							<option value="hu">magyar</option>
							<option value="mt">Malti</option>
							<option value="nl">Nederlands</option>
							<option value="pl">polski</option>
							<option value="pt">português</option>
							<option value="ro">română</option>
							<option value="sk">slovenčina</option>
							<option value="sl">slovenščina</option>
							<option value="fi">suomi</option>
							<option value="sv">svenska</option>
						</select>
		        	</div>
		        	 -->

		        <div id="tabs-3"> 
		        	<h2>By file upload</h2> 
					<form method="post" action="" enctype="multipart/form-data">
						<p>
						<label title="File to be documented" for="file">File: </label><input type="file" name="datafile" size="40"/>
						This file is a: 
						<select name="mimetypeFile">
							<option value="default" selected="selected">Autodetect (if possible)</option>
						    <option value="application/owl+xml">OWL ontology</option>
						    <option value="text/n3">N3 ontology</option>
						    <option value="application/xhtml+xml">XHTML+RDFa document</option>
						    <option value="text/html">HTML+RDFa document</option>
						    <option value="application/rif+xml">RIF XML document</option>
						    <option value="text/x-rif-ps">RIF PS document</option>
						</select>
					    </p>
					    <button id="addFile">add another file</button>

						<div class="buttons">
							<button type="submit" class="positive"><img src="images/tick.png" alt=""/>Generate documentation</button>
						</div>
					</form>
				</div> <!--  /tab3 -->
				
				<div id="tabs-4"> 
		        	<h2>by existing report</h2> 
					<form method="get" action="">
					<c:choose>  
						<c:when test='${empty param.reportURL}'>
							<label for="reportURL" title="URL of an existing Parrot report" class="uri">URL: </label><input id="reportURL" name="reportURL" value="" type="text" size="100" />
						</c:when>
						<c:otherwise>
							<label for="reportURL" title="URL of an existing Parrot report" class="uri">URL: </label><input id="reportURL" name="reportURL" value="<c:out value="${param.reportURL}" />" type="text" size="100" />
						</c:otherwise>
					</c:choose>
					<div class="buttons">
						<button type="submit" class="positive"><img src="images/tick.png" alt=""/>Generate documentation</button>
					</div>
					</form>
				</div> <!-- tab4 -->


		</div> 
		    <div id="tipOfTheDay">
		    <span >Did you know ...</span>
		    </div>
		    <p><img src="images/help.png" alt="help" class="help" />Do you need <a href="help.jsp">help</a>?</p>
	</div>    

    <div id="footer"> 
      <p id="logo"> 
        <a href="http://www.fundacionctic.org/"><img src="images/ctic.png" alt="Fundacion CTIC" /></a> 
         <a href="http://ontorule-project.eu"><img src="images/ontorule.png" alt="ONTORULE Project" /></a> 
      </p> 
      <p> 
        <a href="http://ontorule-project.eu/parrot">Parrot</a> is a RIF and OWL documentation service developed <a href="http://ct.ctic.es">Fundaci&oacute;n CTIC</a>.
      </p> 
      <p> This work has been partially funded by <a href="http://ontorule-project.eu" title="ONTORULE Web site">ONTORULE Project (FP7-ICT-2008-3, project reference 231875)</a>.</p>
	  <p> Some icons has been created by <a href="http://www.famfamfam.com/about/">Mark James</a> and there are distributed under <a href="http://creativecommons.org/licenses/by/2.5/">CreativeCommons-by 2.5</a> license.</p> 
    </div>
    </div> 

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.5.1/jquery.min.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.11/jquery-ui.min.js"></script>
<script type="text/javascript" src="http://3nhanced.com/examples/randomContent/js/jquery.randomContent.js"></script>
<script type="text/javascript" src="https://github.com/malsup/corner/raw/master/jquery.corner.js"></script> 

<script type="text/javascript" src="javascript/scripts.js"></script>

<c:if test='${!empty param.reportURL}'>
<script type="text/javascript">
<![CDATA[ 
jQuery(document).ready(function(){
	$("#tabs").tabs("select", 3);
});
]]>         
</script>
</c:if>

</body> 

</html> 