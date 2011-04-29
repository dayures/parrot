<%@ page language="java" contentType="application/xhtml+xml; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"> 
<head>
  <title>Parrot, RIF and OWL documentation service</title>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <link rel="shortcut icon" href="images/favicon.ico" type="image/png" />
  <meta name="description" content="parrot" />
  <meta name="keywords" content="parrot, documentation, tool, rif, rdf" />
  <link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.8.0r4/build/fonts/fonts-min.css" />  
  <link type="text/css" rel="stylesheet" href="css/style.css" media="screen,projection,print" />
</head>

<body>

<div class="all">

<div id="header">
<h1><a href="http://ontorule-project.eu/parrot"
title="go to parrot project home page">Parrot </a></h1>

<h2>a RIF and OWL documentation service (alpha version)</h2>
</div>

<h2>Metadata for Ontologies</h2>

<p>This table describes the annotates properties relevant for adding metadata
to <strong>ontologies</strong>. Notice that not all the properties are
currently supported by Parrot.</p>

<table id="metadata-support-ontologies">
  <tbody>
    <tr>
      <th>Property</th>
      <th>Vocabulary</th>
      <th colspan="4">Scope</th>
      <th>Description</th>
    </tr>
    <tr>
      <th></th>
      <th></th>
      <th>Ontology</th>
      <th>Class</th>
      <th>Property</th>
      <th>Individual</th>
      <th></th>
    </tr>
    <tr>
      <td><em>dc:contributor</em></td>
      <td><a href="http://dublincore.org/documents/dces/">Dublin Core Elements</a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td>A contributor to the ontological element: a person or an organization.
        Recommended best practice is to use a FOAF profile to describe a
        contributor, although a literal is also allowed.</td>
    </tr>
    <tr>
      <td><em>dc:creator</em></td>
      <td><a href="http://dublincore.org/documents/dces/">Dublin Core Elements</a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td>The creator of the ontological element: a person or an organization. Recommended
        best practice is to use a FOAF profile to describe the creator,
        although a literal is also allowed.</td>
    </tr>
    <tr>
      <td><em>dc:date</em></td>
      <td><a href="http://dublincore.org/documents/dces/">Dublin Core Elements</a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td>Expresses the date of creation or publication of the ontological element.
        Recommended best practice is to use the W3CDTF profile of ISO 8601.</td>
    </tr>
    <tr>
      <td><em>dc:description</em></td>
      <td><a href="http://dublincore.org/documents/dces/">Dublin Core Elements</a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td>See <em>comment</em></td>
    </tr>
    <tr>
      <td><em>dc:publisher</em></td>
      <td><a href="http://dublincore.org/documents/dces/">Dublin Core Elements</a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td>The entity responsible for making the ontological element available. Recommended
        best practice is to use a FOAF profile to describe the publisher,
        although a literal is also allowed.</td>
    </tr>       
    <tr>
      <td><em>dc:rights</em></td>
      <td><a href="http://dublincore.org/documents/dces/">Dublin Core Elements</a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td>Information about rights held in and over the ontological element.</td>
    </tr>
    <tr>
      <td><em>dc:title</em></td>
      <td><a href="http://dublincore.org/documents/dces/">Dublin Core Elements</a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td>See <em>label</em></td>
    </tr>
    <tr>
      <td><em>dct:description</em></td>
      <td><a href="http://dublincore.org/documents/dcmi-terms/">Dublin Core Terms</a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td>See <em>comment</em></td>
    </tr>
    <tr>
      <td><em>dct:license</em></td>
      <td><a href="http://dublincore.org/documents/dcmi-terms/">Dublin Core Terms</a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td>A legal document describing the copyright license of the ontological element.
        Recommended best practice is to use Creative Commons licenses and to
        describe them in RDF with the Creative Commons Rights Expression
        Language (CC REL). </td>
    </tr>    
    <tr>
      <td><em>rdfs:comment</em></td>
      <td><a href="http://www.w3.org/TR/rdf-schema/">RDF Schema</a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td>The description of the ontological element. The range is a literal with a
        language tag.</td>
    </tr>
    <tr>
      <td><em>rdfs:isDefinedBy</em></td>
      <td><a href="http://www.w3.org/TR/rdf-schema/">RDF Schema</a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td>Used to indicate where this ontological element has been defining.</td>
    </tr>
    <tr>
      <td><em>rdfs:label</em></td>
      <td><a href="http://www.w3.org/TR/rdf-schema/">RDF Schema</a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td>The label of the ontological element. The range is a literal with a language
      tag.</td>
    </tr>    
    <tr>
      <td><em>skosxl:altLabel</em></td>
      <td><a href="http://www.w3.org/TR/skos-reference/#xl">SKOS-XL</a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td>An alternative label of a ontological element. The range of the property is an
        instance of the class LexicalLabel.</td>
    </tr>
    <tr>
      <td><em>skosxl:prefLabel</em></td>
      <td><a href="http://www.w3.org/TR/skos-reference/#xl">SKOS-XL</a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td>The preferred label of a ontological element. The range of the property is an
        instance of the class LexicalLabel.</td>
    </tr>
    <tr>
      <td><em>skos:altLabel</em></td>
      <td><a href="http://www.w3.org/TR/skos-reference/#overview">SKOS</a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td>An alternative label of a ontological element. The range is a literal with a
        language tag.</td>
    </tr>
    <tr>
      <td><em>skos:prefLabel</em></td>
      <td><a href="http://www.w3.org/TR/skos-reference/#overview">SKOS</a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td>The preferred label of a ontological element. The range is a literal with a
        language tag. In some cases, it is assumed that <em>prefLabel</em>
        assumes the role of the <em>label</em> for presentation purposes.</td>
    </tr>
    <tr>
      <td><em>foaf:depiction</em></td>
      <td><a href="http://xmlns.com/foaf/spec/">FOAF</a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td>An image associated with the ontological element. </td>
    </tr>
    <tr>
      <td><em>og:video</em></td>
      <td><a href="http://ogp.me">OG</a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td>A video associated with the ontological element. </td>
    </tr>    
    <tr>
      <td><em>owl:versionInfo</em></td>
      <td><a
        href="http://www.w3.org/TR/2004/REC-owl-guide-20040210/#OntologyVersioning">OWL</a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td>Provides a hook suitable for use by versioning systems.</td>
    </tr>
    <tr>
      <td><em>vann:preferredNamespacePrefix</em></td>
      <td><a href="http://vocab.org/vann">VANN</a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td></td><td></td><td></td>
      <td>The preferred namespace prefix when using entities of a ontology.</td>
    </tr>
    <tr>
      <td><em>vann:preferredNamespaceUri</em></td>
      <td><a href="http://vocab.org/vann">VANN</a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td></td><td></td><td></td>
      <td>The preferred namespace URI to use when using terms from a ontology.</td>
    </tr>
    </tbody>
</table>


<h2>Metadata for Rules</h2>

<p>This table describes the annotates properties relevant for adding metadata
to <strong>rules</strong>. Notice that not all the properties are currently
supported by Parrot.</p>

<p></p>

<table id="metadata-support-rules">
  <tbody>
    <tr>
      <th>Property</th>
      <th>Vocabulary</th>
      <th colspan="2">Scope</th>
      <th>Description</th>
    </tr>
    <tr>
      <th></th>
      <th></th>
      <th>Rule Set</th>
      <th>Rule</th>
      <th></th>
    </tr>
    <tr>
      <td><em>dc:contributor</em></td>
      <td><a href="http://dublincore.org/documents/dces/">Dublin Core Elements</a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td>A contributor to the resource: a person or an organization. Recommended
        best practice is to use a FOAF profile to describe a contributor,
        although a literal is also allowed.</td>
    </tr>
    <tr>
      <td><em>dc:creator</em></td>
      <td><a href="http://dublincore.org/documents/dces/">Dublin Core Elements</a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td>The creator of the resource: a person or an organization. Recommended
        best practice is to use a FOAF profile to describe the creator,
        although a literal is also allowed.</td>
    </tr>
    <tr>
      <td><em>dc:date</em></td>
      <td><a href="http://dublincore.org/documents/dces/">Dublin Core Elements</a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td>Expresses the date of creation or publication of the resource.
        Recommended best practice is to use the W3CDTF profile of ISO 8601.</td>
    </tr>
    <tr>
      <td><em>dc:description</em></td>
      <td><a href="http://dublincore.org/documents/dces/">Dublin Core Elements</a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td>See <em>comment</em></td>
    </tr>  
    <tr>
      <td><em>dc:publisher</em></td>
      <td><a href="http://dublincore.org/documents/dces/">Dublin Core Elements</a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td>The entity responsible for making the resource available. Recommended
        best practice is to use a FOAF profile to describe the publisher,
        although a literal is also allowed.</td>
    </tr>
    <tr>
      <td><em>dc:rights</em></td>
      <td><a href="http://dublincore.org/documents/dces/">Dublin Core Elements</a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td>Information about rights held in and over the resource.</td>
    </tr>    
    <tr>
      <td><em>dc:title</em></td>
      <td><a href="http://dublincore.org/documents/dces/">Dublin Core Elements</a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td>See <em>label</em></td>
    </tr>
    <tr>
      <td><em>dct:description</em></td>
	  <td><a href="http://dublincore.org/documents/dcmi-terms/">Dublin Core Terms</a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td>See <em>comment</em></td>
    </tr>
    <tr>
      <td><em>dct:license</em></td>
      <td><a href="http://dublincore.org/documents/dcmi-terms/">Dublin Core Terms</a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td>A legal document describing the copyright license of the resource.
        Recommended best practice is to use Creative Commons licenses and to
        describe them in RDF with the Creative Commons Rights Expression
        Language (CC REL). </td>
    </tr>     
    <tr>
      <td><em>dct:source</em></td>
	  <td><a href="http://dublincore.org/documents/dcmi-terms/">Dublin Core Terms</a></td>
      <td></td><td><img alt="supported" src="images/tick.png"/></td>
      <td>The resources from which the rules are derived. Typically, they are documentary sources (as candidate rules), but diagrams, plans or pictures are also possible.</td>
    </tr>    
    <tr>
      <td><em>rdfs:label</em></td>
      <td><a href="http://www.w3.org/TR/rdf-schema/">RDF Schema</a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td>The label of the resource. The range is a literal with a language
      tag.</td>
    </tr>
    <tr>
      <td><em>rdfs:comment</em></td>
      <td><a href="http://www.w3.org/TR/rdf-schema/">RDF Schema</a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td>The description of the resource. The range is a literal with a
        language tag.</td>
    </tr>
    <tr>
      <td><em>skos:prefLabel</em></td>
      <td><a href="http://www.w3.org/TR/skos-reference/#overview">SKOS</a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td>The preferred label of a resource. The range is a literal with a
        language tag. In some cases, it is assumed that <em>prefLabel</em>
        assumes the role of the <em>label</em> for presentation purposes.</td>
    </tr>
    <tr>
      <td><em>skos:altLabel</em></td>
      <td><a href="http://www.w3.org/TR/skos-reference/#overview">SKOS</a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td>An alternative label of a resource. The range is a literal with a
        language tag.</td>
    </tr>
    <tr>
      <td><em>skosxl:prefLabel</em></td>
      <td><a href="http://www.w3.org/TR/skos-reference/#xl">SKOS-XL</a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td>The preferred label of a resource. The range of the property is an
        instance of the class LexicalLabel.</td>
    </tr>
    <tr>
      <td><em>skosxl:altLabel*</em></td>
      <td><a href="http://www.w3.org/TR/skos-reference/#xl">SKOS-XL</a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td>An alternative label of a resource. The range of the property is an
        instance of the class LexicalLabel.</td>
    </tr>
    <tr>
      <td><em>foaf:depiction</em></td>
      <td><a href="http://xmlns.com/foaf/spec/">FOAF</a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td>An image associated with the resource. </td>
    </tr>
    <tr>
      <td><em>og:video</em></td>
      <td><a href="http://ogp.me">OG</a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td>A video associated with the resource. </td>
    </tr>
    <tr>
      <td><em>owl:versionInfo</em></td>
      <td><a
        href="http://www.w3.org/TR/2004/REC-owl-guide-20040210/#OntologyVersioning">OWL</a></td>
      <td><img alt="supported" src="images/tick.png"/></td><td><img alt="supported" src="images/tick.png"/></td>
      <td>Provides a hook suitable for use by versioning systems.</td>
    </tr>

  </tbody>
</table>


<h2>Namespace references and prefixes</h2>
<table id="prefix-legend">
  <tbody>
    <tr>
      <th>Prefix</th>
      <th>Namespace</th>
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
      <td>dc</td>
      <td>http://purl.org/dc/elements/1.1/</td>
    </tr>
    <tr>
      <td>dct</td>
      <td>http://purl.org/dc/terms/</td>
    </tr>
    <tr>
      <td>rdfs</td>
      <td>http://www.w3.org/2000/01/rdf-schema#</td>
    </tr>
    <tr>
      <td>owl</td>
      <td>http://www.w3.org/2002/07/owl#</td>
    </tr>
    <tr>
      <td>vann</td>
      <td>http://purl.org/vocab/vann/</td>
    </tr>
    <tr>
      <td>foaf</td>
      <td>http://xmlns.com/foaf/0.1/</td>
    </tr>
    <tr>
      <td>og</td>
      <td>http://ogp.me/ns#</td>
    </tr>
  </tbody>
</table>

<h2>Icons in Parrot</h2>

<table id="legend">
  <tbody>
    <tr>
      <th>Icon</th>

      <th>Definition</th>
    </tr>
    <tr>
      <td><img alt="data property"
        src="images/datatype-property-24x24.png" width="24" height="24" /></td>
      <td>A <a href="http://www.w3.org/TR/owl2-syntax#Data_Properties" target="_blank">data property</a> is used to describe attributes of resources, such as the height of a person or the population of a country.</td>
    </tr>
    <tr>
      <td><img alt="object property"
        src="images/object-property-24x24.png" width="24" height="24" /></td>
      <td>An <a href="http://www.w3.org/TR/owl2-syntax#Object_Properties" target="_blank">object property</a> is used to describe relations to other resources, such as the mother of a person or the capital of a country.</td>
    </tr>

    <tr>
      <td><img alt="annotation property"
        src="images/annotation-property-24x24.png" width="24" height="24" /></td>
      <td>An <a href="http://www.w3.org/TR/owl2-syntax#Annotation_Properties" target="_blank">annotation property</a> is used to give more information of resources.</td>
    </tr>

    <tr>
      <td><img alt="reflexive object property"
        src="images/reflexive-property.png" width="24" height="24" /></td>
      <td>A <a href="http://www.w3.org/TR/owl2-syntax/#Reflexive_Object_Properties" target="_blank">reflexive property</a> describes a relation where every resource is related to itself.</td>
    </tr>

    <tr>
      <td><img alt="irreflexive object property"
        src="images/irreflexive-property.png" width="26" height="24" /></td>
      <td>A <a href="http://www.w3.org/TR/owl2-syntax/#Irreflexive_Object_Properties" target="_blank">irreflexive property</a> describes a relation where none resource is related to itself.</td>
    </tr>

    <tr>
      <td><img alt="symmetric object property"
        src="images/symmetric-property.png" width="45" height="24" /></td>
      <td><a href="http://www.w3.org/TR/owl2-syntax/#Symmetric_Object_Properties" target="_blank">Symmetric property</a></td>
    </tr>

    <tr>
      <td><img alt="asymmetric object property"
        src="images/asymmetric-property.png" width="40" height="24" /></td>
      <td><a href="http://www.w3.org/TR/owl2-syntax/#Asymmetric_Object_Properties" target="_blank">Asymmetric property</a></td>
    </tr>

    <tr>
     <td><img alt="transitive object property" src="images/transitive-property.png" width="61" height="24" /></td>
      <td><a href="http://www.w3.org/TR/owl2-syntax/#Transitive_Object_Properties" target="_blank">Transitive property</a></td>
    </tr>
    
    <tr>
     <td><img alt="functional object property" src="images/functional-property.png" width="33" height="24" /></td>
      <td><a href="http://www.w3.org/TR/owl2-syntax/#Functional_Object_Properties" target="_blank">Functional property</a></td>
    </tr>
    
    <tr>
     <td><img alt="inverse functional object property" src="images/inverse-functional-property.png" width="33" height="24" /></td>
      <td><a href="http://www.w3.org/TR/owl2-syntax/#Inverse-Functional_Object_Properties" target="_blank">Inverse functional property</a></td>
    </tr>

  </tbody>
</table>

<h2>Tips</h2>
<h3>Description order</h3>
<p>If you want to add a <strong>description</strong> to an element, the preferred property order to set it is:</p>
<ol>
  <li>http://purl.org/dc/terms/description</li>
  <li>http://purl.org/dc/elements/1.1/description</li>
  <li>http://www.w3.org/2000/01/rdf-schema#comment</li>
</ol>

<h3>Label order</h3>
<p>If you want to add a <strong>label</strong> to an element, the preferred property order to set it is:</p>
<ol>
  <li>http://www.w3.org/2008/05/skos-xl#prefLabel</li>
  <li>http://www.w3.org/2008/05/skos-xl#altLabel</li>
  <li>http://www.w3.org/2004/02/skos/core#prefLabel</li>
  <li>http://www.w3.org/2004/02/skos/core#altLabel</li>
  <li>http://purl.org/dc/elements/1.1/title</li>
  <li>http://www.w3.org/2000/01/rdf-schema#label</li>
</ol>


  <h3>Button RDFa</h3>
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
   

<div id="footer">
<p id="logo"><a href="http://www.fundacionctic.org/"><img src="images/ctic.png"
alt="Fundacion CTIC"/></a> <a href="http://ontorule-project.eu"><img
src="images/ontorule.png" alt="ONTORULE Project"/></a> </p>

<p><a href="http://ontorule-project.eu/parrot">Parrot</a> is a RIF and OWL
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
