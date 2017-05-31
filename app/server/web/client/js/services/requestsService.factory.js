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
                     date: moment(new Date(req.date)).format("D MMM YYYY"),
                     startPeriod: {
                         string: moment(new Date(req.startPeriod)).format("D MMM YYYY"),
                         value: req.startPeriod
                     },
                     endPeriod: {
                         string: moment(new Date(req.endPeriod)).format("D MMM YYYY"),
                        value: req.endPeriod
                     },
                     content: req.content || ''
                 }
             });
          },

           getRequests: function() {
             return this.requests;
         },
        removeById: function(id) {
             delete this.getById(id);
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
                  date: moment(new Date(req.date)).format("D MMM YYYY"),
                 startPeriod: {
                     string: moment(new Date(req.startPeriod)).format("D MMM YYYY"),
                     value: req.startPeriod
                  },
                  endPeriod: {
                     string: moment(new Date(req.endPeriod)).format("D MMM YYYY"),
                     value: req.endPeriod
                  },
                  content: req.content || null
              };
              this.requests.push(newRequest);
         },
        _processData: function(res) {
             if (res.status === 200) {
                 this.setRequests(res.data.requests);
             }
        },
         get: function(callback) {
             var url = '/requests/get/' + SessionService.getCurrentPageUserId();
             var processData = _.bind(function(res) {
                 this._processData(res);
                 callback();
             }, this);

             GetData(url, processData);
          }
      }
}
 
 RequestsServiceController.$inject = ['GetData', 'SessionService', '_'];




