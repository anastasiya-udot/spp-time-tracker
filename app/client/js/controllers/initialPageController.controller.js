TimeTrackerApplication
    .controller('InitialPageController', InitialPageController);

function InitialPageController($scope, CompaniesService) {
     $scope.unAuth = true;
}

InitialPageController.$inject = ['$scope', 'CompaniesService'];
