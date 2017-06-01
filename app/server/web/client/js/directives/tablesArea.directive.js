TimeTrackerApplication
    .directive('tablesArea', TablesAreaDirective);

function TablesAreaDirective() {
    return {
        restrict: "E",
        templateUrl: '../../public/templates/tables-page/tables-area.html',
        controller: tablesAreaDirectiveController
    }
}

function tablesAreaDirectiveController($scope, EmployeesService, FileDialog, _) {

    $scope.users = EmployeesService.getUsers();


    
    $scope.saveFile = function(){

    }

}

tablesAreaDirectiveController.$inject = ['$scope', 'EmployeesService', 'FileDialog', '_'];