angular.module('market', []).controller('indexController', function ($scope, $http) {

    const contextpath = 'http://localhost:8189/market/api/v1'

    $scope.fillTable = function () {
        $http.get(contextpath + '/products')
            .then(function (response) {
                $scope.products = response.data;
                 //console.log(response);
            });
    };

    $scope.deleteProduct = function (id) {
        $http.delete(contextpath + '/products/' + id)
            .then(function (response) {
                $scope.fillTable();
            });
    }

    $scope.createNewProduct = function () {
        $http.post(contextpath + '/products', $scope.newProduct)
            .then(function (response) {
                $scope.newProduct = null;
                $scope.fillTable();
            });
    }
    $scope.loadCart = function () {
        $http.get(contextpath + '/cart').then(function (response) {
            $scope.cartDto = response.data;
        });

    };
    $scope.addProductToCart = function (productId) {
        $http.get(contextpath + '/cart/add/' + productId)
            .then(function () {
                $scope.loadCart();
            })
    };
    $scope.deleteProductFromCart = function (productId) {
        $http.get(contextpath + '/cart/delete/' + productId)
            .then(function () {
                $scope.loadCart();
            })
    };
    $scope.clearCart = function () {
        $http.get(contextpath + '/cart/clear').then(function () {
            $scope.loadCart();
        });

    };
    $scope.subtractOne = function (productId) {
        $http.get(contextpath + '/cart/subtract_one/' + productId)
            .then(function () {
                $scope.loadCart();
            })
    };
    $scope.addOne = function (productId) {
        $http.get(contextpath + '/cart/add_one/' + productId)
            .then(function () {
                $scope.loadCart();
            })
    };

    $scope.fillTable();
    $scope.loadCart();
});