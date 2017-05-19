TimeTrackerApplication
    .controller('OrderWorktimeController', OrderWorktimeDialogController);

function OrderWorktimeDialogController($scope, fileDialog) {
   
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


      $scope.saveFile = function(){
        fileDialog.saveAs(function(filename) {
            // your code
        });
    }
}

OrderWorktimeDialogController.$inject = ['$scope', 'fileDialog'];