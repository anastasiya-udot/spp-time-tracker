TimeTrackerApplication
    .directive('applicationHeader', ApplicationHeaderDirective);

function ApplicationHeaderDirective() {
    return {
        restrict: 'E',
        templateUrl: '../../public/templates/front-page/header.html',
        controller: ApplicationHeaderDirectiveController
    }
}



function ApplicationHeaderDirectiveController($scope, $location, InitialPageLoader, ngDialog, SessionService, _) {
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

    $scope.backToList = function() {
        $location.url('/tables/' + SessionService.getSessionCompanyId());
    };


    $scope.finishSession = function() {
        SessionService.destroySession();

    };

    $scope.openChangeUserInfoDialog = function() {
        ngDialog.open({
            template: '../../public/templates/dialogs/dialog-change-user-info.html',
            className: 'ngdialog-theme-default',
            scope: $scope,
            height: 230,
            width: 500,
            name: "user_info",
            controller: ChangeUserInfoDialogController
        });
    }
}

ApplicationHeaderDirectiveController.$inject = ['$scope', '$location', 'InitialPageLoader', 'ngDialog', 'SessionService', '_'];
