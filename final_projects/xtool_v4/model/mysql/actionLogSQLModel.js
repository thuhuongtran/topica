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

connectMySQL.connect(function (err) {
    if (err) throw err;
    console.log('connected mySQL')
})

module.exports = {
    addLogLeaveRoom: function(logMySQL){
        return new Promise(function (resolve, reject) {
            let sql = "INSERT INTO logsActionLeaveRoom (timeCreated, system, userId, roomId, device, type) VALUES ?";
            let values = [
                [
                    logMySQL.timeCreated,
                    logMySQL.system,
                    logMySQL.userId,
                    logMySQL.roomId,
                    logMySQL.device,
                    logMySQL.type
                ]
            ];
            connectMySQL.query(sql, [values], function (err, result) {
                if (err) reject({status: false, err});
                resolve({status: true, result});
            });
        })
    },
    addLogLogin: function(logMySQL){
        return new Promise(function (resolve, reject) {
            let sql = "INSERT INTO logsActionLogin (timeCreated, system, username, password, device, action) VALUES ?";
            let values = [
                [
                    logMySQL.timeCreated,
                    logMySQL.system,
                    logMySQL.username,
                    logMySQL.password,
                    logMySQL.device,
                    logMySQL.action
                ]
            ];
            connectMySQL.query(sql, [values], function (err, result) {
                if (err) reject({status: false, err});
                resolve({status: true, result});
            });
        })
    },
    addLogSelectClass: function(logMySQL){
        return new Promise(function (resolve, reject) {
            let sql = "INSERT INTO logsActionSelectClass (timeCreated, system, userId, roomId, subjectType, device) VALUES ?";
            let values = [
                [
                    logMySQL.timeCreated,
                    logMySQL.system,
                    logMySQL.userId,
                    logMySQL.roomId,
                    logMySQL.subjectType,
                    logMySQL.device
                ]
            ];
            connectMySQL.query(sql, [values], function (err, result) {
                if (err) reject({status: false, err});
                resolve({status: true, result});
            });
        })
    },
    addLogSelectClassFast: function(logMySQL){
        return new Promise(function (resolve, reject) {
            let sql = "INSERT INTO logsActionSelectClassFast (timeCreated, system, userId, roomId, subjectType, device) VALUES ?";
            let values = [
                [
                    logMySQL.timeCreated,
                    logMySQL.system,
                    logMySQL.userId,
                    logMySQL.roomId,
                    logMySQL.subjectType,
                    logMySQL.device
                ]
            ];
            connectMySQL.query(sql, [values], function (err, result) {
                if (err) reject({status: false, err});
                resolve({status: true, result});
            });
        })
    },
    addLogRaiseHand: function(logMySQL){
        return new Promise(function (resolve, reject) {
            let sql = "INSERT INTO logsActionRaiseHand (timeCreated, system, userId, roomId, status, device) VALUES ?";
            let values = [
                [
                    logMySQL.timeCreated,
                    logMySQL.system,
                    logMySQL.userId,
                    logMySQL.roomId,
                    logMySQL.status,
                    logMySQL.device
                ]
            ];
            connectMySQL.query(sql, [values], function (err, result) {
                if (err) reject({status: false, err});
                resolve({status: true, result});
            });
        })
    },
    addLogMicro: function(logMySQL){
        return new Promise(function (resolve, reject) {
            let sql = "INSERT INTO logsActionMicro (timeCreated, system, userId, roomId, action, status, timeListen) VALUES ?";
            let values = [
                [
                    logMySQL.timeCreated,
                    logMySQL.system,
                    logMySQL.userId,
                    logMySQL.roomId,
                    logMySQL.action,
                    logMySQL.status,
                    logMySQL.timeListen
                ]
            ];
            connectMySQL.query(sql, [values], function (err, result) {
                if (err) reject({status: false, err});
                resolve({status: true, result});
            });
        })
    },
    addLogCamera: function(logMySQL){
        return new Promise(function (resolve, reject) {
            let sql = "INSERT INTO logsActionCamera (timeCreated, system, userId, roomId, action, status, timeListen) VALUES ?";
            let values = [
                [
                    logMySQL.timeCreated,
                    logMySQL.system,
                    logMySQL.userId,
                    logMySQL.roomId,
                    logMySQL.action,
                    logMySQL.status,
                    logMySQL.timeListen
                ]
            ];
            connectMySQL.query(sql, [values], function (err, result) {
                if (err) reject({status: false, err});
                resolve({status: true, result});
            });
        })
    },
    addLogLoadSlide: function(logMySQL){
        return new Promise(function (resolve, reject) {
            let sql = "INSERT INTO logsActionLoadSlide (timeCreated, system, userId, roomId, link, timeLoad) VALUES ?";
            let values = [
                [
                    logMySQL.timeCreated,
                    logMySQL.system,
                    logMySQL.userId,
                    logMySQL.roomId,
                    logMySQL.link,
                    logMySQL.timeLoad
                ]
            ];
            connectMySQL.query(sql, [values], function (err, result) {
                if (err) reject({status: false, err});
                resolve({status: true, result});
            });
        })
    },
    addLogActionSlide: function(logMySQL){
        return new Promise(function (resolve, reject) {
            let sql = "INSERT INTO logsActionSlide (timeCreated, system, userId, roomId, page, next, link, device) VALUES ?";
            let values = [
                [
                    logMySQL.timeCreated,
                    logMySQL.system,
                    logMySQL.userId,
                    logMySQL.roomId,
                    logMySQL.page,
                    logMySQL.next,
                    logMySQL.link,
                    logMySQL.device
                ]
            ];
            connectMySQL.query(sql, [values], function (err, result) {
                if (err) reject({status: false, err});
                resolve({status: true, result});
            });
        })
    },
    addLogMaterialView: function(logMySQL){
        return new Promise(function (resolve, reject) {
            let sql = "INSERT INTO logsActionMaterialView (timeCreated, system, userId, subjectType, link, materialDay, device) VALUES ?";
            let values = [
                [
                    logMySQL.timeCreated,
                    logMySQL.system,
                    logMySQL.userId,
                    logMySQL.subjectType,
                    logMySQL.link,
                    logMySQL.materialDay,
                    logMySQL.device
                ]
            ];
            connectMySQL.query(sql, [values], function (err, result) {
                if (err) reject({status: false, err});
                resolve({status: true, result});
            });
        })
    },
    addLogLinkingPortal: function(logMySQL){
        return new Promise(function (resolve, reject) {
            let sql = "INSERT INTO logsActionLinkingPortal (timeCreated, system, userId, roomId, userIdVcrx, role, roomIdVcrx, firstname, lastname, systemInfo, uri, device) VALUES ?";
            let values = [
                [
                    logMySQL.timeCreated,
                    logMySQL.system,
                    logMySQL.userId,
                    logMySQL.roomId,
                    logMySQL.userIdVcrx,
                    logMySQL.role,
                    logMySQL.roomIdVcrx,
                    logMySQL.firstname,
                    logMySQL.lastname,
                    logMySQL.systemInfo,
                    logMySQL.uri,
                    logMySQL.device
                ]
            ];
            connectMySQL.query(sql, [values], function (err, result) {
                if (err) reject({status: false, err});
                resolve({status: true, result});
            });
        })
    },
    addLogRenderFromLMS: function(logMySQL){
        return new Promise(function (resolve, reject) {
            let sql = "INSERT INTO logsActionRenderFromLMS (timeCreated, system, userId, roomId, userIdVcrx, role, roomIdVcrx, userName, uri, device) VALUES ?";
            let values = [
                [
                    logMySQL.timeCreated,
                    logMySQL.system,
                    logMySQL.userId,
                    logMySQL.roomId,
                    logMySQL.userIdVcrx,
                    logMySQL.role,
                    logMySQL.roomIdVcrx,
                    logMySQL.userName,
                    logMySQL.uri,
                    logMySQL.device
                ]
            ];
            connectMySQL.query(sql, [values], function (err, result) {
                if (err) reject({status: false, err});
                resolve({status: true, result});
            });
        })
    }
}