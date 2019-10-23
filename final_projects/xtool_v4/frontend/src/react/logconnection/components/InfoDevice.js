import React, { Component }                     from 'react';
import { connect }                              from 'react-redux';

class InfoDevice extends Component {
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
                        <th>Hệ điều hành</th>
                        <th>Trình duyệt</th>
                        <th>Phiên bản trình duyệt</th>
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
                                    <td>{log.data.OS ? log.data.OS : log.data.brand}</td>
                                    <td>{log.data.browser == "coc_coc_browser" ? "Cốc cốc" : (log.system == "VCRXCONNECT" ? log.data.systemName + " - " + log.data.deviceId : log.data.browser)}
                                    </td>
                                    <td>{log.system == "VCRXCONNECT" ? log.data.systemVersion : log.data.browserVersion}</td>
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

export default connect(_mapStateToPropsTop)(InfoDevice);