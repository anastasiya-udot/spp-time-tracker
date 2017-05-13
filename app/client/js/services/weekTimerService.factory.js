TimeTrackerApplication
    .factory('WeekService', WeekServiceController);

function WeekServiceController(DrawTimer, StartStopService) {
    return {
        elementId: "#circle-week",
        requiredWorktime: 0,
        actualWorktime: 100800000,
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
            "startDate" : moment().startOf('week').startOf('day'),
            "endDate" : moment().endOf('week').endOf('day')
        },
        setRequiredWorktime: function(workDayHours) { //!!!
            this.requiredWorktime = 7 * workDayHours * 60 * 60 * 1000;
        },
        generateDisplay: function() {
            return this.dates.startDate.format('MMMM Do') + " - " + this.dates.endDate.format('MMMM Do');
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
        setPreviousWeekDates: function(count, callback) {
            this.dates = {
                "startDate" : this.dates.startDate.subtract(1, "week"),
                "endDate" : this.dates.endDate.subtract(1, "week")
            };

           // this.getStatisticsForWeek(callback);
        },
        setNextWeekDates: function(count, callback) {
            this.dates = {
                "startDate" : this.dates.startDate.add(1, "week"),
                "endDate" : this.dates.endDate.add(1, "week")
            };

           // this.getStatisticsForWeek(callback);
        },
        setActualWorktime: function(time) {
            this.actualWorktime = time;
        },
        checkCurrentMomentInPeriod: function() {
            var currentDate = new Date();

            return this.dates.startDate.valueOf() <= currentDate.valueOf() &&
                this.dates.endDate.valueOf() >= currentDate.valueOf();
        },
        getStatisticsForWeek: function(callback) { //!!!!
            //use startDate and endDate
            //callback from controller where
            //setActualWorktime as callback
            //drawTimer as callback
            var _this = this;

            setTimeout(function() {
                _this.drawTimer();
                callback();
            }, 500);
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

WeekServiceController.$inject = ["DrawTimer", "StartStopService"];