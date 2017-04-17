TimeTrackerApplication
    .directive('applicationHeader', ApplicationHeaderDirective);

function ApplicationHeaderDirective() {
    return {
        restrict: 'E',
        templateUrl: '../../templates/header.html',
        controller: applicationHeaderDirectiveController
    }
}

function applicationHeaderDirectiveController($scope) {
   
}

applicationHeaderDirectiveController.$inject = ['$scope'];
