let TimeTrackerApplication = angular.module('time-tracker', [
        'ngRoute'
    ])
    .config(setApplicationConfig);

function setApplicationConfig($routeProvider) {
    $routeProvider
        .when('/', {
            controller: 'InitialController',
            templateUrl: '../templates/application-body.html'
        });
}