jQuery(document).ready(function(){
	$('#header').corner();
	$('.toc').corner();
    $('.details').corner();
    $('.it').corner();
    $('.vci').corner();
    $('.otherinfo').corner();
    
    $('.term h3').click(function() {
	    $(this).nextAll("div").toggle('slow');
	    return false;
    }).nextAll("div").hide();

    $('.term div h4').click(function() {
	    $(this).next().toggle('slow');
	    return false;
    }).next().hide();
    
    $("#bottom-bar").jixedbar({
        transparent: true,
        opacity: 0.5,
        slideSpeed: "slow",
        roundedCorners: true,
        roundedButtons: true,
        menuFadeSpeed: "slow",
        tooltipFadeSpeed: "fast",
        tooltipFadeOpacity: 0.5
    });
    
    $("#openCloseAll").toggle(function() {
        $(".term div:hidden").prevAll("h3").click();
        $(".term div div dl:hidden").prevAll("h4").click();
      }, function() {
        $(".term div:visible").prevAll("h3").click();
        $(".term div div dl:visible").prevAll("h4").click();
      });
    
    
})
