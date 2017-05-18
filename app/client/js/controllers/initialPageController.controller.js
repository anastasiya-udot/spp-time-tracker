TimeTrackerApplication
    .controller('InitialPageController', InitialPageController);

function InitialPageController($scope, CompaniesService) {
   //  CompaniesService.get();
   $scope.unAuth = true;
}

InitialPageController.$inject = ['$scope', 'CompaniesService'];
