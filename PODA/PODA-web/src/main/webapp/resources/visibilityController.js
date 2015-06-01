/**Setea la visibilidad para todos los elementos con cierta clase
 * String ClassName: Nombre de la clase con la cual buscar 
 * String option: puede ser "visible", "hidden" u otros acorde a style.visibility
 */ 
function SetVisibilityByClassName(ClassName, option){
  var list= document.getElementsByClassName(ClassName);
  for (var i = 0; i < list.length; i++) {
    list[i].style.visibility = option;
  }
}

function SectionBecameCurrent(ClassName, direction){
	if(direction == "forward"){
		SetVisibilityByClassName(ClassName, "visible");
	}
	if(direction == "reverse"){
		SetVisibilityByClassName(ClassName, "visible");
	}
}

function SectionLostCurrent(ClassName, direction){
	if(direction == "forward"){
		//nothing
	}
	if(direction == "reverse"){
		SetVisibilityByClassName(ClassName, "hidden");
	}
}