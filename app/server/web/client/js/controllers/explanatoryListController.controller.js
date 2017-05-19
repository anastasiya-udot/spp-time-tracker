TimeTrackerApplication
    .controller('ExplanatoryListDialogController', ExplanatoryListDialogController);

function ExplanatoryListDialogController($scope, ngDialog, RequestsService) {

    $scope.requests =  RequestsService.getRequests();

    $scope.openFormForExplanatory = function() {
        ngDialog.open({
            template: '../../templates/dialogs/dialog-form-explanatory.html',
            className: 'ngdialog-theme-default',
            scope: $scope,
            height: 440,
            width: 500,
            name: "explanatory_form",
            controller: ExplanatoryFormController
        });
    }

    $scope.openNewRequestForm = function() {
        ngDialog.open({
            template: '../../templates/dialogs/dialog-new-request-from.html',
            className: 'ngdialog-theme-default',
            scope: $scope,
            height: 385,
            width: 500,
            name: "new_request_form",
            controller: NewRequestFormController
        });
    }

    $scope.requests =  RequestsService.getRequests();
}

ExplanatoryListDialogController.$inject = ['$scope', 'ngDialog', 'RequestsService'];