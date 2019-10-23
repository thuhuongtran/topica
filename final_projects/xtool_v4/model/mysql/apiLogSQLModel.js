const {
    DATABASE, HOST,
    PASSWORD, USER
} = require('../../config');

let mysql = require('mysql');

let connectMySQL = mysql.createConnection({
    host: HOST,
    user: USER,
    password: PASSWORD,
    database: DATABASE
});
connectMySQL.connect();

module.exports = {
    addApiLMSLogin: function (logMySQL) {
        return new Promise(function (resolve, reject) {
            let sql = "INSERT INTO logsApiLMSLogin (timeCreated, system, username, password, uri, timeResponse) VALUES ?";
            let values = [
                [
                    logMySQL.timeCreated,
                    logMySQL.system,
                    logMySQL.username,
                    logMySQL.password,
                    logMySQL.uri,
                    logMySQL.timeResponse
                ]
            ];
            connectMySQL.query(sql, [values], function (err, result) {
                if (err) reject({status: false, err});
                resolve({status: true, result});
            });
        })
    },
    addApiLMSClass: function (logMySQL) {
        return new Promise(function (resolve, reject) {
            let sql = "INSERT INTO logsApiLMSClass (timeCreated, system, userId, packageType, levelClass, subjectType, uri, timeResponse) VALUES ?";
            let values = [
                [
                    logMySQL.timeCreated,
                    logMySQL.system,
                    logMySQL.userId,
                    logMySQL.packageType,
                    logMySQL.levelClass,
                    logMySQL.subjectType,
                    logMySQL.uri,
                    logMySQL.timeResponse
                ]
            ];
            connectMySQL.query(sql, [values], function (err, result) {
                if (err) reject({status: false, err});
                resolve({status: true, result});
            });
        })
    },
    addApiLMSSelectClassFast: function (logMySQL) {
        return new Promise(function (resolve, reject) {
            let sql = "INSERT INTO logsApiLMSSelectClassFast (timeCreated, system, userId, packageType, levelClass, subjectType, uri, timeResponse) VALUES ?";
            let values = [
                [
                    logMySQL.timeCreated,
                    logMySQL.system,
                    logMySQL.userId,
                    logMySQL.packageType,
                    logMySQL.levelClass,
                    logMySQL.subjectType,
                    logMySQL.uri,
                    logMySQL.timeResponse
                ]
            ];
            connectMySQL.query(sql, [values], function (err, result) {
                if (err) reject({status: false, err});
                resolve({status: true, result});
            });
        })
    },
    addApiLMSSelectClass: function (logMySQL) {
        return new Promise(function (resolve, reject) {
            let sql = "INSERT INTO logsApiLMSSelectClass (timeCreated, system, userId, roomId, packageType, levelClass, subjectType, uri, timeResponse) VALUES ?";
            let values = [
                [
                    logMySQL.timeCreated,
                    logMySQL.system,
                    logMySQL.userId,
                    logMySQL.roomId,
                    logMySQL.packageType,
                    logMySQL.levelClass,
                    logMySQL.subjectType,
                    logMySQL.uri,
                    logMySQL.timeResponse
                ]
            ];
            connectMySQL.query(sql, [values], function (err, result) {
                if (err) reject({status: false, err});
                resolve({status: true, result});
            });
        })
    },
    addApiLMSCara: function (logMySQL) {
        return new Promise(function (resolve, reject) {
            let sql = "INSERT INTO logsApiLMSCara (timeCreated, system, userId, uri, timeResponse) VALUES ?";
            let values = [
                [
                    logMySQL.timeCreated,
                    logMySQL.system,
                    logMySQL.userId,
                    logMySQL.roomId,
                    logMySQL.uri,
                    logMySQL.timeResponse
                ]
            ];
            connectMySQL.query(sql, [values], function (err, result) {
                if (err) reject({status: false, err});
                resolve({status: true, result});
            });
        })
    },
    addApiLMSCaraAdd: function (logMySQL) {
        return new Promise(function (resolve, reject) {
            let sql = "INSERT INTO logsApiLMSCaraAdd (timeCreated, system, userId, roomId, advisor, lesson, teacher, assistant, quality, tech, message, uri, timeResponse) VALUES ?";
            let values = [
                [
                    logMySQL.timeCreated,
                    logMySQL.system,
                    logMySQL.userId,
                    logMySQL.roomId,
                    logMySQL.advisor,
                    logMySQL.lesson,
                    logMySQL.teacher,
                    logMySQL.assistant,
                    logMySQL.quality,
                    logMySQL.tech,
                    logMySQL.message,
                    logMySQL.uri,
                    logMySQL.timeResponse
                ]
            ];
            connectMySQL.query(sql, [values], function (err, result) {
                if (err) reject({status: false, err});
                resolve({status: true, result});
            });
        })
    },
    addApiLMSMaterial: function (logMySQL) {
        return new Promise(function (resolve, reject) {
            let sql = "INSERT INTO logsApiLMSMaterial (timeCreated, system, userId, levelClass, week, uri, timeResponse) VALUES ?";
            let values = [
                [
                    logMySQL.timeCreated,
                    logMySQL.system,
                    logMySQL.userId,
                    logMySQL.levelClass,
                    logMySQL.week,
                    logMySQL.uri,
                    logMySQL.timeResponse
                ]
            ];
            connectMySQL.query(sql, [values], function (err, result) {
                if (err) reject({status: false, err});
                resolve({status: true, result});
            });
        })
    },
    addApiLogin: function (logMySQL) {
        return new Promise(function (resolve, reject) {
            let sql = "INSERT INTO logsApiLogin (timeCreated, system, username, password, uri, timeResponse) VALUES ?";
            let values = [
                [
                    logMySQL.timeCreated,
                    logMySQL.system,
                    logMySQL.username,
                    logMySQL.password,
                    logMySQL.uri,
                    logMySQL.timeResponse
                ]
            ];
            connectMySQL.query(sql, [values], function (err, result) {
                if (err) reject({status: false, err});
                resolve({status: true, result});
            });
        })
    },
    addApiLogChats: function (logMySQL) {
        return new Promise(function (resolve, reject) {
            let sql = "INSERT INTO logsApiLogchats (timeCreated, system, userId, roomId, roomIdVcrx, uri, timeResponse) VALUES ?";
            let values = [
                [
                    logMySQL.timeCreated,
                    logMySQL.system,
                    logMySQL.userId,
                    logMySQL.roomId,
                    logMySQL.roomIdVcrx,
                    logMySQL.uri,
                    logMySQL.timeResponse
                ]
            ];
            connectMySQL.query(sql, [values], function (err, result) {
                if (err) reject({status: false, err});
                resolve({status: true, result});
            });
        })
    },
    addApiLogChatsAdd: function (logMySQL) {
        return new Promise(function (resolve, reject) {
            let sql = "INSERT INTO logsApiLogchatsAdd (timeCreated, system, userId, roomId, roomIdVcrx, fromParticipantId, toParticipantId, mess, uri, timeResponse) VALUES ?";
            let values = [
                [
                    logMySQL.timeCreated,
                    logMySQL.system,
                    logMySQL.userId,
                    logMySQL.roomId,
                    logMySQL.roomIdVcrx,
                    logMySQL.fromParticipantId,
                    logMySQL.toParticipantId,
                    logMySQL.mess.replace(/[\u0800-\uFFFF]/g, ''),
                    logMySQL.uri,
                    logMySQL.timeResponse
                ]
            ];
            connectMySQL.query(sql, [values], function (err, result) {
                if (err) reject({status: false, err});
                resolve({status: true, result});
            });
        })
    },
    addApiLoginoutAdd: function (logMySQL) {
        return new Promise(function (resolve, reject) {
            let sql = "INSERT INTO logsApiLoginoutAdd (timeCreated, system, userId, roomId, roomIdVcrx, participantId, action, role, uri, timeResponse) VALUES ?";
            let values = [
                [
                    logMySQL.timeCreated,
                    logMySQL.system,
                    logMySQL.userId,
                    logMySQL.roomId,
                    logMySQL.roomIdVcrx,
                    logMySQL.participantId,
                    logMySQL.action,
                    logMySQL.role,
                    logMySQL.uri,
                    logMySQL.timeResponse
                ]
            ];
            connectMySQL.query(sql, [values], function (err, result) {
                if (err) reject({status: false, err});
                resolve({status: true, result});
            });
        })
    },
    addApiRoomInfo: function (logMySQL) {
        return new Promise(function (resolve, reject) {
            let sql = "INSERT INTO logsApiRoomInfo (timeCreated, system, userId, roomId, roomIdVcrx, uri, timeResponse) VALUES ?";
            let values = [
                [
                    logMySQL.timeCreated,
                    logMySQL.system,
                    logMySQL.userId,
                    logMySQL.roomId,
                    logMySQL.roomIdVcrx,
                    logMySQL.uri,
                    logMySQL.timeResponse
                ]
            ];
            connectMySQL.query(sql, [values], function (err, result) {
                if (err) reject({status: false, err});
                resolve({status: true, result});
            });
        })
    },
    addApiLogAction: function (logMySQL) {
        return new Promise(function (resolve, reject) {
            let sql = "INSERT INTO logsApiLogAction (timeCreated, system, userId, roomId, roomIdVcrx, typeId, uri, timeResponse) VALUES ?";
            let values = [
                [
                    logMySQL.timeCreated,
                    logMySQL.system,
                    logMySQL.userId,
                    logMySQL.roomId,
                    logMySQL.roomIdVcrx,
                    logMySQL.typeId,
                    logMySQL.uri,
                    logMySQL.timeResponse
                ]
            ];
            connectMySQL.query(sql, [values], function (err, result) {
                if (err) reject({status: false, err});
                resolve({status: true, result});
            });
        })
    },
    addApiLogActionAdd: function (logMySQL) {
        return new Promise(function (resolve, reject) {
            let sql = "INSERT INTO logsApiLogActionAdd (timeCreated, system, userId, roomId, roomIdVcrx, typeId, data, uri, timeResponse) VALUES ?";
            let values = [
                [
                    logMySQL.timeCreated,
                    logMySQL.system,
                    logMySQL.userId,
                    logMySQL.roomId,
                    logMySQL.roomIdVcrx,
                    logMySQL.typeId,
                    logMySQL.data,
                    logMySQL.uri,
                    logMySQL.timeResponse
                ]
            ];
            connectMySQL.query(sql, [values], function (err, result) {
                if (err) reject({status: false, err});
                resolve({status: true, result});
            });
        })
    },
    addApiLogErrorAdd: function (logMySQL) {
        return new Promise(function (resolve, reject) {
            let sql = "INSERT INTO logsApiLogErrorAdd (timeCreated, system, userId, roomId, roomIdVcrx, paticipantId, title, message, device, role, uri, timeResponse) VALUES ?";
            let values = [
                [
                    logMySQL.timeCreated,
                    logMySQL.system,
                    logMySQL.userId,
                    logMySQL.roomId,
                    logMySQL.roomIdVcrx,
                    logMySQL.paticipantId,
                    logMySQL.title,
                    logMySQL.message,
                    logMySQL.device,
                    logMySQL.role,
                    logMySQL.uri,
                    logMySQL.timeResponse
                ]
            ];
            connectMySQL.query(sql, [values], function (err, result) {
                if (err) reject({status: false, err});
                resolve({status: true, result});
            });
        })
    },
    addApiGetTimeServer: function (logMySQL) {
        return new Promise(function (resolve, reject) {
            let sql = "INSERT INTO logsApiGettimeSeverver (timeCreated, system, userId, roomId, roomIdVcrx, uri, timeResponse) VALUES ?";
            let values = [
                [
                    logMySQL.timeCreated,
                    logMySQL.system,
                    logMySQL.userId,
                    logMySQL.roomId,
                    logMySQL.roomIdVcrx,
                    logMySQL.uri,
                    logMySQL.timeResponse
                ]
            ];
            connectMySQL.query(sql, [values], function (err, result) {
                if (err) reject({status: false, err});
                resolve({status: true, result});
            });
        })
    },
}