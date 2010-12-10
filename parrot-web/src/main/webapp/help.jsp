<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="custom-functions.tld" prefix="fn" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en-US">
<head>
  <title>PARROT, RIF and OWL documentation service</title>
  <meta http-equiv="content-type"
  content="application/xhtml+xml; charset=UTF-8">
  <link rel="shortcut icon" href="images/favicon.png" type="image/png">
  <meta name="description" content="parrot">
  <meta name="keywords" content="parrot, documentation, tool, rif, rdf">
  <link type="text/css" rel="stylesheet" href="css/style.css"
  media="screen,projection,print">
  <link rel="stylesheet" type="text/css"
  href="http://yui.yahooapis.com/2.8.0r4/build/fonts/fonts-min.css">
  <link rel="stylesheet" type="text/css"
  href="http://yui.yahooapis.com/2.8.0r4/build/tabview/assets/skins/sam/tabview.css">
</head>

<body class="yui-skin-sam">

<div class="all">

<div id="header">
<h1><a href="http://ontorule-project.eu/parrot"
title="go to parrot project home page">PARROT </a></h1>

<h2>a RIF and OWL documentation service (alpha version)</h2>
</div>

<p>This table captures the properties relevant for describing ontologies and
resources metadata. Notice that not all the properties are currently supported
by PARROT.</p>

<p></p>

<table border="2" style="width: 100%">
  <col>
  <col>
  <col>
  <col>
  <col>
  <col>
  <tbody>
    <tr>
      <td
      style="text-align:center;margin-left:auto;margin-right:auto;font-size: 12pt"><strong>Property</strong></td>
      <td
        style="text-align:center;margin-left:auto;margin-right:auto;"><strong><span
        style="font-size: 12pt">Vocabulary</span></strong></td>
      <td
      style="text-align:center;margin-left:auto;margin-right:auto;font-size: 12pt"><strong>Scope</strong></td>
      <td
      style="text-align:center;margin-left:auto;margin-right:auto;font-size: 12pt"><strong>Description</strong></td>
      <td
      style="text-align:center;margin-left:auto;margin-right:auto;font-size: 12pt"><strong>Namespace</strong></td>
      <td
      style="text-align:center;margin-left:auto;margin-right:auto;font-size: 12pt"><strong>Supported</strong></td>
    </tr>
    <tr>
      <td><em>creator</em></td>
      <td><a href="http://dublincore.org/documents/dcmi-terms">Dublin
      Core</a></td>
      <td>Ontology</td>
      <td>The creator of the ontology: a person or an organization. Recommended
        best practice is to use a FOAF profile to describe the creator,
        although a literal is also allowed.</td>
      <td>http://purl.org/dc/terms/</td>
      <td style="text-align:left;margin-left:0;margin-right:auto;"><img
        alt="icon for ok" src="../../../../../../1291980081_camera_test.png"
        width="31" height="29"><img alt="icon for ok"
        src="images/1291980081_camera_test.png" width="32" height="32"></td>
    </tr>
    <tr>
      <td><em>contributor</em></td>
      <td><a href="http://dublincore.org/documents/dcmi-terms">Dublin
      Core</a></td>
      <td>Ontology</td>
      <td>A contributor to the ontology: a person or an organization.
        Recommended best practice is to use a FOAF profile to describe a
        contributor, although a literal is also allowed.</td>
      <td>http://purl.org/dc/terms/</td>
      <td style="text-align:left;margin-left:0;margin-right:auto;"><img
        alt="icon for ok" src="../../../../../../1291980081_camera_test.png"
        width="31" height="29"><img alt="icon for ok"
        src="images/1291980081_camera_test.png" width="32" height="32"></td>
    </tr>
    <tr>
      <td><em>hasVersion</em></td>
      <td><a href="http://dublincore.org/documents/dcmi-terms">Dublin
      Core</a></td>
      <td>Ontology</td>
      <td>This property relates the ontology with its prior versions, i.e.
        other ontologies. Notice that this property shouldn't be used to relate
        the ontology with knowledge sources it is based on.</td>
      <td>http://purl.org/dc/terms/</td>
      <td style="text-align:left;margin-left:0;margin-right:auto;"><object
        data="images/1291980279_close.png"
        style="display: block; text-align: center; margin-left: auto; margin-right: auto"
        height="34" width="39">
          con for close</object></td>
    </tr>
    <tr>
      <td><em>date</em></td>
      <td><a href="http://dublincore.org/documents/dcmi-terms">Dublin
      Core</a></td>
      <td>Ontology</td>
      <td>Expresses the date of creation or publication of the ontology.
        Recommended best practice is to use the W3CDTF profile of ISO 8601.</td>
      <td>http://purl.org/dc/terms/</td>
      <td style="text-align:left;margin-left:0;margin-right:auto;"><img
        alt="icon for ok" src="../../../../../../1291980081_camera_test.png"
        width="31" height="29"><img alt="icon for ok"
        src="images/1291980081_camera_test.png" width="32" height="32"></td>
    </tr>
    <tr>
      <td><em></em><em>publisher</em></td>
      <td><a href="http://dublincore.org/documents/dcmi-terms">Dublin
      Core</a></td>
      <td>Ontology</td>
      <td>The entity responsible for making the ontology available. Recommended
        best practice is to use a FOAF profile to describe the publisher,
        although a literal is also allowed.</td>
      <td>http://purl.org/dc/terms/</td>
      <td style="text-align:left;margin-left:0;margin-right:auto;"><img
        alt="icon for ok" src="../../../../../../1291980081_camera_test.png"
        width="31" height="29"><img alt="icon for ok"
        src="images/1291980081_camera_test.png" width="32" height="32"></td>
    </tr>
    <tr>
      <td><em>license</em></td>
      <td><a href="http://dublincore.org/documents/dcmi-terms/">Dublin
      Core</a></td>
      <td>Ontology</td>
      <td>A legal document describing the copyright license of the ontology.
        Recommended best practice is to use Creative Commons licenses and to
        describe them in RDF with the Creative Commons Rights Expression
        Language (CC REL). </td>
      <td>http://purl.org/dc/terms/</td>
      <td style="text-align:left;margin-left:0;margin-right:auto;"><object
        data="images/1291980279_close.png"
        style="display: block; text-align: center; margin-left: auto; margin-right: auto"
        height="34" width="39">
          con for close</object></td>
    </tr>
    <tr>
      <td><em>title</em></td>
      <td><a href="http://dublincore.org/documents/dcmi-terms/">Dublin
      Core</a></td>
      <td>Ontology</td>
      <td>See <em>label</em></td>
      <td>http://purl.org/dc/terms/</td>
      <td style="text-align:left;margin-left:0;margin-right:auto;"><object
        data="images/1291980279_close.png"
        style="display: block; text-align: center; margin-left: auto; margin-right: auto"
        height="34" width="39">
          con for close</object></td>
    </tr>
    <tr>
      <td><em>subject</em></td>
      <td><a href="http://dublincore.org/documents/dcmi-terms/">Dublin
      Core</a></td>
      <td>Ontology, Class, Property, Individual</td>
      <td>The topic of a resource. A recommended best practice is to use a
        controlled vocabulary encoded in SKOS format.</td>
      <td>http://purl.org/dc/terms/</td>
      <td style="text-align:left;margin-left:0;margin-right:auto;"><object
        data="images/1291980279_close.png"
        style="display: block; text-align: center; margin-left: auto; margin-right: auto"
        height="34" width="39">
          con for close</object></td>
    </tr>
    <tr>
      <td><em>description</em></td>
      <td><a href="http://dublincore.org/documents/dcmi-terms/">Dublin
      Core</a></td>
      <td>Ontology</td>
      <td>See <em>comment</em></td>
      <td>http://purl.org/dc/terms/</td>
      <td style="text-align:center;margin-left:auto;margin-right:auto;"><object
        data="images/1291980279_close.png"
        style="display: block; text-align: center; margin-left: auto; margin-right: auto"
        height="34" width="39">
          con for close</object></td>
    </tr>
    <tr>
      <td><em>label</em></td>
      <td>RDF Schema</td>
      <td>Ontology, Class, Property, Individual</td>
      <td>The label of the resource. The range is a literal with a language
      tag.</td>
      <td>http://www.w3.org/2000/01/rdf-schema#</td>
      <td style="text-align:left;margin-left:0;margin-right:auto;"><img
        alt="icon for ok" src="../../../../../../1291980081_camera_test.png"
        width="31" height="29"><img alt="icon for ok"
        src="images/1291980081_camera_test.png" width="32" height="32"></td>
    </tr>
    <tr>
      <td><em>comment</em></td>
      <td>RDF Schema</td>
      <td>Ontology, Class, Property, Individual</td>
      <td>The description of the resource. The range is a literal with a
        language tag.</td>
      <td>http://www.w3.org/2000/01/rdf-schema#</td>
      <td style="text-align:left;margin-left:0;margin-right:auto;"><img
        alt="icon for ok" src="../../../../../../1291980081_camera_test.png"
        width="31" height="29"><img alt="icon for ok"
        src="images/1291980081_camera_test.png" width="32" height="32"></td>
    </tr>
    <tr>
      <td><em>prefLabel</em></td>
      <td>SKOS</td>
      <td>Class, Property, Individual</td>
      <td>The preferred label of a resource. The range is a literal with a
        language tag. In some cases, it is assumed that <em>prefLabel</em>
        assumes the role of the <em>label</em> for presentation purposes.</td>
      <td>http://www.w3.org/2004/02/skos/core#</td>
      <td style="text-align:center;margin-left:auto;margin-right:auto;"><object
        data="images/1291980279_close.png"
        style="display: block; text-align: center; margin-left: auto; margin-right: auto"
        height="34" width="39">
          con for close</object></td>
    </tr>
    <tr>
      <td><em>altLabel</em></td>
      <td>SKOS</td>
      <td>Class, Property, Individual</td>
      <td>An alternative label of a resource. The range is a literal with a
        language tag.</td>
      <td>http://www.w3.org/2004/02/skos/core#</td>
      <td style="text-align:left;margin-left:0;margin-right:auto;"><object
        data="images/1291980279_close.png"
        style="display: block; text-align: center; margin-left: auto; margin-right: auto"
        height="34" width="39">
          con for close</object></td>
    </tr>
    <tr>
      <td><em>prefLabel*</em></td>
      <td>SKOS-XL</td>
      <td>Class, Property, Individual</td>
      <td>The preferred label of a resource. The range of the property is an
        instance of the class LexicalLabel.</td>
      <td>http://www.w3.org/2008/05/skos-xl#</td>
      <td style="text-align:left;margin-left:0;margin-right:auto;"><object
        data="images/1291980279_close.png"
        style="display: block; text-align: center; margin-left: auto; margin-right: auto"
        height="34" width="39">
          con for close</object></td>
    </tr>
    <tr>
      <td><em>altLabel*</em></td>
      <td>SKOS-XL</td>
      <td>Class, Property, Individual</td>
      <td>An alternative label of a resource. The range of the property is an
        instance of the class LexicalLabel.</td>
      <td>http://www.w3.org/2008/05/skos-xl#</td>
      <td style="text-align:left;margin-left:0;margin-right:auto;"><object
        data="images/1291980279_close.png"
        style="display: block; text-align: center; margin-left: auto; margin-right: auto"
        height="34" width="39">
          con for close</object></td>
    </tr>
    <tr>
      <td><em>depiction</em></td>
      <td>FOAF</td>
      <td>Ontology, Class, Property, Individual</td>
      <td>An image associated with the resource. </td>
      <td>http://xmlns.com/foaf/0.1/</td>
      <td style="text-align:center;margin-left:auto;margin-right:auto;"><object
        data="images/1291980279_close.png"
        style="display: block; text-align: center; margin-left: auto; margin-right: auto"
        height="34" width="39">
          con for close</object></td>
    </tr>
    <tr>
      <td><em>preferredNamespacePrefix</em></td>
      <td>VANN</td>
      <td>Ontology</td>
      <td>The preferred namespace prefix when using entities of other
        ontologies.</td>
      <td>http://purl.org/vocab/vann/</td>
      <td style="text-align:left;margin-left:0;margin-right:auto;"><img
        alt="icon for ok" src="../../../../../../1291980081_camera_test.png"
        width="31" height="29"><img alt="icon for ok"
        src="images/1291980081_camera_test.png" width="32" height="32"></td>
    </tr>
    <tr>
      <td><em>primaryTopic</em></td>
      <td>FOAF</td>
      <td>Ontology</td>
      <td>See <em>subject</em></td>
      <td>http://xmlns.com/foaf/0.1/</td>
      <td style="text-align:left;margin-left:0;margin-right:auto;"><object
        data="images/1291980279_close.png"
        style="display: block; text-align: center; margin-left: auto; margin-right: auto"
        height="34" width="39">
          con for close</object></td>
    </tr>
  </tbody>
</table>

<p></p>

<p></p>

<div id="footer">
<p id="logo"><a href="http://www.fundacionctic.org/"><img src="images/ctic.png"
alt="Fundacion CTIC"></a> <a href="http://ontorule-project.eu"><img
src="images/ontorule.png" alt="ONTORULE Project"></a> </p>

<p><a href="http://ontorule-project.eu/parrot">PARROT</a> is a RIF and OWL
documentation service developed <a href="http://ct.ctic.es">Fundación
CTIC</a>. </p>

<p>This work has been partially funded by <a href="http://ontorule-project.eu"
title="ONTORULE Web site">ONTORULE Project (FP7-ICT-2008-3, project reference
231875)</a>.</p>

<p>Some icons has been created by <a
href="http://www.famfamfam.com/about/">Mark James</a> and there are distributed
under <a href="http://creativecommons.org/licenses/by/2.5/">CreativeCommons-by
2.5</a> license.</p>
</div>
</div>
<script type="text/javascript"
src="http://yui.yahooapis.com/2.8.0r4/build/yahoo-dom-event/yahoo-dom-event.js">
</script>
<script type="text/javascript"
src="http://yui.yahooapis.com/2.8.0r4/build/element/element-min.js">
</script>
<script type="text/javascript"
src="http://yui.yahooapis.com/2.8.0r4/build/tabview/tabview-min.js">
</script>
<script type="text/javascript" src="javascript/scripts.js">
</script>
&lt; c:if test=${not empty paramValues.uris}&gt;<script type="text/javascript">
        (function() {
                        var tabView = new YAHOO.widget.TabView('demo');
                        tabView.selectTab(0);
        })();</script>
</                                              c:if>&lt; c:if test=${not empty
param.documentText}&gt;<script type="text/javascript">
                (function() {
                        var tabView = new YAHOO.widget.TabView('demo');
                        tabView.selectTab(1);
        })();</script>
</                                              c:if></body>
</html>
