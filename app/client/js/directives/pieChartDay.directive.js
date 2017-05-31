TimeTrackerApplication
    .directive('pieChartDay', PieChartDayDirective);

function PieChartDayDirective() {
    return {
        restrict: 'E',
        templateUrl: '../../templates/employee-page/pie-chart-day.html',
        controller: PieChartDayDirectiveController
    }
}

function PieChartDayDirectiveController($scope, $rootScope, DrawTimer, DayService) {

    $scope.dayDisplay = moment().format('MMMM Do');
    DayService.setRequiredWorktime(EmployeeService.employee.worktype);

    $(DayService.elementId).on("circle-animation-end", function() {
        DrawTimer.initActiveCircle(DayService.elementId, { duration: DayService.requiredWorktime }, 0.0);
    });

    $scope.prevDayClicked = function() {
        DayService.setPreviousDayDates(function() {
            $scope.dayDisplay = DayService.generateDisplay();
            DayService.drawTimer();
        });
        
        var dates = {
            start: (new Date(DayService.dates.startDate._d)).getTime(),
            end: (new Date(DayService.dates.endDate._d)).getTime()
        }
        TasksService.get(EmployeeService.employee.id, dates.start, dates.end, function() {});
    };
    
    $scope.nextDayClicked = function() {
        DayService.setNextDayDates(function() {});
        $scope.dayDisplay = DayService.generateDisplay();
        DayService.drawTimer();
    };
}

PieChartDayDirectiveController.$inject = ["$scope", "$rootScope", "DrawTimer", "DayService", "TasksService", "EmployeeService"];