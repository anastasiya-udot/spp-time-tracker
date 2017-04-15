TimeTrackerApplication
    .directive('applicationContent', applicationContentDirective);

function applicationContentDirective() {
    return {
        restrict: 'E',
        templateURL: '../../templates/header.html',
        controller: applicationContentDirectiveController
    }
}

function applicationContentDirectiveController($scope) {
    $scope.name = "Nastya";
}

applicationContentDirectiveController.$inject = ['$scope'];
