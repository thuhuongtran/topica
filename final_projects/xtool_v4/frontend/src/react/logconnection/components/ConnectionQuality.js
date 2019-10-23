import React, { Component }                     from 'react';
import { connect }                              from 'react-redux';

class ConnectionQuality extends Component {
    render() {
        return (
            <table id="example" className="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>STT</th>
                        <th>Room ID</th>
                        <th>System</th>
                        <th>Type</th>
                        <th>Time</th>
                        <th>User Id</th>
                        <th>Web/app</th>
                        <th>Download</th>
                        <th>Upload</th>
                        <th>Connection Quality</th>
                    </tr>
                </thead>
                <tbody>
                    {   this.props.logs.length !=0 &&
                        this.props.logs.map((log, index) => {
                            var date = new Date(log.timeCreated);
                            let hours = date.getHours();
                            let minutes = "0" + date.getMinutes();
                            let seconds = "0" + date.getSeconds();
                            let formattedTime = hours + ':' + minutes.substr(-2) + ':' + seconds.substr(-2);
                            return(
                                <tr key = {index}>
                                    <td>{index + 1}</td>
                                    <td>{log.data.roomId}</td>
                                    <td>{log.system}</td>
                                    <td>{log.type}</td>
                                    <td>{formattedTime}</td>
                                    <td>{log.data.userId}</td>
                                    <td>{log.data.system}</td>
                                    <td>{log.data.bitrate ? log.data.bitrate.download : ""}</td>
                                    <td>{log.data.bitrate ? log.data.bitrate.upload : ""}</td>
                                    <td>{log.data.connectionQuality}</td>
                                </tr>
                            )
                        })
                    }
                    {
                        this.props.logs.length == 0 &&
                        <tr>
                            <td colSpan = "10">Không có log với mã lớp này!</td>
                        </tr>
                    }
                </tbody>
            </table>     
        );
    }
}

function _mapStateToPropsTop(state) {
	return {
	};
}

export default connect(_mapStateToPropsTop)(ConnectionQuality);