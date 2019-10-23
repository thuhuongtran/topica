import React from 'react';
import ReactDOM from 'react-dom';
import {Provider} from 'react-redux';
import {createStore} from 'redux';
import Thunk from 'redux-thunk';
import {
    Login, Homepage, Admin, Xtool, Header, Sidebar
} from './react/app';
import {
    PrivateComponent
} from './react/base/author';
import {
    UnListening
} from './react/logs';
import {
    LogInfo
} from './react/logconnection';

import {
    BrowserRouter as Router,
    Route
} from 'react-router-dom';
import {browserHistory} from 'react-dom';
import * as serviceWorker from './serviceWorker';
import {
    StateListenerRegistry,
    ReducerRegistry,
    MiddlewareRegistry
} from './react/base/redux';

import {updateUserInfo} from './react/app'
import {OpenClass} from './react/openclass';

window.APP = {}

let _createStore = () => {
    let middleware = MiddlewareRegistry.applyMiddleware(Thunk);
    const reducer = ReducerRegistry.combineReducers();
    const store = createStore(reducer, middleware);
    StateListenerRegistry.subscribe(store);
    if (typeof APP !== 'undefined') {
        window.APP.store = store;
    }
    return store;
}
let store = _createStore();

{
    let userInfo = JSON.parse(localStorage.getItem('userInfo'));
    if (userInfo) {
        store.dispatch(updateUserInfo(userInfo))
    }
}

ReactDOM.render(
        <Provider store={store}>
            <Router history={browserHistory}>
                <div>
                    <Header/>
                    <section className="container-fluid px-0 g-pt-65">
                        <div className="row no-gutters g-pos-rel g-overflow-x-hidden">
                            <Sidebar/>
                            <PrivateComponent>
                                <Route exact path="/" component={Homepage}/>
                            </PrivateComponent>
                            <PrivateComponent roles={["ROLE_ADMIN"]} userIds={[123]}>
                                <Route exact path="/admin" component={Admin}/>
                            </PrivateComponent>
                            <PrivateComponent>
                                <Route path="/xtool" component={Xtool}/>
                            </PrivateComponent>
                            <PrivateComponent>
                                <Route path="/openclass" component={OpenClass}/>
                            </PrivateComponent>
                            <PrivateComponent>
                                <Route path="/logs" component={LogInfo}/>
                            </PrivateComponent>
                            <PrivateComponent>
                                <Route path="/status" component={UnListening}/>
                            </PrivateComponent>
                        </div>
                    </section>
                </div>
                <Route exact path="/login" component={Login}/>
            </Router>
        </Provider>
        , document.getElementById('root')
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
