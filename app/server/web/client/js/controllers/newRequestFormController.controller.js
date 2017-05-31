TimeTrackerApplication
    .controller('NewRequestFormController', NewRequestFormController);

function NewRequestFormController($scope, EmployeeService, ExplanatoryService, RequestsService, SessionService, FactTime, ngDialog, _) {

    $scope.start = moment().subtract(1, 'days');
    $scope.end = moment();


    function getWorktimeForPeriod () {
        let deferred = $.Deferred();
        let data = {
            id: SessionService.getCurrentPageUserId(),
            startPeriod: (new Date($scope.start._d)).getTime(),
            endPeriod: (new Date($scope.end._d)).getTime()
        };
        FactTime.get(data, deferred.resolve);
        return deferred.promise();
    }

    function countExpected() {
        let weekExpectedHours = EmployeeService.employee.worktype;
        let timeDelta = (new Date($scope.end._d)).getTime() - (new Date($scope.start._d)).getTime();

        return Math.trunc(timeDelta * weekExpectedHours / (7 * 24 * 60 * 60 * 1000));
    }

    $scope.checkRangeChanged = function() {
        $scope.expected = countExpected() + ' hours';
        let processResponse = _.bind(function(data) {
            $scope.inFact = Math.trunc(data.sum / 1000 / 60 / 60) + ' hours';
        }, $scope);
        getWorktimeForPeriod().done(function(res) {
            processResponse(res.data)
        });
    };

    $scope.checkRangeChanged();

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
            ngDialog.closeAll();

        });
    }
}

NewRequestFormController.$inject = ['$scope', 'EmployeeService', 'ExplanatoryService', 'RequestsService', 'SessionService', 'FactTime', 'ngDialog', '_'];