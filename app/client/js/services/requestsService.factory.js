TimeTrackerApplication
    .factory('RequestsService', RequestsServiceController);

function RequestsServiceController(GetData, SessionService, _) {
    return {
        requests: [],
        setRequests: function(requests) {
            this.requests = _.map(requests, function(req) {
                return {
                    id: req.id,
                    sender: {
                        id: req.sender.id,
                        name: req.sender.name,
                        surname: req.sender.surname
                    },
                    date: (new Date(req.date)).format("yyyy-mm-dd"),
                    startPeriod: {
                        string: (new Date(req.startPeriod)).format("yyyy-mm-dd"),
                        value: req.startPeriod
                    },
                    endPeriod: {
                        string: (new Date(req.endPeriod)).format("yyyy-mm-dd"),
                        value: req.endPeriod
                    },
                    content: req.content || 'null'
                }
            });
        },
        getRequests: function() {
            return this.requests;
        },
        getById: function(id) {
            return _.find(this.requests, function(req) {
                return req.id === id;
            });
        },
        addNewRequest: function(req) {
            var newRequest = {
                id: req.id,
                sender: {
                    id: req.sender.id,
                    name: req.sender.name,
                    surname: req.sender.surname
                },
                date: (new Date(req.date)).format("yyyy-mm-dd"),
                startPeriod: {
                    string: (new Date(req.startPeriod)).format("yyyy-mm-dd"),
                    value: req.startPeriod
                },
                endPeriod: {
                    string: (new Date(req.endPeriod)).format("yyyy-mm-dd"),
                    value: req.endPeriod
                },
                content: req.content || null
            }
            this.requests.push(newRequest);
        },
        get: function(callback) {
            var url = '/requests/get/' + SessionService.getCurrentPageUserId();
            var processResponse = _.bind(function(res) {
                this.setRequests(res.data);
                callback();
            }, this);

            GetData(url, processResponse);
        }
    }
}

RequestsServiceController.$inject = ['GetData', 'SessionService', '_'];