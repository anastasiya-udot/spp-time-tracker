 TimeTrackerApplication
    .factory('EmployeesService', EmployeesService);

function EmployeesService(PostData) {
    return {
        users: [

        ],
        getUsers: function() {
            return this.users;
        },
        _processData: function() {

        },
        get: function(callback) {

        }
    }
}

EmployeesService.$inject = ['PostData'];