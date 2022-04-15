angular.module('market', []).controller('indexController', function ($scope, $http) {

 const contextPath = 'http://localhost:8189/market/api/v1';
 const contextPathAuth = 'http://localhost:8189/market/auth';

    $scope.totalPrice = function () {
        $http.get(contextPath + '/cart/total/')
            .then(function (response) {
                $scope.total = response.data;
                console.log (response);

            });
    };

    $scope.fillTableCart = function () {
        $http.get(contextPath + '/cart')
            .then(function (response) {
                $scope.cart = response.data;
                $scope.totalPrice();
                // console.log(response);
            });
    };

    $scope.addProductToCart = function (id) {
        $http.get(contextPath + '/cart/add/' + id)
            .then(function (response) {
                $scope.fillTableCart();
                    $scope.totalPrice();
            });
    };

    $scope.deleteProductFromCart = function (id) {
        $http.get(contextPath + '/cart/delete_item/' + id)
            .then(function (response) {
                $scope.fillTableCart();
                    $scope.totalPrice();
            });
    };

    $scope.deleteOneProductFromCart = function (id) {
        $http.get(contextPath + '/cart/delete/' + id)
            .then(function (response) {
                $scope.fillTableCart();
                    $scope.totalPrice();
            });
    };

    $scope.createNewProduct = function () {
        $http.post(contextPath + '/products', $scope.newProduct)
            .then(function (response) {
                $scope.newProduct = null;
                $scope.fillTable();
            });
    };

    $scope.clear = function () {
        $http.get(contextPath + '/cart/clear')
             .then(function (response) {
                $scope.fillTableCart();
                });
    };

    $scope.deleteProduct = function (id) {
        $http.delete(contextPath + '/products/' + id)
             .then(function (response) {
                 $scope.fillTable();

              });
    };

    $scope.fillTable = function () {
        $http.get(contextPath + '/products')
            .then(function (response) {
                $scope.products = response.data;
                // console.log(response);
            });
    };

    $scope.userAuthService = function (){
        $http.post(contextPathAuth , $scope.userAuth)
            .then(function (response) {
            $scope.token = response.data.token
             console.log(response);
            });
    };

    $scope.userInfoMail = function (){
        $http({
        url: contextPathAuth + '/check_email',
        method : 'GET',
        params:
            {
                //Как то надо передавать в шапку Token.
            }
      })
      .then(function (response) {
         $scope.email = response.data;
         console.log(response);
           });
    };

    $scope.fillTable();
    $scope.fillTableCart();
});