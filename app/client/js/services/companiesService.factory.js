 TimeTrackerApplication
    .factory('CompaniesService', CompaniesServiceController);

function CompaniesServiceController(GetData, _) {
    return {
        errors: {
            companies: false
        },
        companies: [],
        _processResponse: function(res) {
            this.errors = {};
            switch (res.status) {
                case 200: {
                    this.companies = res.data.companies;
                    this.errors.companies = false;
                }; break;
                case 500: {
                    this.companies = [];
                    this.errors.companies = true;
                }
            }
        },
        has: function(name) {
            return !!_.find(this.companies, function(company) {
                return company.name.toUpperCase() === name.toUpperCase();
            });
        },
        getCompanies: function() {
            return this.companies;
        },
        get: function(callback) {
            GetData('/companies/get', _.bind(function(res) {
                this._processResponse(res);
                callback();
            }, this));
        }
    }
}

CompaniesServiceController.$inject = ['GetData', '_']