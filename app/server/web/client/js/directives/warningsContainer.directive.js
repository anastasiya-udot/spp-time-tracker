TimeTrackerApplication
    .directive('warningsContainer', WarningsContainer);

function WarningsContainer() {
    return {
        restrict: "E",
        templateUrl: "../../public/templates/front-page/warnings.html"
    }
}