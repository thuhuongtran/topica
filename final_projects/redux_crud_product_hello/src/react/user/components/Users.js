import React, {Component} from 'react';
import {connect} from "react-redux";
import '../../../static/css/bootstrap.min.css';
import {getListUser, deleteUser} from "../actions";

class Users extends Component {

    constructor(props) {
        super(props);
    }

    componentWillMount(): void {
        this.props.dispatch(getListUser());
    }

    deleteUserHandle = (user) => {
        // eslint-disable-next-line no-restricted-globals
        let isDel = confirm("Are you sure want to delete this ? ")
        if(isDel)
            this.props.dispatch(deleteUser(user))
    };

    render() {
        return (
                <div className="container">
                    <br/>
                    <table className="table">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Name</th>
                            <th scope="col">Mail</th>
                            <th scope="col">Phone</th>
                            <th scope="col">Pass</th>
                            <th scope="col">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            this.props.users.map((u, i) => {
                                return (
                                        <tr>
                                            <th scope="row" key={i}>{u.id}</th>
                                            <td>{u.name}</td>
                                            <td>{u.mail}</td>
                                            <td>{u.phone}</td>
                                            <td>{u.pass}</td>
                                            <td>
                                                <a href={"/user/"+u.id}
                                                        className="btn btn-warning">Edit
                                                </a>
                                                <button onClick={() => this.deleteUserHandle(u)} className="btn btn-danger">
                                                    Delete
                                                </button>
                                            </td>
                                        </tr>
                                );
                            })
                        }
                        </tbody>
                    </table>
                </div>
        );
    }
}

function _mapStateToPropsTop(state) {
    return {
        users: state['user/list'].users,
    };
}

export default connect(_mapStateToPropsTop)(Users);