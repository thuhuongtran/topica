import {
    getLogsApi, getByRoomIdApi, getByUserIdApi
}                           from './apis';

function getLogInfo(logs) {
    return {
        type: "GET_LOGS",
        logs
    }
}

function getLogInfoByRoomId(roomLogs) {
    return {
        type: "GET_LOGS_BY_ROOM_ID",
        roomLogs
    }
}

function getLogInfoByUserId(userLogs) {
    return {
        type: "GET_LOGS_BY_USER_ID",
        userLogs
    }
}

export function getLogs() {
    return function (dispatch, getState) {
        getLogsApi().then((logs) => {
            dispatch(getLogInfo(logs))
        })
    }
}

export function getByRoomId(roomId) {
    return function (dispatch, getState) {
        getByRoomIdApi(roomId).then((roomLogs) => {
            dispatch(getLogInfoByRoomId(roomLogs))
        })
    }
}

export function getByUserId(userId) {
    return function (dispatch, getState) {
        getByUserIdApi(userId).then((userLogs) => {
            dispatch(getLogInfoByUserId(userLogs))
        })
    }
}