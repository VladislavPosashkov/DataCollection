var app = angular.module('app', ['ngRoute'])
    .config(["$routeProvider", function ($routeProvider) {
        return $routeProvider.when("/", {
            redirectTo: "/list"
            //templateUrl: "/views/index"
        }).when("/list", {
            templateUrl: "views/fieldsList",
            controller: "FieldsListController"
        }).when("/create", {
            templateUrl: "/views/fieldCreate",
            controller: "FieldCreateController"
        }).when("/field/:id", {
            templateUrl: "/views/fieldEdit",
            controller: "FieldEditController"
        }).otherwise({
            redirectTo: "/list"
        });
    }
    ]).config([
        "$locationProvider", function ($locationProvider) {
            return $locationProvider.html5Mode(true).hashPrefix("!");
        }
    ]);

app.controller("FieldsListController", ["$scope", "$location", "$http", "$route", function ($scope, $location, $http, $route) {
    $http.get('/api/v1/field').
        success(function (data, status, headers, config) {
            $scope.fields = data;
            console.log(status);
        }).
        error(function (data, status, headers, config) {
            console.log(status);
        });
    $scope.type = types;
    $scope.addField = function () {
        $location.path('/create');
    };
    $scope.delField = function (field) {
        $http.delete('/api/v1/field/' + field.id).success($route.reload());
    }
}]);

app.controller("FieldCreateController", ["$scope", "$location", "$http", function ($scope, $location, $http) {
    $scope.item = {label: null, type: null, required: false, isActive: false};
    $scope.types = types;
    $scope.save = function () {
        if ($scope.item.label == null || $scope.item.type == null) {
            alert("label and type not filled");
            return;
        }
        $http.post('/api/v1/field', $scope.item).
            success(function (data, status, headers, config) {
                $location.path('/list');
            }).
            error(function (data, status, headers, config) {
                $location.path('/list');
                console.warning("Error saving");
            });
    };
}]);

app.controller("FieldEditController", ["$scope", "$routeParams", "$location", "$http", function ($scope, $routeParams, $location, $http) {
    $scope.types = types;
    $scope.item = null;
    $http.get('/api/v1/field/' + $routeParams.id).success(function (data, status, headers, config) {
        if (status > 200) {
            $location.path("/list");
        }
        $scope.item = data;
    }).error(function (data, status, headers, config) {
        console.warning("Error saving");
        $location.path("/list");
    });
    $scope.save = function () {
        $http.put('/api/v1/field/' + $scope.item.id, $scope.item).
            success(function (data, status, headers, config) {
                $location.path('/list');
            }).
            error(function (data, status, headers, config) {
                $location.path('/list');
                console.warning("Error saving");
            });
    }
}]);

app.directive('jsonText', function () {
    return {
        restrict: 'A',
        require: 'ngModel',
        link: function (scope, element, attr, ngModel) {
            function into(input) {
                return JSON.parse(input);
            }

            function out(data) {
                return JSON.stringify(data);
            }

            ngModel.$parsers.push(into);
            ngModel.$formatters.push(out);

        }
    };
});

var types = ['singleLineText', 'textarea', 'radioButton', 'checkBox', 'comboBox', 'date'];