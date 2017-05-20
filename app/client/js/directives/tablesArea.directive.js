TimeTrackerApplication
    .directive('tablesArea', TablesAreaDirective);

function TablesAreaDirective() {
    return {
        restrict: "E",
        templateUrl: '../../templates/tables-page/tables-area.html',
        controller: tablesAreaDirectiveController
    }
}

function tablesAreaDirectiveController($scope, EmployeesService, fileDialog, _) {

    $scope.users = EmployeesService.getUsers();

    $scope.changeUserRole = function(id, roleName) {
        var usesrs = EmployeesService.getUsers();
        var user = _.find(users, function(user) {
            return user.id === id;
        });

        if (roleName === 'admin') {

        }
    }

    
    $scope.saveFile = function(){
        fileDialog.saveAs(function(filename) {
            // your code
        });
    }

}

tablesAreaDirectiveController.$inject = ['$scope', 'EmployeesService', 'fileDialog', '_'];