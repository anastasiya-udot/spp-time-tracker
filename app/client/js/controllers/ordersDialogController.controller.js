TimeTrackerApplication
    .controller('OrdersDialogController', OrdersDialogController);

function OrdersDialogController($scope, ngDialog) {
    $scope.openTasksPeriodDialog = function() {
        ngDialog.open({
            template: '../../templates/dialogs/dialog-order-tasks.html',
            className: 'ngdialog-theme-default',
            scope: $scope,
            height: 220,
            width: 500,
            name: "order_tasks",
            controller: OrderTasksDialogController
        })
    };

    $scope.openWorktimePeriodDialog = function() {
        ngDialog.open({
            template: '../../templates/dialogs/dialog-order-worktime.html',
            className: 'ngdialog-theme-default',
            scope: $scope,
            height: 220,
            width: 500,
            name: "order_worktime",
            controller: OrderWorktimeDialogController
        })
    };
}

OrdersDialogController.$inject = ['$scope', 'ngDialog'];
