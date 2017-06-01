TimeTrackerApplication
    .controller('TablesPageController', TablesPageController);

function TablesPageController($scope, SessionService, EmployeesService, EmployeePageLoader) {

    $scope.fileType = 'pdf';

    $scope.saveOrderByTime = function() {
        var url = '/documents/employee/' + $scope.fileType + '?company_id=' + SessionService.getSessionCompanyId() + '&isProtected=true';
        window.open(url);
    };

    $scope.saveOrderByTasks = function() {
        var url = '/documents/tasks/' + $scope.fileType + '?company_id=' + SessionService.getSessionCompanyId() + '&isProtected=true';
        window.open(url);
    };

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
