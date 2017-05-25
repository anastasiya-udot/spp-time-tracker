 TimeTrackerApplication
    .factory('TasksService', TasksService);

function TasksService(PostData, _) {
    return {
        tasks: [],
        getTasks: function() {
            return this.tasks;
        },
        _processData: function(res) {
            var _this = this;

            if (res.status === 200) {
                _.each(res.data, function(task) {
                    _this.tasks.push(task);
                });
            }
        },
        get: function(id, start, finish, callback) {
            var url = '/new-task/post';
            var processData = _.bind(function(res) {
                this._processData(res);
                callback();
            }, this);
            var data = {
                id: id,
                startPeriod: start,
                finishPeriod: finish
            }

            PostData(url, data, processData)
        }
    }
}

TasksService.$inject = ['PostData', '_'];