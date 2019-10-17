import {ReducerRegistry} from "../base/redux";

let stateDefault = {
  user: {},
  users: []
};

ReducerRegistry.register('user', (state = stateDefault, action) => {
  switch (action.type) {
    case "ADD_USER":
      return {
        ...state,
        users: [...state.users,action.user]
      }
    case "EDIT_USER":
      return {
        ...state,
        user: action.user
      }
    case "GET_USER":
      return {
        ...state,
        user: action.user
      }
    default:
      return state;
  }
});

ReducerRegistry.register('user/list', (state = stateDefault, action) => {
  switch (action.type) {
    case "LIST_USER":
      return {
        ...state,
        users: action.users
      }
    case "DELETE_USER":
      return {
        ...state,
        users: action.users
      }
    default:
      return state;
  }
});
