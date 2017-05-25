TimeTrackerApplication
    .factory('EmployeeService', EmployeeServiceController);

function EmployeeServiceController(GetData) {
    return {
        employee: this.defaultEmployee,
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
            id: -1,
            name: "Unknown",
            surname: "Unknown",
            patronymic: "Unknown",
            email: "none",
            roleCode: 0,
            worktype: 0,
            company: {
                id: -1,
                name: "Unknown"
            }
        },
        get: function(id, callback){
            let url = "/employee/get/" + id;
            let processResponse = _.bind(this._processResponse, this);

            GetData(url, function(res) {
                processResponse(res);
                callback();
            });
        }
    }
}

EmployeeServiceController.$inject = ['GetData'];