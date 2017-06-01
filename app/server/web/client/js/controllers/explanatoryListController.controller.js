TimeTrackerApplication
    .controller('ExplanatoryListDialogController', ExplanatoryListDialogController);

function ExplanatoryListDialogController($scope, ngDialog, RequestsService, ExplanatoryService) {

    function getRequests() {
         var deferred = $.Deferred();
         RequestsService.get(deferred.resolve);
         return deferred.promise();
     }

    $scope.fileType = 'pdf';

    $scope.generateOrder = function(id) {
        var url = '/documents/request/' + $scope.fileType + "?request_id=" + id + '&isProtected=true';
        window.open(url);
        ngDialog.closeAll();
    };

    $scope.openFormForExplanatory = function(requestId) {
        ExplanatoryService.set(RequestsService.getById(requestId));

        ngDialog.open({
            template: '../../../public/templates/dialogs/dialog-form-explanatory.html',
            className: 'ngdialog-theme-default',
            scope: $scope,
            height: 440,
            width: 500,
            name: "explanatory_form",
            controller: ExplanatoryFormController
        });
    };

    $scope.openNewRequestForm = function() {
        ngDialog.open({
            template: '../../../public/templates/dialogs/dialog-new-request-from.html',
            className: 'ngdialog-theme-default',
            scope: $scope,
            height: 385,
            width: 500,
            name: "new_request_form",
            controller: NewRequestFormController
        });
    };

   getRequests().done(function() {
         $scope.requests = RequestsService.getRequests();
   });
}

ExplanatoryListDialogController.$inject = ['$scope', 'ngDialog', 'RequestsService', 'ExplanatoryService'];