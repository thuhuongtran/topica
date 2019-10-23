import {
    getLogApi
}                           from './apis';

export function changePage(page){
    return {
        type        : "CHANGE_PAGE",
        page
    };
}

export function getLogInfo(listlog){
    return {
        type        : "GET_LOG_INFO",
        listlog
    }; 
}

export function getLog(type, roomid, userid, limit){
    return function( dispatch, getState ){
        getLogApi(type, roomid,userid,  limit).then(listlog =>{
            dispatch(getLogInfo(listlog));
        });
    }
}