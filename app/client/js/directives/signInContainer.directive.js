TimeTrackerApplication
    .directive('signInContainer', SignInContainerDirective);

function SignInContainerDirective() {
    return {
        restrict: 'E',
        templateUrl: '../../templates/authorization/sign-in.html',
        controller: signInContainerDirectiveController
    }
}

function signInContainerDirectiveController($scope, $location, SessionService, InitialPageLoader, PostData, _) {
    $scope.confirm = function() {
        $scope.newCompany = true;
        InitialPageLoader.setTab('signUp');
    };

    let processResponse = _.bind(function(res) {
        switch(res.status) {
            case 400: {
                let errorType;
                let input;

                switch (res.error) {
                    case 'email': errorType = 'noUser'; break;
                    case 'password': errorType = 'wrongPassword'; break;
                }
                this.signUpForm[res.error].$setValidity(errorType, false);
                this.disableSave = false;
            }; break;
            case 200: {
                let token = res.data.token;
                let id;

                SessionService.startSession(token);
                id = SessionService.getSessionUserId();
                this.disableSave = false;

            }; break;
        }
    }, $scope);

    $scope.sendSignInForm = function() {
        let url = '/authoization/sign-in/post';
        let data = {
            email: $scope.signInEmail,
            password: $scope.signInPassword
        };

        this.disableSave = true;
        PostData(url, data, processResponse);
    };
}

signInContainerDirectiveController.$inject = ['$scope', "$location", "SessionService", 'InitialPageLoader', 'PostData', '_'];