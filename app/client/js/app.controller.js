TimeTrackerApplication
    .controller('InitialController', initialController);

function initialController($scope, InitialPageLoader) {
    $scope.name = "NASTYA";
}

initialController.$inject = ['$scope', 'InitialPageLoader'];
