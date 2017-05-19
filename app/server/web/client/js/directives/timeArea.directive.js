TimeTrackerApplication
    .directive('timeArea', TimeAreaDirective);

function TimeAreaDirective() {
    return {
        restrict: "E",
        templateUrl: '../../public/templates/employee-page/time-area.html',
        controller: TimeAreaDirectiveController
    }
}

function TimeAreaDirectiveController($scope, WeekService, DayService, StartStopService) {
    var _this = this;

    $scope.excessiveTime = {
        week: false,
        day: false
    };

    function setExcessiveDayTime() {
        $scope.excessiveTime.day = true;
    }

    function unsetExcessiveDayTime() {
        $scope.excessiveTime.day = false;
    }

    function setExcessiveWeekTime() {
        $scope.excessiveTime.week = true;
    }

    function unsetExcessiveWeekTime() {
        $scope.excessiveTime.week = false;
    }
    
    var traking = true;
    var weekIntervalSecond;
    var dayIntervalSecond;

    $('.play-button').addClass('hidden');
    $('.stop-button').removeClass('hidden');

    function startTracking() {
        weekIntervalSecond = setInterval(function() {
            var date;

            WeekService.actualWorktime += 1000;
            $scope.excessiveTime.week = WeekService.checkIsExcessiveTime();
            date = new Date(WeekService.actualWorktime);
            $('#tracking-week-days').text(Math.trunc(WeekService.actualWorktime / 1000 / 60 / 60 / 8) + " d");
            $('#tracking-week-hours').text(date.getHours()  + " h");
            $('#tracking-week-minutes').text(date.getMinutes() + " min");
        }, 1000);

        dayIntervalSecond = setInterval(function() {
            var date;

            DayService.actualWorktime += 1000;
            $scope.excessiveTime.day = DayService.checkIsExcessiveTime();
            date = new Date(DayService.actualWorktime);
            $('#tracking-day-hours').text(Math.trunc(DayService.actualWorktime / 60 / 60 / 1000) + " h");
            $('#tracking-day-minutes').text(date.getMinutes() + " min");
        }, 1000);
    }

    function stopTracking() {
        if (weekIntervalSecond) {
            clearInterval(weekIntervalSecond);
        }

        if (dayIntervalSecond) {
            clearInterval(dayIntervalSecond);
        }
    }

    $scope.startClick = function() {
        startTracking();
        $('.play-button').addClass('hidden');
        $('.stop-button').removeClass('hidden');
    };

    $scope.stopClick = function() {
         stopTracking();
         $('.play-button').removeClass('hidden');
         $('.stop-button').addClass('hidden');
        //_.each(DrawTimer.getCircleIDs(), function(elementId) {
         //   DrawTimer.setCircleInValue(elementId).circle
        //});
    };

    function updateTimeTracking() {
        if (StartStopService.isStarted) {
            startTracking();
        } else {
            stopTracking();
        }
    }

    WeekService.setExcessiveTimeCallback(setExcessiveWeekTime, this);
    WeekService.setRequiredWorktime(8);
    WeekService.getStatisticsForWeek(updateTimeTracking.bind(this));

    DayService.setExcessiveTimeCallback(setExcessiveDayTime, this);
    DayService.setRequiredWorktime(8);
    DayService.getStatisticsForDay(updateTimeTracking.bind(this));
}

TimeAreaDirectiveController.$inject = ["$scope", "WeekService", "DayService", "StartStopService"];