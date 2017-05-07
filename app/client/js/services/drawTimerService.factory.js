TimeTrackerApplication
    .factory('DrawTimer', DrawTimerContoller);

function DrawTimerContoller() {
    return {
        getRandomColor: function() {
            var letters = '0123456789ABCDEF';
            var color = '#';
            for (var i = 0; i < 6; i++ ) {
                color += letters[Math.floor(Math.random() * 16)];
            }
            return color;
        },
        initCircle: function(elementId, animation, startValue, value) {
            $(elementId).circleProgress({
                value: value,
                size: 250,
                startAngle: -Math.PI / 2,
                fill: {
                    gradient: [this.getRandomColor(), this.getRandomColor()],
                },
                animation: animation,
                animationStartValue: startValue
            });
        }
    }
} 