import React                        from 'react';
import { connect }                  from 'react-redux';
import {
    login
}                                   from '../actions';
import { Redirect }                 from 'react-router-dom';


class Login extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            username : "",
            password : ""
        }
        if (this.props.userInfo) { 
            this.props.history.push('/');
        }
        this.submitLogin    = this.submitLogin.bind(this);
    }

    submitLogin() {
        if(this.state.username !== "" && this.state.password !== ""){
            this.props.dispatch(login(this.state.username, this.state.password));
        }else{
            alert("Sai thông tin đăng nhập!");
        }
    }

    changeUsername = (e) => {
        this.setState({username: e.target.value})
    }

    changePassword = (e) => {
        this.setState({password: e.target.value})
    }

    render(){
        if(this.props.userInfo){
            return <Redirect to={{ pathname: '/' }} />
        }
        return(
            <div className="col-md-6 col-md-offset-3">
                <h2>Login</h2>
                <div>
                    <div>
                        <label>Username</label>
                        <input type="text" className="form-control" name="username" onChange={this.changeUsername}/>
                    </div>
                    <div>
                        <label htmlFor="password">Password</label>
                        <input type="password" className="form-control" name="password" onChange={this.changePassword}/>
                    </div>
                    <div className="form-group">
                        <button className="btn btn-primary" onClick={this.submitLogin} >Login</button>
                    </div>
                </div>
            </div>
        )
    }
}

function _mapStateToPropsTop(state) {
	return {
        userInfo: state['xtool/app'].userInfo
	};
}
export default connect(_mapStateToPropsTop)(Login);
