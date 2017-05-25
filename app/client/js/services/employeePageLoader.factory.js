 TimeTrackerApplication
    .factory('EmployeePageLoader', EmployeePageLoaderController);

function EmployeePageLoaderController($location, EmployeeService, _) {
    return {
        load: function(userId) {

            function getUser() {
                var deferred = $.Deferred();
                EmployeeService.get(userId, deferred.resolve);
                return deferred.promise();
            }

            getUser().done(function() {
                $location.path('/employee/' + EmployeeService.employee.id);
            });
        }
    }
}

EmployeePageLoaderController.$inject = ["$location", 'EmployeeService', '_'];