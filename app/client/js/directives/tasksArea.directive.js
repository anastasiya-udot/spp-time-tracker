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
            height: 293,
            width: 420,
            name: "new_task_form",
            controller: NewTaskFormController
        });
    };

    $scope.openConfirmDeleteDialog = function() {
        ngDialog.open({
            template: '../../templates/dialogs/dialog-confirm-delete.html',
            className: 'ngdialog-theme-default',
            scope: $scope,
            height: 127,
            width: 500,
            name: "cofirm_delete",
            controller: ConfirmDeleteDialogController
        });
    };
}

TasksAreaDirectiveController.$inject = ["$scope", "ngDialog"];