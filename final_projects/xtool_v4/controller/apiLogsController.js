const {
    MOBILE, LMS_LOGIN,
    SYSTEM_NTL, SYSTEM_NVN,
    TYPE_APP, TYPE_WEB, UPDATE_FAIL,
    UPDATE_SUCCESS,
    TOTAL_DOCUMENT, LMS_CLASSES,
    LMS_SELECTCLASS_FAST,
    LMS_SELECTCLASS,
    LMS_CARA, LMS_CARA_ADD,
    LMS_MATERIALS,
    API_LOGIN, API_LOGCHATS,
    API_LOGCHAT_ADD,
    API_LOGINOUT_ADD,
    API_ROOM_INFO,
    API_LOGACTION,
    API_LOGACTION_ADD,
    API_LOGERROR_ADD,
    API_GETTIMESERVER
} = require('../constants');

const ApiLogsModelMongoDB   = require("../model/mongo/apiLogsModel");
const ApiLogsModelMySQL     = require('../model/mysql/apiLogSQLModel');

var fs          = require('fs');
var router = require('express').Router();

router.route('/jobconvertlmslogin').get(convertLMSLogin);
router.route('/jobconvertlmsclass').get(convertLMSClass);
router.route('/jobconvertlmsselectclassfast').get(convertLMSSelectClassFast);
router.route('/jobconvertlmsselectclass').get(convertLMSSelectClass);
router.route('/jobconvertlmscara').get(convertLMSCara);
router.route('/jobconvertlmscaraadd').get(convertLMSCaraAdd);
router.route('/jobconvertlmsmaterial').get(convertLMSMaterial);
router.route('/jobconvertapilogin').get(convertApiLogin);
router.route('/jobconvertapilogchats').get(convertApiLogChats);
router.route('/jobconvertapilogchatsadd').get(convertApiLogChatsAdd);
router.route('/jobconvertapiloginoutadd').get(convertApiLoginoutAdd);
router.route('/jobconvertapiroominfo').get(convertApiRoomInfo);
router.route('/jobconvertapilogaction').get(convertApiLogAction);
router.route('/jobconvertapilogactionadd').get(convertApiLogActionAdd);
router.route('/jobconvertapilogerroradd').get(convertApiLogErrorAdd);
router.route('/jobconvertapigettimeserver').get(convertApiGetTimeServer);

function convertLMSLogin(req, res){
    let where = {
        'data.type': LMS_LOGIN,
        backup: { $ne: UPDATE_SUCCESS }
    };
    ApiLogsModelMongoDB.gets(where, 0, TOTAL_DOCUMENT , {timeCreated : -1}).then((logsAPIs) => {
        res.json(logsAPIs);
        if (logsAPIs){
            logsAPIs.forEach((logsAPI, index) => {
                let logMySQL = {
                    timeCreated : logsAPI.timeCreated.toString(),
                    system      : SYSTEM_NTL,
                    username    : logsAPI.data.data.username,
                    password    : logsAPI.data.data.password,
                    uri         : logsAPI.data.uri,
                    timeResponse: logsAPI.data.timeResponse
                }
                ApiLogsModelMySQL.addApiLMSLogin(logMySQL).then(res => {
                    if (res.status){
                        ApiLogsModelMongoDB.update(logsAPI._id, { backup: UPDATE_SUCCESS }).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAPI._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoApiLMSLogin.log'
                                fs.appendFile(namefile, JSON.stringify(logsAPI),'utf8',function (err) {});
                            }
                        })
                    } else {
                        ApiLogsModelMongoDB.update(logsAPI._id, { backup: UPDATE_FAIL}).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAPI._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoApiLMSLogin.log'
                                fs.appendFile(namefile, JSON.stringify(logsAPI),'utf8',function (err) {});
                            }
                        })
                    }
                }).catch(err => console.log(err));
            })
        }
    })
}

function convertLMSClass(req, res){
    let where = {
        'data.type': LMS_CLASSES,
        backup: { $ne: UPDATE_SUCCESS }
    };
    ApiLogsModelMongoDB.gets(where, 0, TOTAL_DOCUMENT , {timeCreated : -1}).then((logsAPIs) => {
        res.json(logsAPIs);
        if (logsAPIs){
            logsAPIs.forEach((logsAPI, index) => {
                let logMySQL = {
                    timeCreated     : logsAPI.timeCreated.toString(),
                    system          : SYSTEM_NTL,
                    userId          : logsAPI.data.data.userId,
                    packageType     : logsAPI.data.data.packageType,
                    levelClass      : logsAPI.data.data.levelClass,
                    subjectType     : logsAPI.data.data.subjectType,
                    uri             : logsAPI.data.uri,
                    timeResponse    : logsAPI.data.timeResponse
                }
                ApiLogsModelMySQL.addApiLMSClass(logMySQL).then(res => {
                    if (res.status){
                        ApiLogsModelMongoDB.update(logsAPI._id, { backup: UPDATE_SUCCESS }).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAPI._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoApiLMSClass.log'
                                fs.appendFile(namefile, JSON.stringify(logsAPI),'utf8',function (err) {});
                            }
                        })
                    } else {
                        ApiLogsModelMongoDB.update(logsAPI._id, { backup: UPDATE_FAIL}).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAPI._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoApiLMSClass.log'
                                fs.appendFile(namefile, JSON.stringify(logsAPI),'utf8',function (err) {});
                            }
                        })
                    }
                }).catch(err => console.log(err));
            })
        }
    })
}

function convertLMSSelectClassFast(req, res){
    let where = {
        'data.type': LMS_SELECTCLASS_FAST,
        backup: { $ne: UPDATE_SUCCESS }
    };
    ApiLogsModelMongoDB.gets(where, 0, TOTAL_DOCUMENT , {timeCreated : -1}).then((logsAPIs) => {
        res.json(logsAPIs);
        if (logsAPIs){
            logsAPIs.forEach((logsAPI, index) => {
                let logMySQL = {
                    timeCreated     : logsAPI.timeCreated.toString(),
                    system          : SYSTEM_NTL,
                    userId          : logsAPI.data.data.userId,
                    packageType     : logsAPI.data.data.packageType,
                    levelClass      : logsAPI.data.data.levelClass,
                    subjectType     : logsAPI.data.data.subjectType,
                    uri             : logsAPI.data.uri,
                    timeResponse    : logsAPI.data.timeResponse
                }
                ApiLogsModelMySQL.addApiLMSSelectClassFast(logMySQL).then(res => {
                    if (res.status){
                        ApiLogsModelMongoDB.update(logsAPI._id, { backup: UPDATE_SUCCESS }).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAPI._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoApiLMSSelectClassFast.log'
                                fs.appendFile(namefile, JSON.stringify(logsAPI),'utf8',function (err) {});
                            }
                        })
                    } else {
                        ApiLogsModelMongoDB.update(logsAPI._id, { backup: UPDATE_FAIL}).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAPI._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoApiLMSSelectClassFast.log'
                                fs.appendFile(namefile, JSON.stringify(logsAPI),'utf8',function (err) {});
                            }
                        })
                    }
                }).catch(err => console.log(err));
            })
        }
    })
}

function convertLMSSelectClass(req, res){
    let where = {
        'data.type': LMS_SELECTCLASS,
        backup: { $ne: UPDATE_SUCCESS }
    };
    ApiLogsModelMongoDB.gets(where, 0, TOTAL_DOCUMENT , {timeCreated : -1}).then((logsAPIs) => {
        res.json(logsAPIs);
        if (logsAPIs){
            logsAPIs.forEach((logsAPI, index) => {
                let logMySQL = {
                    timeCreated     : logsAPI.timeCreated.toString(),
                    system          : SYSTEM_NTL,
                    userId          : logsAPI.data.data.userId,
                    roomId          : logsAPI.data.data.idRoom,
                    packageType     : logsAPI.data.data.packageType,
                    levelClass      : logsAPI.data.data.levelClass,
                    subjectType     : logsAPI.data.data.subjectType,
                    uri             : logsAPI.data.uri,
                    timeResponse    : logsAPI.data.timeResponse
                }
                ApiLogsModelMySQL.addApiLMSSelectClass(logMySQL).then(res => {
                    if (res.status){
                        ApiLogsModelMongoDB.update(logsAPI._id, { backup: UPDATE_SUCCESS }).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAPI._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoApiLMSSelectClass.log'
                                fs.appendFile(namefile, JSON.stringify(logsAPI),'utf8',function (err) {});
                            }
                        })
                    } else {
                        ApiLogsModelMongoDB.update(logsAPI._id, { backup: UPDATE_FAIL}).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAPI._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoApiLMSSelectClass.log'
                                fs.appendFile(namefile, JSON.stringify(logsAPI),'utf8',function (err) {});
                            }
                        })
                    }
                }).catch(err => console.log(err));
            })
        }
    })
}

function convertLMSCara(req, res){
    let where = {
        'data.type': LMS_CARA,
        backup: { $ne: UPDATE_SUCCESS }
    };
    ApiLogsModelMongoDB.gets(where, 0, TOTAL_DOCUMENT , {timeCreated : -1}).then((logsAPIs) => {
        res.json(logsAPIs);
        if (logsAPIs){
            logsAPIs.forEach((logsAPI, index) => {
                let logMySQL = {
                    timeCreated     : logsAPI.timeCreated.toString(),
                    system          : SYSTEM_NTL,
                    userId          : logsAPI.data.userId.toString(),
                    uri             : logsAPI.data.uri,
                    timeResponse    : logsAPI.data.timeResponse
                }
                ApiLogsModelMySQL.addApiLMSCara(logMySQL).then(res => {
                    if (res.status){
                        ApiLogsModelMongoDB.update(logsAPI._id, { backup: UPDATE_SUCCESS }).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAPI._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoApiLMSCara.log'
                                fs.appendFile(namefile, JSON.stringify(logsAPI),'utf8',function (err) {});
                            }
                        })
                    } else {
                        ApiLogsModelMongoDB.update(logsAPI._id, { backup: UPDATE_FAIL}).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAPI._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoApiLMSCara.log'
                                fs.appendFile(namefile, JSON.stringify(logsAPI),'utf8',function (err) {});
                            }
                        })
                    }
                }).catch(err => console.log(err));
            })
        }
    })
}

function convertLMSCaraAdd(req, res){
    let where = {
        'data.type': LMS_CARA_ADD,
        backup: { $ne: UPDATE_SUCCESS }
    };
    ApiLogsModelMongoDB.gets(where, 0, TOTAL_DOCUMENT , {timeCreated : -1}).then((logsAPIs) => {
        res.json(logsAPIs);
        if (logsAPIs){
            logsAPIs.forEach((logsAPI, index) => {
                let logMySQL = {
                    timeCreated     : logsAPI.timeCreated.toString(),
                    system          : SYSTEM_NTL,
                    userId          : logsAPI.data.data.user_id.toString(),
                    roomId          : logsAPI.data.data.room_id.toString(),
                    advisor         : logsAPI.data.data.advisor,
                    lesson          : logsAPI.data.data.lesson,
                    teacher         : logsAPI.data.data.teacher,
                    assistant       : logsAPI.data.data.assistant,
                    quality         : logsAPI.data.data.class_quality,
                    tech            : logsAPI.data.data.support_tech,
                    message         : logsAPI.data.data.message,
                    uri             : logsAPI.data.uri,
                    timeResponse    : logsAPI.data.timeResponse
                }
                ApiLogsModelMySQL.addApiLMSCaraAdd(logMySQL).then(res => {
                    if (res.status){
                        ApiLogsModelMongoDB.update(logsAPI._id, { backup: UPDATE_SUCCESS }).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAPI._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoApiLMSCaraAdd.log'
                                fs.appendFile(namefile, JSON.stringify(logsAPI),'utf8',function (err) {});
                            }
                        })
                    } else {
                        ApiLogsModelMongoDB.update(logsAPI._id, { backup: UPDATE_FAIL}).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAPI._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoApiLMSCaraAdd.log'
                                fs.appendFile(namefile, JSON.stringify(logsAPI),'utf8',function (err) {});
                            }
                        })
                    }
                }).catch(err => console.log(err));
            })
        }
    })
}

function convertLMSMaterial(req, res){
    let where = {
        'data.type': LMS_MATERIALS,
        backup: { $ne: UPDATE_SUCCESS }
    };
    ApiLogsModelMongoDB.gets(where, 0, TOTAL_DOCUMENT , {timeCreated : -1}).then((logsAPIs) => {
        res.json(logsAPIs);
        if (logsAPIs){
            logsAPIs.forEach((logsAPI, index) => {
                let logMySQL = {
                    timeCreated     : logsAPI.timeCreated.toString(),
                    system          : SYSTEM_NTL,
                    userId          : logsAPI.data.userId,
                    levelClass      : logsAPI.data.data.levelClass,
                    week            : logsAPI.data.data.week,
                    uri             : logsAPI.data.uri,
                    timeResponse    : logsAPI.data.timeResponse
                }
                ApiLogsModelMySQL.addApiLMSMaterial(logMySQL).then(res => {
                    if (res.status){
                        ApiLogsModelMongoDB.update(logsAPI._id, { backup: UPDATE_SUCCESS }).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAPI._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoApiLMSMaterial.log'
                                fs.appendFile(namefile, JSON.stringify(logsAPI),'utf8',function (err) {});
                            }
                        })
                    } else {
                        ApiLogsModelMongoDB.update(logsAPI._id, { backup: UPDATE_FAIL}).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAPI._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoApiLMSMaterial.log'
                                fs.appendFile(namefile, JSON.stringify(logsAPI),'utf8',function (err) {});
                            }
                        })
                    }
                }).catch(err => console.log(err));
            })
        }
    })
}

function convertApiLogin(req, res){
    let where = {
        'data.type': API_LOGIN,
        backup: { $ne: UPDATE_SUCCESS }
    };
    ApiLogsModelMongoDB.gets(where, 0, TOTAL_DOCUMENT , {timeCreated : -1}).then((logsAPIs) => {
        res.json(logsAPIs);
        if (logsAPIs){
            logsAPIs.forEach((logsAPI, index) => {
                let logMySQL = {
                    timeCreated     : logsAPI.timeCreated.toString(),
                    system          : logsAPI.system === SYSTEM_NTL ? SYSTEM_NTL : SYSTEM_NVN,
                    username        : logsAPI.data.data.username,
                    password        : logsAPI.data.data.password,
                    uri             : logsAPI.data.uri,
                    timeResponse    : logsAPI.data.timeResponse
                }
                ApiLogsModelMySQL.addApiLogin(logMySQL).then(res => {
                    if (res.status){
                        ApiLogsModelMongoDB.update(logsAPI._id, { backup: UPDATE_SUCCESS }).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAPI._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoApiLogin.log'
                                fs.appendFile(namefile, JSON.stringify(logsAPI),'utf8',function (err) {});
                            }
                        })
                    } else {
                        ApiLogsModelMongoDB.update(logsAPI._id, { backup: UPDATE_FAIL}).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAPI._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoApiLogin.log'
                                fs.appendFile(namefile, JSON.stringify(logsAPI),'utf8',function (err) {});
                            }
                        })
                    }
                }).catch(err => console.log(err));
            })
        }
    })
}

function convertApiLogChats(req, res){
    let where = {
        'data.type': API_LOGCHATS,
        backup: { $ne: UPDATE_SUCCESS }
    };
    ApiLogsModelMongoDB.gets(where, 0, TOTAL_DOCUMENT , {timeCreated : -1}).then((logsAPIs) => {
        res.json(logsAPIs);
        if (logsAPIs){
            logsAPIs.forEach((logsAPI, index) => {
                let logMySQL = {
                    timeCreated     : logsAPI.timeCreated.toString(),
                    system          : logsAPI.system === SYSTEM_NTL ? SYSTEM_NTL : SYSTEM_NVN,
                    userId          : logsAPI.data.userId.toString(),
                    roomId          : logsAPI.data.roomId,
                    roomIdVcrx      : logsAPI.data.data.idRoomVcrx,
                    uri             : logsAPI.data.uri,
                    timeResponse    : logsAPI.data.timeResponse
                }
                ApiLogsModelMySQL.addApiLogChats(logMySQL).then(res => {
                    if (res.status){
                        ApiLogsModelMongoDB.update(logsAPI._id, { backup: UPDATE_SUCCESS }).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAPI._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoApiLogChats.log'
                                fs.appendFile(namefile, JSON.stringify(logsAPI),'utf8',function (err) {});
                            }
                        })
                    } else {
                        ApiLogsModelMongoDB.update(logsAPI._id, { backup: UPDATE_FAIL}).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAPI._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoApiLogChats.log'
                                fs.appendFile(namefile, JSON.stringify(logsAPI),'utf8',function (err) {});
                            }
                        })
                    }
                }).catch(err => console.log(err));
            })
        }
    })
}

function convertApiLogChatsAdd(req, res){
    let where = {
        'data.type': API_LOGCHAT_ADD,
        backup: { $ne: UPDATE_SUCCESS }
    };
    ApiLogsModelMongoDB.gets(where, 0, TOTAL_DOCUMENT , {timeCreated : -1}).then((logsAPIs) => {
        res.json(logsAPIs);
        if (logsAPIs){
            logsAPIs.forEach((logsAPI, index) => {
                let logMySQL = {
                    timeCreated         : logsAPI.timeCreated.toString(),
                    system              : logsAPI.system === SYSTEM_NTL ? SYSTEM_NTL : SYSTEM_NVN,
                    userId              : logsAPI.data.userId.toString(),
                    roomId              : logsAPI.data.roomId,
                    roomIdVcrx          : logsAPI.data.data.roomId,
                    fromParticipantId   : logsAPI.data.data.fromParticipantId.toString(),
                    toParticipantId     : logsAPI.data.data.toParticipantId.toString(),
                    mess                : logsAPI.data.data.data,
                    uri                 : logsAPI.data.uri,
                    timeResponse        : logsAPI.data.timeResponse
                }
                ApiLogsModelMySQL.addApiLogChatsAdd(logMySQL).then(res => {
                    if (res.status){
                        ApiLogsModelMongoDB.update(logsAPI._id, { backup: UPDATE_SUCCESS }).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAPI._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoApiLogChatsAdd.log'
                                fs.appendFile(namefile, JSON.stringify(logsAPI),'utf8',function (err) {});
                            }
                        })
                    } else {
                        ApiLogsModelMongoDB.update(logsAPI._id, { backup: UPDATE_FAIL}).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAPI._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoApiLogChatsAdd.log'
                                fs.appendFile(namefile, JSON.stringify(logsAPI),'utf8',function (err) {});
                            }
                        })
                    }
                }).catch(err => console.log(err));
            })
        }
    })
}

function convertApiLoginoutAdd(req, res){
    let where = {
        'data.type': API_LOGINOUT_ADD,
        backup: { $ne: UPDATE_SUCCESS }
    };
    ApiLogsModelMongoDB.gets(where, 0, TOTAL_DOCUMENT , {timeCreated : -1}).then((logsAPIs) => {
        res.json(logsAPIs);
        if (logsAPIs){
            logsAPIs.forEach((logsAPI, index) => {
                let logMySQL = {
                    timeCreated         : logsAPI.timeCreated.toString(),
                    system              : logsAPI.system === SYSTEM_NTL ? SYSTEM_NTL : SYSTEM_NVN,
                    userId              : logsAPI.data.userId,
                    roomId              : logsAPI.data.roomId,
                    roomIdVcrx          : logsAPI.data.data.roomId + '',
                    participantId       : logsAPI.data.data.participantId + '',
                    action              : logsAPI.data.data.action,
                    role                : JSON.parse(logsAPI.data.data.data).role,
                    uri                 : logsAPI.data.uri,
                    timeResponse        : logsAPI.data.timeResponse
                }
                ApiLogsModelMySQL.addApiLoginoutAdd(logMySQL).then(res => {
                    if (res.status){
                        ApiLogsModelMongoDB.update(logsAPI._id, { backup: UPDATE_SUCCESS }).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAPI._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoApiLoginoutAdd.log'
                                fs.appendFile(namefile, JSON.stringify(logsAPI),'utf8',function (err) {});
                            }
                        })
                    } else {
                        ApiLogsModelMongoDB.update(logsAPI._id, { backup: UPDATE_FAIL}).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAPI._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoApiLoginoutAdd.log'
                                fs.appendFile(namefile, JSON.stringify(logsAPI),'utf8',function (err) {});
                            }
                        })
                    }
                }).catch(err => console.log(err));
            })
        }
    })
}

function convertApiRoomInfo(req, res){
    let where = {
        'data.type': API_ROOM_INFO,
        backup: { $ne: UPDATE_SUCCESS }
    };
    ApiLogsModelMongoDB.gets(where, 0, TOTAL_DOCUMENT , {timeCreated : -1}).then((logsAPIs) => {
        res.json(logsAPIs);
        if (logsAPIs){
            logsAPIs.forEach((logsAPI, index) => {
                let logMySQL = {
                    timeCreated         : logsAPI.timeCreated.toString(),
                    system              : logsAPI.system === SYSTEM_NTL ? SYSTEM_NTL : SYSTEM_NVN,
                    userId              : logsAPI.data.userId,
                    roomId              : logsAPI.data.roomId,
                    roomIdVcrx          : logsAPI.data.data.idRoomVcrx + '',
                    uri                 : logsAPI.data.uri,
                    timeResponse        : logsAPI.data.timeResponse
                }
                ApiLogsModelMySQL.addApiRoomInfo(logMySQL).then(res => {
                    if (res.status){
                        ApiLogsModelMongoDB.update(logsAPI._id, { backup: UPDATE_SUCCESS }).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAPI._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoApiRoomInfo.log'
                                fs.appendFile(namefile, JSON.stringify(logsAPI),'utf8',function (err) {});
                            }
                        })
                    } else {
                        ApiLogsModelMongoDB.update(logsAPI._id, { backup: UPDATE_FAIL}).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAPI._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoApiRoomInfo.log'
                                fs.appendFile(namefile, JSON.stringify(logsAPI),'utf8',function (err) {});
                            }
                        })
                    }
                }).catch(err => console.log(err));
            })
        }
    })
}

function convertApiLogAction(req, res){
    let where = {
        'data.type': API_LOGACTION,
        backup: { $ne: UPDATE_SUCCESS }
    };
    ApiLogsModelMongoDB.gets(where, 0, TOTAL_DOCUMENT , {timeCreated : -1}).then((logsAPIs) => {
        res.json(logsAPIs);
        if (logsAPIs){
            logsAPIs.forEach((logsAPI, index) => {
                let logMySQL = {
                    timeCreated         : logsAPI.timeCreated.toString(),
                    system              : logsAPI.system === SYSTEM_NTL ? SYSTEM_NTL : SYSTEM_NVN,
                    userId              : logsAPI.data.userId,
                    roomId              : logsAPI.data.roomId,
                    roomIdVcrx          : logsAPI.data.data.idRoomVcrx,
                    typeId              : logsAPI.data.data.logTypeId,
                    uri                 : logsAPI.data.uri,
                    timeResponse        : logsAPI.data.timeResponse
                }
                ApiLogsModelMySQL.addApiLogAction(logMySQL).then(res => {
                    if (res.status){
                        ApiLogsModelMongoDB.update(logsAPI._id, { backup: UPDATE_SUCCESS }).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAPI._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoApiLogAction.log'
                                fs.appendFile(namefile, JSON.stringify(logsAPI),'utf8',function (err) {});
                            }
                        })
                    } else {
                        ApiLogsModelMongoDB.update(logsAPI._id, { backup: UPDATE_FAIL}).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAPI._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoApiLogAction.log'
                                fs.appendFile(namefile, JSON.stringify(logsAPI),'utf8',function (err) {});
                            }
                        })
                    }
                }).catch(err => console.log(err));
            })
        }
    })
}

function convertApiLogActionAdd(req, res){
    let where = {
        'data.type': API_LOGACTION_ADD,
        backup: { $ne: UPDATE_SUCCESS }
    };
    ApiLogsModelMongoDB.gets(where, 0, TOTAL_DOCUMENT , {timeCreated : -1}).then((logsAPIs) => {
        res.json(logsAPIs);
        if (logsAPIs){
            logsAPIs.forEach((logsAPI, index) => {
                let logMySQL = {
                    timeCreated         : logsAPI.timeCreated.toString(),
                    system              : logsAPI.system === SYSTEM_NTL ? SYSTEM_NTL : SYSTEM_NVN,
                    userId              : logsAPI.data.userId,
                    roomId              : logsAPI.data.roomId,
                    roomIdVcrx          : logsAPI.data.data.roomId,
                    typeId              : logsAPI.data.data.logTypeId,
                    data                : logsAPI.data.data.data,
                    uri                 : logsAPI.data.uri,
                    timeResponse        : logsAPI.data.timeResponse
                }
                ApiLogsModelMySQL.addApiLogActionAdd(logMySQL).then(res => {
                    if (res.status){
                        ApiLogsModelMongoDB.update(logsAPI._id, { backup: UPDATE_SUCCESS }).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAPI._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoApiLogActionAdd.log'
                                fs.appendFile(namefile, JSON.stringify(logsAPI),'utf8',function (err) {});
                            }
                        })
                    } else {
                        ApiLogsModelMongoDB.update(logsAPI._id, { backup: UPDATE_FAIL}).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAPI._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoApiLogActionAdd.log'
                                fs.appendFile(namefile, JSON.stringify(logsAPI),'utf8',function (err) {});
                            }
                        })
                    }
                }).catch(err => console.log(err));
            })
        }
    })
}

function convertApiLogErrorAdd(req, res){
    let where = {
        'data.type': API_LOGERROR_ADD,
        backup: { $ne: UPDATE_SUCCESS }
    };
    ApiLogsModelMongoDB.gets(where, 0, TOTAL_DOCUMENT , {timeCreated : -1}).then((logsAPIs) => {
        res.json(logsAPIs);
        if (logsAPIs){
            logsAPIs.forEach((logsAPI, index) => {
                let logMySQL = {
                    timeCreated         : logsAPI.timeCreated.toString(),
                    system              : logsAPI.system === SYSTEM_NTL ? SYSTEM_NTL : SYSTEM_NVN,
                    userId              : logsAPI.data.userId + '',
                    roomId              : logsAPI.data.roomId,
                    roomIdVcrx          : logsAPI.data.data.roomId,
                    paticipantId        : logsAPI.data.data.paticipantId + '',
                    title               : logsAPI.data.data.title,
                    message             : logsAPI.data.data.message,
                    device              : logsAPI.data.data.device,
                    role                : logsAPI.data.data.userRoleLMS,
                    uri                 : logsAPI.data.uri,
                    timeResponse        : logsAPI.data.timeResponse
                }
                ApiLogsModelMySQL.addApiLogErrorAdd(logMySQL).then(res => {
                    if (res.status){
                        ApiLogsModelMongoDB.update(logsAPI._id, { backup: UPDATE_SUCCESS }).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAPI._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoApiLogErrorAdd.log'
                                fs.appendFile(namefile, JSON.stringify(logsAPI),'utf8',function (err) {});
                            }
                        })
                    } else {
                        ApiLogsModelMongoDB.update(logsAPI._id, { backup: UPDATE_FAIL}).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAPI._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoApiLogErrorAdd.log'
                                fs.appendFile(namefile, JSON.stringify(logsAPI),'utf8',function (err) {});
                            }
                        })
                    }
                }).catch(err => console.log(err));
            })
        }
    })
}

function convertApiGetTimeServer(req, res){
    let where = {
        'data.type': API_GETTIMESERVER,
        backup: { $ne: UPDATE_SUCCESS }
    };
    ApiLogsModelMongoDB.gets(where, 0, TOTAL_DOCUMENT , {timeCreated : -1}).then((logsAPIs) => {
        res.json(logsAPIs);
        if (logsAPIs){
            logsAPIs.forEach((logsAPI, index) => {
                let logMySQL = {
                    timeCreated         : logsAPI.timeCreated.toString(),
                    system              : logsAPI.system === SYSTEM_NTL ? SYSTEM_NTL : SYSTEM_NVN,
                    userId              : logsAPI.data.userId,
                    roomId              : logsAPI.data.roomId,
                    roomIdVcrx          : logsAPI.data.data ? logsAPI.data.data.idRoom : '0',
                    uri                 : logsAPI.data.uri,
                    timeResponse        : logsAPI.data.timeResponse
                }
                ApiLogsModelMySQL.addApiGetTimeServer(logMySQL).then(res => {
                    if (res.status){
                        ApiLogsModelMongoDB.update(logsAPI._id, { backup: UPDATE_SUCCESS }).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAPI._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoApiGetTimeServer.log'
                                fs.appendFile(namefile, JSON.stringify(logsAPI),'utf8',function (err) {});
                            }
                        })
                    } else {
                        ApiLogsModelMongoDB.update(logsAPI._id, { backup: UPDATE_FAIL}).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAPI._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoApiGetTimeServer.log'
                                fs.appendFile(namefile, JSON.stringify(logsAPI),'utf8',function (err) {});
                            }
                        })
                    }
                }).catch(err => console.log(err));
            })
        }
    })
}

module.exports = router;