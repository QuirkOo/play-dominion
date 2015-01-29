/**
 * Created by beatka on 28.01.15.
 */

var dominionApp = angular.module('dominionApp', []);

dominionApp.controller('MainCtrl', [ '$scope', 'webSocket', function($scope, webSocket) {
    $scope.actions = 0;
    $scope.buys = 0;
    $scope.gold = 0;
    $scope.rejects = 0;
    $scope.throws = 0;
    $scope.cards = [{name: 'Copper', desc: 'Worth 1', type: 'treasure'}, {name: 'Estate', desc: 'Worth 1 Victory Point', type: 'victory'}, {name: 'Cellar', desc:'Dupa', type: 'action'}];

    webSocket.register(function(message) {
        var data = JSON.parse(message).state;
        console.log(data);
        $scope.actions = data.actions || 0;
        $scope.rejects = data.rejects || 0;
        $scope.throws = data.throws || 0;
        $scope.cards = data.hand;
        $scope.buys = data.buys;
        $scope.gold = data.gold;

        $scope.$apply();
    });

    $scope.play = function(card) {
        var data = { action: 'act', cardName: card.name };
        console.log(JSON.stringify(data));
        webSocket.send(JSON.stringify(data));
    }

    $scope.table = {}
    $scope.table.cards = [{name: 'Cellar', desc: 'Buy something', cost: 0}, {name:'A', desc:'Dupa', cost:3},{name:'A', desc:'Dupa', cost:3},{name:'A', desc:'Dupa', cost:3},{name:'A', desc:'Dupa', cost:3},{name:'A', desc:'Dupa', cost:3},{name:'A', desc:'Dupa', cost:3}];

    webSocket.register(function(message) {
       var data = JSON.parse(message);
        $scope.table.cards = data.table;

        $scope.$apply();
    });

    $scope.buy = function(card) {
        var data = { action: 'buy', cardName: card.name };
        webSocket.send(JSON.stringify(data));
    };
}]);