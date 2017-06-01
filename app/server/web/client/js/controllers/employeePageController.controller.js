TimeTrackerApplication
    .controller('EmployeePageController', EmployeePageController);

function EmployeePageController($scope, SessionService, RoleService, TasksService, PermissionsService, _) {
     SessionService.observe();
     RoleService.setCode(SessionService.getSessionRoleCode());
     PermissionsService.get();
}
 EmployeePageController.$inject = ['$scope', 'SessionService', 'RoleService', 'TasksService', 'PermissionsService', '_'];