 TimeTrackerApplication
    .factory('EmployeesService', EmployeesService);

function EmployeesService() {
    return {
        users: [
            {
                id: 1,
                name: 'Nastya',
                surname: 'Udot',
                patronymic: 'Lalal',
                plan: 40,
                worked: 35
            },
            {
                id: 2,
                name: 'Someone',
                surname: 'Else',
                patronymic: 'Lalal',
                plan: 40,
                worked: 30
            },
            {
                id: 2,
                name: 'Guest',
                surname: 'Guest',
                patronymic: 'Lalal',
                plan: 0,
                worked: 0
            }
        ],
        getUsers: function() {
            return this.users;
        }
    }
}