TimeTrackerApplication
    .directive('pieChartWeek', PieChartWeekDirective);

function PieChartWeekDirective() {
    return {
        restrict: 'E',
        templateUrl: '../../../public/templates/employee-page/pie-chart-week.html',
        controller: PieChartWeekDirectiveController
    }
}

function PieChartWeekDirectiveController($scope, $rootScope, WeekService, EmployeeService, _) {

    function getTimeForWeek() {
        var deferred = $.Deferred();
        WeekService.getTime(deferred.resolve);
        return deferred.promise();
    }

    let setPieChartWeek = _.bind(function() {
        $scope.weekTimeFact = Math.trunc(WeekService.actualTime / 1000 / 60 / 60 * 100) / 100;
        var employee = EmployeeService.employee || EmployeeService.defaultEmployee;
        $scope.weekTimeExpected = employee.worktype;
    }, this);

    setPieChartWeek();

    getTimeForWeek().done(function() {
        setPieChartWeek();
        $rootScope.weekDisplay = WeekService.dates.startDate.format('MMM Do') + "-" + WeekService.dates.endDate.format('MMM Do');
    });

    $scope.prevWeekClicked = function() {
        WeekService.setPreviousWeekDates(function() {
            setPieChartWeek();
            $rootScope.weekDisplay = WeekService.dates.startDate.format('MMM Do') + "-" + WeekService.dates.endDate.format('MMM Do');
        });
    };

    $scope.nextWeekClicked = function() {
        WeekService.setNextWeekDates(function() {
            setPieChartWeek();
            $rootScope.weekDisplay = WeekService.dates.startDate.format('MMM Do') + "-" + WeekService.dates.endDate.format('MMM Do');
        });
    };
}

PieChartWeekDirectiveController.$inject = ["$scope", "$rootScope", 'WeekService', 'EmployeeService', '_'];