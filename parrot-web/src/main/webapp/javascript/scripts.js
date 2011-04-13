
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
addLoadEvent(addRemoveLink);

