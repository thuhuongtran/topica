let mongoose = require('mongoose');

let infoLogSchema = new mongoose.Schema({
    timeCreated: Number,
    system: String,
    type: String,
    data: Object
});

let listenLogSchema = new mongoose.Schema({
    roomId: String,
    userId: String,
    userName: String,
    system: String,
    download: Number,
    timeCreated: Number,
    statusNow: Boolean
});

let infoLogBackup = mongoose.model("infologs", infoLogSchema);
let listenStatusLog = mongoose.model("status_logs", listenLogSchema);

module.exports = {
    gets: function (start, end) {
        return new Promise(function (resolve, reject) {
            infoLogBackup.aggregate([
                        {
                            $match:
                                    {
                                        "type": "CONNECTION_QUALITY",
                                        "timeCreated": {$gte: start, $lt: end}
                                    }
                        },
                        {$match: {$or: [{"system": "NVN"}, {"system": "VCRXCONNECT"}]}},
                        {
                            $group: {
                                _id:
                                        {
                                            roomId: "$data.roomId",
                                        },
                                data: {
                                    $push: {
                                        userId: "$data.userId",
                                        userName: "$data.userName",
                                        system: "$system",
                                        download: "$data.bitrate.download",
                                        timeCreated: "$timeCreated"
                                    }
                                },
                                maxDownload: {$max: "$data.bitrate.download"}
                            }
                        }
                    ], function (err, rs) {
                        if (err) reject(err);
                        resolve(rs)
                    }
            );
        })
    },
    add: function (listenLog) {
        return new Promise(function (resolve, reject) {
            listenStatusLog.create(listenLog, function (err, rs) {
                if (err) reject(err);
                console.log(rs);
                resolve(rs)
            })
        })
    },
    getListenStatusLogs: function (start, end) {
        return new Promise(function (resolve, reject) {
            listenStatusLog.aggregate([
                {$sort: {timeCreated: -1}},
                {
                    $match:
                            {
                                "timeCreated": {$gte: start, $lt: end}
                            }
                },
                {
                    $group: {
                        _id: {roomId: "$roomId", userId: "$userId", userName: "$userName", system: "$system"},
                        data: {
                            $push: {
                                status: "$statusNow",
                                download: "$download",
                                timeCreated: "$timeCreated"
                            }
                        }
                    }
                },
                {
                    $group: {
                        _id: "$_id.roomId",
                        usersLog: {
                            $push: "$$ROOT"
                        }
                    }
                }
            ], function (err, rs) {
                if (err) reject(err);
                resolve(rs)
            });
        })
    },
    getListenStatusLogsByRoomId: function (roomId) {
        return new Promise(function (resolve, reject) {
            listenStatusLog.aggregate([
                {$sort: {timeCreated: -1}},
                {$match: {"roomId": "" + roomId + ""}},
                {
                    $group: {
                        _id: {roomId: "$roomId", userId: "$userId", userName: "$userName",system: "$system"},
                        data: {
                            $push: {
                                status: "$statusNow",
                                download: "$download",
                                timeCreated: "$timeCreated"
                            }
                        }
                    }
                },
                {
                    $group: {
                        _id: "$_id.roomId",
                        usersLog: {
                            $push: "$$ROOT"
                        }
                    }
                }
            ], function (err, rs) {
                if (err) reject(err);
                resolve(rs)
            });
        })
    },
    getListenStatusLogsByUserId: function (userId) {
        return new Promise(function (resolve, reject) {
            listenStatusLog.aggregate([
                {$sort: {timeCreated: -1}},
                {$match: {"userId": ""+userId+""}},
                {
                    $group: {
                        _id: {roomId: "$roomId", userId: "$userId", userName: "$userName",system: "$system"},
                        data: {
                            $push: {
                                status: "$statusNow",
                                download: "$download",
                                timeCreated: "$timeCreated"
                            }
                        }
                    }
                },
                {
                    $group: {
                        _id: "$_id.roomId",
                        usersLog: {
                            $push: "$$ROOT"
                        }
                    }
                }
            ], function (err, rs) {
                if (err) reject(err);
                resolve(rs)
            });
        })
    }
};