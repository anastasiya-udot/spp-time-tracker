 TimeTrackerApplication
    .factory('StartStopService', StartStopServiceController);

function StartStopServiceController() {
    return {
        isStarted: true,
        isStoped: false,
        setStart: function() {
            this.isStarted = true;
            this.isStoped = false;
        },
        setStop: function() {
            this.isStarted = false;
            this.isStoped = true;
        }
    }
}