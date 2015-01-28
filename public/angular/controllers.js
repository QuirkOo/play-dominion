/**
 * Created by beatka on 28.01.15.
 */

var dominionApp = angular.module('dominionApp', []);

dominionApp.controller('MainCtrl', [ '$scope', 'webSocket', function($scope, webSocket) {
}]);

dominionApp.controller('CardCtrl', [ '$scope', 'webSocket', function($scope, webSocket) {
    $scope.actions = 1;
    $scope.buys = 1;
    $scope.gold = 0;
    $scope.rejects = 0;
    $scope.throws = 0;
    $scope.cards = [];

    webSocket.register(function(data) {
        $scope.actions = data.actions;
        $scope.buys = data.buys;
        $scope.gold = data.gold;
        $scope.rejects = data.rejects;
        $scope.throws = data.throws;
        $scope.cards = data.cards;
    });
}]);

dominionApp.controller('TableCtrl', [ '$scope', 'webSocket', function($scope, webSocket) {

    $scope.cards = [];

    webSocket.register(function(message) {
       var data = JSON.parse(message);
        $scope.cards = data.table;
    });
}]);