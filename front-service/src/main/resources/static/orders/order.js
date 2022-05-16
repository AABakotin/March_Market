angular.module('market').controller('ordersController', function ($scope, $http) {

    $scope.loadOrders = function () {
        $http.get('http://localhost:5555/core/api/v1/order')
            .then(function (response) {
                console.log(response);
                $scope.orders = response.data;
            });
    };
    $scope.loadOrders();
});