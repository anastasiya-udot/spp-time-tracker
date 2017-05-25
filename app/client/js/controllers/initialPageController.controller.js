TimeTrackerApplication
    .controller('InitialPageController', InitialPageController);

function InitialPageController($scope, $rootScope, SessionService, RoleService, EmployeePageLoader, PermissionsService) {
     SessionService.observe();
     RoleService.setCode(SessionService.getSessionRoleCode());
     PermissionsService.get();

     if ($rootScope.loggedIn) {
         EmployeePageLoader.load(SessionService.getSessionUserId());
     }
}

InitialPageController.$inject = ['$scope', '$rootScope', 'SessionService', 'RoleService', 'EmployeePageLoader', 'PermissionsService'];
