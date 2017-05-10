let TimeTrackerApplication = angular.module('time-tracker', [
        'ngRoute',
        'underscore',
        'ngDropdowns',
        'ngMessages'
    ])
    .config(setApplicationConfig);

function setApplicationConfig($routeProvider) {
    $routeProvider
        .when('/', {
            controller: 'InitialPageController',
            templateUrl: '../templates/front-page/index.html'
        })
        .when('/employee/:id', {
            controller: 'EmployeePageController',
            templateUrl: '../templates/employee-page/index.html'
        });
}