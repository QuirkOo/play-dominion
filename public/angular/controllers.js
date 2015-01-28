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
    $scope.cards = [{name: "Copper"}, {name: "Estate"}, {name: "Cellar", desc:"Dupa"}];

    webSocket.register(function(message) {
        var data = JSON.parse(message);
        console.log(data);
        $scope.actions = data.actions;
        $scope.buys = data.buys;
        $scope.gold = data.gold;
        $scope.rejects = data.rejects;
        $scope.throws = data.throws;
        $scope.cards = data.hand;

        $scope.$apply();
    });
}]);

dominionApp.controller('TableCtrl', [ '$scope', 'webSocket', function($scope, webSocket) {

    $scope.cards = [{name: "Cellar", desc: "Buy something", cost: 2}, {name:"A", desc:"Dupa", cost:3},{name:"A", desc:"Dupa", cost:3},{name:"A", desc:"Dupa", cost:3},{name:"A", desc:"Dupa", cost:3},{name:"A", desc:"Dupa", cost:3},{name:"A", desc:"Dupa", cost:3}];

    webSocket.register(function(message) {
       var data = JSON.parse(message);
        $scope.cards = data.table;

        $scope.$apply();
    });
}]);