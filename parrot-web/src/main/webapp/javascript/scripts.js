jQuery(document).ready(function(){
	$( "#tabs" ).tabs();

    $('#extra_opt_uri').click(function() {
    	if ($(this).attr("class") == "more-options-open")
            $(this).attr("class", "more-options-closed");
         else
            $(this).attr("class", "more-options-open");
    	
	    $(this).nextAll('div').toggle();
	    return false;
    }).nextAll('div').hide();

    $('#tipOfTheDay').corner();
    $('#tipOfTheDay').randomContent({xmlPath: "tipOfTheDay.xml", nodeName: "tip"});
    
    $('#addFile').button({
        icons: {
            primary: "ui-icon-circle-plus"
        }
    }).click(function() {
        $('#tabs-3 form p:first').clone().insertBefore($(this));
        $('#tabs-3 form p:last').html($('#tabs-3 form p:last').html()); // clear input file
    	return false;
    }); 

    $('#addURI').button({
        icons: {
            primary: "ui-icon-circle-plus"
        }}).click(function(){ 
    		var p = document.createElement("p");

    		var lbl = document.createElement("label");
    		lbl.setAttribute("title", "URL of the page containing the OWL/RIF document");
    		lbl.setAttribute("for", "documentUri");
    		lbl.setAttribute("class", "uri");
    		text = document.createTextNode("URI: ");
    		lbl.appendChild(text);
    		p.appendChild(lbl);

    		var inp = document.createElement("input");
    		inp.setAttribute("type", "text");
    		inp.setAttribute("size", "100");
    		inp.setAttribute("value", "http://");
    		inp.setAttribute("name", "documentUri");
    		p.appendChild(inp);

    		text = document.createTextNode(" ");
    		p.appendChild(text);

    		var oSelect = document.createElement("select");
    		oSelect.setAttribute("name", "mimetype");

    		var oOption = document.createElement("option");
    		var t0 = document.createTextNode("Allow content negotiation");
    		oOption.setAttribute("value", "default");
    		oOption.setAttribute("selected", "selected");
    		oOption.appendChild(t0);
    		oSelect.appendChild(oOption);

    		oOption = document.createElement("option");
    		var t1 = document.createTextNode("It is a OWL ontology");
    		oOption.setAttribute("value", "application/owl+xml");
    		oOption.appendChild(t1);
    		oSelect.appendChild(oOption);
    	
    		oOption = document.createElement("option");
    		var t2 = document.createTextNode("It is a N3 ontology");
    		oOption.setAttribute("value", "text/n3");
    		oOption.appendChild(t2);
    		oSelect.appendChild(oOption);

    		oOption = document.createElement("option");
    		var t3 = document.createTextNode("It is a XHTML+RDFa document");
    		oOption.setAttribute("value", "application/xhtml+xml");
    		oOption.appendChild(t3);
    		oSelect.appendChild(oOption);

    		oOption = document.createElement("option");
    		var t4 = document.createTextNode("It is a HTML+RDFa document");
    		oOption.setAttribute("value", "text/html");
    		oOption.appendChild(t4);
    		oSelect.appendChild(oOption);
    		
    		oOption = document.createElement("option");
    		var t5 = document.createTextNode("It is a RIF XML document");
    		oOption.setAttribute("value", "application/rif+xml");
    		oOption.appendChild(t5);
    		oSelect.appendChild(oOption);
    		
    		oOption = document.createElement("option");
    		var t6 = document.createTextNode("It is a RIF PS document");
    		oOption.setAttribute("value", "text/x-rif-ps");
    		oOption.appendChild(t6);
    		oSelect.appendChild(oOption);
    		
    		p.appendChild(oSelect);

    		var span = document.createElement("span");
    		span.setAttribute("class", "removeURI");
    		text = document.createTextNode("remove");
    		span.appendChild(text);
    		
    		span.onclick = function() {
    			var parent = this.parentNode;
    			var grandparent = parent.parentNode;
    			grandparent.removeChild(parent);
    		};
    		p.appendChild(span);
    		
    		var br = document.createElement("br");
    		p.appendChild(br);

    		document.getElementById("addURI").parentNode.insertBefore(p, document.getElementById("addURI"));
    	});
});

/*
 From  vapour.sf.net scripts
*/

function example() {
	var node = document.getElementById("example");
	node.onclick = function() { document.getElementById("documentUri").value=this.innerHTML; };
}

function addLoadEvent(func) {
	//by Simon Willison:
	//   http://simon.incutio.com/archive/2004/05/26/addLoadEvent
	var oldonload = window.onload;
	if (typeof window.onload != 'function') {
		window.onload = func;
	}
	else {
		window.onload = function() {
			oldonload();
			func();
		};
	}
}

function addRemoveLink() {
	var nodes = document.getElementsByName("mimetype");

	for (x=0;x<nodes.length;x++){
	    var span = document.createElement("span");
	    span.setAttribute("class", "removeURI");
	    text = document.createTextNode("remove");
	    span.appendChild(text);
		
	    span.onclick = function() {
	    	var parent = this.parentNode;
	    	var grandparent = parent.parentNode;
	    	grandparent.removeChild(parent);
	    };
	    nodes[x].parentNode.insertBefore(span, nodes[x].nextSibling);
	}
}

addLoadEvent(example);
addLoadEvent(addRemoveLink);

