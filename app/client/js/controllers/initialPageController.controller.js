TimeTrackerApplication
    .controller('InitialPageController', InitialPageController);

function InitialPageController($scope, CompaniesService) {
   //  CompaniesService.get();
}

InitialPageController.$inject = ['$scope', 'CompaniesService'];
