function showTextend(end){
  //console.log("showTextend called");
  var n = end.length;
  if(n!=0){
    var time=0;
    var idTexto = end[0];
    var spanWidth = $(idTexto + ' span').width();
    time=spanWidth*18;

    //remove end[0]
    var new_end = [];
    for(i=1; i<n; i++){
      new_end.push(end[i]);
    }
    //console.log("showTextend animating");
    $(idTexto).animate({ width: spanWidth}, time, "linear" , function(){showTextend(new_end)});
  }//else{ /*nothing*/}
  //console.log("showTextend ending =========================");
}

function hideTextend(end){
  //console.log("hideTextend called");
  var n = end.length;
  for(i=0; i<n; i++){//ocultar todo
    $(end[i]).stop();
    $(end[i]).width(0);
  }
}

function textBecameCurrent(end, direction){
  if(direction == "forward"){
    hideTextend(end);
    showTextend(end);
  }
  if(direction == "reverse"){
    hideTextend(end);
    showTextend(end);
  }
}

function textLostCurrent(end, direction){
  if(direction =="forward"){
    var n = end.length;
    for(i=0; i<n; i++){ //mostrar todo
      $(end[i]).stop();
      var spanWidth = $(end[i] + ' span').width();
      $(end[i]).width(spanWidth);
    }
  }

  if(direction == "reverse"){
    hideTextend(end);
  }
}




/* Algoritmo e idea:
  La idea seria poner un tag stop y luego separar con <span> o directamente separar con etiquetas el texto
  entonces la linea 1 quedaria asi en un end
  [texto1] [texto2] [texto3]
 la idea es llamar a la función con todo el arreglo y decirle cual es su ID o un index.

*/

function showSpanend(IdMano,DivID, Spanend, position){

  var n = Spanend.length;
  if(position<n && n!=0){
    var time = 0;
    var divWidthStart = 0;
    var divWidthEnd = 0;
    
    for(i=0; i<=position; i++){

      divWidthEnd = divWidthEnd + $(Spanend[i]).width();
    }

    divWidthStart = divWidthEnd - $(Spanend[position]).width();

    time = $(Spanend[position]).width() *18;

    $(DivID).stop()
    $(DivID).width(divWidthStart);
    $(DivID).animate({ width: divWidthEnd}, time, "linear");


    var dist=0;
    var up=-80;
    var down=-40;
        
    if(divWidthStart != divWidthEnd){
	    hand_down(IdMano,divWidthEnd,divWidthStart-50,dist,up,down);
    }  
      
  }
}
function hand_down(IdMano,end,start,dist,up,down) {

        $(IdMano).show();
          dist=start; 
          if(dist<=end-50){          
            $(IdMano).animate({
              "margin-top": down+"px"
              ,left: dist}, 200, "linear", function () {
                start+=11.8;
                hand_up(IdMano,end,start,dist,up,down);
            });
          
        }else{
          $(IdMano).hide();
        }
      }

function hand_up(IdMano,end,start,dist,up,down) {

         dist=start;
         
          if(dist<=end-50){
            $(IdMano).animate({
              "margin-top": up+"px"
              ,left: dist}, 200, "linear", function () {
                start+=11.8;
                hand_down(IdMano,end,start,dist,up,down);
            });
          }else{
          $(IdMano).hide();
        }
}
function hideSpanend(IdMano, DivID, Spanend, position){
  var n = Spanend.length;
  if(position>0 && position<n && n!=0){

    var time=0;
    var idSpan = Spanend[position];
    var divWidth = 0;
    for(i=0; i<=position-1; i++){
      divWidth = divWidth + $(Spanend[i]).width();
    }
    time= $(Spanend[position]).width() *18;

    $(DivID).width(divWidth);
    
  }else{

    if(position == 0){
      $(DivID).stop();
      $(DivID).width(0);
      $(IdMano).stop();
      $(IdMano).hide();
    }
  }
}

function spanBecameCurrent(IdMano, DivID, Spanend, position, direction){
  if(direction == "forward"){
    $(IdMano).stop();
    showSpanend(IdMano, DivID, Spanend, position);
  }
  if(direction == "reverse"){
    hideSpanend(IdMano, DivID, Spanend, position);
    hideMano(IdMano);
    showSpanend(IdMano, DivID, Spanend, position);
  }
}

function spanLostCurrent(IdMano, DivID, Spanend, position, direction){
  if(direction =="forward"){
    var n = Spanend.length;
    console.log("n="+n+" position=" +position);
    if(position == n-1){
      var DivWidth = 0;
      for(i=0; i<n;i++){
        DivWidth = DivWidth + $(Spanend[i]).width();
      }
      $(DivID).stop();
      $(DivID).width(DivWidth);
      $(IdMano).finish();
    }
  }
  if(direction == "reverse"){
    hideSpanend(IdMano, DivID, Spanend, position);
  }
}
function hideMano(IdMano){
  $(IdMano).stop();
  $(IdMano).animate({left: 0},0);
}