TimeTrackerApplication
    .directive('tasksArea', TasksAreaDirective);

function TasksAreaDirective() {
    return {
        restrict: "E",
        templateUrl: '../../templates/employee-page/tasks-area.html',
        cotroller: TasksAreaDirectiveController
    }
}

function TasksAreaDirectiveController($scope) {

}

TasksAreaDirectiveController.$inject = ["$scope"];