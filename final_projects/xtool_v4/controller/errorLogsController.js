const {
    MOBILE, ERROR_SLIDE,
    SYSTEM_NTL, SYSTEM_NVN,
    TYPE_APP, TYPE_WEB, UPDATE_FAIL,
    UPDATE_SUCCESS,
    TOTAL_DOCUMENT
} = require('../constants');

const ErrorLogsModelMongoDB   = require("../model/mongo/errorLogModel");
const ErrorLogsModelMySQL     = require('../model/mysql/errorLogSQLModel');

var fs          = require('fs');
var router = require('express').Router();

router.route('/jobconvertlogerrorslide').get(convertErrorSlide);

function convertErrorSlide(req, res){
    let where = {
        type: ERROR_SLIDE,
        backup: { $ne: UPDATE_SUCCESS }
    };
    ErrorLogsModelMongoDB.gets(where, 0, TOTAL_DOCUMENT , {timeCreated : -1}).then((logsErrors) => {
        res.json(logsErrors);
        if (logsErrors){
            logsErrors.forEach((logsError, index) => {
                let logMySQL = {
                    timeCreated: logsError.timeCreated.toString(),
                    system : logsError.system === SYSTEM_NTL ? SYSTEM_NTL : SYSTEM_NVN,
                    userId : logsError.data.userId.toString(),
                    roomId : logsError.data.roomId + '',
                    link   : logsError.data.linkSlide,
                    msg    : logsError.data.msg
                }
                ErrorLogsModelMySQL.addLogError(logMySQL).then(res => {
                    if (res.status){
                        ErrorLogsModelMongoDB.update(logsError._id, { backup: UPDATE_SUCCESS }).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsError._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoErrorSlide.log'
                                fs.appendFile(namefile, JSON.stringify(logsError),'utf8',function (err) {});
                            }
                        })
                    } else {
                        ErrorLogsModelMongoDB.update(logsError._id, { backup: UPDATE_FAIL}).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsError._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoErrorSlide.log'
                                fs.appendFile(namefile, JSON.stringify(logsError),'utf8',function (err) {});
                            }
                        })
                    }
                }).catch(err => console.log(err));
            })
        }
    })
}

module.exports = router;