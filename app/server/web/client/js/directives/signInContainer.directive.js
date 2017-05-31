TimeTrackerApplication
    .directive('signInContainer', SignInContainerDirective);

function SignInContainerDirective() {
    return {
        restrict: 'E',
        templateUrl: '../../public/templates/front-page/sign-in.html',
        controller: signInContainerDirectiveController
    }
}

function signInContainerDirectiveController($scope, InitialPageLoader, EmployeePageLoader, PostData, SessionService, RoleService, _) {
      $scope.confirm = function() {
          $scope.newCompany = true;
          InitialPageLoader.setTab('signUp');
      };
  

    $scope.sendSignInForm = function() {
        let url = '/authorization/sign-in/post';
        let data = {
            email: $scope.signInEmail,
            password: $scope.signInPassword
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
                     let userId;
 
                     SessionService.startSession(res.data.token);
                     userId = SessionService.getSessionUserId();
                     EmployeePageLoader.load(userId);
                     this.disableSave = false;
                 }; break;
             }
         }, $scope);

          function signInUser() {
             var deferred = $.Deferred();
             PostData(url, data, deferred.resolve);
             return deferred.promise();
         }

        this.disableSave = true;
        signInUser().done(function(res) {
             processResponse(res);
         });
    };
}

signInContainerDirectiveController.$inject = ["$scope", "InitialPageLoader",
"EmployeePageLoader", "PostData",  "SessionService", "RoleService", "_"]