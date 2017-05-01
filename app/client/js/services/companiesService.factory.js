 TimeTrackerApplication
    .factory('CompaniesService', CompaniesServiceController);

function CompaniesServiceController(GetData, _) {
    return {
        errors: {
            companies: false
        },
        companies: [
            {
                id: 1,
                name: "Happy Tree Friends"
            },
            {
                id: 2,
                name: "My Duck's vision"
            },
            {
                id: 3,
                name: "Abstergo Entertainment"
            },
            {
                id: 4,
                name: "Epam Global"
            },
            {
                id: 5,
                name: "Resilio Inc."
            }
        ],
        _processResponse: function(res) {
            if (res.status == 200) {
                this.companies = res.companies;
                this.errors.companies = false;
            } else {
                this.errors.companies = true;
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
        get: function() {
            GetData('/companies/get', this._processResponse);
        }
    }
}

CompaniesServiceController.$inject = ['GetData', '_']