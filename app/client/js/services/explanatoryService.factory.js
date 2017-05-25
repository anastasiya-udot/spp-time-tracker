 TimeTrackerApplication
    .factory('ExplanatoryService', ExplanatoryServiceController);

function ExplanatoryServiceController(PostData, _) {
    return {
        explanatory: {},
        get: function() {
            return this.explanatory;
        },
        set: function(expl) {
            this.explanatory = expl;
        },
        send: function(data, callback) {
            var url = '/add-new-request/post'
            PostData(url, data, callback);
        }
    }
}

ExplanatoryServiceController.$inject = ['PostData', '_'];