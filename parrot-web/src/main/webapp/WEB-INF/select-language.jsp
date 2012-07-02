<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="language">
	<label>Report language: </label> <select name="language">
		<c:choose>  
			<c:when test='${empty param.language}'>
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
			</c:when>
			<c:otherwise>
				<option value="bg" <c:if test="${param.language eq 'bg'}">selected="selected"</c:if> >български</option>
				<option value="cs" <c:if test="${param.language eq 'cs'}">selected="selected"</c:if> >čeština</option>
				<option value="da" <c:if test="${param.language eq 'da'}">selected="selected"</c:if> >dansk</option>
				<option value="de" <c:if test="${param.language eq 'de'}">selected="selected"</c:if> >Deutsch</option>
				<option value="et" <c:if test="${param.language eq 'et'}">selected="selected"</c:if> >eesti</option>
				<option value="el" <c:if test="${param.language eq 'el'}">selected="selected"</c:if> >ελληνικά</option>
				<option value="en" <c:if test="${param.language eq 'en'}">selected="selected"</c:if> >English</option>
				<option value="es" <c:if test="${param.language eq 'es'}">selected="selected"</c:if> >español</option>
				<option value="fr" <c:if test="${param.language eq 'fr'}">selected="selected"</c:if> >français</option>
				<option value="ga" <c:if test="${param.language eq 'ga'}">selected="selected"</c:if> >Gaeilge</option>
				<option value="it" <c:if test="${param.language eq 'it'}">selected="selected"</c:if> >italiano</option>
				<option value="lv" <c:if test="${param.language eq 'lv'}">selected="selected"</c:if> >latviešu</option>
				<option value="lt" <c:if test="${param.language eq 'lt'}">selected="selected"</c:if> >lietuvių</option>
				<option value="hu" <c:if test="${param.language eq 'hu'}">selected="selected"</c:if> >magyar</option>
				<option value="mt" <c:if test="${param.language eq 'mt'}">selected="selected"</c:if> >Malti</option>
				<option value="nl" <c:if test="${param.language eq 'nl'}">selected="selected"</c:if> >Nederlands</option>
				<option value="pl" <c:if test="${param.language eq 'pl'}">selected="selected"</c:if> >polski</option>
				<option value="pt" <c:if test="${param.language eq 'pt'}">selected="selected"</c:if> >português</option>
				<option value="ro" <c:if test="${param.language eq 'ro'}">selected="selected"</c:if> >română</option>
				<option value="sk" <c:if test="${param.language eq 'sk'}">selected="selected"</c:if> >slovenčina</option>
				<option value="sl" <c:if test="${param.language eq 'sl'}">selected="selected"</c:if> >slovenščina</option>
				<option value="fi" <c:if test="${param.language eq 'fi'}">selected="selected"</c:if> >suomi</option>
				<option value="sv" <c:if test="${param.language eq 'sv'}">selected="selected"</c:if> >svenska</option>
			</c:otherwise>
		</c:choose>
	</select>
</div>