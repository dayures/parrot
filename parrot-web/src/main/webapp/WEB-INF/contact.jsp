<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"> 

<jsp:include page="head.jsp">
	<jsp:param name="title" value="Contact" />
</jsp:include>


<body>

<div class="all">

<jsp:include page="header.jsp" />

<h2>Contact</h2>
<p>PARROT viewer is an on-going work developed by researchers from <a href="http://fundacionctic.org/en">Fundación CTIC</a>.</p> 
<p>Please, contact any of us whether you have any question, suggestion or comment about PARROT.</p>

<h3>Team</h3>
<ul>
<li>Carlos Tejo Alonso (<code>carlos.tejo@fundacionctic.org</code>)</li>
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
