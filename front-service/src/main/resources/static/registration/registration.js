angular.module('market').controller('registrationController', function ($scope, $http, $location) {

    $scope.tryToRegistration = function () {
        if($scope.newUser.password == $scope.newUser.confirmPassword){
            $http.post('http://localhost:5555/auth/registration', $scope.newUser)
                .then(function successCallback(response) {
                    // alert("Регистрация пользователя"+response.value+"прошла успешно")
                    $location.path('/');
                });
        } else {
            alert("Пароли не соответствуют друг другу")
        }

    };

});
