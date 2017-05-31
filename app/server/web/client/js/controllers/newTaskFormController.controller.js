TimeTrackerApplication
    .controller('NewTaskFormController', NewTaskFormController);

function NewTaskFormController($scope, TasksService, ngDialog, SessionService, _) {

    $scope.addNewTask = function() {
        var id = SessionService.getCurrentPageUserId();
        var code = $scope.newTaskCode;
        var description = $scope.newTaskDescription || "";
        TasksService.send(id, code, description, _.bind(function() {
            ngDialog.closeAll();
        }, this));
    };
}

NewTaskFormController.$inject = ['$scope', 'TasksService', 'ngDialog', 'SessionService', '_'];