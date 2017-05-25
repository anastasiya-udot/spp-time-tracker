 TimeTrackerApplication
    .factory('TasksService', TasksService);

function TasksService(GetData) {
    return {
         tasks: [],
          getTasks: function() {
              return this.tasks;
          }
      }
  
 }
 
 TasksService.$inject = ['GetData']; 