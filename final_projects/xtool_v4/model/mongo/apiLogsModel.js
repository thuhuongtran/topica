let mongoose = require('mongoose');
let ObjectId = require('mongoose').ObjectId;

let apiLogSchema = new mongoose.Schema({
    _id: ObjectId,
    timeCreated: Number,
    system: String,
    type: String,
    data: Object,
    backup: Number
});

let apilogs = mongoose.model('apilogs_1', apiLogSchema);

module.exports = {
    gets: function(where, start, display, order){
        return new Promise(function (resolve, reject) {
            apilogs.aggregate([
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
    update: function (apiLogId, dataUpdate) {
        return new Promise(function (resolve, reject) {
            apilogs.update({"_id": apiLogId},{ $set: dataUpdate },{},function(err, rs) {
                if(err) reject(err);
                resolve(rs);
            });
        })
    }
};