export function get(url, param={}, type="") {
  return new Promise(function (resolve, reject) {
    fetch(url, {
      method: "GET",
      headers: {
        'Access-Control-Allow-Origin':'*',
        'Accept': 'application/json',
        'Content-Type': 'application/json',
      }
    }).then(function (response) {
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
  })
}

export function post(url, param={}, type="") {
  return new Promise(function (resolve, reject) {
    fetch(url, {
      method: "POST",
      headers: {
        'Access-Control-Allow-Origin':'*',
        'Accept': 'application/json',
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(param)
    }).then(function (response) {
      console.log('response='+JSON.stringify(response))
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
  })
}


export function put(url, param={}, type="") {
  return new Promise(function (resolve, reject) {
    fetch(url, {
      method: "PUT",
      headers: {
        'Access-Control-Allow-Origin':'*',
        'Accept': 'application/json',
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(param)
    }).then(function (response) {
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
  })
}


export function del(url, param={}, type="") {
  return new Promise(function (resolve, reject) {
    fetch(url, {
      method: "DELETE",
    }).then(function (response) {
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
  })
}