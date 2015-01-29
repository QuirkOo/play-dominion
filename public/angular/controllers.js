/**
 * Created by beatka on 28.01.15.
 */

var dominionApp = angular.module('dominionApp', []);

dominionApp.controller('MainCtrl', [ '$scope', 'webSocket', function($scope, webSocket) {
}]);

dominionApp.controller('CardCtrl', [ '$scope', 'webSocket', 'funds', function($scope, webSocket, funds) {
    $scope.actions = 1;
    $scope.buys = 1;
    $scope.gold = funds;
    $scope.rejects = 0;
    $scope.throws = 0;
    $scope.cards = [{name: 'Copper', desc: 'Worth 1', type: 'treasure'}, {name: 'Estate', desc: 'Worth 1 Victory Point', type: 'victory'}, {name: 'Cellar', desc:'Dupa', type: 'action'}];

    webSocket.register(function(message) {
        var data = JSON.parse(message);
        console.log(data);
        $scope.actions = data.actions;
        $scope.buys = data.buys;
        $scope.rejects = data.rejects;
        $scope.throws = data.throws;
        $scope.cards = data.hand;

        $scope.$apply();
    });

    $scope.play = function(card) {
        var data = { action: 'act', cardName: card.name };
        console.log(JSON.stringify(data));
        webSocket.send(JSON.stringify(data));
    }

}]);

dominionApp.controller('TableCtrl', [ '$scope', 'webSocket', 'funds', function($scope, webSocket, funds) {

    $scope.cards = [{name: 'Cellar', desc: 'Buy something', cost: 2}, {name:'A', desc:'Dupa', cost:3},{name:'A', desc:'Dupa', cost:3},{name:'A', desc:'Dupa', cost:3},{name:'A', desc:'Dupa', cost:3},{name:'A', desc:'Dupa', cost:3},{name:'A', desc:'Dupa', cost:3}];
    $scope.funds = funds;

    webSocket.register(function(message) {
       var data = JSON.parse(message);
        $scope.cards = data.table;

        $scope.$apply();
    });

    $scope.buy = function(card) {
        if (funds < card.cost) return;
        var data = { action: 'buy', cardName: card.name };
        webSocket.send(JSON.stringify(data));
    };
}]);