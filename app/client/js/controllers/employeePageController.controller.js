TimeTrackerApplication
    .controller('EmployeePageController', EmployeePageController);

function EmployeePageController($scope, SessionService/*, EmployeeService, SessionService, RoleService, _*/) {
    let pageUserId = SessionService.getCurrentPageUserId();
    $scope.unAuth = false;
 /*   let sessionUserId = SessionService.getSessionUserId();
    let canControl = RoleService.get();   

    if (pageUserId === sessionUserId) {
        EmployeeService.get(pageUserId, _.bind(updateScope, $scope));
    } else {
        if (canControl !== 0 && canControl !== 2) {
            EmployeeService.get(pageUserId, _.bind(updateScope, $scope));
        }
    }*/

    function updateScope() {
        
    }
}

EmployeePageController.$inject = ['$scope', 'SessionService'/*, 'EmployeeService', 'SessionService', 'RoleService', '_'*/];
