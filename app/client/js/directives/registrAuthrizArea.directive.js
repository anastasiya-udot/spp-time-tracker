TimeTrackerApplication
    .directive('registrationAuthorizationArea', RegistraionAuthorizationAreaDirective);

function RegistraionAuthorizationAreaDirective() {
    return {
        restrict: 'E',
        templateUrl: '../../templates/registr-auth.html',
        controller: registraionAuthorizationAreaDirectiveController
    }
}

function registraionAuthorizationAreaDirectiveController($scope, InitialPageLoader, _) {
    $scope.tabs = {};

    _.each(InitialPageLoader.getTabs(), function(tab) {
        $scope.tabs[tab] = false;
    });

    $scope.render = function(tab) {
        $scope.tabs = _.mapObject($scope.tabs, function() {
            return false;
        });
        $scope.tabs[tab] = true;
    }

    InitialPageLoader.initializeRender($scope.render);
}

registraionAuthorizationAreaDirectiveController.$inject = ['$scope', 'InitialPageLoader', '_'];