TimeTrackerApplication
    .factory('FactTime', FactTimeController);

function FactTimeController(PostData) {
    return {
        get: function(data, callback) {
            var url = '/worktime-for-period/get';
            PostData(url, data, callback);
        }
    }
}

FactTimeController.$inject = ['PostData'];