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
    
    $.fn.qtip.styles.parrot = { 
            border: {
               width:1,
               radius: 5,
            },
            padding: 10, 
            textAlign: 'center',
    		fontSize: '.9em',
//            width: 300,
            tip: true, // Give it a speech bubble tip with automatic corner detection
            name: 'green' // Style it according to the preset 'cream' style
      }

      $.fn.qtip.defaults.position.corner = {
           tooltip: 'bottomMiddle',
           target: 'topMiddle'
      }

      $.fn.qtip.defaults.hide.when.event = 'mouseout';
      $.fn.qtip.defaults.hide.fixed = true;
    
      $('.icon-info-description').qtip({
          content: 'Add a description using http://purl.org/dc/terms/description', // Use the tooltip attribute of the element for the content //
          style: 'parrot'
      });
      
      $('.datatype-property-icon').qtip({
          content: '<a href="http://www.w3.org/TR/owl2-syntax#Data_Properties" target="_blank">Datatype property</a>', // Use the tooltip attribute of the element for the content //
          style: 'parrot'
      });
      
      $('.object-property-icon').qtip({
          content: '<a href="http://www.w3.org/TR/owl2-syntax#Object_Properties" target="_blank">Object property</a>', // Use the tooltip attribute of the element for the content //
          style: 'parrot'
      });
      
      $('.annotation-property-icon').qtip({
          content: '<a href="http://www.w3.org/TR/owl2-syntax#Annotation_Properties" target="_blank">Annotation property</a>', // Use the tooltip attribute of the element for the content //
          style: 'parrot'
      });
      
      $('.reflexive-property-icon').qtip({
          content: '<a href="http://www.w3.org/TR/owl2-syntax/#Reflexive_Object_Properties" target="_blank">Reflexive property</a>', // Use the tooltip attribute of the element for the content //
          style: 'parrot'
      });
      
      $('.irreflexive-property-icon').qtip({
          content: '<a href="http://www.w3.org/TR/owl2-syntax/#Irreflexive_Object_Properties" target="_blank">Irreflexive property</a>', // Use the tooltip attribute of the element for the content //
          style: 'parrot'
      });
      
      $('.symmetric-property-icon').qtip({
          content: '<a href="http://www.w3.org/TR/owl2-syntax/#Symmetric_Object_Properties" target="_blank">Symmetric property</a>', // Use the tooltip attribute of the element for the content //
          style: 'parrot'
      });
      
      $('.asymmetric-property-icon').qtip({
          content: '<a href="http://www.w3.org/TR/owl2-syntax/#Asymmetric_Object_Properties" target="_blank">Asymmetric property</a>', // Use the tooltip attribute of the element for the content //
          style: 'parrot'
      });

})
