TimeTrackerApplication
    .controller('ExplanatoryFormController', ExplanatoryFormController);

function ExplanatoryFormController($scope, ExplanatoryService) {
     $scope.currentExplanatory = ExplanatoryService.get();
}

ExplanatoryFormController.$inject = ['$scope', 'ExplanatoryService'];