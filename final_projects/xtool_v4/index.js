let express = require('express');
let app = express();
let path = require("path");
app.use(express.static(path.join(__dirname, "./frontend/build")));

let logsInfo = require('./controller/logsInfoController');
let actionLogs = require('./controller/actionLogsController');
let errorLogs = require('./controller/errorLogsController');
let apiLogs = require('./controller/apiLogsController');
let listenLogs = require('./controller/apiListenLogsController');

let mongoose = require('mongoose');
mongoose.connect('mongodb://root:topica123@171.244.4.45:27017/vcrx?authSource=admin');
let db = mongoose.connection;
//
// let mongooseLocal = require('mongoose');
// mongooseLocal.connect('mongodb://localhost:27017/topica_vcrx_test');
// let dbLocal = mongooseLocal.connection;

app.use('/listenLogs', listenLogs);
app.use('/infologs', logsInfo);
app.use('/v1/infologs', logsInfo);
app.use('/v1/actionlogs', actionLogs);
app.use('/v1/errorlogs', errorLogs);
app.use('/v1/apilogs', apiLogs);
app.use('/*', function (request, response) {
    response.sendFile(path.resolve(__dirname, './frontend/build', 'index.html'));
});

app.listen(3009, function (req, res) {
    db.on('error', console.error.bind(console, 'connection error:'));
    db.once('open', function callback() {
        console.log("db connected");
    });
    //
    // dbLocal.on('error', console.error.bind(console, 'connection error:'));
    // dbLocal.once('open', function callback() {
    //     console.log("db local connected");
    // });
});