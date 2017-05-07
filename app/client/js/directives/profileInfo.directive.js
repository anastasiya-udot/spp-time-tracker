TimeTrackerApplication
    .directive('profileInfo', ProfileInfoDirective);

function ProfileInfoDirective() {
    return {
        restrict: "E",
        templateUrl: '../../templates/employee-page/profile-info.html',
        controller: profileInfoDirectiveController
    }
}

function profileInfoDirectiveController($scope) {

}

profileInfoDirectiveController.$inject = ['$scope'];