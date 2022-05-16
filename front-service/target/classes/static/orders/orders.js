angular.module('market').controller('ordersController', function ($scope, $http){

const contextPath = 'http://localhost:5555/core/api/v1';

    $scope.getOrders = function () {
            $http.get(contextPath + '/order')
                .then(function (response) {
                $scope.orders = response.data;
                console.log(response);
                  });
        };

$scope.getOrders();


});
