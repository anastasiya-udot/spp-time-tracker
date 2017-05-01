TimeTrackerApplication
    .directive('registrationContainer', RegistrationContainerDirective);

function RegistrationContainerDirective() {
    return {
        restrict: 'E',
        templateUrl: '../../templates/front-page/registration.html',
        controller: registrationContainerDirectiveController
    }
}

function registrationContainerDirectiveController($scope) {
   
}

registrationContainerDirectiveController.$inject = ['$scope'];