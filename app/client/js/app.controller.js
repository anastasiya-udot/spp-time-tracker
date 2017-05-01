TimeTrackerApplication
    .controller('InitialController', InitialController);

function InitialController($scope, CompaniesService) {
   //  CompaniesService.get();
}

InitialController.$inject = ['$scope', 'CompaniesService'];
