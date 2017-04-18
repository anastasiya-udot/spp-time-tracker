TimeTrackerApplication
    .directive('signInContainer', SignInContainerDirective);

function SignInContainerDirective() {
    return {
        restrict: 'E',
        templateUrl: '../../templates/authorization/sign-in.html',
        controller: signInContainerDirectiveController
    }
}

function signInContainerDirectiveController($scope) {
   
}

signInContainerDirectiveController.$inject = ['$scope'];