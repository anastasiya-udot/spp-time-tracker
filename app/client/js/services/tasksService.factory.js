 TimeTrackerApplication
    .factory('TasksService', TasksService);

function TasksService() {
    return {
        tasks: [
            {
                id: 1,
                code: 'SYS-1',
                description: 'add new page',
                date: '02.03.2017'
            },
            {
                id: 2,
                code: 'SYS-2',
                description: 'create table',
                date: '10.02.2017'
            },
            {
                id: 3,
                code: 'SYS-3',
                description: 'delete image',
                date: '29.01.2017'
            },
            {
                id: 4,
                code: 'SYS-4',
                description: 'fixed bags',
                date: '05.01.2017'
            },
        ],
        getTasks: function() {
            return this.tasks;
        }
    }
}