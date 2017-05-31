 TimeTrackerApplication
    .factory('PermissionsService', PermissionsServiceController);

function PermissionsServiceController($rootScope, RoleService) {
    return {
        _getBit: function(value, number) {
            let n = +value;
            let binary = n.toString(2);

            while (binary.length !== 5) {
                binary = '0' + binary;
            }

            return !!+binary[binary.length - 1 - number];
        },
        get: function() {
            let code = RoleService.get();

             $rootScope.permission = {
                REQUEST_FOR_EXPLANATION: this._getBit(code, 0),
                WORKPROCESS_CONTROL: this._getBit(code, 1),
                ADMIN_CONTROL: this._getBit(code, 2),
                HR_CONTROL: this._getBit(code, 3),
                EMPLOYEE_CONTROL: this._getBit(code, 4)
            }
        },
        checkPermission: function(currentPageUserId){
            /*if(!currentPageUserId)
                currentPageUserId = this.getCurrentPageUserId();

            if (this.getSessionUserId()){
                if(this.getSessionUserId() == currentPageUserId){
                    $rootScope.pageOwner = true;
                } else {
                    $rootScope.pageOwner = false;
                }
            } else {
                $rootScope.pageOwner = false;
            }*/
        },
    }
}

PermissionsServiceController.$inject = ['$rootScope', 'RoleService'];