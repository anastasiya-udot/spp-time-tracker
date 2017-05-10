TimeTrackerApplication
    .controller('EmployeePageController', EmployeePageController);

function EmployeePageController($scope, EmployeeService, SessionService, RoleService, _) {
    let pageUserId = SessionService.getCurrentPageUserId();
    let sessionUserId = SessionService.getSessionUserId();
    let canControl = RoleService.get();   

    if (pageUserId === sessionUserId) {
        EmployeeService.get(pageUserId, _.bind(updateScope, $scope));
    } else {
        if (canControl !== 0 && canControl !== 2) {
            EmployeeService.get(pageUserId, _.bind(updateScope, $scope));
        }
    }

    function updateScope() {
        
    }
}

EmployeePageController.$inject = ['$scope', 'EmployeeService', 'SessionService', 'RoleService', '_'];
