TimeTrackerApplication
    .controller('ChangeUserInfoDialogController', ChangeUserInfoDialogController);

function ChangeUserInfoDialogController($scope, _) {
    /*$scope.types = _.map(WorkdayTypes.getTypes(), function (model) {
        return {
            text: model.name,
            value: model.id
        };
    });

    $scope.type = $scope.companies[0] || { text: "No companies"};*/

    $scope.types = [
        {
            text: "full day",
            value: 8
        },
        {
            text: "half of the day",
            value: 4,
        },
        {
            text: "no limit",
            value: 0
        }
    ];

    $scope.type = $scope.types[0];

}

ChangeUserInfoDialogController.$inject = ['$scope', '_'];