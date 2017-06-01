TimeTrackerApplication
    .directive('tasksArea', TasksAreaDirective);

function TasksAreaDirective() {
    return {
        restrict: "E",
        templateUrl: '../../../public/templates/employee-page/tasks-area.html',
        controller: TasksAreaDirectiveController
    }
}

function TasksAreaDirectiveController($scope, $rootScope, ngDialog, TasksService, DayService, SessionService, _) {

    $rootScope.updateTasks = function() {
        var id = SessionService.getCurrentPageUserId();
        var startPeriod = (new Date(DayService.dates.startDate._d)).getTime();
        var endPeriod = (new Date(DayService.dates.endDate._d)).getTime();

        TasksService.get(id, startPeriod, endPeriod, _.bind(function(){
            $scope.tasks = TasksService.tasks;
        }, this));
    };

    $rootScope.updateTasks();

    $scope.openAddNewTaskForm = function() {
        ngDialog.open({
            template: '../../public/templates/dialogs/dialog-form-task.html',
            className: 'ngdialog-theme-default',
            scope: $scope,
            height: 293,
            width: 420,
            name: "new_task_form",
            controller: NewTaskFormController
        });
    };

    $scope.openConfirmDeleteDialog = function(id) {
        $rootScope.deleteTaskId = id;
        ngDialog.open({
            template: '../../public/templates/dialogs/dialog-confirm-delete.html',
            className: 'ngdialog-theme-default',
            scope: $scope,
            height: 127,
            width: 500,
            name: "cofirm_delete",
            controller: ConfirmDeleteDialogController
        });
    };
}

TasksAreaDirectiveController.$inject = ["$scope", "$rootScope", "ngDialog", "TasksService", 'DayService', 'SessionService', '_'];