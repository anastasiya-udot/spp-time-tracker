TimeTrackerApplication
    .directive('tasksArea', TasksAreaDirective);

function TasksAreaDirective() {
    return {
        restrict: "E",
        templateUrl: '../../templates/employee-page/tasks-area.html',
        cotroller: TasksAreaDirectiveController
    }
}

function TasksAreaDirectiveController($scope,  ngDialog) {
    $scope.openAddNewTaskForm = function() {
        ngDialog.open({
            template: '../../templates/dialogs/dialog-form-task.html',
            className: 'ngdialog-theme-default',
            scope: $scope,
            height: 440,// (!!!!! здесь будешь регулировать высоту и ширину выпадающего окна)
            width: 500,
            name: "new_task_form",
            controller: NewTaskFormController
        });
    }
}

TasksAreaDirectiveController.$inject = ["$scope", " ngDialog"];