TimeTrackerApplication
    .directive('applicationHeader', ApplicationHeaderDirective);

function ApplicationHeaderDirective() {
    return {
        restrict: 'E',
        templateUrl: '../../templates/header.html',
        controller: applicationHeaderDirectiveController
    }
}



function applicationHeaderDirectiveController($scope, InitialPageLoader, _) {
    $scope.tabs = {};

    function resetTabs() {
        _.each(InitialPageLoader.getTabs(), function(tab) {
            $scope.tabs[tab] = false;
        });
    }
    
    resetTabs();
    $scope.tabs[InitialPageLoader.currentTab] = true;

    $scope.onClick = function(tab) {
        resetTabs();
        $scope.tabs[tab] = true;
        InitialPageLoader.setTab(tab);
    };
}

applicationHeaderDirectiveController.$inject = ['$scope', 'InitialPageLoader', '_'];
