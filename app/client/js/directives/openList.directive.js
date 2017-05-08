TimeTrackerApplication
    .directive('openList', OpenListDirective);

function OpenListDirective() {
    return {
        restrict: "E",
        templateUrl: "../../templates/employee-page/open-list.html",
        controller: OpenListDirectiveController
    }
}

function OpenListDirectiveController($scope) {

}

OpenListDirectiveController.$inject = ["$scope"];