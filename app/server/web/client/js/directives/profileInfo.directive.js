TimeTrackerApplication
    .directive('profileInfo', ProfileInfoDirective);

function ProfileInfoDirective() {
    return {
        restrict: "E",
        templateUrl: '../../../public/templates/employee-page/profile-info.html',
        controller: profileInfoDirectiveController
    }
}

function profileInfoDirectiveController($scope, ngDialog, EmployeeService, SessionService, _) {

    if (!EmployeeService.employee) {
        $scope.employee = EmployeeService.defaultEmployee;

        function getEmployee() {
            var deferred = $.Deferred();
            EmployeeService.get(SessionService.getCurrentPageUserId(), deferred.resolve);
            return deferred.promise();
        }

        getEmployee().done(_.bind(function() {
            $scope.employee = EmployeeService.employee;
        }, this));
    } else {
        $scope.employee = EmployeeService.employee;
    }

    $scope.openExplanatoryDialog = function() {
        ngDialog.open({
            template: '../../public/templates/dialogs/dialog-list-explanatory.html',
            className: 'ngdialog-theme-default',
            height: 340,
            name: "explanatory_list",
            controller: ExplanatoryListDialogController
        });
    };

    $scope.openOrdersDialog = function() {
        ngDialog.open({
            template: '../../public/templates/dialogs/dialog-orders.html',
            className: 'ngdialog-theme-default',
            height: 165,
            name: "orders",
            controller: OrdersDialogController
        });
    };
}

profileInfoDirectiveController.$inject = ['$scope', 'ngDialog', 'EmployeeService', 'SessionService', '_'];