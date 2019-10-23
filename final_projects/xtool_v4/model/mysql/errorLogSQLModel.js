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
    addLogError: function (logMySQL) {
        return new Promise(function (resolve, reject) {
            let sql = "INSERT INTO logsErrorSlide (timeCreated, system, userId, roomId, link, msg) VALUES ?";
            let values = [
                [
                    logMySQL.timeCreated,
                    logMySQL.system,
                    logMySQL.userId,
                    logMySQL.roomId,
                    logMySQL.link,
                    logMySQL.msg
                ]
            ];
            connectMySQL.query(sql, [values], function (err, result) {
                if (err) reject({status: false, err});
                resolve({status: true, result});
            });
        })
    }
}