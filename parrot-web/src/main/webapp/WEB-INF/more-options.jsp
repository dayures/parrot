<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fieldset class="moreoptions">
	<legend class="extra_opt_uri more-options-closed">More Options</legend>
	<div class="options">
		<c:choose>  
			<c:when test='${empty param.profile}'>
				<input type="radio" name="profile" value="business" /><label>Show business report</label> <input type="radio" name="profile" value="technical" checked="checked" /><label>Show technical report</label>
			</c:when>
			<c:otherwise>
				<input type="radio" name="profile" value="business" <c:if test="${param.profile eq 'business'}">checked="checked"</c:if> /><label>Show business report</label> <input type="radio" name="profile" value="technical" <c:if test="${param.profile eq 'technical'}">checked="checked"</c:if> /><label>Show technical report</label>
			</c:otherwise>
		</c:choose>
	</div>
	<jsp:include page="select-language.jsp"/>
	<div>
		<c:choose>  
			<c:when test='${empty param.customizeCssUrl}'>
				<label title="Customize CSS URL">Customize CSS URL</label><input name="customizeCssUrl" value="" type="text" size="100" />
			</c:when>
			<c:otherwise>
				<label title="Customize CSS URL">Customize CSS URL</label><input name="customizeCssUrl" value="${param.customizeCssUrl}" type="text" size="100" />
			</c:otherwise>
		</c:choose>
		
	</div>
	<hr />
</fieldset>					
