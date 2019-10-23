import React, { Component }                     from 'react';
import { connect }                              from 'react-redux';
import $                                        from 'jquery';
import ConnectionQuality                        from './ConnectionQuality';
import InfoDevice                               from './InfoDevice';


import { getLog, changePage }                               from '../actions';

var logTypes = [
    'CONNECTION_QUALITY',
    'INFO_DEVICE'
]

class LogInfo extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            logs : [],
            logType: "CONNECTION_QUALITY",
            roomId: "",
            userId: "",
            reload: false
        }
    }

    componentWillMount(){
        this.props.dispatch(changePage("LOGSCONNECTION"));

    }

    componentDidMount(){
        let this1 = this;
        $('input[type="checkbox"]').click(function(){
            if($(this).prop("checked") == true){
                this1.setState({reload : true});
            }
            else if($(this).prop("checked") == false){
                this1.setState({reload : false});
            }
        });
    }

    componentWillReceiveProps(nextProps){
        this.setState({logs: nextProps.listlog.result});
        this.intervalId = setInterval(()=>{
            this.setState({logs: nextProps.listlog.result});
        },15000);
    }

    getLogs = () =>{
        var type = $("#select-log").val();
        var roomid = $("#roomid").val().trim();
        var userid = $("#userid").val().trim();
        var limit = 1000;
        if(roomid == ""){
            alert("Bạn chưa nhập mã lớp!");
            $("#roomid").focus();
        }
        this.props.dispatch(getLog(type, roomid, userid, limit));
    }

    onTypeChange = (e) => {
        let logType = e.target.value;
        this.setState({logType, logs: []});
    }

    render() {
        return (
            <div className="col-12 open-class xtool-renderlink mg-left">
                <div className = "box-xtool">
                    <h2 className="text-center qllh-header">Logs Connection Quality</h2>
                    <div className = "box-connection" >
                        <div className="form-group">
                            <label>Chọn loại log:</label>
                            <select className="form-control select-log" id = "select-log" onChange={this.onTypeChange}>
                                {
                                    logTypes.map((e, i) => {
                                        return (
                                            <option value={e} key={i} >{e}</option>
                                        )
                                    })
                                }
                            </select>
                            <input id = "roomid" type = "text" placeholder = "Nhập Room ID (*)"/>
                            <input id = "userid" type = "text" placeholder = "Nhập User ID"/>
                            <button type="button" className="btn btn-info" onClick = {this.getLogs}>Submit</button>
                            <label className="checkbox-inline">
                                <input type="checkbox" className ="checkbox-reload" value=""/> Tự động reload<br/>
                            </label>
                            { this.state.logType == "CONNECTION_QUALITY" && <ConnectionQuality logs={this.state.logs}/> }
                            { this.state.logType == "INFO_DEVICE" && <InfoDevice logs={this.state.logs}/> }
                        </div>
                    </div>
                </div>
            </div>         
        );
    }
}

function _mapStateToPropsTop(state) {
	return {
        listlog : state['xtool/logconnection'].listlog,
	};
}
export default connect(_mapStateToPropsTop)(LogInfo);