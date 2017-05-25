 TimeTrackerApplication
    .factory('RoleService', RoleServiceController);

function RoleServiceController(GetData) {
    return {
        _processData: function(res) {
            switch (res.status) {
                case 200: this.setCode(res.data.roleCode); break;
                case 500: {
                    this.code = 0;
                }; break;
            }
            
        },
        roleCode: 31,
        get: function() {
            return this.roleCode;
        },
        setCode: function(code) {
             this.roleCode = code;
        },
        set: function(id, callback) {
            let url = "/role/" + id;

            GetData(url, function() {
               this._processData();
               callback()
            });
        }
    }
}

RoleServiceController.$inject = ['GetData'];