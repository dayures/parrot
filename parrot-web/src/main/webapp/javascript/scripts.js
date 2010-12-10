
/*
 From  vapour.sf.net scripts
*/

function example() {
	var node = document.getElementById("example");
	node.onclick = function() { document.getElementById("documentUri").value=this.innerHTML; };
}

function cleanInputs() {
    var inputs = new Array("documentUri");
    for (var i=0; i<inputs.length; i++) {
        var input = document.getElementById(inputs[i]);
        input.onfocus = function() { if (this.value=='http://') { this.value=''; } };
        input.onblur = function() { if (this.value=='') {this.value='http://'; } };
    }
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

function moreURIs() {
	
	var addURI = document.getElementById("addURILink");
	addURI.onclick = function() { 
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
		inp.setAttribute("style", "width:80%");
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
		var t2 = document.createTextNode("It is a RIF document");
		oOption.setAttribute("value", "application/rif+xml");
		oOption.appendChild(t2);
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

		document.getElementById("tab1").insertBefore(p, document.getElementById("addURI"));
	};
						
/*
nodes.each(
	function(node) {
		node.onclick = function() { alert("inside"); 
			var p = document.createElement("p");
			text = document.createTextNode("add another URI");
			p.appendChild(text);
			document.getElementById("tab1").appendChild(p);
		};
	}
);
*/
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
addLoadEvent(cleanInputs);
addLoadEvent(moreURIs);
addLoadEvent(addRemoveLink);
(function() {
	var tabView = new YAHOO.widget.TabView('demo');
	tabView.selectTab(0);
})();
