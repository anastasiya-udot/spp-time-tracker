TimeTrackerApplication
    .directive('timeArea', TimeAreaDirective);

function TimeAreaDirective() {
    return {
        restrict: "E",
        templateUrl: '../../../public/templates/employee-page/time-area.html',
        controller: TimeAreaDirectiveController
    }
}

function TimeAreaDirectiveController($scope, StartStopService) {
    $scope.controlButtonsDisabled = false;

    $scope.startClick = function() {
        $scope.controlButtonsDisabled = true;

        StartStopService.startSession(function() {
            $scope.controlButtonsDisabled = false;
        });
    };

    $scope.stopClick = function() {
        $scope.controlButtonsDisabled = true;

        StartStopService.stopSession(function() {
            $scope.controlButtonsDisabled = false;
        });
    };
}

TimeAreaDirectiveController.$inject = ["$scope", 'StartStopService'];