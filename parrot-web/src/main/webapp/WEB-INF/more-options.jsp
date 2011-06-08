<%@ page pageEncoding="UTF-8"%>
<fieldset class="moreoptions">
	<legend class="extra_opt_uri more-options-closed">More Options</legend>
	<div class="options">
		<input type="radio" name="profile" value="business" checked="checked" /><label>Show business report</label> <input type="radio" name="profile" value="technical" /><label>Show technical report</label>
	</div>
	<jsp:include page="select-language.jsp"/>
	<div>
		<label title="Customize CSS URL">Customize CSS URL</label><input name="customizeCssUrl" value="" type="text" size="100" />
	</div>
	<hr />
</fieldset>					
