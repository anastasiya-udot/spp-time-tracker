TimeTrackerApplication
    .factory('EmployeesService', EmployeesService);

function EmployeesService(PostData, SessionService, _) {
    return {
        users: [],
        getUsers: function() {
            return this.users;
        },
        _processData: function(res) {
            if (res.status) {
                this.users = res.data.employees;
            }
        },
        get: function(callback) {
            var data = {
                "startPeriod" : moment().startOf('week').startOf('day')._d.getTime(),
                "finishPeriod" : moment().endOf('week').endOf('day')._d.getTime(),
                "companyId": SessionService.getSessionCompanyId()
            };
            var url = '/get-all-employees/get';
            var processData = _.bind(function(res) {
                this._processData(res);
                callback();
            }, this);

            PostData(url, data, processData);
        }
    }
}

EmployeesService.$inject = ['PostData', 'SessionService', '_'];