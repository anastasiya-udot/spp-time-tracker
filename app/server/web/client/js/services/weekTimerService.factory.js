TimeTrackerApplication
    .factory('WeekService', WeekServiceController);

function WeekServiceController($rootScope, FactTime, SessionService, _) {
    return {
        dates: {
            "startDate" : moment().startOf('week').startOf('day'),
            "endDate" : moment().endOf('week').endOf('day')
        },
        actualTime: 0,
        setPreviousWeekDates: function(callback) {
            this.dates = {
                "startDate" : this.dates.startDate.subtract(1, "week"),
                "endDate" : this.dates.endDate.subtract(1, "week")
            };

            this.getTime(callback);
        },
        setNextWeekDates: function(callback) {
            this.dates = {
                "startDate" : this.dates.startDate.add(1, "week"),
                "endDate" : this.dates.endDate.add(1, "week")
            };

            this.getTime(callback);
        },
        _processGetTime: function(res) {
            if (res.status === 200) {
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

WeekServiceController.$inject = ["$rootScope", "FactTime", "SessionService", "_"];