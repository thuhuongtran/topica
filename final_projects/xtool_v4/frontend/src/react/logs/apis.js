import { get} from "../base/portal";

export function getLogsApi() {
    return get("/listenLogs/apiListenLogs", "", "GET_LOGS")
}

export function getByRoomIdApi(roomId) {
    return get("/listenLogs/apiGetLogsByRoomId?roomId="+roomId, "", "GET_LOGS_BY_ROOM_ID")
}

export function getByUserIdApi(userId) {
    return get("/listenLogs/apiGetLogsByUserId?userId="+userId, "", "GET_LOGS_BY_USER_ID")
}