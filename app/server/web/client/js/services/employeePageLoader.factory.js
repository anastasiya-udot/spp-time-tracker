 TimeTrackerApplication
    .factory('EmplyeePageLoader', EmplyeePageLoaderController);

function EmplyeePageLoaderController($location, SessionService, RoleService) {
    return {
        load: function(token) {
            let userId;
            let roleId;

            SessionService.startSession(token);

            userId = SessionService.getSessionUserId();
            roleId = SessionService.getSessionRoleId();

            RoleService.set(roleId, function() {
                $location.path('/employee/' + userId);
            });
        }
    }
}

EmplyeePageLoaderController.$inject = ["$location", "SessionService", "RoleService"];