import config                   from '../config';

export function openClassAPI() {
    return new Promise((resolve,reject) => {
        fetch(`${config.domain.thailand.lms}/vcrx/createrooms`, {
            method: 'GET',
            })
            .then(response => {
                resolve(response.json())
            })
            .catch(error => reject(error))
    })
}

export function getClassListAPI() {
    return new Promise((resolve,reject) => {
        fetch(`${config.domain.thailand.lms}/vcrx/getclasslist`).then(response =>
            response.json().then(roomTL => ({
                roomTL: roomTL,
                status: response.status
            })
        ).then(res => {
            resolve(res.roomTL);
        }));
    })
}

export function getListUserAPI(from, to) {
    return new Promise((resolve,reject) => {
        fetch(`${config.domain.thailand.lms}/vcrx/getusers`).then(response =>
            response.json().then(userlist => ({
                userlist: userlist,
                status: response.status
            })
        ).then(res => {
            resolve(res.userlist);
        }));
    })
}

export function getListRoomAPI(from, to) {
    return new Promise((resolve,reject) => {
        fetch(`${config.domain.thailand.lms}/vcrx/getrooms`).then(response =>
            response.json().then(data => ({
                data: data,
                status: response.status
            })
        ).then(res => {
            resolve(res.data);
        }));
    })
}

export function getUsersTLAPI() {
    return new Promise((resolve,reject) => {
        fetch(`${config.domain.thailand.lms}/vcrx/getuserstl`).then(response =>
            response.json().then(userTL => ({
                userTL: userTL,
                status: response.status
            })
        ).then(res => {
            resolve(res.userTL);
        }));
    })
}
