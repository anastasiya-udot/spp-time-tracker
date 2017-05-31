TimeTrackerApplication
    .directive('emailValidation', EmailValidationDirective)
    .directive('passwordValidation', PasswordValidationDirective)
    .directive('nameValidation', NameValidationDirective)
    .directive('companyValidation', CompanyValidationDirective)
    .directive('confirmPasswordValidation', ConfirmPasswordValidationDirective)
    .directive('userExists', UserExistsDirective)
    .directive('wrongPassword', WrongPasswordDirective)
    .directive('noUser', NoUserDirective);

function EmailValidationDirective(PatternService, InitialPageLoader) {
    return {
        restrict: 'A',
        require: 'ngModel',
        link: function(scope, element, attr, ngModel) {
            let regexp = new RegExp(PatternService.email);
            let errorType = attr.emailValidation;
            let input = attr.name;

            ngModel.$parsers.unshift(function(value) {
                let result = regexp.test(value);
                let form = scope[InitialPageLoader.currentTab + "Form"];
                

                form[input].$setValidity(errorType, result);
                return value;
            });
        }
    }
}

function UserExistsDirective(InitialPageLoader) {
    return {
        restrict: 'A',
        require: 'ngModel',
        link: function(scope, element, attr, ngModel) {
            let errorType = attr.userExists;
            let input = attr.name;

            ngModel.$parsers.unshift(function(value) {
                let form = scope[InitialPageLoader.currentTab + "Form"];

                form[input].$setValidity(errorType, true);
                return value;
            });

        }
    }
}

function NoUserDirective(InitialPageLoader) {
    return {
        restrict: 'A',
        require: 'ngModel',
        link: function(scope, element, attr, ngModel) {
            let errorType = attr.noUser;
            let input = attr.name;

            ngModel.$parsers.unshift(function(value) {
                let form = scope[InitialPageLoader.currentTab + "Form"];

                form[input].$setValidity(errorType, true);
                return value;
            });

        }
    }
}

function PasswordValidationDirective(PatternService, InitialPageLoader) {
    return {
        restrict: 'A',
        require: 'ngModel',
        link: function(scope, element, attr, ngModel) {
            let regexp = new RegExp(PatternService.password);
            let errorType = attr.passwordValidation;
            let input = attr.name;

            ngModel.$parsers.unshift(function(value) {
                let form = scope[InitialPageLoader.currentTab + "Form"];
                let result = regexp.test(value);
                let dirtyConfirm = false;
                
                if (InitialPageLoader.currentTab === "signUp") {
                    dirtyConfirm = form.confirmPassword.$dirty;
                }   

                if (dirtyConfirm) {
                    let confirmPassword = form.confirmPassword.$viewValue;
                    let cofirmErrorType = form.confirmPassword.$$attr.confirmPasswordValidation;

                    form.confirmPassword.$setValidity(cofirmErrorType, confirmPassword === value);
                }

                form[input].$setValidity(errorType, result);
                return value;
            });

        }
    }
}

function WrongPasswordDirective(InitialPageLoader) {
    return {
        restrict: 'A',
        require: 'ngModel',
        link: function(scope, element, attr, ngModel) {
            let errorType = attr.wrongPassword;
            let input = attr.name;

            ngModel.$parsers.unshift(function(value) {
                let form = scope[InitialPageLoader.currentTab + "Form"];

                form[input].$setValidity(errorType, true);
                return value;
            });

        }
    }
}

function NameValidationDirective(PatternService, InitialPageLoader) {
    return {
        restrict: 'A',
        require: 'ngModel',
        link: function(scope, element, attr, ngModel) {
            let regexp = new RegExp(PatternService.name);
            let errorType = attr.nameValidation;
            let input = attr.name;

            ngModel.$parsers.unshift(function(value) {
                let form = scope[InitialPageLoader.currentTab + "Form"];
                let result = regexp.test(value);

                form[input].$setValidity(errorType, result);

                return value;
            });

        }
    }
}


function CompanyValidationDirective(PatternService, InitialPageLoader) {
    return {
        restrict: 'A',
        require: 'ngModel',
        link: function(scope, element, attr, ngModel) {
            let regexp = new RegExp(PatternService.company);
            let errorType = attr.companyValidation;
            let input = attr.name;

            ngModel.$parsers.unshift(function(value) {
                let form = scope[InitialPageLoader.currentTab + "Form"];
                let result = regexp.test(value);

                form[input].$setValidity(errorType, result);

                return value;
            });

        }
    }
}

// function CompaniesValidationDirective(InitialPageLoader, CompaniesService) {
//     return {
//         restrict: 'A',
//         link: function(scope, element, attr) {
//             let form = scope["signUpForm"];
//             let errorType = attr.companiesValidation;
//             let newCompany = scope.newCompany;
//
//             !newCompany && form.$setValidity(errorType, !!CompaniesService.getCompanies().length);
//         }
//     }
// }

// function CompanyExistsDirective(InitialPageLoader, CompaniesService) {
//     return {
//         restrict: 'A',
//         require: 'ngModel',
//         link: function(scope, element, attr, ngModel) {
//             let errorType = attr.companyExists;
//             let input = attr.name;
//
//             ngModel.$parsers.unshift(function(value) {
//                 let form = scope[InitialPageLoader.currentTab + "Form"];
//                 let result = CompaniesService.has(value);
//
//                 form[input].$setValidity(errorType, !result);
//                 return !result;
//             });
//
//         }
//     }
// }

function ConfirmPasswordValidationDirective(InitialPageLoader) {
    return {
        restrict: 'A',
        require: 'ngModel',
        link: function(scope, element, attr, ngModel) {
            let errorType = attr.confirmPasswordValidation;
            let input = attr.name;

            ngModel.$parsers.unshift(function(value) {
                let form = scope[InitialPageLoader.currentTab + "Form"];
                let password = form.password.$viewValue;
                let dirtyPass = form.password.$dirty;
                let result = password === value;

                if (dirtyPass) {
                    form[input].$setValidity(errorType, result);
                }

                return value;
            });
        }
    }
}


EmailValidationDirective.$inject = ["PatternService", "InitialPageLoader"];
PasswordValidationDirective.$inject = ["PatternService", "InitialPageLoader"];
NameValidationDirective.$inject = ["PatternService", "InitialPageLoader"];
ConfirmPasswordValidationDirective.$inject = ["InitialPageLoader"];
UserExistsDirective.$inject = ["InitialPageLoader"];
WrongPasswordDirective.$inject = ["InitialPageLoader"];
NoUserDirective.$inject = ["InitialPageLoader"];
CompanyValidationDirective.$inject = ["PatternService", "InitialPageLoader"]