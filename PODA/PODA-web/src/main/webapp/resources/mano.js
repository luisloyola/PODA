function move_down(ManoID, array, num, dist, up,down, atrLeft) {
  var n = array.length;
  if(n!=0){
    var linea = array[0];
    var spanWidth = $(linea+' span').width();
    dist=num;
    if(n==1){
      if(dist<=spanWidth+atrLeft+20 ){
        $(ManoID).animate({
          "margin-top": down+"px"
          ,left: dist}, 200, "linear", function () {
            num+=11.8;
            move_up(ManoID, array, num, dist, up,down, atrLeft);
          });
      }else{
        var new_array = [];
        for(i=1; i<n; i++){
          new_array.push(array[i]);   
        }
        $(ManoID).animate({left: atrLeft},0);
        move_down(ManoID, new_array, atrLeft, dist, up+32, down+32, atrLeft);
      }
    }else{
      if(dist<=spanWidth+atrLeft){
        $(ManoID).animate({
          "margin-top": down+"px"
          ,left: dist}, 200, "linear", function () {
            num+=11.8;
            move_up(ManoID, array, num, dist, up,down, atrLeft);
          });
      }else{
        var new_array = [];
        for(i=1; i<n; i++){
          new_array.push(array[i]);   
        }
        $(ManoID).animate({left: atrLeft},0);
        move_down(ManoID, new_array, atrLeft, dist, up+32, down+32, atrLeft);
      }
    }
  }else{
    $(ManoID).hide();
  }
}

function move_up(ManoID, array, num, dist, up,down, atrLeft) {
  var n = array.length;

  if(n!=0){
    var linea = array[0];
    var spanWidth = $(linea+' span').width();
    dist=num;
    if(n==1){
      if(dist<=spanWidth+atrLeft+20 ){
        $(ManoID).animate({
          "margin-top": up+"px"
          ,left: dist}, 200, "linear", function () {
            num+=11.8;
            move_down(ManoID, array, num, dist, up,down, atrLeft);
          });
      }else{
        var new_array = [];
        for(i=1; i<n; i++){
          new_array.push(array[i]);   
        }
        $(ManoID).animate({left: atrLeft},0);
        move_down(ManoID, new_array, atrLeft, dist, up+32, down+32, atrLeft);
      }
    }else{
      if(dist<=spanWidth+atrLeft){
        $(ManoID).animate({
          "margin-top": up+"px"
          ,left: dist}, 200, "linear", function () {
            num+=11.8;
            move_down(ManoID, array, num, dist, up, down, atrLeft);
          });
      }else{
        var new_array = [];
        for(i=1; i<n; i++){
          new_array.push(array[i]);   
        }
        $(ManoID).animate({left: atrLeft},0);
        move_down(ManoID, new_array, atrLeft, dist, up+32, down+32, atrLeft);
      }
    }
  }else{
    $(ManoID).hide();
  }
}

function manoBecameCurrent(ManoID, array, atrLeft){
  var dist;
  $(ManoID).show();
  var up=-80;
  var down=-40;
  move_down(ManoID, array,atrLeft,dist,up,down, atrLeft);
}

function manoLostCurrent(ManoID, direction, atrLeft){
  if(direction == "reverse"){
    $(ManoID).stop();
    $(ManoID).animate({left: atrLeft},0);
  } else {
    $(ManoID).finish();
    $(ManoID).hide();
  }
}


