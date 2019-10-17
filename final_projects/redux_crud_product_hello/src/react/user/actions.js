import {postUser, getListUserApi, putUser, getUserApi, deleteUserApi} from "./apis";

function changeUser(user) {
    return {
        type: "ADD_USER",
        user
    }
}

export function updateUser(user) {
    return {
        type: "EDIT_USER",
        user
    }
}

export function getUserInfo(user) {
    return {
        type: "GET_USER",
        user
    }
}

function getUsers(users) {
    return {
        type: "LIST_USER",
        users
    }
}

function getUpdateUsers(users, id) {
    return {
        type: "LIST_USER",
        users: users.filter(u => u.id !== id)
    }
}

export function addUser(user) {
    return function (dispatch, getState) {
        postUser(user).then((response) => {
            if (response.id) {
                dispatch(changeUser(user))
                alert("Post new user successfully.");
            } else
                alert("Error post new user")
        })
    }
}

export function editUser(user) {
    return function (dispatch, getState) {
        putUser(user).then((response) => {
            if (response.id) {
                dispatch(updateUser(user))
                alert("Edit user successfully.");
            } else
                alert("Error edit user")
        })
    }
}

export function getListUser() {
    return function (dispatch, getState) {
        getListUserApi().then((users) => {
            dispatch(getUsers(users))
        })
    }
}

export function getUser(id) {
    return function (dispatch, getState) {
        getUserApi(id).then((user) => {
            dispatch(getUserInfo(user))
        })
    }
}

export function deleteUser(user) {
    return function (dispatch, getState) {
        deleteUserApi(user.id).then((response) => {
            if (response) {
                dispatch(getUpdateUsers(getState()['user/list'].users, user.id))
                alert("Delete user successfully.");
            } else
                alert("Error delete user")
        })
    }
}