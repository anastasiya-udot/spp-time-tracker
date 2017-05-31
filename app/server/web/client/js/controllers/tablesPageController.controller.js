TimeTrackerApplication
    .controller('TablesPageController', TablesPageController);

function TablesPageController($scope, SessionService, EmployeesService, EmployeePageLoader) {

    function getAllEmployees() {
        var deferred = $.Deferred();
        EmployeesService.get(deferred.resolve);
        return deferred.promise();
    }

    getAllEmployees().done(function(){
        $scope.employees = EmployeesService.getUsers();
    });

    $scope.showUser = function(id) {
        EmployeePageLoader.load(id);
    };
}

TablesPageController.$inject = ['$scope', 'SessionService', 'EmployeesService', 'EmployeePageLoader'];
