/**
 * Created by beatka on 28.01.15.
 */

var dominionApp = angular.module('dominionApp', []);

dominionApp.controller('MainCtrl', [ '$scope', 'webSocket', function($scope, webSocket) {
}]);

dominionApp.controller('CardCtrl', [ '$scope', 'webSocket', 'money', function($scope, webSocket, money) {
    $scope.actions = 0;
    $scope.buys = money.buys;
    $scope.gold = money.funds;
    $scope.rejects = 0;
    $scope.throws = 0;
    $scope.cards = [{name: 'Copper', desc: 'Worth 1', type: 'treasure'}, {name: 'Estate', desc: 'Worth 1 Victory Point', type: 'victory'}, {name: 'Cellar', desc:'Dupa', type: 'action'}];

    webSocket.register(function(message) {
        var data = JSON.parse(message);
        console.log(data);
        $scope.actions = data.actions || 0;
        $scope.rejects = data.rejects || 0;
        $scope.throws = data.throws || 0;
        $scope.cards = data.handZz;

        $scope.$apply();
    });

    $scope.play = function(card) {
        var data = { action: 'act', cardName: card.name };
        console.log(JSON.stringify(data));
        webSocket.send(JSON.stringify(data));
    }

}]);

dominionApp.controller('TableCtrl', [ '$scope', 'webSocket', 'money', function($scope, webSocket, money) {

    $scope.cards = [{name: 'Cellar', desc: 'Buy something', cost: 0}, {name:'A', desc:'Dupa', cost:3},{name:'A', desc:'Dupa', cost:3},{name:'A', desc:'Dupa', cost:3},{name:'A', desc:'Dupa', cost:3},{name:'A', desc:'Dupa', cost:3},{name:'A', desc:'Dupa', cost:3}];
    $scope.funds = money.funds;
    $scope.buys = money.buys;

    webSocket.register(function(message) {
       var data = JSON.parse(message);
        $scope.cards = data.table;

        $scope.$apply();
    });

    $scope.buy = function(card) {
        var data = { action: 'buy', cardName: card.name };
        webSocket.send(JSON.stringify(data));
    };
}]);