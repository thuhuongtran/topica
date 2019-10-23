import{
    openClassAPI, getClassListAPI, getListRoomAPI, getListUserAPI, getUsersTLAPI
}                               from './apis';

export function changePage(page){
    return {
        type        : "CHANGE_PAGE",
        page
    };
}

export function getRoom(data){
    return {
        type        : "GET_ROOM",
        data
    };
}

export function getUserList(userlist){
    return {
        type        : "GET_USER_LIST",
        userlist
    };
}

export function getUsersTL(userTL){
    return {
        type        : "GET_USER_TL",
        userTL
    };
}

export function getRoomTL(roomTL){
    return {
        type        : "GET_ROOM_NTL",
        roomTL
    };
}

export function handleOpenClasses(){
    return function( dispatch, getState ){
        openClassAPI();
    }
}

export function getRoomsNVN(){
    return function( dispatch, getState ){
        getListRoomAPI().then(data =>{
            dispatch(getRoom(data));
        });
    }
}

export function getRoomsNTL(){
    return function( dispatch, getState ){
        getClassListAPI().then(roomTL =>{
            dispatch(getRoomTL(roomTL));
        });
    }
}

export function getUser(){
    return function( dispatch, getState ){
        getListUserAPI().then(userlist =>{
            dispatch(getUserList(userlist));
        });
    }
}

export function getUserTL(){
    return function( dispatch, getState ){
        getUsersTLAPI().then(userTL =>{
            dispatch(getUsersTL(userTL));
        });
    }
}