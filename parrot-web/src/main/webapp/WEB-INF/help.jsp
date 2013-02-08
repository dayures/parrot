<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"> 
<head>
  <title>Parrot, RIF and OWL documentation service</title>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <link rel="shortcut icon" href="images/favicon.ico" type="image/png" />
  <meta name="description" content="parrot" />
  <meta name="keywords" content="parrot, documentation, tool, rif, rdf" />
  <!-- standalone --> 
  <!-- <link rel="stylesheet" type="text/css" href="standalone/yui.yahooapis.com/2.8.0r4/build/fonts/fonts-min.css" /> -->  
  <link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.8.0r4/build/fonts/fonts-min.css" />
  <link type="text/css" rel="stylesheet" href="css/style.css" media="screen,projection,print" />
</head>

<body>

<div class="all">

<div id="header">
<h1><a href="/parrot"
title="go to parrot home page">Parrot </a></h1>

<h2>a RIF and OWL documentation service</h2>
</div>

<h2>Table of contents</h2>
<ul>
<li><a href="#metadata">Supported metadata</a></li>
<!-- 
<li><a href="#ontologies">Metadata for Ontologies</a></li>
<li><a href="#rules">Metadata for Rules</a></li>
<li><a href="#vocabularies">Metadata for Vocabularies</a></li>
<li><a href="#datasets">Metadata for Datasets</a></li>
-->
<li><a href="#namespace-prefix">Namespace references and prefixes</a></li>
<li><a href="#icons">Icons in Parrot</a></li>
<li><a href="#rest">Use as a REST service</a></li>
<li><a href="#tips">Tips</a>
	<ul>
	<li><a href="#tip-label">Label order</a></li>
	<li><a href="#tip-description">Description order</a></li>
	<li><a href="#tip-button-rdfa">Button RDFa</a></li>
	<li><a href="#tip-features">Other features</a></li>
	</ul>
</li>
</ul>
<br/>

<h2 id="metadata">Supported metadata</h2>

<p>This table describes the annotates properties relevant for adding metadata.</p>

<table id="metadata-support-all">
  <tbody>
    <tr>
      <th rowspan="2">Property</th>
      <th colspan="8">Scope</th>
      <th colspan="4">Place</th>
      <th rowspan="2">Description</th>
    </tr>
    <tr>
      <th>Ontology</th>
      <th>Class</th>
      <th>Property</th>
      <th>Individual</th>
      <th>Rule Set</th>
      <th>Rule</th>
      <th>Vocabulary</th>
      <th>Dataset</th>
      <th><abbr title="Version Control Information">VC</abbr></th>
      <th>Definition</th><!-- TI -->
      <th>In use</th>
      <th>Documentation</th> <!-- Other information -->
    </tr>
    <tr>
      <td><a href="http://www.w3.org/TR/rdf-schema/#ch_label"><em>rdfs:label</em></a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td></td><td></td><td></td><td><img alt="supported" src="images/page.png"/></td>
      <td>
      	<p>The label of the element. The range is a literal with a language tag (optional).</p>
        <p>For example: <code>&lt;rdfs:label xml:lang="en"&gt;Temperature defect&lt;/rdfs:label&gt;</code></p>
      </td>
    </tr>
    <tr>
      <td><a href="http://dublincore.org/documents/dcmi-terms/#terms-title"><em>dct:title</em></a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td></td><td></td><td></td><td><img alt="supported" src="images/page.png"/></td>
      <td>
      	<p>See <em>rdfs:label</em></p>
        <p>For example: <code>&lt;dct:title xml:lang="en"&gt;Temperature defect&lt;/dct:title&gt;</code></p>
      </td>
    </tr>
    <tr>
      <td><a href="http://dublincore.org/documents/dces/#title"><em>dc:title</em></a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td></td><td></td><td></td><td><img alt="supported" src="images/page.png"/></td>
      <td>
      	<p>See <em>rdfs:label</em></p>
        <p>For example: <code>&lt;dc:title xml:lang="en"&gt;Temperature defect&lt;/dc:title&gt;</code></p>
      </td>
    </tr>
    <tr>
      <td><a href="http://www.w3.org/TR/skos-reference/#xl-altLabel"><em>skosxl:altLabel</em></a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td></td><td></td><td></td><td><img alt="supported" src="images/page.png"/></td>
      <td>
      	<p>An alternative label of a element. The range of the property is an instance of the class LexicalLabel.</p>
	  	<p>For example: <code>&lt;skosxl:altLabel rdf:resource="http://www.example.org/skos/temperature-fault" /&gt;</code></p>
      </td>
    </tr>
    <tr>
      <td><a href="http://www.w3.org/TR/skos-reference/#xl-prefLabel"><em>skosxl:prefLabel</em></a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td></td><td></td><td></td><td><img alt="supported" src="images/page.png"/></td>
      <td>
      	<p>The preferred label of a element. The range of the property is an instance of the class LexicalLabel.</p>
	  	<p>For example: <code>&lt;skosxl:prefLabel rdf:resource="http://www.example.org/skos/temperature-defect" /&gt;</code></p>
      </td>
    </tr>
    <tr>
      <td><a href="http://www.w3.org/TR/skos-reference/#altLabel"><em>skos:altLabel</em></a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td></td><td></td><td></td><td><img alt="supported" src="images/page.png"/></td>
      <td>
      	<p>An alternative label of a element. The range is a literal with a language tag.</p>
        <p>For example: <code>&lt;skos:altLabel xml:lang="en"&gt;Temperature fault&lt;/skos:altLabel&gt;</code></p>
      </td>
    </tr>
    <tr>
      <td><a href="http://www.w3.org/TR/skos-reference/#prefLabel"><em>skos:prefLabel</em></a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td></td><td></td><td></td><td><img alt="supported" src="images/page.png"/></td>
      <td>
      	<p>The preferred label of a element. The range is a literal with a language tag (optional). In some cases, it is assumed that <em>prefLabel</em> assumes the role of the <em>label</em> for presentation purposes.</p>
        <p>For example: <code>&lt;skos:prefLabel xml:lang="en"&gt;Temperature defect&lt;/skos:prefLabel&gt;</code></p>
      </td>
    </tr>
    <tr>
      <th rowspan="2">Property</th>
      <th colspan="8">Scope</th>
      <th colspan="4">Place</th>
      <th rowspan="2">Description</th>
    </tr>
    <tr>
      <th>Ontology</th>
      <th>Class</th>
      <th>Property</th>
      <th>Individual</th>
      <th>Rule Set</th>
      <th>Rule</th>
      <th>Vocabulary</th>
      <th>Dataset</th>
      <th><abbr title="Version Control Information">VC</abbr></th>
      <th>Definition</th><!-- TI -->
      <th>In use</th>
      <th>Documentation</th> <!-- Other information -->
    </tr>
   <tr>
      <td><a href="http://www.w3.org/TR/rdf-schema/#ch_comment"><em>rdfs:comment</em></a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td></td><td></td><td></td><td></td>
      <td>
      	<p>The description of the element. The range is a literal with a language tag.</p>
		<p>For example: <code>&lt;rdfs:comment xml:lang="en"&gt;It is a defect having to do with temperature issues.&lt;/rdfs:comment&gt;</code></p>      	
      </td>
    </tr>
	<tr>
      <td><a href="http://dublincore.org/documents/dcmi-terms/#terms-description"><em>dct:description</em></a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td></td><td></td><td></td><td></td>
      <td>
      	<p>See <em>rdfs:comment</em></p>
		<p>For example: <code>&lt;dct:description xml:lang="en"&gt;It is a defect having to do with temperature issues.&lt;/dct:description&gt;</code></p>      	
      </td>
    </tr>    
    <tr>
      <td><a href="http://dublincore.org/documents/dces/#description"><em>dc:description</em></a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td></td><td></td><td></td><td></td>
      <td>
      	<p>See <em>rdfs:comment</em></p>
      	<p>For example: <code>&lt;dc:description&gt;The Friend of a Friend (FOAF) RDF vocabulary, described using W3C RDFS and OWL.&lt;/dc:description&gt;</code></p>
      </td>
    </tr>
   
    <tr>
      <th rowspan="2">Property</th>
      <th colspan="8">Scope</th>
      <th colspan="4">Place</th>
      <th rowspan="2">Description</th>
    </tr>
    <tr>
      <th>Ontology</th>
      <th>Class</th>
      <th>Property</th>
      <th>Individual</th>
      <th>Rule Set</th>
      <th>Rule</th>
      <th>Vocabulary</th>
      <th>Dataset</th>
      <th><abbr title="Version Control Information">VC</abbr></th>
      <th>Definition</th><!-- TI -->
      <th>In use</th>
      <th>Documentation</th> <!-- Other information -->
    </tr>
    <tr>
      <td><a href="http://dublincore.org/documents/dcmi-terms/#terms-creator"><em>dct:creator</em></a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
	  <td><img alt="place" src="images/page.png"/></td><td></td><td></td><td></td>
	  <td>
	  	<p>An entity primarily responsible for making the element: a person, an organization or a service. Recommended best practice is to use a FOAF profile to describe a creator.</p>
	  	<p>For example: <code>&lt;dct:creator rdf:resource="http://www.wikier.org/foaf#wikier" /&gt;</code></p>
	  </td>
	</tr>
    <tr>
      <td><a href="http://dublincore.org/documents/dces/#creator"><em>dc:creator</em></a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
	  <td><img alt="place" src="images/page.png"/></td><td></td><td></td><td></td>
	  <td>
	  	<p>An entity primarily responsible for making the element: a person, an organization or a service. Typically, the name of a creator should be used to indicate the entity.</p>
	  	<p>For example: <code>&lt;dc:creator&gt;Sergio Fernández&lt;/dc:creator&gt;</code></p>
	  </td>
    </tr>    
    <tr>
      <td><a href="http://dublincore.org/documents/dcmi-terms/#terms-contributor"><em>dct:contributor</em></a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td><img alt="place" src="images/page.png"/></td><td></td><td></td><td></td>
      <td>
      	<p>An entity responsible for making the element: a person, an organization or a service. Recommended best practice is to use a FOAF profile to describe a contributor.</p>
	  	<p>For example: <code>&lt;dct:contributor rdf:resource="http://berrueta.net/foaf.rdf#me" /&gt;</code></p>
      </td>
    </tr>
    <tr>
      <td><a href="http://dublincore.org/documents/dces/#contributor"><em>dc:contributor</em></a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td><img alt="place" src="images/page.png"/></td><td></td><td></td><td></td>
      <td>
      	<p>An entity responsible for making the element: a person, an organization or a service. Typically, the name of a contributor should be used to indicate the entity.</p>
      	<p>For example: <code>&lt;dc:contributor&gt;Diego Berrueta&lt;/dc:contributor&gt;</code></p>
      </td>
    </tr>
	<tr>
      <td><a href="http://dublincore.org/documents/dcmi-terms/#terms-publisher"><em>dct:publisher</em></a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
	  <td><img alt="place" src="images/page.png"/></td><td></td><td></td><td></td>
	  <td>
	  	<p>An entity responsible for making the element available: a person, an organization or a service. Recommended best practice is to use a FOAF profile to describe a publisher.</p>
	  	<p>For example: <code>&lt;dct:publisher rdf:resource="http://oreilly.com/" /&gt;</code></p>
	  </td>
    </tr>
    <tr>
      <td><a href="http://dublincore.org/documents/dces/#publisher"><em>dc:publisher</em></a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
	  <td><img alt="place" src="images/page.png"/></td><td></td><td></td><td></td>
	  <td>
	  	<p>An entity responsible for making the element available: a person, an organization or a service. Typically, the name of a publisher should be used to indicate the entity.</p>
        <p>For example: <code>&lt;dc:publisher&gt;O'Reilly&lt;/dc:publisher&gt;</code></p>
	  </td>
    </tr>
    <tr>
      <td><a href="http://xmlns.com/foaf/spec/#term_maker"><em>foaf:maker</em></a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td><img alt="supported" src="images/page.png"/></td><td></td><td></td><td></td>
      <td>
      	<p>See <em>dct:creator</em>.</p>
	  	<p>For example: <code>&lt;foaf:maker rdf:resource="http://berrueta.net/foaf.rdf#me" /&gt;</code></p>
	  </td>
    </tr>
    <tr>
      <th rowspan="2">Property</th>
      <th colspan="8">Scope</th>
      <th colspan="4">Place</th>
      <th rowspan="2">Description</th>
    </tr>
    <tr>
      <th>Ontology</th>
      <th>Class</th>
      <th>Property</th>
      <th>Individual</th>
      <th>Rule Set</th>
      <th>Rule</th>
      <th>Vocabulary</th>
      <th>Dataset</th>
      <th><abbr title="Version Control Information">VC</abbr></th>
      <th>Definition</th><!-- TI -->
      <th>In use</th>
      <th>Documentation</th> <!-- Other information -->
    </tr>
    <tr>
      <td><a href="http://dublincore.org/documents/dcmi-terms/#terms-date"><em>dct:date</em></a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td><img alt="place" src="images/page.png"/></td><td></td><td></td><td></td>
      <td>
      	<p>The date of creation or publication of the element. Recommended best practice is to use the W3CDTF profile of ISO 8601.</p>
        <p>For example: <code>&lt;dct:date&gt;1981-01-20&lt;/dct:date&gt;</code></p>
      </td>
    </tr>    
    <tr>
      <td><a href="http://dublincore.org/documents/dces/#date"><em>dc:date</em></a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td></td><td><img alt="supported" src="images/tick.png"/></td>
      <td><img alt="place" src="images/page.png"/></td><td></td><td></td><td></td>
      <td>
      	<p>The date of creation or publication of the element. Recommended best practice is to use the W3CDTF profile of ISO 8601.</p>
        <p>For example: <code>&lt;dc:date&gt;1981-01-20&lt;/dc:date&gt;</code></p>
      </td>
    </tr>
    <tr>
      <td><a href="http://dublincore.org/documents/dcmi-terms/#terms-issued"><em>dct:issued</em></a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td><img alt="place" src="images/page.png"/></td><td></td><td></td><td></td>
      <td>
      	<p>Date of formal issuance (e.g., publication) of the element. Recommended best practice is to use the W3CDTF profile of ISO 8601.</p>
        <p>For example: <code>&lt;dct:issued&gt;1979-03-23&lt;/dct:issued&gt;</code></p>
      </td>
    </tr>
    <tr>
      <td><a href="http://dublincore.org/documents/dcmi-terms/#terms-modified"><em>dct:modified</em></a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td><img alt="place" src="images/page.png"/></td><td></td><td></td><td></td>
      <td>
      	<p>The date on which the element was changed. Recommended best practice is to use the W3CDTF profile of ISO 8601.</p>
        <p>For example: <code>&lt;dct:modified&gt;2010-07-11&lt;/dct:modified&gt;</code></p>
      </td>
    </tr>    
    <tr>
      <th rowspan="2">Property</th>
      <th colspan="8">Scope</th>
      <th colspan="4">Place</th>
      <th rowspan="2">Description</th>
    </tr>
    <tr>
      <th>Ontology</th>
      <th>Class</th>
      <th>Property</th>
      <th>Individual</th>
      <th>Rule Set</th>
      <th>Rule</th>
      <th>Vocabulary</th>
      <th>Dataset</th>
      <th><abbr title="Version Control Information">VC</abbr></th>
      <th>Definition</th><!-- TI -->
      <th>In use</th>
      <th>Documentation</th> <!-- Other information -->
    </tr>

    <tr>
      <td><a href="http://dublincore.org/documents/dcmi-terms/#terms-license"><em>dct:license</em></a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td></td><td><img alt="supported" src="images/tick.png"/></td>
      <td><img alt="place" src="images/page.png"/></td><td></td><td></td><td></td>
      <td>
      	<p>A legal document describing the copyright license of the element. Recommended best practice is to use Creative Commons licenses and to describe them in RDF with the Creative Commons Rights Expression Language (CC REL).</p>
	  	<p>For example: <code>&lt;dct:license rdf:resource="http://creativecommons.org/licenses/by/3.0/" /&gt;</code></p>
	  </td>
    </tr>    
    <tr>
      <td><a href="http://creativecommons.org/ns"><em>cc:license</em></a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td></td><td><img alt="supported" src="images/tick.png"/></td>
      <td><img alt="place" src="images/page.png"/></td><td></td><td></td><td></td>
      <td>
      	<p>A legal document describing the copyright license of the element. Recommended best practice is to use Creative Commons licenses and to describe them in RDF with the Creative Commons Rights Expression Language (CC REL).</p>
	  	<p>For example: <code>&lt;cc:license rdf:resource="http://creativecommons.org/licenses/by/3.0/" /&gt;</code></p>
	  </td>
    </tr>
    <tr>
      <td><a href="http://web.resource.org/rss/1.0/modules/cc/"><em>cc-deprecated:license</em></a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td></td><td><img alt="supported" src="images/tick.png"/></td>
      <td><img alt="place" src="images/page.png"/></td><td></td><td></td><td></td>
      <td>
      	<p>A legal document describing the copyright license of the element. Recommended best practice is to use Creative Commons licenses and to describe them in RDF with the Creative Commons Rights Expression Language (CC REL).</p>
	  	<p>For example: <code>&lt;cc-deprecated:license rdf:resource="http://creativecommons.org/licenses/by/3.0/" /&gt;</code></p>
	  </td>
    </tr>
    <tr>
      <td><a href="http://dublincore.org/documents/dces/#rights"><em>dc:rights</em></a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td></td><td></td>
      <td><img alt="place" src="images/page.png"/></td><td></td><td></td><td></td>
      <td>
      	<p>Information about rights held in and over the element.</p>
        <p>For example: <code>&lt;dc:rights&gt;CTIC Foundation, some rights reserved&lt;/dc:rights&gt;</code></p>
      </td>
    </tr>
    <tr>
      <th rowspan="2">Property</th>
      <th colspan="8">Scope</th>
      <th colspan="4">Place</th>
      <th rowspan="2">Description</th>
    </tr>
    <tr>
      <th>Ontology</th>
      <th>Class</th>
      <th>Property</th>
      <th>Individual</th>
      <th>Rule Set</th>
      <th>Rule</th>
      <th>Vocabulary</th>
      <th>Dataset</th>
      <th><abbr title="Version Control Information">VC</abbr></th>
      <th>Definition</th><!-- TI -->
      <th>In use</th>
      <th>Documentation</th> <!-- Other information -->
    </tr>
    <tr>
      <td><a href="http://www.w3.org/TR/2004/REC-owl-ref-20040210/#versionInfo-def"><em>owl:versionInfo</em></a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td></td><td></td>
      <td><img alt="place" src="images/page.png"/></td><td></td><td></td><td></td>
      <td>
      	<p>Provides a hook suitable for use by versioning systems.</p>
        <p>For example: <code>&lt;owl:versionInfo&gt;1.17&lt;/owl:versionInfo&gt;</code></p>
      </td>
    </tr>
    <tr>
      <td><a href="http://www.w3.org/2003/06/sw-vocab-status/note.html#vocab"><em>vs:term_status</em></a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td></td><td></td><td></td><td></td>
      <td><img alt="place" src="images/page.png"/></td><td></td><td></td><td></td>
      <td>
      	<p>Status of a term, expressed as a short symbolic string; known values include 'unstable','testing', 'stable' and 'archaic'.</p>
		<p>For example: <code>&lt;vs:term_status&gt;testing&lt;/vs:term_status&gt;</code></p>      	
      </td>
    </tr>    
    <tr>
      <td><a href="http://www.w3.org/TR/2009/REC-owl2-syntax-20091027/#a_deprecated"><em>owl:deprecated</em></a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td></td><td></td><td></td><td></td>
      <td></td><td></td><td></td><td></td>
      <td>
      	<p>An annotation with the owl:deprecated annotation property and the value equal to <code>"true"^^xsd:boolean</code> can be used to specify that an IRI is deprecated.
      	There are other ways to mark as deprecated an ontology or a class, using <code>owl:DeprecatedProperty</code>, <code>owl:DeprecatedClass</code></p>
        <p>For example: <code>&lt;owl:deprecated rdf:datatype="http://www.w3.org/2001/XMLSchema#boolean"&gt;true&lt;/owl:deprecated&gt;</code></p>
      </td>
    </tr>
    <tr>
      <td><a href="http://xmlns.com/foaf/spec/#term_depiction"><em>foaf:depiction</em></a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td></td><td></td>
      <td></td><td></td><td></td><td><img alt="supported" src="images/page.png"/></td>
      <td>
      	<p>An image associated with the element.</p>
	  	<p>For example: <code>&lt;foaf:depiction rdf:resource="http://farm5.static.flickr.com/4014/4396193788_707ae6ec23.jpg" /&gt;</code></p>
      </td>
    </tr>    
    <tr>
      <td><a href="http://ogp.me/#video"><em>og:video</em></a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td></td><td></td>
      <td></td><td></td><td></td><td><img alt="supported" src="images/page.png"/></td>
      <td>
      	<p>A video associated with the element.</p>
		<p>For example: <code>&lt;og:video rdf:resource="http://www.youtube.com/watch?v=5h10QHpA5EU" /&gt;</code></p>      	
      </td>
    </tr>    
    <tr>
      <td><a href="http://www.w3.org/TR/rdf-schema/#ch_seealso"><em>rdfs:seeAlso</em></a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td></td><td></td><td></td><td><img alt="supported" src="images/page.png"/></td>
      <td>
      	<p>Specifies a resource that might provide additional information about the element.</p>
	  	<p>For example: <code>&lt;rdfs:seeAlso rdf:resource="http://www.ietf.org/rfc/rfc1766.txt" /&gt;</code></p>
      </td>
    </tr>    
    <tr>
      <td><a href="http://dublincore.org/documents/dcmi-terms/#terms-source"><em>dct:source</em></a></td>
      <td></td><td></td><td></td><td></td><td></td><td><img alt="supported" src="images/tick.png"/></td><td></td><td></td>
      <td></td><td></td><td></td><td><img alt="place" src="images/page.png"/></td>
      <td>
      	<p>The resources from which the rules are derived. Typically, they are documentary sources (as candidate rules), but diagrams, plans or pictures are also possible.</p>
        <p>For example: <code>&lt;dct:source rdf:resource="http://example.org/candidate-rule" /&gt;</code></p>      	
      </td>
    </tr>
    <tr>
      <th rowspan="2">Property</th>
      <th colspan="8">Scope</th>
      <th colspan="4">Place</th>
      <th rowspan="2">Description</th>
    </tr>
    <tr>
      <th>Ontology</th>
      <th>Class</th>
      <th>Property</th>
      <th>Individual</th>
      <th>Rule Set</th>
      <th>Rule</th>
      <th>Vocabulary</th>
      <th>Dataset</th>
      <th><abbr title="Version Control Information">VC</abbr></th>
      <th>Definition</th><!-- TI -->
      <th>In use</th>
      <th>Documentation</th> <!-- Other information -->
    </tr>
    <tr>
      <td><a href="http://vocab.org/vann/.html#preferredNamespacePrefix"><em>vann:preferredNamespacePrefix</em></a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td></td><td></td><td></td><td></td><td></td><td><img alt="supported" src="images/tick.png"/></td><td></td>
      <td></td><td><img alt="place" src="images/page.png"/></td><td></td><td></td>
      <td>
      	<p>The preferred namespace prefix when using entities of an ontology or a vocabulary.</p>
        <p>For example: <code>&lt;vann:preferredNamespacePrefix&gt;whois&lt;/vann:preferredNamespacePrefix&gt;</code></p>
      </td>
    </tr>
    <tr>
      <td><a href="http://vocab.org/vann/.html#preferredNamespaceUri"><em>vann:preferredNamespaceUri</em></a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td></td><td></td><td></td><td></td><td></td><td><img alt="supported" src="images/tick.png"/></td><td></td>
      <td></td><td><img alt="place" src="images/page.png"/></td><td></td><td></td>
      <td>
      	<p>The preferred namespace URI to use when using terms from an ontology or a vocabulary.</p>
        <p>For example: <code>&lt;vann:preferredNamespaceUri&gt;http://www.kanzaki.com/ns/whois#&lt;/vann:preferredNamespaceUri&gt;</code> or <code>&lt;vann:preferredNamespaceUri rdf:resource="http://www.kanzaki.com/ns/whois#"/&gt;</code> </p>
      </td>
    </tr>
    <tr>
      <td><a href="http://labs.mondeca.com/vocab/voaf/#classNumber"><em>voaf:classNumber</em></a></td>
      <td></td><td></td><td></td><td></td><td></td><td></td><td><img alt="supported" src="images/tick.png"/></td><td></td>
      <td></td><td><img alt="place" src="images/page.png"/></td><td></td><td></td>
      <td>
      	<p>The number of classes defined in the vocabulary.</p>
      	<p>For example: <code>&lt;voaf:classNumber&gt;42&lt;/voaf:classNumber&gt;</code></p>
      </td>
    </tr>    
    <tr>
      <td><a href="http://labs.mondeca.com/vocab/voaf/#propertyNumber"><em>voaf:propertyNumber</em></a></td>
      <td></td><td></td><td></td><td></td><td></td><td></td><td><img alt="supported" src="images/tick.png"/></td><td></td>
      <td></td><td><img alt="place" src="images/page.png"/></td><td></td><td></td>
      <td>
      	<p>The number of properties defined in the vocabulary.</p>
      	<p>For example: <code>&lt;voaf:propertyNumber&gt;55&lt;/voaf:propertyNumber&gt;</code></p>
      </td>
    </tr>   
    <tr>
      <td><a href="http://xmlns.com/foaf/spec/#term_homepage"><em>foaf:homepage</em></a></td>
      <td></td><td></td><td></td><td></td><td></td><td></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td></td><td><img alt="place" src="images/page.png"/></td><td></td><td></td>
      <td>
      	<p>The homepage (typically a Web page in HTML format) of the vocabulary.</p>
      	<p>For example: <code>&lt;foaf:homepage rdf:resource="http://rdfs.org/sioc/spec/" /&gt;</code></p>      	
      </td>
    </tr>
    <tr>
      <td><a href="http://rdfs.org/ns/void#dataDump"><em>void:dataDump</em></a></td>
      <td></td><td></td><td></td><td></td><td></td><td></td><td></td><td><img alt="supported" src="images/tick.png"/></td>
      <td></td><td><img alt="place" src="images/page.png"/></td><td></td><td></td>
      <td>
      	<p>An RDF dump, partial or complete, of the dataset.</p>
      	<p>For example: <code>&lt;void:dataDump rdf:resource="http://ec.europa.eu/eurostat/ramon/rdfdata/migs.rdf" /&gt;</code></p>
      </td>
    </tr>
    <tr>
      <td><a href="http://vocab.deri.ie/void#sparqlEndpoint"><em>void:sparqlEndpoint</em></a></td>
      <td></td><td></td><td></td><td></td><td></td><td></td><td></td><td><img alt="supported" src="images/tick.png"/></td>
      <td></td><td><img alt="place" src="images/page.png"/></td><td></td><td></td>
      <td>
      	<p>The SPARQL endpoint of the dataset.</p>
      	<p>For example: <code>&lt;void:sparqlEndpoint rdf:resource="http://risp.asturias.es/sparql" /&gt;</code></p>      	
      </td>
    </tr>    
    <tr>
      <td><a href="http://vocab.deri.ie/void#vocabulary"><em>void:vocabulary</em></a></td>
      <td></td><td></td><td></td><td></td><td></td><td></td><td></td><td><img alt="supported" src="images/tick.png"/></td>
      <td></td><td><img alt="place" src="images/page.png"/></td><td></td><td></td>
      <td>
      	<p>A vocabulary or <em>owl:Ontology</em> whose classes or properties are used in the dataset.</p>
      	<p>For example: <code>&lt;void:vocabulary rdf:resource="http://xmlns.com/foaf/0.1/" /&gt;</code></p>      	
      </td>
    </tr>
     <tr>
      <td><a href="http://www.w3.org/TR/rdf-schema/#ch_isdefinedby"><em>rdfs:isDefinedBy</em></a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td></td><td></td>
      <td></td><td></td><td><img alt="place" src="images/page.png"/></td><td></td>
      <td>
      	<p>Used to indicate where this element has been defining.</p>
	  	<p>For example: <code>&lt;rdfs:isDefinedBy rdf:resource="http://purl.org/vocab/vann/" /&gt;</code></p>
      </td>
    </tr>    
    </tbody>
</table>

<h2 id="namespace-prefix">Namespace references and prefixes</h2>
<table id="prefix-legend">
  <tbody>
    <tr>
      <th>Prefix</th>
      <th>Namespace</th>
    </tr>
    <tr>
      <td>cc</td>
      <td>http://creativecommons.org/ns#</td>
    </tr>
    <tr>
      <td>cc-deprecated</td>
      <td>http://web.resource.org/cc/</td>
    </tr>
    <tr>
      <td>dc</td>
      <td>http://purl.org/dc/elements/1.1/</td>
    </tr>
    <tr>
      <td>dct</td>
      <td>http://purl.org/dc/terms/</td>
    </tr>
    <tr>
      <td>foaf</td>
      <td>http://xmlns.com/foaf/0.1/</td>
    </tr>
    <tr>
      <td>og</td>
      <td>http://ogp.me/ns#</td>
    </tr>
    <tr>
      <td>owl</td>
      <td>http://www.w3.org/2002/07/owl#</td>
    </tr>
    <tr>
      <td>rdfs</td>
      <td>http://www.w3.org/2000/01/rdf-schema#</td>
    </tr>
    <tr>
      <td>skosxl</td>
      <td>http://www.w3.org/2008/05/skos-xl#</td>
    </tr>
    <tr>
      <td>skos</td>
      <td>http://www.w3.org/2004/02/skos/core#</td>
    </tr>
    <tr>
      <td>vann</td>
      <td>http://purl.org/vocab/vann/</td>
    </tr>
    <tr>
      <td>voaf</td>
      <td>http://labs.mondeca.com/vocab/voaf#</td>
    </tr>
    <tr>
      <td>void</td>
      <td>http://rdfs.org/ns/void#</td>
    </tr>
    <tr>
      <td>vs</td>
      <td>http://www.w3.org/2003/06/sw-vocab-status/ns#</td>
    </tr>
  </tbody>
</table>

<h2 id="icons">Icons in Parrot</h2>

<table>
  <tbody id="table-icons">
    <tr>
      <th>Icon</th>

      <th>Definition</th>
    </tr>
    <tr>
      <td><img alt="data property"
        src="report/images/datatype-property-24.png" width="24" height="24" /></td>
      <td>A <a href="http://www.w3.org/TR/owl2-syntax#Data_Properties" target="_blank">data property</a> is used to describe attributes of resources, such as the height of a person or the population of a country.</td>
    </tr>
    <tr>
      <td><img alt="object property"
        src="report/images/object-property-24.png" width="24" height="24" /></td>
      <td>An <a href="http://www.w3.org/TR/owl2-syntax#Object_Properties" target="_blank">object property</a> is used to describe relations to other resources, such as the mother of a person or the capital of a country.</td>
    </tr>

    <tr>
      <td><img alt="annotation property"
        src="report/images/annotation-property-24.png" width="24" height="24" /></td>
      <td>An <a href="http://www.w3.org/TR/owl2-syntax#Annotation_Properties" target="_blank">annotation property</a> is used to give more information of resources.</td>
    </tr>

    <tr>
      <td><img alt="reflexive object property"
        src="report/images/reflexive-property.png" width="24" height="24" /></td>
      <td>A <a href="http://www.w3.org/TR/owl2-syntax/#Reflexive_Object_Properties" target="_blank">reflexive property</a> describes a relation where every resource is related to itself.</td>
    </tr>

    <tr>
      <td><img alt="irreflexive object property"
        src="report/images/irreflexive-property.png" width="26" height="24" /></td>
      <td>A <a href="http://www.w3.org/TR/owl2-syntax/#Irreflexive_Object_Properties" target="_blank">irreflexive property</a> describes a relation where none resource is related to itself.</td>
    </tr>

    <tr>
      <td><img alt="symmetric object property"
        src="report/images/symmetric-property.png" width="45" height="24" /></td>
      <td><a href="http://www.w3.org/TR/owl2-syntax/#Symmetric_Object_Properties" target="_blank">Symmetric property</a></td>
    </tr>

    <tr>
      <td><img alt="asymmetric object property"
        src="report/images/asymmetric-property.png" width="40" height="24" /></td>
      <td><a href="http://www.w3.org/TR/owl2-syntax/#Asymmetric_Object_Properties" target="_blank">Asymmetric property</a></td>
    </tr>

    <tr>
     <td><img alt="transitive object property" src="report/images/transitive-property.png" width="61" height="24" /></td>
      <td><a href="http://www.w3.org/TR/owl2-syntax/#Transitive_Object_Properties" target="_blank">Transitive property</a></td>
    </tr>
    
    <tr>
     <td><img alt="functional object property" src="report/images/functional-property.png" width="33" height="24" /></td>
      <td><a href="http://www.w3.org/TR/owl2-syntax/#Functional_Object_Properties" target="_blank">Functional property</a></td>
    </tr>
    
    <tr>
     <td><img alt="inverse functional object property" src="report/images/inverse-functional-property.png" width="33" height="24" /></td>
      <td><a href="http://www.w3.org/TR/owl2-syntax/#Inverse-Functional_Object_Properties" target="_blank">Inverse functional property</a></td>
    </tr>

  </tbody>
</table>

<h2 id="rest">Use as a REST service</h2>

<h3>Generate report from URI/s</h3> 
<table> 
    <tr><td>URL</td><td colspan="2">/parrot</td></tr> 
    <tr><td>Method</td><td colspan="2">GET</td></tr> 
    <tr><td rowspan="5">Querystring</td><td>documentUri</td><td>URI of an input document</td></tr> 
    <tr><td>mimetype</td><td>Mimetype of the input document. Supported values are
    							<ul>
    								<li><tt>default</tt> Allow content negotiation</li> 
    								<li><tt>application/owl+xml</tt> OWL ontology</li> 
    								<li><tt>text/n3</tt> N3 ontology</li> 
    								<li><tt>application/xhtml+xml</tt> XHTML+RDFa document</li> 
    								<li><tt>text/html</tt> HTML+RDFa documentn</li> 
    								<li><tt>application/rif+xml</tt> RIF XML document</li> 
    								<li><tt>text/x-rif-ps</tt> RIF PS document</li> 
								</ul> 
    </td></tr> 
    <tr><td>profile <span class="optional">(optional)</span></td><td>The profile for generate a customize report. Supported values are
    							<ul>
    								<li><tt>technical</tt></li>
    								<li><tt>business</tt></li>
    							</ul>
    							Default value is <tt>technical</tt>.
    </td></tr> 
    <tr><td>language <span class="optional">(optional)</span></td><td>The language uses to generate the report. The possible values are in the <a href="http://www.iana.org/assignments/language-subtag-registry">IANA registry of language tags</a>. Default value is <tt>en</tt> (English).</td></tr>
    <tr><td>customizeCssUrl  <span class="optional">(optional)</span></td><td>The URL of a customize <abbr title="Cascading Style Sheets"><span class="abbr" title="Cascading Style Sheets">CSS</span></abbr></td></tr> 
    <tr><td rowspan="3">Returns</td><td colspan="2">200 OK</td></tr> 
    <tr><td colspan="2">400 Illegal Request</td></tr>
    <tr><td colspan="2">500 Unexpected error</td></tr> 
</table>

You can send multiple <tt>documentUri</tt> parameter values. In that case, each one should be accompanied by a <tt>mimetype</tt> parameter.


<p>For example:</p>
<p class="code">GET /parrot?documentUri=http://purl.org/dc/terms/&amp;mimetype=default&amp;profile=business&amp;language=en</p>
<br />

<h3>Generate report from direct input</h3> 
<table> 
    <tr><td>URL</td><td colspan="2">/parrot</td></tr> 
    <tr><td>Method</td><td colspan="2">POST</td></tr> 
    <tr><td rowspan="5">Request Body</td><td>documentText</td><td>The input source text.</td></tr> 
    <tr><td>mimetypeText</td><td>Mimetype of the input source text. Supported values are
    							<ul>
    								<li><tt>application/owl+xml</tt> OWL ontology</li> 
    								<li><tt>text/n3</tt> N3 ontology</li> 
    								<li><tt>application/xhtml+xml</tt> XHTML+RDFa document</li> 
    								<li><tt>text/html</tt> HTML+RDFa documentn</li> 
    								<li><tt>application/rif+xml</tt> RIF XML document</li> 
    								<li><tt>text/x-rif-ps</tt> RIF PS document</li> 
								</ul> 
    </td></tr> 
    <tr><td>profile <span class="optional">(optional)</span></td><td>The profile for generate a customize report. Supported values are
    							<ul>
    								<li><tt>technical</tt></li>
    								<li><tt>business</tt></li>
    							</ul>
    							Default value is <tt>technical</tt>.
    </td></tr> 
    <tr><td>language <span class="optional">(optional)</span></td><td>The language uses to generate the report. The possible values are in the <a href="http://www.iana.org/assignments/language-subtag-registry">IANA registry of language tags</a>. Default value is <tt>en</tt> (English).</td></tr>
    <tr><td>customizeCssUrl <span class="optional">(optional)</span></td><td>The URL of a customize <abbr title="Cascading Style Sheets"><span class="abbr" title="Cascading Style Sheets">CSS</span></abbr></td></tr> 
    <tr><td rowspan="3">Returns</td><td colspan="2">200 OK</td></tr> 
    <tr><td colspan="2">400 Illegal Request</td></tr>
    <tr><td colspan="2">500 Unexpected error</td></tr> 
</table>

<p>For example:</p>
<p class="code">POST /parrot
	
         documentText=&lt;rdf:RDF xmlns:owl=&quot;http://www.w3.org/2002/07/owl#&quot;
                               xmlns:rdf=&quot;http://www.w3.org/1999/02/22-rdf-syntax-ns#&quot;&gt;
                         &lt;owl:Ontology rdf:about=&quot;http://ontorule-project.eu/resources/steel-30&quot;&gt;
                              &lt;rdfs:label xml:lang=&quot;en&quot;&gt;Steel Ontology&lt;/rdfs:label&gt;
                         &lt;/owl:Ontology&gt;
                      &lt;/rdf:RDF&gt;
         mimetypeText=application/owl+xml
         profile=business
         language=en
         customizeCssUrl=http://example.org/style.css</p>

You can send multiple <tt>documentText</tt> parameter values. In that case, each one should be accompanied by a <tt>mimetype</tt> parameter.
<br />
<h3>Generate report from existing report</h3> 
<table> 
    <tr><td>URL</td><td colspan="2">/parrot</td></tr> 
    <tr><td>Method</td><td colspan="2">GET</td></tr> 
    <tr><td rowspan="4">Querystring</td><td>reportURL</td><td>URL of the existing report</td></tr> 
    <tr><td>profile <span class="optional">(optional)</span></td><td>The profile for generate a customize report. Supported values are
    							<ul>
    								<li><tt>technical</tt></li>
    								<li><tt>business</tt></li>
    							</ul>
    							Default value is <tt>technical</tt>.
    </td></tr> 
    <tr><td>language <span class="optional">(optional)</span></td><td>The language uses to generate the report. The possible values are in the <a href="http://www.iana.org/assignments/language-subtag-registry">IANA registry of language tags</a>. Default value is <tt>en</tt> (English).</td></tr>
    <tr><td>customizeCssUrl <span class="optional">(optional)</span></td><td>The URL of a customize <abbr title="Cascading Style Sheets"><span class="abbr" title="Cascading Style Sheets">CSS</span></abbr></td></tr> 
    <tr><td rowspan="3">Returns</td><td colspan="2">200 OK</td></tr> 
    <tr><td colspan="2">400 Illegal Request</td></tr>
    <tr><td colspan="2">500 Unexpected error</td></tr> 
</table>

<p>For example:</p>
<p class="code">GET /parrot?reportURL=http://ontorule-project.eu/resources/parrot/examples/previous-report-metadata.html&amp;profile=business&amp;language=en</p>

<h2 id="tips">Tips</h2>

<h3 id="tip-label">Label order</h3>
<p>If you want to add a <strong>label</strong> to an element, the preferred property order to set it is:</p>
<ol>
  <li>http://www.w3.org/2008/05/skos-xl#prefLabel</li>
  <li>http://www.w3.org/2004/02/skos/core#prefLabel</li>
  <li>http://www.w3.org/2008/05/skos-xl#altLabel</li>
  <li>http://www.w3.org/2004/02/skos/core#altLabel</li>
  <li>http://purl.org/dc/terms/title</li>
  <li>http://purl.org/dc/elements/1.1/title</li>
  <li>http://www.w3.org/2000/01/rdf-schema#label</li>
</ol>

<h3 id="tip-description">Description order</h3>
<p>If you want to add a <strong>description</strong> to an element, the preferred property order to set it is:</p>
<ol>
  <li>http://purl.org/dc/terms/description</li>
  <li>http://purl.org/dc/elements/1.1/description</li>
  <li>http://www.w3.org/2000/01/rdf-schema#comment</li>
</ol>

  <h3 id="tip-button-rdfa">Button RDFa</h3>
  <p>You can <strong>create a report from a webpage with RDFa</strong>. Just add a button in your webpage in order to create a link to the Parrot report.</p>
  <img src="images/button-referer.png" alt="Document with Parrot"/>
  <p>
   We encourage you to use the XHTML code below (or its HTML equivalent),
   but you may use a different code to integrate the icon within your web page
   as long as the icon is used as a link to document the Web page it is in.
   Sample code is as follows:
  </p> 
  <pre> 
   &lt;p&gt;
      &lt;a href="http://ontorule-project.eu/parrot?referer=true"&gt;&lt;img
          src="http://ontorule-project.eu/parrot/images/button-referer.png"
          alt="Document with Parrot" /&gt;&lt;/a&gt;
    &lt;/p&gt;
  </pre> 

<h3 id="tip-features">Other features</h3>

<p>If a resource has more than one <em>rdf:type</em>, this resource will be document in the report just once (with no priority to document the resource like a concrete <em>rdf:type</em>).</p>
<p>For instance, if a resource is declared like:</p>
<pre><code>
&lt;rdf:RDF xmlns:rdf=&quot;http://www.w3.org/1999/02/22-rdf-syntax-ns#&quot;&gt;
	&lt;rdf:Description rdf:about=&quot;http://example.org/resource&quot;&gt;
		&lt;rdf:type rdf:resource=&quot;http://www.w3.org/2002/07/owl#Ontology&quot;/&gt;
		&lt;rdf:type rdf:resource=&quot;http://rdfs.org/ns/void#Dataset&quot;/&gt;
	&lt;/rdf:Description&gt;
&lt;/rdf:RDF&gt; 
</code></pre>

<p>The documentation for the resource <em>http://example.org/resource</em> will only appears once (like an ontology or like a dataset).</p>

<div id="footer">
<p id="logo"><a href="http://www.fundacionctic.org/"><img src="images/ctic.png"
alt="Fundacion CTIC"/></a> <a href="http://ontorule-project.eu"><img
src="images/ontorule.png" alt="ONTORULE Project"/></a> </p>

<p><a href="http://ontorule-project.eu/parrot">Parrot</a> is a RIF and OWL
documentation service developed <a href="http://ct.ctic.es">Fundación
CTIC</a>. <a href="https://bitbucket.org/fundacionctic/parrot">Source code is available</a>.</p>

<p>This work has been partially funded by <a href="http://ontorule-project.eu"
title="ONTORULE Web site">ONTORULE Project (FP7-ICT-2008-3, project reference
231875)</a>.</p>

<p>Some icons has been created by <a
href="http://www.famfamfam.com/about/">Mark James</a> and there are distributed
under <a href="http://creativecommons.org/licenses/by/2.5/">CreativeCommons-by
2.5</a> license.</p>
</div>
</div>
<script type="text/javascript">
<![CDATA[ 
var _gaq = _gaq || [];
_gaq.push(['_setAccount', 'UA-8820144-1']);
_gaq.push(['_trackPageview']);

(function() {
  var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
  ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
  var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
})();
]]>         
</script>
</body>
</html>
