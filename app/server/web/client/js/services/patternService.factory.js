TimeTrackerApplication
    .factory('PatternService', PatternServiceController);

function PatternServiceController() {
    return {
        name: "^[\\da-zA-Z]{3,20}$",
        email: "^[\\da-zA-Z]+([\\.\\-\\_][\\da-zA-Z]+)*@[\\da-zA-Z]+\\.[a-zA-Z]+$",
        password: "^[\\da-zA-Z]{8,15}$",
        company: "^([\\da-zA-Z]+[-., ])+[\\da-zA-Z]+[.]{,1};"
    }
}