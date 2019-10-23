import { ReducerRegistry }  from '../base/redux';
let stateDefault = {
    logs: [],
    roomLogs: [],
    userLogs: []
}

ReducerRegistry.register('logs', (state = stateDefault, action) => {
    switch (action.type) {
        case "GET_LOGS":
            return {
                ...state,
                logs: action.logs
            };
        case "GET_LOGS_BY_ROOM_ID":
            return {
                ...state,
                roomLogs: action.roomLogs
            };
        case "GET_LOGS_BY_USER_ID":
            return {
                ...state,
                userLogs: action.userLogs
            };
        default:
            return state;
    }
});