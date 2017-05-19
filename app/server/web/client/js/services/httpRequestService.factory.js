 TimeTrackerApplication
    .factory('PostData', [ function($http){
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
            /*callback({
                data: {
                    user: {
                        name: "Nastya",
                        surname: "Udot",
                        patroymic: "Yuryevna",
                        email: "anastasiya@gmail.com"
                    },
                    token: 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjEiLCJzdXJuYW1lIjoiVWRvdCIsImVtYWlsIjoiYW5hc3Rhc2l5YUBnbWFpbC5jb20ifQ.d5PI2a24YXMqyxmdbtMA3dOHCJ-ctMFXXYL2FJaEBFA'
                },
                status: 200
            });*/
        //}
    }])

    .factory('DeleteData', ['$http', function($http){
        return function(url, data, resolve, reject){

            $http({
                method: 'DELETE',
                url: url,
                data: data,
                headers: {'Content-Type': 'application/json;charset=utf-8'}
            }).then(function(response){
                if( angular.isDefined(response.error)){
                    reject(response);
                } else {
                    resolve(response)
                }
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