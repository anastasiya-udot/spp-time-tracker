TimeTrackerApplication
    .directive('tasksArea', TasksAreaDirective);

function TasksAreaDirective() {
    return {
        restrict: "E",
        templateUrl: '../../templates/employee-page/tasks-area.html',
        controller: TasksAreaDirectiveController
    }
}

function TasksAreaDirectiveController($scope, ngDialog) {
    $scope.openAddNewTaskForm = function() {
        ngDialog.open({
            template: '../../templates/dialogs/dialog-form-task.html',
            className: 'ngdialog-theme-default',
            scope: $scope,
            height: 280,
            width: 420,
            name: "new_task_form",
            controller: NewTaskFormController
        });
    }
}

TasksAreaDirectiveController.$inject = ["$scope", "ngDialog"];