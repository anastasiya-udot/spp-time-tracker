TimeTrackerApplication
    .directive('pieChartDay', PieChartDayDirective);

function PieChartDayDirective() {
    return {
        restrict: 'E',
        templateUrl: '../../templates/employee-page/pie-chart-day.html',
        controller: PieChartDayDirectiveController
    }
}

function PieChartDayDirectiveController($scope, DrawTimer) {
    var elementId = "#circle-day";
    var animation = { duration: 6000 };
    var startValue = 0.0;
    var value = 1;

    DrawTimer.initCircle(elementId, animation, startValue, value);

    $(elementId).on("circle-animation-end", function() {
        DrawTimer.initCircle(elementId, { duration: 10000 }, startValue, value);
    });
}

PieChartDayDirectiveController.$inject = ["$scope", "DrawTimer"];