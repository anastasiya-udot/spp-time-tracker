 TimeTrackerApplication
    .factory('PostData', ['$http', function($http){
        return function(url, data, callback){
            const config = {
                headers : {
                    'Content-Type': 'application/json;charset=utf-8;'
                }
            };

            $http.post(url, data, config)
                .then(function (response) {
                    callback(response)
                });
            };
    }])

    .factory('DeleteData', ['$http', function($http){
        return function(url, callback){

            $http({
                method: 'DELETE',
                url: url,
                headers: {'Content-Type': 'application/json;charset=utf-8'}
            }).then(function(data){
               callback(data);
            });
        }
    }])

    .factory('GetData', ['$http', function($http){
        return function(url, callback){
            const config = {
                headers : {
                    'Content-Type': 'application/json;charset=utf-8;'
                }
            };

            $http.get(url, config)
                .then(function (data) {
                    callback(data)
                });
        };
    }]);