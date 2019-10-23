let mongoose = require('mongoose');
let ObjectId = require('mongoose').ObjectId;

let infoLogSchema = new mongoose.Schema({
    _id: ObjectId,
    timeCreated: Number,
    system: String,
    type: String,
    data: Object,
    backup: Number
});

let infoLogs = mongoose.model('infologs_1', infoLogSchema);

module.exports = {
    gets: function(where, start, display, order){
        return new Promise(function (resolve, reject) {
            infoLogs.aggregate([
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
    update: function (logInfoId, dataUpdate) {
        return new Promise(function (resolve, reject) {
            infoLogs.update({"_id": logInfoId},{ $set: dataUpdate },{},function(err, rs) {
				if(err) reject(err);
				resolve(rs);
			});
        })
    }
};