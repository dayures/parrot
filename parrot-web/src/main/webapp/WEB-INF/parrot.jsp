<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="custom-functions.tld" prefix="fn" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"> 
<head> 
<title>PARROT, RIF and OWL documentation service</title> 
<meta http-equiv="content-type" content="text/xhtml+xml; charset=utf-8" /> 
<link rel="shortcut icon" href="images/favicon.png" type="image/png" /> 
<meta name="description" content="parrot" /> 
<meta name="keywords" content="parrot, documentation, tool, rif, rdf" /> 
<link type="text/css" rel="stylesheet" href="css/style.css"	media="screen,projection,print" /> 
<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.8.0r4/build/fonts/fonts-min.css" /> 
<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.8.0r4/build/tabview/assets/skins/sam/tabview.css" /> 
 
</head> 
<body class="yui-skin-sam">
	<div class="all"> 

	<div id="header">
	<h1><a href="http://ontorule-project.eu/parrot"
		title="go to parrot project home page">PARROT </a></h1>
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
		<div id="demo" class="yui-navset"> 
		    <ul class="yui-nav"> 
		        <li><a href="#tab1"><em>by URI</em></a></li> 
		        <li><a href="#tab2"><em>by Direct Input</em></a></li> 
		    </ul>            
		    <div class="yui-content"> 
				<form method="get" action="">
		        <div id="tab1"> 
		        	<h2>By URI</h2> 
		        	<c:choose>
    					<c:when test='${not empty paramValues.documentUri}'>
				        	<%-- For every String[] item of paramValues... --%>
		      				<c:forEach var='uri' items='${paramValues.documentUri}' varStatus='uriStatus'> 
				        	<p <c:if test="${fn:contains(errorsUri, uri)}">class="error"</c:if>>
								<label <c:if test='${uriStatus.first}'>for="documentUri"</c:if> title="URL of the page containing the OWL/RIF document" class="uri">URI: </label>
				        		<input <c:if test='${uriStatus.first}'>id="documentUri"</c:if> name="documentUri" value="<c:out value="${uri}" escapeXml="true"/>" type="text" size="100" style="width:80%" />
							<select name="mimetype">
								<option value="default" <c:if test="${paramValues.mimetype[uriStatus.index] eq 'default'}">selected="selected"</c:if>>Allow content negotiation</option>
								<option value="application/owl+xml" <c:if test="${paramValues.mimetype[uriStatus.index] eq 'application/owl+xml'}">selected="selected"</c:if>>It is a OWL ontology</option>
								<option value="application/xhtml+xml" <c:if test="${paramValues.mimetype[uriStatus.index] eq 'application/xhtml+xml'}">selected="selected"</c:if>>It is a XHTML+RDFa document</option>
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
							for="documentUri" class="uri">URI: </label><input id="documentUri" name="documentUri" value="http://" type="text" size="100" style="width:80%" />
							<select name="mimetype">
								<option value="default" selected="selected">Allow content negotiation</option>
								<option value="application/owl+xml">It is a OWL ontology</option>
								<option value="application/xhtml+xml">It is a XHTML+RDFa document</option>
								<option value="application/rif+xml">It is a RIF XML document</option>
								<option value="text/x-rif-ps">It is a RIF PS document</option>
							</select>	 
							<span class="uriHint">(for example: <tt id="example">http://ontorule-project.eu/resources/steel-30.owl</tt>)</span>
							</p>
						</c:otherwise>
					</c:choose>
					<p id="addURI"><img src="images/add.png" width="16" height="16" alt=""/><span id="addURILink">add another URI</span></p>
		        </div> 
				<div class="buttons">
					<button type="submit" class="positive"><img src="images/tick.png" alt=""/>Generate documentation</button>
				</div>
				</form>
				
				<form method="post" action="">
			        <div id="tab2"> 
			        	<h2>By direct input</h2> 
			        	<p class="direct-input"><label title="Text of the document" for="documentText" class="text">Enter your document:</label><br />
			        	<textarea id="documentText" name="documentText" cols="150" rows="15" style="width:80%"><c:if test='${not empty param.documentText}'><c:out value="${param.documentText}" escapeXml="true"/></c:if></textarea></p>
						<p class="direct-input">This is an : 
						  <select name="mimetypeText">
					        <option value="application/owl+xml" <c:if test="${param.mimetypeText eq 'application/owl+xml'}">selected="selected"</c:if>>OWL ontology</option>
					        <option value="application/xhtml+xml" <c:if test="${param.mimetypeText eq 'application/xhtml+xml'}">selected="selected"</c:if>>It is a XHTML+RDFa document</option>
					        <option value="application/rif+xml" <c:if test="${param.mimetypeText eq 'application/rif+xml'}">selected="selected"</c:if>>RIF XML document</option>
					        <option value="text/x-rif-ps" <c:if test="${param.mimetypeText eq 'text/x-rif-ps'}">selected="selected"</c:if>>RIF PS document</option>
					      </select>
					    </p> 
			        </div> 
					<div class="buttons">
						<button type="submit" class="positive"><img src="images/tick.png" alt=""/>Generate documentation</button>
					</div>
				</form>
		    </div> 
		    
		    <p>Do you need <a href="help.jsp">help</a>?</p>
		</div> 

	</div>    

    <div id="footer"> 
      <p id="logo"> 
        <a href="http://www.fundacionctic.org/"><img src="images/ctic.png" alt="Fundacion CTIC" /></a> 
         <a href="http://ontorule-project.eu"><img src="images/ontorule.png" alt="ONTORULE Project" /></a> 
      </p> 
      <p> 
        <a href="http://ontorule-project.eu/parrot">PARROT</a> is a RIF and OWL documentation service developed <a href="http://ct.ctic.es">Fundaci&oacute;n CTIC</a>.
      </p> 
      <p> This work has been partially funded by <a href="http://ontorule-project.eu" title="ONTORULE Web site">ONTORULE Project (FP7-ICT-2008-3, project reference 231875)</a>.</p>
	  <p> Some icons has been created by <a href="http://www.famfamfam.com/about/">Mark James</a> and there are distributed under <a href="http://creativecommons.org/licenses/by/2.5/">CreativeCommons-by 2.5</a> license.</p> 
    </div> 
    </div> 

<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/element/element-min.js"></script> 
<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/tabview/tabview-min.js"></script> 
<script type="text/javascript" src="javascript/scripts.js"></script>

<c:if test='${not empty paramValues.uris}'>
	<script type="text/javascript">
	(function() {
			var tabView = new YAHOO.widget.TabView('demo');
			tabView.selectTab(0);
	})();
	</script>
</c:if>

<c:if test='${not empty param.documentText}'>
		<script type="text/javascript">
		(function() {
			var tabView = new YAHOO.widget.TabView('demo');
			tabView.selectTab(1);
	})();
	</script>
</c:if>

</body> 

</html> 