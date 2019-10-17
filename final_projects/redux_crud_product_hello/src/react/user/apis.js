import {post, get, put, del} from "../base/portal";

export function postUser(user) {
  let params = user
  return post("http://localhost:8080/user", params, "ADD_USER")
}

export function putUser(user) {
  let params = user
  return put("http://localhost:8080/user/"+user.id, params, "EDIT_USER")
}

export function getListUserApi() {
  return get("http://localhost:8080/users", "", "LIST_USER")
}

export function getUserApi(id) {
  return get("http://localhost:8080/user/"+id, "", "GET_USER")
}

export function deleteUserApi(id) {
  return del("http://localhost:8080/user/"+id, "", "DELETE_USER")
}