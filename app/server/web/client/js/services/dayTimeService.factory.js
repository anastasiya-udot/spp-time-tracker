TimeTrackerApplication
    .factory('DayService', DayServiceController);

function DayServiceController($rootScope, FactTime, SessionService, _) {
    return {
        dates: {
            "startDate" : moment().startOf('day'),
            "endDate" : moment().endOf('day')
        },
        actualTime: 0,
        setPreviousDayDates: function(callback) {
            this.dates = {
                "startDate" : this.dates.startDate.subtract(1, "day"),
                "endDate" : this.dates.endDate.subtract(1, "day")
            };

            this.getTime(callback);
        },
        setNextDayDates: function(callback) {
            this.dates = {
                "startDate" : this.dates.startDate.add(1, "day"),
                "endDate" : this.dates.endDate.add(1, "day")
            };
            this.getTime(callback);
        },
        _processGetTime: function(res) {
            if (res.status === 200) {
                $rootScope.sessionStartedTime = res.data.startPeriod;
                this.actualTime = res.data.sum;
            }
        },
        getTime: function(callback) {
            var data = {
              id: SessionService.getCurrentPageUserId(),
              startPeriod: (new Date(this.dates.startDate._d)).getTime(),
              endPeriod: (new Date(this.dates.endDate._d)).getTime()
            };
            FactTime.get(data, _.bind(function(res) {
                this._processGetTime(res);
                callback();
            }, this));
        }
    }
}

DayServiceController.$inject = ["$rootScope", "FactTime", "SessionService", "_"];