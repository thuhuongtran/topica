const ApiInfoLogModelMongo = require("../model/mongo/listenInfoLogModel");
const {getNumberFromStr} = require("../utils/helper");

var router = require('express').Router();

router.route('/jobNotifyUnListenable').get(jobLogNotifyUnListenable);
router.route('/apiListenLogs').get(getListenLogs);
router.route('/apiGetLogsByRoomId').get(getListenLogsByRoomId);
router.route('/apiGetLogsByUserId').get(getListenLogsByUserId);


let date = new Date();
let startDate = date.getFullYear() + " " + (date.getMonth() + 1) + " " + date.getDate() + " " + (date.getHours()-1) + ":45:00";
let endDate = date.getFullYear() + " " + (date.getMonth() + 1) + " " + date.getDate() + " " + date.getHours() + ":45:00";
let start = Date.parse(startDate);
let end = Date.parse(endDate);

function jobLogNotifyUnListenable(req, res) {
    let resJson = "";
    for (let t = start; t < end; t += 30000) {
        ApiInfoLogModelMongo.gets(t, t + 30000).then((infoLogs) => {
            if (infoLogs) {
                let maxDownload = infoLogs.maxDownload;
                infoLogs.forEach((infoLog, i) => {
                    infoLog.data.forEach((log, i) => {
                        let listenLog = {};
                        if (maxDownload <= 22) {
                            listenLog = {
                                roomId: infoLog._id.roomId,
                                userId: log.userId,
                                userName: log.userName,
                                system: log.system,
                                download: log.download,
                                timeCreated: log.timeCreated,
                                statusNow: true
                            };
                        } else {
                            listenLog = {
                                roomId: infoLog._id.roomId,
                                userId: log.userId,
                                userName: log.userName,
                                system: log.system,
                                download: log.download,
                                timeCreated: log.timeCreated,
                                statusNow: log.download > 22
                            };
                        }
                        ApiInfoLogModelMongo.add(listenLog).then(res => {
                        }).catch(err => console.log(err));
                    })
                });
                resJson += infoLogs.toString() + "\n";
            }
        })
    }
    res.json(resJson)
}

/**
 * get info-log of all users in all running rooms
 * @param req
 * @param res
 */
function getListenLogs(req, res) {
    ApiInfoLogModelMongo.getListenStatusLogs(start, end).then((statusLogs) => {
        res.json(statusLogs);
    })
}

/**
 * get info-log of all users by room-id
 * @param req
 * @param res
 */
function getListenLogsByRoomId(req, res) {
    let roomId = getNumberFromStr(req.query.roomId);
    ApiInfoLogModelMongo.getListenStatusLogsByRoomId(roomId).then((statusLogs) => {
        res.json(statusLogs);
    })
}

/**
 * get info-log of a user by user-id
 * @param req
 * @param res
 */
function getListenLogsByUserId(req, res) {
    let userId = getNumberFromStr(req.query.userId);
    ApiInfoLogModelMongo.getListenStatusLogsByUserId(userId).then((statusLogs) => {
        res.json(statusLogs);
    })
}

module.exports = router;