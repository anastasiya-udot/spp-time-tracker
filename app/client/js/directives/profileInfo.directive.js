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
            height: 300,
            name: "explanatory_list",
            controller: ExplanatoryListDialogController
        });
    }
}

profileInfoDirectiveController.$inject = ['$scope', 'ngDialog'];