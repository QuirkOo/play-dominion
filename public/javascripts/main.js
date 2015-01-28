
if ('WebSocket' in window){

  var connection = new WebSocket('ws://localhost:9000/game-socket');

  connection.onopen = function() {
    console.log('Connection open');
  }

  connection.onclose = function() {
    console.log('Connection closed');
  }

  connection.onerror = function(e) {
    console.log('Error opening connection: ' + e.message);
  }
}
else {
  console.log('WebSockets not supported');
}