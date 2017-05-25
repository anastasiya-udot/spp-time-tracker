TimeTrackerApplication
    .factory('DayService', DayServiceController);

function DayServiceController(DrawTimer, StartStopService, FactTime) {
    return {
        elementId: "#circle-day",
        requiredWorktime: 0,
        actualWorktime:  5 * 60 * 60 * 1000,
        onExcessiveTime: null,
        setExcessiveTimeCallback: function(callback) { //!!!
            this.onExcessiveTime = callback;
        },
        getDuration: function() {
            return {
                duration: Math.abs(this.requiredWorktime - this.actualWorktime)
            };
        },
        dates: {
            "startDate" : moment().startOf('day'),
            "endDate" : moment().endOf('day'),
        },
        setRequiredWorktime: function(workDayHours) {
            this.requiredWorktime = workDayHours * 60 * 60 * 1000;
        },
        getStartValue: function() {
            var value = this.actualWorktime / this.requiredWorktime;

            if (this.checkIsExcessiveTime()) {
                value = value - Math.floor(value);
                 this.onExcessiveTime();
            }

            return value;
        },
        checkIsExcessiveTime: function() {
            return this.actualWorktime > this.requiredWorktime;
        },
        setPreviousDayDates: function(callback) {
            this.dates = {
                "startDate" : this.dates.startDate.subtract(1, "day"),
                "endDate" : this.dates.endDate.subtract(1, "day")
            };

            this.getStatisticsForDay(callback);
        },
        generateDisplay: function() {
            return this.dates.startDate.format('MMMM Do');
        },
        setNextDayDates: function(callback) {
            this.dates = {
                "startDate" : this.dates.startDate.add(1, "day"),
                "endDate" : this.dates.endDate.add(1, "day")
            };

            this.getStatisticsForDay(callback);
        },
        setActualWorktime: function(time) {
            this.actualWorktime = time;
        },
        checkCurrentMomentInPeriod: function() {
            var currentDate = new Date();

            return this.dates.startDate.valueOf() <= currentDate.valueOf() &&
                this.dates.endDate.valueOf() >= currentDate.valueOf();
        },
        getStatisticsForDay: function(callback) { //!!!!
            //use startDate and endDate
            //callback from controller where
            //setActualWorktime as callback
            //drawTimer as callback
            var _this = this;

            function getDayTime() {
                var deferrred = $.Deferred();
            }
            _this.drawTimer();

        },
        drawTimer: function() {
            if (this.checkCurrentMomentInPeriod() && StartStopService.isStarted) {
                DrawTimer.initActiveCircle(
                    this.elementId,
                    this.getDuration(),
                    this.getStartValue()
                );
            } else {
                DrawTimer.initStaticCircle(
                    this.elementId,
                    this.getStartValue()
                );
            }
        }
    }
}

DayServiceController.$inject = ["DrawTimer", "StartStopService"];