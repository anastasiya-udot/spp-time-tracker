TimeTrackerApplication
    .directive('signUpContainer', SignUpContainerDirective);
    
function SignUpContainerDirective() {
    return {
        restrict: 'E',
        templateUrl: '../../public/templates/front-page/sign-up.html',
        controller: SignUpContainerDirectiveController
    }
}


function SignUpContainerDirectiveController($scope, InitialPageLoader, CompaniesService, PostData, _) {
   
    $scope.companies = [];
    $scope.company = { text: "No companies"};

    $scope.back = function() {
        InitialPageLoader.setTab('registerCompany');
        this.newCompany = false;
    };

    function getCompanies() {
        var deferred = $.Deferred();
        CompaniesService.get(deferred.resolve);
        return deferred.promise();
    }

   getCompanies().done(function(){
        $scope.companies =  _.map(CompaniesService.getCompanies(), function (model) {
            return {
                text: model.name,
                value: model.id
            };
        });
       $scope.company = $scope.companies[0] || { text: "No companies"};
    });
    
    $scope.sendSignUpForm = function() {
        let data = {
            name: $scope.signUpName,
            surname: $scope.signUpSurname,
            patronymic: $scope.signUpPatronymic,
            email: $scope.signUpEmail,
            password: $scope.signUpPassword
        };

        let processResponseRegisterCompany = _.bind(function(res) {
             switch (res.status) {
             case 400: {
                 if (res.error === 'company') {
                     this.registerCompanyForm.company.$setValidity('companyExists', false);
                     InitialPageLoader.setTab('registerCompany');
                 }
                 this.disableSave = false;
             }; break;
             case 200: {
                 this.companies.unshift({
                     text: res.data.name,
                     value: res.data.id
                 });
                 this.company = this.companies[0];
                 InitialPageLoader.setTab('signIn');
                 this.disableSave = false;
                 this.newCompany = false;
             }; break;
             }
         }, $scope);
         let processResponseSignUp = _.bind(function(res) {
             switch (res.status) {
                 case 400: {
                     if (res.error === 'email') {
                         this.signUpForm.email.$setValidity('userExists', false);
                     }
                     this.disableSave = false;
                 }; break;
                 case 200: {
                 InitialPageLoader.setTab('signIn');
                 this.disableSave = false;
                 }; break;
             }
         }, $scope);


        $scope.disableSave = true;

        function registerNewUser() {
            var deferred = $.Deferred();
             PostData(url, data, deferred.resolve);
             return deferred.promise();
         }
 
         function registerCompany() {
             var deferred = $.Deferred();
             PostData(url, data, deferred.resolve);
             return deffered.promise();
         }

        if ($scope.newCompany) {
            url = '/authorization/new-company/post';
            _.extend(data, {
                company: $scope.companyName,
                legalNumber: $scope.registrationLegalNumber,
            });

         registerCompany().done(function(res) {
                 processResponseRegisterCompany(res);
             });

            PostData(url, data, processResponseRegisterCompany);
        } else {
            url = '/authorization/new-user/post';
            _.extend(data, {
                company: $scope.company.id
            });

            registerNewUser().done(function(res) {
                 processResponseSignUp(res);
             });
        }
    };
}

SignUpContainerDirectiveController.$inject = ['$scope', 'InitialPageLoader', 'CompaniesService', 'PostData', '_'];