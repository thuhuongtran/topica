const {
    CONNECTION_QUALITY, MOBILE,
    SYSTEM_NTL, SYSTEM_NVN,
    TYPE_APP, TYPE_WEB, UPDATE_FAIL,
    UPDATE_SUCCESS,
    ACCESS_MICRO,
    TOTAL_DOCUMENT,
    INFO_DEVICE
} = require('../constants');
var fs          = require('fs');

const InfoLogsModelMongoDB   = require("../model/mongo/infoLogModel");
const InfoLogsModelMongoDB2  = require("../model/mongo/infoLogModel2");
const InfoLogsModelMySQL     = require('../model/mysql/infoLogSQLModel');

var router = require('express').Router();
router.route('/jobconvertlogconnectquality').get(convertConnectionQuality);
router.route('/jobconvertlogconnectquality2').get(convertConnectionQuality2);
router.route('/jobconvertlogaccessmicro').get(convertAccessMicro);
router.route('/jobconvertloginfodevice').get(convertInfoDevice);
router.route('/jobconvertloginfodevice2').get(convertInfoDevice2);
router.route('/dev').get(dev);

function dev(req, res){
    res.json("logsInfos");
}

function convertConnectionQuality(req, res){
    let where = {
        type: CONNECTION_QUALITY,
        backup: { $ne: UPDATE_SUCCESS }
    };
    InfoLogsModelMongoDB.gets(where, 0, TOTAL_DOCUMENT , {timeCreated : -1}).then((logsInfos) => {
        res.json(logsInfos);
        if (logsInfos){
            logsInfos.forEach((logsInfo, index) => {
                let logMySQL = {
                    timeCreated: logsInfo.timeCreated.toString(),
                    system : logsInfo.system === SYSTEM_NTL ? SYSTEM_NTL : SYSTEM_NVN,
                    userId : logsInfo.data.userId,
                    roomId : logsInfo.data.roomId,
                    device : logsInfo.data.system === MOBILE ? TYPE_APP : TYPE_WEB,
                    connectionQuality : logsInfo.data.connectionQuality,
                    download : logsInfo.data.bitrate.download,
                    upload : logsInfo.data.bitrate.upload
                }
                InfoLogsModelMySQL.addLogConnectionQuality(logMySQL).then(res => {
                    if (res.status){
                        InfoLogsModelMongoDB.update(logsInfo._id, { backup: UPDATE_SUCCESS }).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsInfo._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoConnectionQuality.log'
                                fs.appendFile(namefile, JSON.stringify(logsInfo),'utf8',function (err) {});
                            }
                        })
                    } else {
                        InfoLogsModelMongoDB.update(logsInfo._id, UPDATE_FAIL).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsInfo._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoConnectionQuality.log'
                                fs.appendFile(namefile, JSON.stringify(logsInfo),'utf8',function (err) {});
                            }
                        })
                    }
                }).catch(err => {
                    console.log('err', err);
                });
            })
        }
    })
}

function convertConnectionQuality2(req, res){
    let where = {
        type: CONNECTION_QUALITY,
        backup: { $ne: UPDATE_SUCCESS}
    };
    InfoLogsModelMongoDB2.gets(where, 0, TOTAL_DOCUMENT , {timeCreated : -1}).then((logsInfos) => {
        res.json(logsInfos);
        if (logsInfos){
            logsInfos.forEach((logsInfo, index) => {
                let logMySQL = {
                    timeCreated: logsInfo.timeCreated.toString(),
                    system : logsInfo.system === SYSTEM_NTL ? SYSTEM_NTL : SYSTEM_NVN,
                    userId : logsInfo.data.userId,
                    roomId : logsInfo.data.roomId,
                    device : logsInfo.data.system === MOBILE ? TYPE_APP : TYPE_WEB,
                    connectionQuality : logsInfo.data.connectionQuality,
                    download : logsInfo.data.bitrate.download,
                    upload : logsInfo.data.bitrate.upload
                }
                InfoLogsModelMySQL.addLogConnectionQuality(logMySQL).then(res => {
                    if (res.status){
                        InfoLogsModelMongoDB2.update(logsInfo._id, { backup: UPDATE_SUCCESS }).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsInfo._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoConnectionQuality.log'
                                fs.appendFile(namefile, JSON.stringify(logsInfo),'utf8',function (err) {});
                            }
                        })
                    } else {
                        InfoLogsModelMongoDB2.update(logsInfo._id, UPDATE_FAIL).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsInfo._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoConnectionQuality.log'
                                fs.appendFile(namefile, JSON.stringify(logsInfo),'utf8',function (err) {});
                            }
                        })
                    }
                }).catch(err => {
                    console.log('err', err);
                });
            })
        }
    })
}

function convertAccessMicro(req, res){
    let where = {
        type: ACCESS_MICRO,
        backup: { $ne: UPDATE_SUCCESS}
    };
    InfoLogsModelMongoDB.gets(where, 0, TOTAL_DOCUMENT , {timeCreated : -1}).then((logsInfos) => {
        res.json(logsInfos);
        if (logsInfos){
            logsInfos.forEach((logsInfo, index) => {
                let logMySQL = {
                    timeCreated: logsInfo.timeCreated.toString(),
                    system : logsInfo.system === SYSTEM_NTL ? SYSTEM_NTL : SYSTEM_NVN,
                    userId : logsInfo.data.userId.toString(),
                    roomId : logsInfo.data.roomId.toString(),
                    device : logsInfo.data.system === MOBILE ? TYPE_APP : TYPE_WEB,
                    accessMicro: logsInfo.data.status ? 1 : 0
                }
                InfoLogsModelMySQL.addLogAccessMicro(logMySQL).then(res => {
                    if (res.status){
                        InfoLogsModelMongoDB.update(logsInfo._id, { backup: UPDATE_SUCCESS }).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsInfo._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoAccessMicro.log'
                                fs.appendFile(namefile, JSON.stringify(logsInfo),'utf8',function (err) {});
                            }
                        })
                    } else {
                        InfoLogsModelMongoDB.update(logsInfo._id, UPDATE_FAIL).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsInfo._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoAccessMicro.log'
                                fs.appendFile(namefile, JSON.stringify(logsInfo),'utf8',function (err) {});
                            }
                        })
                    }
                }).catch(err => {
                    console.log('err', err);
                });
            })
        }
    })
}

function convertInfoDevice(req, res){
    let where = {
        type: INFO_DEVICE,
        backup: { $ne: UPDATE_SUCCESS}
    };
    InfoLogsModelMongoDB.gets(where, 0, 10 , {timeCreated : -1}).then((logsInfos) => {
        res.json(logsInfos);
        if (logsInfos){
            logsInfos.forEach((logsInfo, index) => {
                let isApp = logsInfo.data.system === MOBILE ? true : false;
                let logMySQL = {
                    timeCreated: logsInfo.timeCreated.toString(),
                    system : logsInfo.system === SYSTEM_NTL ? SYSTEM_NTL : SYSTEM_NVN,
                    userId : logsInfo.data.userId ? logsInfo.data.userId + '' : '0',
                    roomId : logsInfo.data.roomId ? logsInfo.data.roomId + '' : '0',
                    device : isApp ? TYPE_APP : TYPE_WEB,
                    appVersion: logsInfo.data.appVersion ? logsInfo.data.appVersion : '0',
                    brand: isApp ? logsInfo.data.brand : logsInfo.data.browser,
                    systemVesion: isApp ? logsInfo.data.brand : logsInfo.data.browser,
                    deviceName: isApp ? `${logsInfo.data.deviceName.replace(/([".*+?^=!:${}()|\[\]\/\\'])/g, "")}` : '',
                    systemName: isApp ? logsInfo.data.systemName : logsInfo.data.OS,
                    timeZone: isApp ? logsInfo.data.timeZone : '',
                    carrier: isApp ? logsInfo.data.carrier : '',
                    deviceId: isApp ? logsInfo.data.deviceId : '',
                    pin: isApp ? logsInfo.data.pin : 0
                }
                InfoLogsModelMySQL.addLogInfoDevice(logMySQL).then(res => {
                    if (res.status){
                        InfoLogsModelMongoDB.update(logsInfo._id, { backup: UPDATE_SUCCESS }).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsInfo._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoInfoDevice.log'
                                fs.appendFile(namefile, JSON.stringify(logsInfo),'utf8',function (err) {});
                            }
                        })
                    } else {
                        InfoLogsModelMongoDB.update(logsInfo._id, UPDATE_FAIL).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsInfo._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoInfoDevice.log'
                                fs.appendFile(namefile, JSON.stringify(logsInfo),'utf8',function (err) {});
                            }
                        })
                    }
                }).catch(err => {
                    console.log('err', err);
                });
            })
        }
    })
}

function convertInfoDevice2(req, res){
    let where = {
        type: INFO_DEVICE,
        backup: { $ne: UPDATE_SUCCESS}
    };
    InfoLogsModelMongoDB2.gets(where, 0, TOTAL_DOCUMENT , {timeCreated : -1}).then((logsInfos) => {
        res.json(logsInfos);
        if (logsInfos){
            logsInfos.forEach((logsInfo, index) => {
                let isApp = logsInfo.data.system === MOBILE ? true : false;
                let logMySQL = {
                    timeCreated: logsInfo.timeCreated.toString(),
                    system : logsInfo.system === SYSTEM_NTL ? SYSTEM_NTL : SYSTEM_NVN,
                    userId : logsInfo.data.userId ? logsInfo.data.userId + '' : '0',
                    roomId : logsInfo.data.roomId ? logsInfo.data.roomId + '' : '0',
                    device : isApp ? TYPE_APP : TYPE_WEB,
                    appVersion: logsInfo.data.appVersion ? logsInfo.data.appVersion : '0',
                    brand: isApp ? logsInfo.data.brand : logsInfo.data.browser,
                    systemVesion: isApp ? logsInfo.data.brand : logsInfo.data.browser,
                    deviceName: isApp ? `${logsInfo.data.deviceName.replace(/([".*+?^=!:${}()|\[\]\/\\'])/g, "")}` : '',
                    systemName: isApp ? logsInfo.data.systemName : logsInfo.data.OS,
                    timeZone: isApp ? logsInfo.data.timeZone : '',
                    carrier: isApp ? logsInfo.data.carrier : '',
                    deviceId: isApp ? logsInfo.data.deviceId : '',
                    pin: isApp ? logsInfo.data.pin : 0
                }
                InfoLogsModelMySQL.addLogInfoDevice(logMySQL).then(res => {
                    if (res){
                        InfoLogsModelMongoDB2.update(logsInfo._id, { backup: UPDATE_SUCCESS }).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsInfo._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoInfoDevice.log'
                                fs.appendFile(namefile, JSON.stringify(logsInfo),'utf8',function (err) {});
                            }
                        })
                    } else {
                        InfoLogsModelMongoDB2.update(logsInfo._id, UPDATE_FAIL).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsInfo._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoInfoDevice.log'
                                fs.appendFile(namefile, JSON.stringify(logsInfo),'utf8',function (err) {});
                            }
                        })
                    }
                }).catch(err => {
                    console.log('err', err);
                });
            })
        }
    })
}

module.exports = router;