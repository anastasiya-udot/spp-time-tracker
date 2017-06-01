TimeTrackerApplication
    .controller('ExplanatoryFormController', ExplanatoryFormController);

function ExplanatoryFormController($scope, ExplanatoryService, RequestsService, ngDialog) {
     $scope.currentExplanatory = ExplanatoryService.get();

     $scope.removeExplanatory = function() {
          ExplanatoryService.remove(function(id) {
              RequestsService.removeById(id);
              ngDialog.closeAll();
          });
     };

     $scope.sendExplanatory = function() {
         ExplanatoryService.explanatory.content = $scope.content || '';
         ExplanatoryService.update(function() {
             ngDialog.closeAll();
         });
     };
}

ExplanatoryFormController.$inject = ['$scope', 'ExplanatoryService', 'RequestsService', 'ngDialog'];