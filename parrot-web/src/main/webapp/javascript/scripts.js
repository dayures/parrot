jQuery(document).ready(function(){

	$('#tabs').tabs();
	
	$('#feedback').feedback({
		'position': 'left',
		'mouseoffColor': '#8470ff'
	});
	
    $('#feedback-dialog').dialog({
        autoOpen: false,
		modal: true,
		resizable: false,
		draggable: false
	});
    
    $('#feedback').click(function() {
        $('#feedback-dialog').dialog('open');
        $('#feedback-dialog a').blur(); // remove manually the focus on a elements
		return false;
    });
	
	$("button.generate").button({
        icons: {
            primary: "ui-icon-check"
        }
    })

    $('.extra_opt_uri').click(function() {
    	if ($(this).attr("class") == "more-options-open")
            $(this).attr("class", "extra_opt_uri more-options-closed");
         else
            $(this).attr("class", "extra_opt_uri more-options-open");
    	
	    $(this).nextAll('div').toggle();
	    return false;
    }).nextAll('div').hide();

    $('#tipOfTheDay').corner();
    $('#tipOfTheDay').randomContent({xmlPath: "tipOfTheDay.xml", nodeName: "tip"});
    
    $('#addURI').button({
        icons: {
            primary: "ui-icon-plusthick"
        }}).click(function(){ 
            $('#tabs-1 form p:first.uri-input').clone(true).insertBefore($(this));
            $('#tabs-1 form p:last.uri-input input').val(''); // clear the textarea
            $('#tabs-1 form p:last.uri-input span.uriHint').remove();
    	});
    
    $('.removeURI').click(function() {
    	if ($(this).parent().siblings('.uri-input').length > 0) { // not remove all inputs
    		$(this).parent().remove();
    	}
    	
    });
    
    $('#example').click(function() {
    	$('#documentUri').val($('#example').html()); 
    });
    
    $('#example-report').click(function() {
    	$('#reportURL').val($('#example-report').html()); 
    });
    
    $('#addText').button({
        icons: {
            primary: "ui-icon-plusthick"
        }
    }).click(function() {
        $('#tabs-2 form div:first.direct-input').clone(true).insertBefore($(this));
        $('#tabs-2 form div:last.direct-input textarea').val(''); // clear the textarea
    	return false;
    });
    
    $('.removeText').click(function() {
    	if ($(this).parent().parent().siblings('.direct-input').length > 0) { // not remove all textareas
    		$(this).parent().parent().remove();
    	}
    });
    
    $('#addFile').button({
        icons: {
            primary: "ui-icon-plusthick"
        }
    }).click(function() {
        $('#tabs-3 form p:first').clone().insertBefore($(this));
        $('#tabs-3 form p:last').html($('#tabs-3 form p:last').html()); // clear input file
    	return false;
    });
    
    $('#documentUri, #reportURL').focus(function(){
	   if (this.value=='http://') { 
		   this.value=''; 
	   }	   
    }).blur(function(){
 	   if (this.value=='') { 
 		   this.value='http://'; 
 	   }	   
    });

    $('#generating-dialog').dialog({
        autoOpen: false,
		modal: true,
		resizable: false,
		draggable: false
	});
    
    // disable submit button after submit (prevent duplicate requests)
    $("form").submit(function() {
        $(":submit", this).attr("disabled", "disabled");
        $('#generating-dialog').dialog('open');
    });

    $('#header').corner();
    $('.inputData').corner();

    $('#video').corner();
    
});
