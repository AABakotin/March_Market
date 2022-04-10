angular.module('market', []).controller('indexController', function ($scope, $http) {

    $scope.fillTable = function () {
        $http.get('http://localhost:8189/market/api/v1/products')
            .then(function (response) {
                $scope.products = response.data;
                // console.log(response);
            });
    };

     $scope.totalPrice = function () {
        $http.get('http://localhost:8189/market/api/v1/cart/total/')
            .then(function (response) {
                $scope.total = response.data;
                console.log (response);

            });
    };

    $scope.fillTableCart = function () {
        $http.get('http://localhost:8189/market/api/v1/cart/')
            .then(function (response) {
                $scope.cart = response.data;
                $scope.totalPrice();
                // console.log(response);
            });
    };

    $scope.deleteProduct = function (id) {
        $http.delete('http://localhost:8189/market/api/v1/products/' + id)
            .then(function (response) {
                $scope.fillTable();

            });
    }
       $scope.addProductToCart = function (id) {
        $http.get('http://localhost:8189/market/api/v1/cart/add/' + id)
            .then(function (response) {
                $scope.fillTableCart();
                    $scope.totalPrice();
            });
    }
     $scope.deleteProductFromCart = function (id) {
        $http.get('http://localhost:8189/market/api/v1/cart/delete_item/' + id)
            .then(function (response) {
                $scope.fillTableCart();
                    $scope.totalPrice();
            });
    }
      $scope.deleteOneProductFromCart = function (id) {
        $http.get('http://localhost:8189/market/api/v1/cart/delete/' + id)
            .then(function (response) {
                $scope.fillTableCart();
                    $scope.totalPrice();
            });
    }

    $scope.createNewProduct = function () {
        $http.post('http://localhost:8189/market/api/v1/products', $scope.newProduct)
            .then(function (response) {
                $scope.newProduct = null;
                $scope.fillTable();
            });
    }
    $scope.userAuthService = function (){
    $http.post('http://localhost:8189/market/auth', $scope.userAuth)
            .then(function (response) {
            $scope.token = response.data
             console.log(response);
            });
    }

       $scope.userInfoMail = function (){
    $http.get('http://localhost:8189/market/auth/check_email/', $scope.userAuthName)
            .then(function (response) {
            $scope.email = response.data;
             console.log(response);
            });
    }

        $scope.clear = function () {
            $http.get('http://localhost:8189/market/api/v1/cart/clear')
                .then(function (response) {
                    $scope.fillTableCart();
                });
        }


    $scope.fillTable();
    $scope.fillTableCart();
});