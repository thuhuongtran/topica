

export function getLogApi(type, roomid, userid, limit) {
    let url = "";
    if(userid){
        url = "https://xtooldev8.topica.vn/api/infologs?type=" + type + "&roomId=" + roomid + "&userId=" + userid + "&limit=" + limit;
    }
    else{
        url = "https://xtooldev8.topica.vn/api/infologs?type=" + type + "&roomId=" + roomid + "&limit=" + limit;
    }
    return new Promise((resolve,reject) => {
        fetch(url).then(response => 
		    response.json().then(listlog => ({
		        listlog: listlog,
		        status: response.status
		    })
		).then(res => {
            resolve(res.listlog)
		}).catch(error => reject(error)));   
    })
}