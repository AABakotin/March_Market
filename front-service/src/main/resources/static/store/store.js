angular.module('market').controller('storeController', function ($scope, $http, $localStorage){

const contextPathCart = 'http://localhost:5555/cart/api/v1/cart/' + $localStorage.marchMarketGuestCartId;
const contextPath = 'http://localhost:5555/core/api/v1/';

    $scope.fillTable = function (page) {
        $http({
            url: 'http://localhost:5555/core/api/v1/products',
            method: 'GET',
            params: {
                page: page,
                title_part: $scope.filter ? $scope.filter.title_part : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null,
                page_size: $scope.filter ? $scope.filter.page_size : null,
            }
        }).then(function (response) {
            $scope.productsPage = response.data;
            $scope.generatePagesList($scope.productsPage.totalPages);
            console.log($scope.productsPage);
        });
    };

    $scope.addProductToCart = function (id) {
        $http.get(contextPathCart + '/add/' + id)
            .then(function (response) {
            });
    };

    $scope.generatePagesList = function (totalPages) {
            out = [];
            for (let i = 0; i < totalPages; i++) {
                out.push(i + 1);
            }
            $scope.pagesList = out;
        }
        $scope.fillTable();

});