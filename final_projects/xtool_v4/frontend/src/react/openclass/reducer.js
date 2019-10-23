import { ReducerRegistry }  from '../base/redux';
let stateDefault = {
    page: "DASHBOARD",
    data:[],
    userlist:[],
    userTL:[],
    roomTL: []
}

ReducerRegistry.register('xtool/openclass', (state = stateDefault, action) => {
    switch (action.type) {
        case "CHANGE_PAGE":
            return {
                ...state,
                page: action.page
            }
        case "GET_ROOM":
                return {
                    ...state,
                    data: action.data
                }
        case "GET_USER_LIST":
                return{
                    ...state,
                    userlist: action.userlist
                }        
        case "GET_USER_TL":
                return{
                    ...state,
                    userTL: action.userTL
                } 
        case "GET_ROOM_NTL":
                return{
                    ...state,
                    roomTL: action.roomTL
                }
        default:
            return state;
    }
});
