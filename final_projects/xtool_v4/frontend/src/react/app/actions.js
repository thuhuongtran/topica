import{
    loginApi, setLocalstorage
}                           from './apis';

import config               from'../config';

export function changeUserinfo(userInfo){
    return {
        type        : "CHANGE_USERINFO",
        userInfo
    };
}

export function logout(){
    return {
        type        : "LOGOUT"
    };
}

export function changePage(page){
    return {
        type        : "CHANGE_PAGE",
        page
    };
}

export function updateUserInfo(userInfo){
    return function (dispatch, getState){
        dispatch(changeUserinfo(userInfo));
    }
}

export function submitLogout(){
    localStorage.removeItem(config.localstorageKey.userinfo);
}

export function login(username,password){
    return function( dispatch, getState ) {
        let state = getState();
        loginApi( username,password ).then((loginResult)=>{
            let {accessToken: tokenApi, username, participantId, displayName, roleName: role} = loginResult.result;  
            let system = "";
            if(loginResult.status) {
                switch(loginResult.result.systemType) {
                    case config.system.vietnam:
                        system = config.system.vietnam;
                        setLocalstorage(config.localstorageKey.userinfo,JSON.stringify({tokenApi, system, username, participantId, displayName, role}));
                        dispatch(changeUserinfo({tokenApi, system, username, participantId, displayName, role}));
                        break;
                    case config.system.thailand:
                        system = config.system.thailand;
                        setLocalstorage(config.localstorageKey.userinfo,JSON.stringify({tokenApi, system, username, participantId, displayName, role}));
                        dispatch(changeUserinfo({tokenApi,system, username, participantId, displayName, role}));
                        break;
                    default:
                        break;
                }
            } else {
                alert("Error");
            }
        });
    }
}
