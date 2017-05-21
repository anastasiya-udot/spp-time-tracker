TimeTrackerApplication
    .controller('NewRequestFormController', NewRequestFormController);

function NewRequestFormController($scope, ExplanatoryService, RequestsService, SessionService, FactTime, _) {

    $scope.start = moment().subtract(1, 'days');
    $scope.end = moment();

    function getWorktimeForPeriod () {
        var deferred = $.Deferred();
        var data = {
            id: SessionService.getCurrentPageUserId(),
            startPeriod: (new Date($scope.start._d)).getTime(),
            endPeriod: (new Date($scope.end._d)).getTime()
        };
        FactTime.get(data, deferred.resolve);
        return deferred.promise();
    }

    $scope.checkRangeChanged = function() {
        getWorktimeForPeriod().done(function(res) {
            
        });
    };

    $scope.checkRangeChanged

    $scope.presets = [
        {
            'name': 'This Week',
            'start': moment().startOf('week').startOf('day'),
            'end': moment().endOf('week').endOf('day'),
        },
        {
            'name': 'This Day',
            'start': moment().startOf('day'),
            'end': moment().endOf('day')
        }
    ];

    $scope.sendNewExplanatoryRequest = function() {
        let processResponse = _.bind(function(res) {
            RequestsService.addNewRequest(res.data)
        }, this);
        let data = {
            idSource: SessionService.getSessionUserId(),
            idDestination: SessionService.getCurrentPageUserId(),
            startPeriod: (new Date($scope.start._d)).getTime(),
            endPeriod: (new Date($scope.end._d)).getTime(),
            date: (new Date()).getTime()
        };

        function sendNewRequest() {
            var deferred = $.Deferred();
            ExplanatoryService.send(data, deferred.resolve);
            return deferred.promise();
        }

        sendNewRequest().done(function(res) {
            processResponse(res);
        });
    }
}

NewRequestFormController.$inject = ['$scope', 'ExplanatoryService', 'RequestsService', 'SessionService', 'FactTime', '_'];