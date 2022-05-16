angular.module('market').controller('cartController', function ($scope, $http, $localStorage) {

const contextPathCart = 'http://localhost:5555/cart/api/v1/cart/' + $localStorage.marchMarketGuestCartId;
const contextPath = 'http://localhost:5555/core/api/v1';

    $scope.fillTableCart = function () {
        $http.get(contextPathCart)
            .then(function (response) {
                $scope.cart = response.data;
                console.log(response);
            });
    };

    $scope.addProductToCart = function (productId) {
        $http.get(contextPathCart + '/add/' + productId)
            .then(function (response) {
                $scope.fillTableCart();
            });
    };


    $scope.deleteOneProductFromCart = function (productId) {
        $http.get(contextPathCart + '/remove/' + productId)
           .then(function (response) {
           $scope.fillTableCart();
                });
            };

    $scope.clear = function () {
                $http.get(contextPathCart + '/clear')
                     .then(function (response) {
                     $scope.fillTableCart();
                        });
            };

    $scope.createOrder = function () {
        $http.post(contextPath + '/order', $scope.newOrder)
            .then(function (response) {
             console.log(response);
              alert("Заказ успешно сформирован");
             $scope.clear();
          });
    };

    $scope.guestCreateOrder = function () {
        alert('Для оформления заказа необходимо войти в учетную запись');
    }

 $scope.fillTableCart();

});

