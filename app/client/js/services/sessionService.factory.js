 TimeTrackerApplication
    .factory('SessionService',[ '$window', '$rootScope', '$location', function($window, $rootScope, $location){
        return {
            isLogged: false,
            observe: function(){
                $rootScope.loggedIn = false;
                this.checkPermission();

                if ($window.sessionStorage.token){
                    return $rootScope.loggedIn = true;
                }
            },
            getCurrentPageId: function(){
                let url = $location.absUrl();
                return url.split('/').splice(-1,1);
            },
            checkPermission: function(currentPageUserId){
                if(!currentPageUserId)
                    currentPageUserId = this.getCurrentPageId();

                if (this.getSessionUserId()){
                    if(this.getSessionUserId() == currentPageUserId){
                        $rootScope.role = "owner";
                    } else {
                        $rootScope.role = "guest";
                    }
                } else {
                    $rootScope.role = "unauthorized";
                }
            },
            startSession: function(token){
                $window.sessionStorage.token = token;
            },
            destroySession: function(){
                delete $window.sessionStorage.token;
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