TimeTrackerApplication
    .controller('OrderTasksDialogController', OrderTasksDialogController);

function OrderTasksDialogController($scope) {
    $scope.start = moment().subtract(1, 'days');
    $scope.end = moment();

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
}

OrderTasksDialogController.$inject = ['$scope'];