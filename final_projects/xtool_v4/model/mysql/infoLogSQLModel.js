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
    addLogConnectionQuality: function(logMySQL){
        return new Promise(function (resolve, reject) {
                let sql = "INSERT INTO logsInfoConnectionQuality (timeCreated, system, userId, roomId, device, connectionQuality, download, upload) VALUES ?";
                let values = [
                    [
                        logMySQL.timeCreated,
                        logMySQL.system,
                        logMySQL.userId,
                        logMySQL.roomId,
                        logMySQL.device,
                        logMySQL.connectionQuality,
                        logMySQL.download,
                        logMySQL.upload
                    ]
                ];
                connectMySQL.query(sql, [values], function (err, result) {
                    if (err) reject({status: false, err});
                    resolve({status: true, result});
                });
        })
    },
    addLogAccessMicro: function(logMySQL){
        return new Promise(function (resolve, reject) {
                let sql = "INSERT INTO logsInfoAccessMicro (timeCreated, system, userId, roomId, device, accessMicro) VALUES ?";
                let values = [
                    [
                        logMySQL.timeCreated,
                        logMySQL.system,
                        logMySQL.userId,
                        logMySQL.roomId,
                        logMySQL.device,
                        logMySQL.accessMicro
                    ]
                ];
                connectMySQL.query(sql, [values], function (err, result) {
                    if (err) reject({status: false, err});
                    resolve({status: true, result});
                });
        })
    },
    addLogInfoDevice: function(logMySQL){
        return new Promise(function (resolve, reject) {
                let sql = `INSERT INTO logsInfoDevice 
                    (timeCreated, system, userId, roomId, device, 
                    appVersion, brand, systemVesion, deviceName, 
                    systemName, timeZone, carrier, deviceId, pin)
                    VALUES 
                    ("${logMySQL.timeCreated}","${logMySQL.system}","${logMySQL.userId}","${logMySQL.roomId}",
                    "${logMySQL.device }","${logMySQL.appVersion}","${logMySQL.brand}","${logMySQL.systemVesion}",
                    '${logMySQL.deviceName.replace(/[\u0800-\uFFFF]/g, '')}',"${logMySQL.systemName}","${logMySQL.timeZone}","${logMySQL.carrier}",
                    "${logMySQL.deviceId}","${logMySQL.pin}")`;
                connectMySQL.query(sql, function (err, result) {
                    if (err) reject({status: false, err});
                    resolve({status: true, result});
                });
        })
    }
}