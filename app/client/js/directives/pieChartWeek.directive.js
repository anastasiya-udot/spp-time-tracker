TimeTrackerApplication
    .directive('pieChartWeek', PieChartWeekDirective);

function PieChartWeekDirective() {
    return {
        restrict: 'E',
        templateUrl: '../../templates/employee-page/pie-chart-week.html',
        controller: PieChartWeekDirectiveController
    }
}

function PieChartWeekDirectiveController($scope, DrawTimer) {
    var elementId = "#circle-week";
    var animation = { duration: 6000 };
    var startValue = 0.0;
    var value = 1;

    DrawTimer.initCircle(elementId, animation, startValue, value);

    $(elementId).on("circle-animation-end", function() {
        DrawTimer.initCircle(elementId, { duration: 10000 }, startValue, value);
    });
}

PieChartWeekDirectiveController.$inject = ["$scope", "DrawTimer"];