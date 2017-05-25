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



   /* if (_.isEmpty(EmployeeService.employee)) {

        function getUser() {
            var deferred = $.Deferred();
            EmployeeService.get(SessionService.getCurrentPageUserId(), deferred.resolve);
            return deferred.promise();
        }

        getUser().done(function() {
            
        });
    }*/

}

EmployeePageController.$inject = ['$scope', 'SessionService', 'EmployeeService', 'RoleService', 'TasksService', 'PermissionsService', '_'];
