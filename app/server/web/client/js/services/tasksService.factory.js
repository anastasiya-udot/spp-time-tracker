TimeTrackerApplication
    .factory('TasksService', TasksService);

function TasksService($rootScope, PostData, DeleteData, SessionService, _) {
    return {
        tasks: [],
        getTasks: function() {
            return this.tasks;
        },
        _processGetTasks: function(res) {
            var _this = this;
            this.tasks = [];

            if (res.status === 200) {
                _.each(res.data, function(task) {
                    _this.tasks.push(task);
                });
            }
        },
        _processSendTask: function(res) {
            if (res.status === 200) {
                this.tasks.push(res.data);
            }
        },
        _processDeleteTask: function(res) {
            if (res.status === 200) {
                var index = _.findIndex(this.tasks, function(task) {
                    return task.id === $rootScope.deleteTaskId;
                });
                this.tasks.splice(index, 1);
            }
        },
        delete: function(id, callback) {
            var url = '/task/delete/' + id + '/' + SessionService.getCurrentPageUserId();
            var processDeleteTask = _.bind(function(res) {
                this._processDeleteTask(res);
                callback();
            }, this);

            DeleteData(url, processDeleteTask);
        },
        send: function(id, code, description, callback) {
            var url = '/new-task/post';
            var data = {
                id: id,
                date: (new Date()).getTime(),
                code: code,
                description: description
            };

            PostData(url, data, _.bind(function(res) {
                this._processSendTask(res);
                callback();
            }, this));

        },
        get: function(id, start, finish, callback) {
            var url = '/tasks/get';
            var processData = _.bind(function(res) {
                this._processGetTasks(res);
                callback();
            }, this);
            var data = {
                id: id,
                startPeriod: start,
                finishPeriod: finish
            };

            PostData(url, data, processData)
        }
    }
}

TasksService.$inject = ['$rootScope', 'PostData', 'DeleteData', 'SessionService', '_'];