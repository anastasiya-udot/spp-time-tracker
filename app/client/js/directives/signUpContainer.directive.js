TimeTrackerApplication
    .directive('signUpContainer', SignUpContainerDirective);

function SignUpContainerDirective() {
    return {
        restrict: 'E',
        templateUrl: '../../templates/authorization/sign-up.html',
        controller: signUpContainerDirectiveController
    }
}

function signUpContainerDirectiveController($scope) {
   
}

signUpContainerDirectiveController.$inject = ['$scope'];