TimeTrackerApplication
    .directive('pieChartDay', PieChartDayDirective);

function PieChartDayDirective() {
    return {
        restrict: 'E',
        templateUrl: '../../../public/templates/employee-page/pie-chart-day.html',
        controller: PieChartDayDirectiveController
    }
}

function PieChartDayDirectiveController($scope, $rootScope, DayService, EmployeeService, _) {

    function getTimeForDay() {
        var deferred = $.Deferred();
        DayService.getTime(deferred.resolve);
        return deferred.promise();
    }

    let setPieChartDay = _.bind(function() {
        $scope.dayTimeFact = Math.trunc(DayService.actualTime / 1000 / 60 / 60 * 100) / 100;
        var employee = EmployeeService.employee || EmployeeService.defaultEmployee;
        $scope.dayTimeExpected = employee.worktype / 5;
    }, this);

    setPieChartDay();

    getTimeForDay().done(function() {
        setPieChartDay();
        $rootScope.showPlayButton = !$rootScope.sessionStartedTime;
        $rootScope.dayDisplay = DayService.dates.startDate.format('MMM Do YY');
    });

    $scope.prevDayClicked = function() {
        DayService.setPreviousDayDates(function() {
            setPieChartDay();
            $rootScope.dayDisplay = DayService.dates.startDate.format('MMM Do YY');
            $rootScope.updateTasks();
        });
    };

    $scope.nextDayClicked = function() {
        DayService.setNextDayDates(function() {
            setPieChartDay();
            $rootScope.dayDisplay = DayService.dates.startDate.format('MMM Do YY');
            $rootScope.updateTasks();
        });
    };
}

PieChartDayDirectiveController.$inject = ["$scope", "$rootScope", 'DayService', 'EmployeeService', '_'];