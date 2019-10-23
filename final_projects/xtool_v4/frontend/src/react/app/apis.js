import { post }     from './../base/portal';

export function getLocalstorage( key){
    return localStorage.getItem(key);
}

export function setLocalstorage( key, value){
    localStorage.setItem(key, value);
}

export function removeLocalstorage( key){
    localStorage.removeItem(key);
}

export function loginApi(username='', password='') {
    let params = {
        username,
        password
    }
    return post('https://vcrxapitester.topica.vn/api/user/login', "", params, "" )
}