TimeTrackerApplication
    .directive('warningsContainer', WarningsContainer);

function WarningsContainer() {
    return {
        restrict: "E",
        templateUrl: "../../templates/front-page/warnings.html"
    }
}