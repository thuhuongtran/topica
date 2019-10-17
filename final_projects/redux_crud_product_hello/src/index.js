import React from 'react';
import ReactDOM from 'react-dom';
import { Provider }         from 'react-redux';
import { createStore }      from 'redux';
import Thunk                from 'redux-thunk';
import {
  BrowserRouter as Router,
  Route
}                           from 'react-router-dom';
import * as serviceWorker from './serviceWorker';
import {
  StateListenerRegistry,
  ReducerRegistry,
  MiddlewareRegistry
}                           from './react/base/redux';
import {Users, User} from './react/user';
window.APP = {}

let _createStore = () => {
  let middleware = MiddlewareRegistry.applyMiddleware(Thunk);
  const reducer = ReducerRegistry.combineReducers();
  const store = createStore( reducer, middleware );
  StateListenerRegistry.subscribe(store);
  if (typeof APP !== 'undefined') {
    window.APP.store = store;
  }
  return store;
}
let store = _createStore();

ReactDOM.render(
  <Provider store={store}>
    <Router>
      <div>
        <Route exact path="/"               component={Users}/>
        <Route exact path="/user/:id"        component={User}/>
        <Route exact path="/user"        component={User}/>
      </div>
    </Router>
  </Provider>
  , document.getElementById('root')
);

serviceWorker.unregister();
