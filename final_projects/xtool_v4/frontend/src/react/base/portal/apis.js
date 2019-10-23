export function get(url, token="", param={}, type=""){
    return new Promise(function(resolve,reject){
        fetch( url , {
            method: "GET",
            headers: {
                'Access-Control-Allow-Origin':'*',
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                "Authorization" : "Bearer "+token
            },
            mode: 'cors'
        }).then(function(response) {
            if(response.status === 200){
                return response.json();
            }else{
                resolve({success: false});
            }
        }).then(function(response){
            resolve(response);
        }).catch(err=>{
            reject({success: false, err});
        });
    });
}

export function post(url, token="", param, type){
    return new Promise(function(resolve,reject){
        fetch(url, {
            method: "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                "Authorization" : "Bearer "+token
            },
            body: JSON.stringify( param )
        }).then(function(response) {
            if(response.status === 200){
                return response.json();
            }else{
                return {};
            }
        }).then(function(response){
            resolve(response);
        }).catch(err=>{
            reject({success: false, err});
        });
    });
}