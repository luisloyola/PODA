function showTextArray(array){
  //console.log("showTextArray called");
  var n = array.length;
  if(n!=0){
    var time=0;
    var idTexto = array[0];
    var spanWidth = $(idTexto + ' span').width();
    time=spanWidth*18;

    //remove array[0]
    var new_array = [];
    for(i=1; i<n; i++){
      new_array.push(array[i]);
    }
    //console.log("showTextArray animating");
    $(idTexto).animate({ width: spanWidth}, time, "linear" , function(){showTextArray(new_array)});
  }//else{ /*nothing*/}
  //console.log("showTextArray ending =========================");
}

function hideTextArray(array){
  //console.log("hideTextArray called");
  var n = array.length;
  for(i=0; i<n; i++){//ocultar todo
    $(array[i]).stop();
    $(array[i]).width(0);
  }
}

function textBecameCurrent(array, direction){
  if(direction == "forward"){
    hideTextArray(array);
    showTextArray(array);
  }
  if(direction == "reverse"){
    hideTextArray(array);
    showTextArray(array);
  }
}

function textLostCurrent(array, direction){
  if(direction =="forward"){
    var n = array.length;
    for(i=0; i<n; i++){ //mostrar todo
      $(array[i]).stop();
      var spanWidth = $(array[i] + ' span').width();
      $(array[i]).width(spanWidth);
    }
  }

  if(direction == "reverse"){
    hideTextArray(array);
  }
}
