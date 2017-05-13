TimeTrackerApplication
    .directive('pieChartWeek', PieChartWeekDirective);

function PieChartWeekDirective() {
    return {
        restrict: 'E',
        templateUrl: '../../templates/employee-page/pie-chart-week.html',
        controller: PieChartWeekDirectiveController
    }
}

function PieChartWeekDirectiveController($scope, DrawTimer, WeekService) {

    $scope.weekDisplay = moment().startOf('week').format('MMMM Do') +
        " - " + moment().endOf('week').format('MMMM Do');

    $(WeekService.elementId).on("circle-animation-end", function() {
        DrawTimer.initActiveCircle(WeekService.elementId, { duration: WeekService.requiredWorktime }, 0.0);
    });

    $scope.prevWeekClicked = function() {
        WeekService.setPreviousWeekDates(function() {});
        $scope.weekDisplay = WeekService.generateDisplay();
        WeekService.drawTimer();
    };
    
    $scope.nextWeekClicked = function() {
        WeekService.setNextWeekDates(function() {});
        $scope.weekDisplay = WeekService.generateDisplay();
        WeekService.drawTimer();
    };
}

PieChartWeekDirectiveController.$inject = ["$scope", "DrawTimer", "WeekService"];