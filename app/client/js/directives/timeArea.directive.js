TimeTrackerApplication
    .directive('timeArea', TimeAreaDirective);

function TimeAreaDirective() {
    return {
        restrict: "E",
        templateUrl: '../../templates/employee-page/time-area.html',
        controller: TimeAreaDirectiveController
    }
}

function TimeAreaDirectiveController($scope) {

}

TimeAreaDirectiveController.$inject = ["$scope"];