TimeTrackerApplication
    .directive('profileInfo', ProfileInfoDirective);

function ProfileInfoDirective() {
    return {
        restrict: "E",
        templateUrl: '../../templates/employee-page/profile-info.html',
        controller: profileInfoDirectiveController
    }
}

function profileInfoDirectiveController($scope, ngDialog) {
    $scope.openExplanatoryDialog = function() {
        ngDialog.open({
            template: '../../templates/dialogs/dialog-list-explanatory.html',
            className: 'ngdialog-theme-default',
            height: 320,
            name: "explanatory_list",
            controller: ExplanatoryListDialogController
        });
    };

    $scope.openOrdersDialog = function() {
        ngDialog.open({
            template: '../../templates/dialogs/dialog-orders.html',
            className: 'ngdialog-theme-default',
            height: 165,
            name: "orders",
            controller: OrdersDialogController
        });
    };
}

profileInfoDirectiveController.$inject = ['$scope', 'ngDialog'];