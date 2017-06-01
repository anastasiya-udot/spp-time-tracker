TimeTrackerApplication
    .controller('OrderWorktimeController', OrderWorktimeDialogController);

function OrderWorktimeDialogController($scope, SessionService, ngDialog) {
   
    $scope.start = moment().subtract(1, 'days');
    $scope.end = moment();
    $scope.fileType = 'pdf';

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
        var url = '/documents/worked/' + $scope.fileType + "?employee_id=" + SessionService.getCurrentPageUserId() + '&isProtected=true';
        window.open(url);
        ngDialog.closeAll();
    };
}

OrderWorktimeDialogController.$inject = ['$scope', 'SessionService', 'ngDialog'];