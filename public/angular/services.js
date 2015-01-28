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

    connection.listeners = []
    connection.register = function(callback) {
        this.listeners.push(callback);
    };

    connection.onmessage = function(message) {
        console.log('Received message: ' + message);
        for(listener in this.listeners) {
            listener(message);
        }
    };

    return connection;
});