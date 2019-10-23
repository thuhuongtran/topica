import { ReducerRegistry }  from '../base/redux';
let stateDefault = {
    page: "DASHBOARD",
    listlog: []
}

ReducerRegistry.register('xtool/logconnection', (state = stateDefault, action) => {
    switch (action.type) {
        case "CHANGE_PAGE":
            return {
                ...state,
                page: action.page
            }
        case "GET_LOG_INFO":
                return {
                    ...state,
                    listlog: action.listlog
                }
        default:
            return state;
    }
});
