function play(idaudio){
  var audio = document.getElementById(idaudio);
  audio.play();
}

function pause(idaudio){
  var audio = document.getElementById(idaudio);
  audio.pause();
  audio.currentTime = 0;
}

function AudioBecameCurrent(idaudio){
  console.log(idaudio + " becameCurrent");
  pause(idaudio);
  play(idaudio);
}

function AudioLostCurrent(idaudio){
  console.log(idaudio + " lostCurrent");
  pause(idaudio);
}