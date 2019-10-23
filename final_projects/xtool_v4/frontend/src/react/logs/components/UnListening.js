import React, {Component} from 'react';
import {connect} from "react-redux";
import {getLogs, getByRoomId, getByUserId} from "../actions";

class UnListening extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isLoad: false,
            isRoomLoad: false,
            isUserLoad: false
        }
    }

    componentWillMount() {
        this.props.dispatch(getLogs())
    }

    loadLogs = () => {
        this.setState({...this.state, isLoad: true, isRoomLoad: false, isUserLoad: false})
        this.props.dispatch(getLogs())
    };

    setRoomId = (e) => {
        this.setState({...this.state, roomId: e.target.value})
    };

    setUserId = (e) => {
        this.setState({...this.state, userId: e.target.value})
    };

    findByRoomID = () => {
        let roomId = this.state.roomId;
        if (roomId) {
            this.props.dispatch(getByRoomId(roomId));
        }
        this.setState({
            ...this.state,
            isLoad: false,
            isRoomLoad: true,
            isUserLoad: false
        })
    };

    findByUserID = () => {
        let userId = this.state.userId;
        if (userId) {
            this.props.dispatch(getByUserId(userId));
        }
        this.setState({
            ...this.state,
            isLoad: false,
            isRoomLoad: false,
            isUserLoad: true
        })
    };

    render() {
        let statusLogs = [];
        if(this.state.isLoad) statusLogs = this.props.logs;
        else if(this.state.isRoomLoad) statusLogs = this.props.roomLogs;
        else if(this.state.isUserLoad) statusLogs = this.props.userLogs;
        return (
                <div className="col-12 open-class xtool-renderlink mg-left">
                    <div className="box-xtool">
                        <div className="form-row align-items-center">
                            <div className="col-auto">
                                <label className="sr-only" htmlFor="room-id">Room Id</label>
                                <div className="input-group mb-2">
                                    <div className="input-group-prepend">
                                        <div className="input-group-text">Room Id</div>
                                    </div>
                                    <input type="number" className="form-control" id="room-id"
                                           onChange={this.setRoomId}
                                           min="1" maxLength="10" placeholder="Nhập room ID"/>
                                </div>
                            </div>
                            <div className="col-auto">
                                <button type="submit" onClick={this.findByRoomID} className="btn btn-info mb-2">Find by
                                    RoomId
                                </button>
                            </div>
                            <div className="col-auto col-md-1">
                            </div>
                            <div className="col-auto">
                                <label className="sr-only" htmlFor="room-id">User Id</label>
                                <div className="input-group mb-2">
                                    <div className="input-group-prepend">
                                        <div className="input-group-text">User Id</div>
                                    </div>
                                    <input type="number" className="form-control" id="user-id"
                                           onChange={this.setUserId}
                                           min="1" maxLength="10" placeholder="Nhập user ID"/>
                                </div>
                            </div>
                            <div className="col-auto">
                                <button type="submit" onClick={this.findByUserID} className="btn btn-danger mb-2">Find
                                    by UserId
                                </button>
                            </div>
                        </div>
                        <div className="form-row align-items-center">
                            <br/><br/>
                            <div className="col-auto">
                                <label className="sr-only">Load all opening rooms</label>
                                <button type="button" onClick={this.loadLogs} className="btn btn-success">Load all
                                </button>
                                <br/>
                            </div>
                        </div>
                        {
                            statusLogs.length === 0 ?
                                    this.state.isLoad &&
                                    <h2>No data display</h2>
                                    :
                                    statusLogs.map((log, i) => {
                                        return (
                                                <div>
                                                    <div className="row p-3 mb-2 bg-info text-white">
                                                        <p className="font-weight-bold">Room
                                                            Id:</p>&nbsp;&nbsp;&nbsp;{log._id}
                                                    </div>
                                                    <table className="table table-bordered">
                                                        <thead>
                                                        <tr>
                                                            <th>User Id</th>
                                                            <th>User Name</th>
                                                            <th>System</th>
                                                            <th>Status Now</th>
                                                            <th>
                                                                Time
                                                            </th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        {log.usersLog.map((u, i) => {
                                                            return (
                                                                    <tr>
                                                                        <th>
                                                                            <div className="justify-content-center small font-weight-bold">
                                                                                {u._id.userId}
                                                                            </div>
                                                                        </th>
                                                                        <td>
                                                                            <div className="justify-content-center small font-weight-bold">
                                                                                {u._id.userName}
                                                                            </div>
                                                                        </td>
                                                                        <td>
                                                                            <div className="justify-content-center small font-weight-bold">
                                                                                {u._id.system === 'NVN' ? 'Web' : 'Mobile'}
                                                                            </div>
                                                                        </td>
                                                                        {
                                                                            u.data[0].status ?
                                                                                    <td bgcolor="#00cc00">
                                                                                        <div className="justify-content-center text-white small font-weight-bold">
                                                                                            {u.data[0].download}
                                                                                        </div>
                                                                                    </td>
                                                                                    :
                                                                                    <td bgcolor="#cc0000">
                                                                                        <div className="justify-content-center text-white small font-weight-bold">
                                                                                            {u.data[0].download}
                                                                                        </div>
                                                                                    </td>
                                                                        }
                                                                        <td className="bg-white">
                                                                            <div className="row justify-content-center">
                                                                                {u.data.length === 0 ?
                                                                                        <p className="font-weight-bold text-sm-center">
                                                                                            No data display
                                                                                        </p>
                                                                                        :
                                                                                        u.data.map((status, i) => {
                                                                                            return (
                                                                                                    <table className="table-striped">
                                                                                                        <thead>
                                                                                                        <tr className="bg-white">
                                                                                                            <th className=" small font-weight-bold">{new Intl.DateTimeFormat('vi-VI',
                                                                                                                    {
                                                                                                                        minute: '2-digit',
                                                                                                                        second: '2-digit'
                                                                                                                    }).format(status.timeCreated)
                                                                                                            }</th>
                                                                                                        </tr>
                                                                                                        </thead>
                                                                                                        <tbody>
                                                                                                        <tr>
                                                                                                            {
                                                                                                                status.status ?
                                                                                                                        <td bgcolor="#00cc00"
                                                                                                                            className="text-white small font-weight-bold">
                                                                                                                            {status.download}
                                                                                                                        </td>
                                                                                                                        :
                                                                                                                        <td bgcolor="#cc0000"
                                                                                                                            className="text-white small font-weight-bold">
                                                                                                                            {status.download}
                                                                                                                        </td>
                                                                                                            }
                                                                                                        </tr>
                                                                                                        </tbody>
                                                                                                    </table>

                                                                                            );
                                                                                        })
                                                                                }
                                                                            </div>
                                                                        </td>
                                                                    </tr>
                                                            );
                                                        })}
                                                        </tbody>
                                                    </table>
                                                </div>
                                        )
                                                ;
                                    })
                        }
                    </div>
                </div>
        );
    }

}

function _mapStateToPropsTop(state) {
    return {
        logs: state['logs'].logs,
        roomLogs: state['logs'].roomLogs,
        userLogs: state['logs'].userLogs
    };
}

export default connect(_mapStateToPropsTop)(UnListening);
