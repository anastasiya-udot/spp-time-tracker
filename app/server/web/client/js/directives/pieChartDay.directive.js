TimeTrackerApplication
    .directive('pieChartDay', PieChartDayDirective);

function PieChartDayDirective() {
    return {
        restrict: 'E',
        templateUrl: '../../public/templates/employee-page/pie-chart-day.html',
        controller: PieChartDayDirectiveController
    }
}

function PieChartDayDirectiveController($scope, DrawTimer, DayService) {

    $scope.dayDisplay = moment().format('MMMM Do');

    $(DayService.elementId).on("circle-animation-end", function() {
        DrawTimer.initActiveCircle(DayService.elementId, { duration: DayService.requiredWorktime }, 0.0);
    });

    $scope.prevDayClicked = function() {
        DayService.setPreviousDayDates(function() {});
        $scope.dayDisplay = DayService.generateDisplay();
        DayService.drawTimer();
    };
    
    $scope.nextDayClicked = function() {
        DayService.setNextDayDates(function() {});
        $scope.dayDisplay = DayService.generateDisplay();
        DayService.drawTimer();
    };
}

PieChartDayDirectiveController.$inject = ["$scope", "DrawTimer", "DayService"];