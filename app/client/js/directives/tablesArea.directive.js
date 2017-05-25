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

    
    $scope.saveFile = function(){
        fileDialog.saveAs(function(filename) {
            // your code
        });
    }

}

tablesAreaDirectiveController.$inject = ['$scope', 'EmployeesService', 'fileDialog', '_'];