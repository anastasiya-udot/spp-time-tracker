TimeTrackerApplication
    .controller('OrderTasksDialogController', OrderTasksDialogController);

function OrderTasksDialogController($scope, FileSaver, ngDialog, Blob, _) {
    $scope.start = moment().subtract(1, 'days');
    $scope.end = moment();
    $scope.fileType = '.pdf';

    $scope.presets = [
        {
            'name': 'This Week',
            'start': moment().startOf('week').startOf('day'),
            'end': moment().endOf('week').endOf('day')
        },
        {
            'name': 'This Day',
            'start': moment().startOf('day'),
            'end': moment().endOf('day')
        }
    ];

    $scope.saveFile = function(){
        var data = new Blob(['hey!!'], { type: 'text/plain;charset=utf-8' });
        FileSaver.saveAs(data, 'order' + $scope.fileType);
        ngDialog.closeAll();
    };
}

OrderTasksDialogController.$inject = ['$scope', 'FileSaver', 'ngDialog', 'Blob', '_'];