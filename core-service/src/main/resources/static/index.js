angular.module('market', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {

 const contextPath = 'http://localhost:8189/market-core/api/v1';
 const contextPathAuth = 'http://localhost:8189/market-core/auth';
 const contextPathCart = 'http://localhost:8190/market-cart/api/v1/cart';

   if ($localStorage.marchMarketUser) {
        try {
            let jwt = $localStorage.marchMarketUser.token;
            let payload = JSON.parse(atob(jwt.split('.')[1]));
            let currentTime = parseInt(new Date().getTime() / 1000);
            if (currentTime > payload.exp) {
                console.log("Token is expired!!!");
                delete $localStorage.marchMarketUser;
                $http.defaults.headers.common.Authorization = '';
            }
        } catch (e) {
        }

        if ($localStorage.marchMarketUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.marchMarketUser.token;
        }
    }


    $scope.totalPrice = function () {
        $http.get(contextPathCart + '/total/')
            .then(function (response) {
                $scope.total = response.data;
                console.log (response);

            });
    };

    $scope.fillTableCart = function () {
        $http.get(contextPathCart)
            .then(function (response) {
                $scope.cart = response.data.items;
                $scope.totalPrice();
                // console.log(response);
            });
    };

    $scope.addProductToCart = function (id) {
        $http.get(contextPathCart + '/add/' + id)
            .then(function (response) {
                $scope.fillTableCart();
                    $scope.totalPrice();
            });
    };

    $scope.deleteProductFromCart = function (id) {
        $http.get(contextPathCart + '/delete_item/' + id)
            .then(function (response) {
                $scope.fillTableCart();
                    $scope.totalPrice();
            });
    };

    $scope.deleteOneProductFromCart = function (id) {
        $http.get(contextPathCart + '/delete/' + id)
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
        $http.get(contextPathCart + '/clear')
             .then(function (response) {
                $scope.totalPrice();
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
        $http.post(contextPathAuth, $scope.user)
            .then(function successCallback(response) {
            if (response.data.token) {
            $http.defaults.headers.common.Authorization  = 'Bearer ' + response.data.token;
            $localStorage.marchMarketUser = {username: $scope.user.username, token: response.data.token};
            $scope.user.username = null;
            $scope.user.password = null;

            }
        }, function errorCallback(response) {
      });
    };


   $scope.tryToLogout = function () {
        $scope.clearUser();
    };


    $scope.createOrder = function () {
            $http.post(contextPath + '/order')
                .then(function (response) {
                console.log(response);
                $scope.clear();
                });
        };



    $scope.getOrders = function () {
            $http.get(contextPath + '/order')
                .then(function (response) {
                $scope.orderItems = response.data;
                console.log(response);


                });
        };


        $scope.clearUser = function () {
            delete $localStorage.marchMarketUser;
            $http.defaults.headers.common.Authorization = '';
        };

            $scope.isUserLoggedIn = function () {
                if ($localStorage.marchMarketUser) {
                    return true;
                } else {
                    return false;
                }
            };





    $scope.fillTable();
    $scope.fillTableCart();
});