import React, {Component} from 'react';
import {addUser, editUser, getUser} from "../actions";
import {connect} from "react-redux";

class User extends Component {
    constructor(props) {
        super(props);
        console.log(this.props.user)
        this.state = {
            name: "",
            phone: "",
            mail: "",
            pass: ""
        }
    }

    componentWillMount(): void {
        let id = this.props.match.params.id;
        if (id > 0) {
            this.props.dispatch(getUser(id));
        }
    }

    changeName = (e) => {
        this.setState({name: e.target.value})
    }

    changePhone = (e) => {
        this.setState({phone: e.target.value})
    }

    changeMail = (e) => {
        this.setState({mail: e.target.value})
    }

    changePass = (e) => {
        this.setState({pass: e.target.value})
    }

    changeUserHandle = () => {
        if (this.props.user.id > 0) {
            let user = {
                id: this.props.user.id,
                name: this.state.name ? this.state.name : this.props.user.name,
                phone: this.state.phone ? this.state.phone : this.props.user.phone,
                mail: this.state.mail ? this.state.mail : this.props.user.mail,
                pass: this.state.pass ? this.state.pass : this.props.user.pass
            };
            this.props.dispatch(editUser(user))
        } else {
            let user = {
                name: this.state.name,
                phone: this.state.phone,
                mail: this.state.mail,
                pass: this.state.pass
            };
            this.props.dispatch(addUser(user))
        }
    }

    render() {
        return (
                <div className="container">
                    <br/>
                    <div className="form-group">
                        <label className="small font-weight-bold" htmlFor="name">Name</label>
                        <input type="text" name="name" id="name"
                               onChange={this.changeName}
                               defaultValue={this.props.user.name}
                               className="form-control" placeholder="Enter your full name here"/>
                    </div>
                    <div className="form-group">
                        <label className="small font-weight-bold" htmlFor="mail">Mail</label>
                        <input type="text" name="mail" id="mail"
                               onChange={this.changeMail}
                               defaultValue={this.props.user.mail}
                               className="form-control" placeholder="Enter your mail here"/>
                    </div>
                    <div className="form-group">
                        <label className="small font-weight-bold" htmlFor="phone">Phone</label>
                        <input type="text" name="phone" id="phone"
                               onChange={this.changePhone}
                               defaultValue={this.props.user.phone}
                               className="form-control" placeholder="Enter your phone here"/>
                    </div>
                    <div className="form-group">
                        <label className="small font-weight-bold" htmlFor="pwd">Password</label>
                        <input type="password" name="pwd" id="pwd"
                               onChange={this.changePass}
                               defaultValue={this.props.user.pass}
                               className="form-control" placeholder="Enter your password here"/>
                    </div>
                    <button type="submit" onClick={this.changeUserHandle} className="btn btn-primary">Submit</button>
                    <br/>
                </div>
        );
    }
}

function _mapStateToPropsTop(state) {
    return {
        user: state['user'].user
    };
}

export default connect(_mapStateToPropsTop)(User);