TimeTrackerApplication
    .controller('ConfirmDeleteDialogController', ConfirmDeleteDialogController);

function ConfirmDeleteDialogController($scope, $rootScope, ngDialog, TasksService, _) {
    $scope.okClicked = function() {
        TasksService.delete($rootScope.deleteTaskId, _.bind(function() {
            $rootScope.deleteTaskId = undefined;
            ngDialog.closeAll();
        }, this));
    };

    $scope.cancelClicked = function() {
        $rootScope.deleteTaskId = undefined;
        ngDialog.closeAll();
    };
}

ConfirmDeleteDialogController.$inject = ['$scope',  '$rootScope', 'ngDialog', 'TasksService', '_'];