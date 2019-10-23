const {
    MOBILE, LEAVE_ROOM,
    SYSTEM_NTL, SYSTEM_NVN,
    TYPE_APP, TYPE_WEB, UPDATE_FAIL,
    UPDATE_SUCCESS,
    TOTAL_DOCUMENT, LOGIN,
    SELECT_CLASS, SELECT_CLASS_FAST,
    RAISE_HAND, TURN_ON_OFF_MICRO,
    TURN_ON_OFF_CAMERA, LOAD_SLIDE,
    NEXT_PREV_SLIDE, MATERIAL_VIEW,
    LINKING_PORTAL_CONNECT,
    RENDER_FROM_LMS
} = require('../constants');

const ActionLogsModelMongoDB   = require("../model/mongo/actionLogModel");
const ActionLogsModelMySQL     = require('../model/mysql/actionLogSQLModel');

var fs          = require('fs');
var router = require('express').Router();

router.route('/jobconvertlogleaveroom').get(convertLeaveRoom);
router.route('/jobconvertloglogin').get(convertLogin);
router.route('/jobconvertlogselectclass').get(convertSelectClass);
router.route('/jobconvertlogselectclassfast').get(convertSelectClassFast);
router.route('/jobconvertlograisehand').get(convertRaiseHand);
router.route('/jobconvertlogmicro').get(convertMicro);
router.route('/jobconvertlogcamera').get(convertCamera);
router.route('/jobconvertlogloadslide').get(convertLoadSlide);
router.route('/jobconvertlogactionslide').get(convertActionSlide);
router.route('/jobconvertlogmaterialview').get(convertMaterialView);
router.route('/jobconvertloglinkingportal').get(convertLinkingPortal);
router.route('/jobconvertlogrenderfromlms').get(convertRenderFromLMS);

function convertLeaveRoom(req, res){
    let where = {
        type: LEAVE_ROOM,
        backup: { $ne: UPDATE_SUCCESS }
    };
    ActionLogsModelMongoDB.gets(where, 0, TOTAL_DOCUMENT , {timeCreated : -1}).then((logsActions) => {
        res.json(logsActions);
        if (logsActions){
            logsActions.forEach((logsAction, index) => {
                let logMySQL = {
                    timeCreated: logsAction.timeCreated.toString(),
                    system : logsAction.system === SYSTEM_NTL ? SYSTEM_NTL : SYSTEM_NVN,
                    userId : logsAction.data.userId,
                    roomId : logsAction.data.roomId,
                    device : logsAction.data.device === MOBILE ? TYPE_APP : TYPE_WEB,
                    type   : logsAction.data.type
                }
                ActionLogsModelMySQL.addLogLeaveRoom(logMySQL).then(res => {
                    if (res.status){
                        ActionLogsModelMongoDB.update(logsAction._id, { backup: UPDATE_SUCCESS }).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAction._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoActionLeaveRoom.log'
                                fs.appendFile(namefile, JSON.stringify(logsAction),'utf8',function (err) {});
                            }
                        })
                    } else {
                        ActionLogsModelMongoDB.update(logsAction._id, { backup: UPDATE_FAIL}).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAction._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoActionLeaveRoom.log'
                                fs.appendFile(namefile, JSON.stringify(logsAction),'utf8',function (err) {});
                            }
                        })
                    }
                }).catch(err => console.log(err));
            })
        }
    })
}

function convertLogin(req, res){
    let where = {
        type: LOGIN,
        backup: { $ne: UPDATE_SUCCESS }
    };
    ActionLogsModelMongoDB.gets(where, 0, TOTAL_DOCUMENT , {timeCreated : -1}).then((logsActions) => {
        res.json(logsActions);
        if (logsActions){
            logsActions.forEach((logsAction, index) => {
                let logMySQL = {
                    timeCreated : logsAction.timeCreated.toString(),
                    system      : logsAction.system === SYSTEM_NTL ? SYSTEM_NTL : SYSTEM_NVN,
                    username    : logsAction.data.username,
                    password    : logsAction.data.password,
                    device      : TYPE_APP,
                    action      : logsAction.data.action
                }
                ActionLogsModelMySQL.addLogLogin(logMySQL).then(res => {
                    if (res.status){
                        ActionLogsModelMongoDB.update(logsAction._id, { backup: UPDATE_SUCCESS }).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAction._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoActionLogin.log'
                                fs.appendFile(namefile, JSON.stringify(logsAction),'utf8',function (err) {});
                            }
                        })
                    } else {
                        ActionLogsModelMongoDB.update(logsAction._id, { backup: UPDATE_FAIL}).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAction._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoActionLogin.log'
                                fs.appendFile(namefile, JSON.stringify(logsAction),'utf8',function (err) {});
                            }
                        })
                    }
                }).catch(err => console.log(err));
            })
        }
    })
}

function convertSelectClass(req, res){
    let where = {
        type: SELECT_CLASS,
        backup: { $ne: UPDATE_SUCCESS }
    };
    ActionLogsModelMongoDB.gets(where, 0, TOTAL_DOCUMENT , {timeCreated : -1}).then((logsActions) => {
        res.json(logsActions);
        if (logsActions){
            logsActions.forEach((logsAction, index) => {
                let logMySQL = {
                    timeCreated : logsAction.timeCreated.toString(),
                    system      : SYSTEM_NTL,
                    userId      : logsAction.data.userId.toString(),
                    roomId      : logsAction.data.roomId.toString(),
                    subjectType : logsAction.data.subjectType,
                    device      : TYPE_APP
                }
                ActionLogsModelMySQL.addLogSelectClass(logMySQL).then(res => {
                    if (res.status){
                        ActionLogsModelMongoDB.update(logsAction._id, { backup: UPDATE_SUCCESS }).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAction._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoActionSelectClass.log'
                                fs.appendFile(namefile, JSON.stringify(logsAction),'utf8',function (err) {});
                            }
                        })
                    } else {
                        ActionLogsModelMongoDB.update(logsAction._id, { backup: UPDATE_FAIL}).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAction._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoActionSelectClass.log'
                                fs.appendFile(namefile, JSON.stringify(logsAction),'utf8',function (err) {});
                            }
                        })
                    }
                }).catch(err => console.log(err));
            })
        }
    })
}

function convertSelectClassFast(req, res){
    let where = {
        type: SELECT_CLASS_FAST,
        backup: { $ne: UPDATE_SUCCESS }
    };
    ActionLogsModelMongoDB.gets(where, 0, TOTAL_DOCUMENT , {timeCreated : -1}).then((logsActions) => {
        res.json(logsActions);
        if (logsActions){
            logsActions.forEach((logsAction, index) => {
                let logMySQL = {
                    timeCreated : logsAction.timeCreated.toString(),
                    system      : SYSTEM_NTL,
                    userId      : logsAction.data.userId.toString(),
                    roomId      : logsAction.data.roomId.toString(),
                    subjectType : logsAction.data.subjectType,
                    device      : TYPE_APP
                }
                ActionLogsModelMySQL.addLogSelectClassFast(logMySQL).then(res => {
                    if (res.status){
                        ActionLogsModelMongoDB.update(logsAction._id, { backup: UPDATE_SUCCESS }).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAction._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoActionSelectClassFast.log'
                                fs.appendFile(namefile, JSON.stringify(logsAction),'utf8',function (err) {});
                            }
                        })
                    } else {
                        ActionLogsModelMongoDB.update(logsAction._id, { backup: UPDATE_FAIL}).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAction._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoActionSelectClassFast.log'
                                fs.appendFile(namefile, JSON.stringify(logsAction),'utf8',function (err) {});
                            }
                        })
                    }
                }).catch(err => console.log(err));
            })
        }
    })
}

function convertRaiseHand(req, res){
    let where = {
        type: RAISE_HAND,
        backup: { $ne: UPDATE_SUCCESS }
    };
    ActionLogsModelMongoDB.gets(where, 0, TOTAL_DOCUMENT , {timeCreated : -1}).then((logsActions) => {
        res.json(logsActions);
        if (logsActions){
            logsActions.forEach((logsAction, index) => {
                let logMySQL = {
                    timeCreated : logsAction.timeCreated.toString(),
                    system : logsAction.system === SYSTEM_NTL ? SYSTEM_NTL : SYSTEM_NVN,
                    userId      : logsAction.data.userId.toString(),
                    roomId      : logsAction.data.roomId.toString(),
                    status      : logsAction.data.status ? 1 : 0,
                    device      : logsAction.data.system === MOBILE ? TYPE_APP : TYPE_WEB,
                }
                ActionLogsModelMySQL.addLogRaiseHand(logMySQL).then(res => {
                    if (res.status){
                        ActionLogsModelMongoDB.update(logsAction._id, { backup: UPDATE_SUCCESS }).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAction._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoActionRaiseHand.log'
                                fs.appendFile(namefile, JSON.stringify(logsAction),'utf8',function (err) {});
                            }
                        })
                    } else {
                        ActionLogsModelMongoDB.update(logsAction._id, { backup: UPDATE_FAIL}).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAction._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoActionRaiseHand.log'
                                fs.appendFile(namefile, JSON.stringify(logsAction),'utf8',function (err) {});
                            }
                        })
                    }
                }).catch(err => console.log(err));
            })
        }
    })
}

function convertMicro(req, res){
    let where = {
        type: TURN_ON_OFF_MICRO,
        backup: { $ne: UPDATE_SUCCESS }
    };
    ActionLogsModelMongoDB.gets(where, 0, TOTAL_DOCUMENT , {timeCreated : -1}).then((logsActions) => {
        res.json(logsActions);
        if (logsActions){
            logsActions.forEach((logsAction, index) => {
                let logMySQL = {
                    timeCreated : logsAction.timeCreated.toString(),
                    system : logsAction.system === SYSTEM_NTL ? SYSTEM_NTL : SYSTEM_NVN,
                    userId      : logsAction.data.userId.toString(),
                    roomId      : logsAction.data.roomId.toString(),
                    action      : logsAction.data.action,
                    status      : logsAction.data.status ,
                    timeListen  : logsAction.data.timeListen
                }
                ActionLogsModelMySQL.addLogMicro(logMySQL).then(res => {
                    if (res.status){
                        ActionLogsModelMongoDB.update(logsAction._id, { backup: UPDATE_SUCCESS }).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAction._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoActionTurnOnOffMicro.log'
                                fs.appendFile(namefile, JSON.stringify(logsAction),'utf8',function (err) {});
                            }
                        })
                    } else {
                        ActionLogsModelMongoDB.update(logsAction._id, { backup: UPDATE_FAIL}).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAction._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoActionTurnOnOffMicro.log'
                                fs.appendFile(namefile, JSON.stringify(logsAction),'utf8',function (err) {});
                            }
                        })
                    }
                }).catch(err => console.log(err));
            })
        }
    })
}

function convertCamera(req, res){
    let where = {
        type: TURN_ON_OFF_CAMERA,
        backup: { $ne: UPDATE_SUCCESS }
    };
    ActionLogsModelMongoDB.gets(where, 0, TOTAL_DOCUMENT , {timeCreated : -1}).then((logsActions) => {
        res.json(logsActions);
        if (logsActions){
            logsActions.forEach((logsAction, index) => {
                let logMySQL = {
                    timeCreated : logsAction.timeCreated.toString(),
                    system : logsAction.system === SYSTEM_NTL ? SYSTEM_NTL : SYSTEM_NVN,
                    userId      : logsAction.data.userId.toString(),
                    roomId      : logsAction.data.roomId.toString(),
                    action      : logsAction.data.action,
                    status      : logsAction.data.status ,
                    timeListen  : logsAction.data.timeListen
                }
                ActionLogsModelMySQL.addLogCamera(logMySQL).then(res => {
                    if (res.status){
                        ActionLogsModelMongoDB.update(logsAction._id, { backup: UPDATE_SUCCESS }).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAction._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoActionTurnOnOffCamera.log'
                                fs.appendFile(namefile, JSON.stringify(logsAction),'utf8',function (err) {});
                            }
                        })
                    } else {
                        ActionLogsModelMongoDB.update(logsAction._id, { backup: UPDATE_FAIL}).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAction._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoActionTurnOnOffCamera.log'
                                fs.appendFile(namefile, JSON.stringify(logsAction),'utf8',function (err) {});
                            }
                        })
                    }
                }).catch(err => console.log(err));
            })
        }
    })
}

function convertLoadSlide(req, res){
    let where = {
        type: LOAD_SLIDE,
        backup: { $ne: UPDATE_SUCCESS }
    };
    ActionLogsModelMongoDB.gets(where, 0, TOTAL_DOCUMENT , {timeCreated : -1}).then((logsActions) => {
        res.json(logsActions);
        if (logsActions){
            logsActions.forEach((logsAction, index) => {
                let logMySQL = {
                    timeCreated : logsAction.timeCreated.toString(),
                    system      : logsAction.system === SYSTEM_NTL ? SYSTEM_NTL : SYSTEM_NVN,
                    userId      : logsAction.data.userId.toString(),
                    roomId      : logsAction.data.roomId.toString(),
                    link        : logsAction.data.linkSlide,
                    timeLoad    : logsAction.data.timeLoad
                }
                ActionLogsModelMySQL.addLogLoadSlide(logMySQL).then(res => {
                    if (res.status){
                        ActionLogsModelMongoDB.update(logsAction._id, { backup: UPDATE_SUCCESS }).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAction._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoActionLoadSlide.log'
                                fs.appendFile(namefile, JSON.stringify(logsAction),'utf8',function (err) {});
                            }
                        })
                    } else {
                        ActionLogsModelMongoDB.update(logsAction._id, { backup: UPDATE_FAIL}).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAction._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoActionLoadSlide.log'
                                fs.appendFile(namefile, JSON.stringify(logsAction),'utf8',function (err) {});
                            }
                        })
                    }
                }).catch(err => console.log(err));
            })
        }
    })
}

function convertActionSlide(req, res){
    let where = {
        type: NEXT_PREV_SLIDE,
        backup: { $ne: UPDATE_SUCCESS }
    };
    ActionLogsModelMongoDB.gets(where, 0, TOTAL_DOCUMENT , {timeCreated : -1}).then((logsActions) => {
        res.json(logsActions);
        if (logsActions){
            logsActions.forEach((logsAction, index) => {
                let logMySQL = {
                    timeCreated : logsAction.timeCreated.toString(),
                    system      : logsAction.system === SYSTEM_NTL ? SYSTEM_NTL : SYSTEM_NVN,
                    userId      : logsAction.data.userId.toString(),
                    roomId      : logsAction.data.roomId.toString(),
                    page        : logsAction.data.page,
                    next        : logsAction.data.next ? 1 : 0,
                    link        : logsAction.data.linkSlide,
                    device      : TYPE_WEB
                }
                ActionLogsModelMySQL.addLogActionSlide(logMySQL).then(res => {
                    if (res.status){
                        ActionLogsModelMongoDB.update(logsAction._id, { backup: UPDATE_SUCCESS }).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAction._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoActionNextPrevSlide.log'
                                fs.appendFile(namefile, JSON.stringify(logsAction),'utf8',function (err) {});
                            }
                        })
                    } else {
                        ActionLogsModelMongoDB.update(logsAction._id, { backup: UPDATE_FAIL}).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAction._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoActionNextPrevSlide.log'
                                fs.appendFile(namefile, JSON.stringify(logsAction),'utf8',function (err) {});
                            }
                        })
                    }
                }).catch(err => console.log(err));
            })
        }
    })
}

function convertMaterialView(req, res){
    let where = {
        type: MATERIAL_VIEW,
        backup: { $ne: UPDATE_SUCCESS }
    };
    ActionLogsModelMongoDB.gets(where, 0, TOTAL_DOCUMENT , {timeCreated : -1}).then((logsActions) => {
        res.json(logsActions);
        if (logsActions){
            logsActions.forEach((logsAction, index) => {
                let logMySQL = {
                    timeCreated : logsAction.timeCreated.toString(),
                    system      : logsAction.system === SYSTEM_NTL ? SYSTEM_NTL : SYSTEM_NVN,
                    userId      : logsAction.data.userId.toString(),
                    subjectType : logsAction.data.subjectType,
                    link        : logsAction.data.linkMaterial,
                    materialDay : logsAction.data.materialDay,
                    device      : TYPE_APP
                }
                ActionLogsModelMySQL.addLogMaterialView(logMySQL).then(res => {
                    if (res.status){
                        ActionLogsModelMongoDB.update(logsAction._id, { backup: UPDATE_SUCCESS }).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAction._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoActionMaterialView.log'
                                fs.appendFile(namefile, JSON.stringify(logsAction),'utf8',function (err) {});
                            }
                        })
                    } else {
                        ActionLogsModelMongoDB.update(logsAction._id, { backup: UPDATE_FAIL}).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAction._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoActionMaterialView.log'
                                fs.appendFile(namefile, JSON.stringify(logsAction),'utf8',function (err) {});
                            }
                        })
                    }
                }).catch(err => console.log(err));
            })
        }
    })
}

function convertLinkingPortal(req, res){
    let where = {
        type: LINKING_PORTAL_CONNECT,
        backup: { $ne: UPDATE_SUCCESS }
    };
    ActionLogsModelMongoDB.gets(where, 0, TOTAL_DOCUMENT , {timeCreated : -1}).then((logsActions) => {
        res.json(logsActions);
        if (logsActions){
            logsActions.forEach((logsAction, index) => {
                let logMySQL = {
                    timeCreated : logsAction.timeCreated.toString(),
                    system      : logsAction.system === SYSTEM_NTL ? SYSTEM_NTL : SYSTEM_NVN,
                    userId      : logsAction.data.userId.toString(),
                    roomId      : logsAction.data.roomId ? logsAction.data.roomId : '0',
                    userIdVcrx  : logsAction.data.userIdVcrx,
                    role        : logsAction.data.role,
                    roomIdVcrx  : logsAction.data.roomIdVcrx,
                    firstname   : logsAction.data.firstname,
                    lastname    : logsAction.data.lastname,
                    systemInfo  : logsAction.data.systemInfo ,
                    uri         : logsAction.data.uri ,
                    device      : TYPE_APP
                }
                ActionLogsModelMySQL.addLogLinkingPortal(logMySQL).then(res => {
                    if (res.status){
                        ActionLogsModelMongoDB.update(logsAction._id, { backup: UPDATE_SUCCESS }).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAction._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoActionLinkingPortalConnection.log'
                                fs.appendFile(namefile, JSON.stringify(logsAction),'utf8',function (err) {});
                            }
                        })
                    } else {
                        ActionLogsModelMongoDB.update(logsAction._id, { backup: UPDATE_FAIL}).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAction._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoActionLinkingPortalConnection.log'
                                fs.appendFile(namefile, JSON.stringify(logsAction),'utf8',function (err) {});
                            }
                        })
                    }
                }).catch(err => console.log(err));
            })
        }
    })
}

function convertRenderFromLMS(req, res){
    let where = {
        type: RENDER_FROM_LMS,
        backup: { $ne: UPDATE_SUCCESS }
    };
    ActionLogsModelMongoDB.gets(where, 0, TOTAL_DOCUMENT , {timeCreated : -1}).then((logsActions) => {
        res.json(logsActions);
        if (logsActions){
            logsActions.forEach((logsAction, index) => {
                let logMySQL = {
                    timeCreated : logsAction.timeCreated.toString(),
                    system      : logsAction.system === SYSTEM_NTL ? SYSTEM_NTL : SYSTEM_NVN,
                    userId      : logsAction.data.userId,
                    roomId      : logsAction.data.roomId ? logsAction.data.roomId : '0',
                    userIdVcrx  : logsAction.data.userIdVcrx,
                    role        : logsAction.data.role,
                    roomIdVcrx  : logsAction.data.roomIdVcrx,
                    userName   : logsAction.data.userName,
                    uri         : logsAction.data.uri ,
                    device      : TYPE_WEB
                }
                ActionLogsModelMySQL.addLogRenderFromLMS(logMySQL).then(res => {
                    if (res.status){
                        ActionLogsModelMongoDB.update(logsAction._id, { backup: UPDATE_SUCCESS }).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAction._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoActionRenderFromLMS.log'
                                fs.appendFile(namefile, JSON.stringify(logsAction),'utf8',function (err) {});
                            }
                        })
                    } else {
                        ActionLogsModelMongoDB.update(logsAction._id, { backup: UPDATE_FAIL}).then(resUpdate => {
                            if(resUpdate.nModified == 0){
                                console.log(resUpdate, logsAction._id)
                                let d        = new Date();
                                let namefile = './logs/'+d.getFullYear()+d.getMonth()+d.getDate()+'mongoActionRenderFromLMS.log'
                                fs.appendFile(namefile, JSON.stringify(logsAction),'utf8',function (err) {});
                            }
                        })
                    }
                }).catch(err => console.log(err));
            })
        }
    })
}

module.exports = router;