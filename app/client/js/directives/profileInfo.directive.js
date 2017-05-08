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
    $scope.openExplanataryDialog = function() {
        ngDialog.open({
            template: '../../templates/employee-page/dialog-explanatary.html',
            className: 'ngdialog-theme-default',
            scope: $scope,
            height: 200
        });
    }
}

profileInfoDirectiveController.$inject = ['$scope', 'ngDialog'];