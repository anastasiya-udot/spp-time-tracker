TimeTrackerApplication
    .directive('warningsContainer', WarningsContainerDirective);

function WarningsContainerDirective() {
    return {
        restrict: 'E',
        templateUrl: '../../templates/authorization/warnings.html',
        controller: WarningsContainerDirectiveController
    }
}


function WarningsContainerDirectiveController($scope) {

}

WarningsContainerDirectiveController.$inject = ['$scope'];