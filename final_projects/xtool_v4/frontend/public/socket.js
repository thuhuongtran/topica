var express = require('express');  
var app = express();  
var server = require('http').createServer(app);  
var io = require('socket.io')(server);

let clients = [];

io.on('connection', function(socket){
    socket.on('user-join',data=>{
        console.log("User join: Userid-"+data.userid+" Roomid-"+data.roomid)
        let { roomid, userid, role, fullname } = data;
        let time = Math.floor(Date.now() / 1000).toString();
        socket.userid = userid;
        socket.roomid = roomid;
        socket.join(data.roomid);
        let index = clients.findIndex(client=>client.roomid == roomid);
        if(index != -1){
            clients[index].users.push({ roomid, userid, role, fullname, time, timeout: -1 });
        }else{
            clients.push({roomid:roomid,users:[{ roomid, userid, role, fullname, time, timeout: -1 }]});
        } 
        io.emit('user-joins',{clients})
    });

    socket.on('disconnect', ()=> {
        let userid = socket.userid;
        let roomid = socket.roomid;

        let index = clients.findIndex(client=>client.roomid == roomid);
        if(index != -1 && userid != 0){
            let indexUser = clients[index].users.findIndex(u=>u.userid == userid && u.timeout == -1);
            if(indexUser != -1){
                clients[index].users[indexUser].timeout = Math.floor(Date.now() / 1000).toString();
                console.log("Disconnect: Userid-"+userid) 
                io.emit('user-disconnects',{clients})
            }
        }
    });

    socket.on('chat-send',data=>{
        let roomid = socket.roomid;
        let userid = socket.userid;
        console.log("Chat: Roomid-"+roomid+" Userid-"+userid+" Msg-"+data.msg)
        io.to(roomid).emit('chat-receive',{msg: data.msg,from: socket.userid})
    });
});

app.use(express.static(__dirname + '/bower_components'));  
app.get('/', function(req, res,next) {  
    res.sendFile(__dirname + '/index.html');
});

server.listen(8546); 
console.log("AA")