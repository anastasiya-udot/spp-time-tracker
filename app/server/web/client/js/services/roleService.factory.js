 TimeTrackerApplication
    .factory('RoleService', RoleServiceController);

function RoleServiceController(GetData) {
    return {
        _processData: function(res) {
            switch (res.status) {
                case 200: this.code = res.data.roleCode; break;
                case 500: {
                    this.code = 0;
                }; break;
            }
            
        },
        code: 2,
        get: function() {
            return this.roleCode;
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