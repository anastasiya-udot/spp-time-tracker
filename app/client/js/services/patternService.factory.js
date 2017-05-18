TimeTrackerApplication
    .factory('PatternService', PatternServiceController);

function PatternServiceController() {
    return {
        name: "^[\\dа-яА-ЯёЁa-zA-Z]{3,20}$",
        email: "^[\\da-zA-Z]+([\\.\\-\\_][\\da-zA-Z]+)*@[\\da-zA-Z]+\\.[a-zA-Z]+$",
        password: "^[\\da-zA-Z]{8,15}$",
        company: "^([\\dа-яА-ЯёЁa-zA-Z]+[-., ])+[\\dа-яА-ЯёЁa-zA-Z]+[.]{,1};"
    }
}