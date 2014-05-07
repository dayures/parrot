jQuery(document).ready(function(){
    $('#header').corner();
	
    
	var alltips = new Array();
	$.get("tipOfTheDay.xml", {}, function(xml){
		$("tip",xml).each(function(i) {
			alltips.push($(this).text());
		});

		for(i in alltips){
			$('#alltips').append("<p class='tip-compilation'>"+alltips[i]+"</p>")
		}
	});
	
});

