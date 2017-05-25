TimeTrackerApplication
    .controller('EmployeePageController', EmployeePageController);

function EmployeePageController($scope, SessionService, EmployeeService, RoleService, TasksService, PermissionsService, _) {
     SessionService.observe();
     RoleService.setCode(SessionService.getSessionRoleCode());
     PermissionsService.get();
 
     let loadEmployeeData = _.bind(function() {
 
         function getTasks() {
             var deferred = $.Deferred();
             TasksService.get()
          }
          
     }, this); 
}
 EmployeePageController.$inject = ['$scope', 'SessionService', 'EmployeeService', 'RoleService', 'TasksService', 'PermissionsService', '_'];