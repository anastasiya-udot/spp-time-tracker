 TimeTrackerApplication
    .factory('PermissionsService', PermissionsServiceController);

function PermissionsServiceController($rootScope, RoleService) {
    return {
        masks: [
            00001,
            00010,
            00100,
            01000,
            10000
        ],
        _getBit: function(value, number) {
            return !!+value.toString(2)[4 - number];
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
             $rootScope.pageOwner = true;
           /* if(!currentPageUserId)
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