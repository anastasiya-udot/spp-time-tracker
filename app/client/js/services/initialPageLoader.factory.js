TimeTrackerApplication
    .factory('InitialPageLoader', InitialPageLoaderController);

function InitialPageLoaderController(GetData, _) {
    return {
        currentTab: 'signUp',
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
        },
        setCompanies: function(companies) {
            this.companies = companies;
        },
        getCompnies: function(callback) {
            GetData('/companies/get', callback);
        }
    };
}

InitialPageLoaderController.$inject = ['GetData', '_'];
