 TimeTrackerApplication
    .factory('StartStopService', StartStopServiceController);

function StartStopServiceController(PostData, $rootScope, SessionService, _) {
    return {
        _processStartSession: function(res) {
            if (res.status) {
                $rootScope.showPlayButton = false;
            }
        },
        _processStopSession: function(res) {
            if (res.status) {
                $rootScope.showPlayButton = true;
            }
        },
        stopSession: function(callback) {
            var url = "/finish-preiod/post";
            var data = {
                id: SessionService.getCurrentPageUserId(),
                finishPeriod: (new Date()).getTime()
            };
            var processStopSession = _.bind(function(res) {
                this._processStopSession(res);
                callback();
            }, this);

            PostData(url, data, processStopSession);
        },
        startSession: function(callback) {
            var url = '/start-period/post';
            var data = {
                id: SessionService.getCurrentPageUserId(),
                startPeriod: (new Date()).getTime()
            };
            var processStartSession = _.bind(function(res) {
                this._processStartSession(res);
                callback();
            }, this);

            PostData(url, data, processStartSession);
        }
    }
}

 StartStopServiceController.$inject = ["PostData", "$rootScope", "SessionService", "_"];