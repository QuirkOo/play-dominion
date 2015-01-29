/**
 * Created by beatka on 28.01.15.
 */

var dominionApp = angular.module('dominionApp');

dominionApp.factory('webSocket', function() {
    var connection = new WebSocket('ws://localhost:9000/game-socket');

    connection.onopen = function () {
        console.log('Connection open');
    };
    connection.onclose = function () {
        console.log('Connection closed');
    };
    connection.onerror = function (e) {
        console.log('Error opening connection: ' + e.message);
    };

    connection.listeners = [];
    connection.register = function(callback) {
        connection.listeners.push(callback);
    };

    connection.onmessage = function(message) {
        console.log(message);
        for(var i = 0; i < connection.listeners.length; i++) {
            connection.listeners[i](message.data);
        }
    };

    return connection;
});

dominionApp.factory('money', [ 'webSocket', function(webSocket) {
    var money = {};
    money.funds = 0;
    money.buys = 1;

    webSocket.register(function(message) {
        var data = JSON.parse(message);
        money.funds = data.gold;
        money.buys = data.buys;
    });

    return money;
}]);