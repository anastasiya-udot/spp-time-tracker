let TimeTrackerApplication = angular.module('time-tracker', [
        'ngRoute',
        'underscore',
        'ngDropdowns',
        'ngMessages',
        'g1b.datetime-range',
        'ui.bootstrap.datetimepicker',
        'ngDialog',
        'DWand.nw-fileDialog',
        'ngFileSaver'

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
        }) 
        .when('/tables/:id', {
            controller: 'TablesPageController',
            templateUrl: '../templates/tables-page/index.html'
        });
}