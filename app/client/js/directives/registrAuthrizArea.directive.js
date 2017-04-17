TimeTrackerApplication
    .directive('registrationAuthorizationArea', RegistraionAuthorizationAreaDirective);

function RegistraionAuthorizationAreaDirective() {
    return {
        restrict: 'E',
        templateUrl: '../../templates/registr-auth.html',
        controller: registraionAuthorizationAreaDirectiveController
    }
}

function registraionAuthorizationAreaDirectiveController($scope) {
   
}

registraionAuthorizationAreaDirectiveController.$inject = ['$scope'];