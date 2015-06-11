function ShowIframe(IDinsert, IDremove,IDremove2, url){
	console.log("ShowIframe called");
	var linea1 = "<iframe src=\"".concat(url).concat("\" width=\"100%\" height=\"700\">");
	linea1 = linea1.concat("<p> Tu navegador no soporta iframes </p>");
	linea1 = linea1.concat("</iframe>");

	$(IDinsert).append(linea1);
	$(IDremove2).html("");
	$(IDremove).html("");
	//var myNode = $(IDremove);
	//while (myNode.firstChild) {
    //	myNode.removeChild(myNode.firstChild);
	//}
}
