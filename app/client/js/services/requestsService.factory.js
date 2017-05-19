TimeTrackerApplication
    .factory('RequestsService', RequestsServiceController);

function RequestsServiceController(GetData, _) {
    return {
        errors: {
            requests: false
        },
        requests: [
            {
                id: 1,
                sender:{
                    id: 1,
                    name: "Zoya",
                    surname: "Raxtenberg"
                },
                date: "10.02.2008"
            },
            {
                id: 2,
                sender:{
                    id: 2,
                    name: "Petr",
                    surname: "Perviy"
                },
                date: "07.09.2016"
            },
            {
                id: 3,
                sender:{
                    id: 3,
                    name: "Artyr",
                    surname: "Belyakov"
                },
                date: "15.09.2006"
            },
            {
                id: 4,
                sender:{
                    id: 4,
                    name: "Alexandr",
                    surname: "Petkyn"
                },
                date: "21.10.2016"
            }
        ],
        getRequests: function() {
            return this.requests;
        }
    }
}