TimeTrackerApplication
    .factory('InitialPageLoader', initialPageLoader);

function initialPageLoader(_) {
    return {
        currentTab: 'registerCompany',
        initializeRender: function(render) {
            this.renderScope = render;
            this.setTab(this.currentTab);
        },
        tabs: [
            'registerCompany',
            'signIn',
            'signUp'
        ],
        getTabs: function() {
            return this.tabs;
        },
        setTab: function(tab) {
            this.currentTab = tab;
            this.renderScope(tab);
        }  
    };
}

initialPageLoader.$inject = ['_'];
