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
	
	<p>FIXME: write documentation here.</p>

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