let mongoose = require('mongoose');
let ObjectId = require('mongoose').ObjectId;

let errorLogSchema = new mongoose.Schema({
    _id: ObjectId,
    timeCreated: Number,
    system: String,
    type: String,
    data: Object,
    backup: Number
});

let errorLogs = mongoose.model('errorlogs_1', errorLogSchema);

module.exports = {
    gets: function(where, start, display, order){
        return new Promise(function (resolve, reject) {
            errorLogs.aggregate([
                { $match : where },
                { $sort : order },
                { $limit : display },
                { $skip : start }
            ], function (err, total) {
                if(err) reject(err);
                resolve(total)
            })
        })
    },
    update: function (errorLogId, dataUpdate) {
        return new Promise(function (resolve, reject) {
            errorLogs.update({"_id": errorLogId},{ $set: dataUpdate },{},function(err, rs) {
                if(err) reject(err);
                resolve(rs);
            });
        })
    }
};