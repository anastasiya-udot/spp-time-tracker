 TimeTrackerApplication
    .factory('SessionService',[ '$window', '$rootScope', '$location', function($window, $rootScope, $location){
        return {
            observe: function(){
               /* $rootScope.loggedIn = false;

                if ($window.sessionStorage.token){
                    return $rootScope.loggedIn = true;
                }*/
                $rootScope.loggedIn = true;
            },
            getCurrentPageUserId: function(){
                let url = $location.absUrl();
                return url.split('/').splice(-1,1)[0];
            },
            startSession: function(token){
                $window.sessionStorage.token = token;
            },
            destroySession: function(){
                delete $window.sessionStorage.token;
            },
            getSessionRoleId: function() {
                /* let token =  $window.sessionStorage.token;
  
 
                 if (token){
                      let payload = token.split('.')[1];
  
                      payload = $window.atob(payload);
                      payload = JSON.parse(payload);
                 
                    return payload.role;
                  }
                return 0;*/
                return 31;
            },
            getSessionUserId : function(){
                let token =  $window.sessionStorage.token;

                if(token){
                    let payload = token.split('.')[1];

                    payload = $window.atob(payload);
                    payload = JSON.parse(payload);
                    return payload.id;
                }
                return null;
            }
        }
    }]);