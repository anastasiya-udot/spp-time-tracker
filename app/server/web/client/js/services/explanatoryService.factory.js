 TimeTrackerApplication
     .factory('ExplanatoryService', ExplanatoryServiceController);
 
 function ExplanatoryServiceController(PostData, DeleteData, _) {
     return {
         explanatory: {},
         get: function() {
             return this.explanatory;
         },
         set: function(expl) {
             this.explanatory = expl;
         },
         send: function(data, callback) {
             var url = '/add-new-request/post'
             PostData(url, data, callback);
         },
         update: function(callback) {
             var url = '/update-request/post';
             var data = {
                 id: this.explanatory.id,
                 content: this.explanatory.content
             };

             PostData(url, data, callback);

         },
         remove: function(callback) {
             var id = this.get().id;
             var url = '/request/delete/' + id;

             DeleteData(url, _.bind(function() {
                callback(id);
             }, this));
         }
     }
 }
 
 ExplanatoryServiceController.$inject = ['PostData', 'DeleteData', '_'];