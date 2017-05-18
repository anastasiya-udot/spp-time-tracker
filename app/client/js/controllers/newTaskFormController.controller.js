TimeTrackerApplication
	.controller('NewTaskFormController', NewTaskFormController);

function NewTaskFormController($scope) {

	$scope.addNewTask = function() {
		console.log($scope.newTaskCode);
	};
}

NewTaskFormController.$inject = ['$scope'];
