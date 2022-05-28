angular.module('market').controller('storeController', function ($scope, $http, $localStorage){

const contextPathCart = 'http://localhost:5555/cart/api/v1/cart/' + $localStorage.marchMarketGuestCartId;
const contextPath = 'http://localhost:5555/core/api/v1/';

    $scope.fillTable = function () {
        $http.get(contextPath + 'products')
             .then(function (response) {
                 $scope.products = response.data;
             });
    };

    $scope.addProductToCart = function (id) {
        $http.get(contextPathCart + '/add/' + id)
            .then(function (response) {
            });
    };

//    $scope.deleteProduct = function (id) {
//        $http.delete(contextPath + '/products/' + id)
//             .then(function (response) {
//                $scope.fillTable();
//             });
//    };

//    $scope.createNewProduct = function () {
//        $http.post(contextPath + '/products', $scope.newProduct)
//            .then(function (response) {
//                $scope.newProduct = null;
//                $scope.fillTable();
//            });
//    };

    $scope.fillTable();

});