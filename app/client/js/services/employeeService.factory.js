TimeTrackerApplication
    .factory('EmployeeService', EmployeeServiceController);

function EmployeeServiceController(GetData) {
    return {
        _processResponse: function(res) {
            switch(res.status) {
                case 200: {
                    this.employee = res.data;
                }; break;
                case 500: {
                    this.employee = this.defaultEmployee;
                }; break;
            }
        },
        defaultEmployee: {
            name: "Unknown",
            surname: "Unknown",
            patronymic: "Unknown",
            email: "none"
        },
        get: function(id, callback){
            let url = "/employee/get/" + id;

            GetData(url, function() {
                this._processResponse();
                callback();
            });
        }
    }
}

EmployeeServiceController.$inject = ['GetData'];