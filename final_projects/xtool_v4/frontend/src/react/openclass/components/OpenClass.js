import React, { Component }                     from 'react';
import config                                   from '../../config';
import { connect }                              from 'react-redux';
import { 
    changePage, handleOpenClasses, getRoomsNTL, getRoomsNVN, getUser, getUserTL
}                                               from '../actions';                    

var sections = ['08h - 09h','09h - 10h','10h - 11h','11h - 12h','12h - 13h','13h - 14h','14h - 15h','15h - 16h','16h - 17h','17h - 18h','18h - 19h','19h - 20h','20h - 21h','21h - 22h','22h - 23h','23h - 24h'];

class OpenClass extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            roomsInSection  : [],
            teachers        : [],
            POs             : [],
            students        : [],
            assistants      : [],
            section         : "08",
            created         : false, 
            classes         : [],
            dataCheat       :[],
            studentTL       : [],
            teacherTL       : [],
            isLoading       : false,
            teacherVN       : [], 
            POTL            : []
        }
    }

    componentWillMount(){
        this.props.dispatch(changePage("OPENCLASS"));
    }

    componentDidMount() {
       this.getRooms();
       this.getUsers();
       this.getUsersTL();
    }

    handleOpenClass = async () => {
        if(!this.state.created) {
            this.props.dispatch(handleOpenClasses());
            this.getRooms();
            this.setState({ created: true });
        }
    }

    componentWillReceiveProps(nextProps){
        let userInfo = JSON.parse(localStorage.getItem(config.localstorageKey.userinfo));
        let users = null;
        let rooms = [];
        if(userInfo.system == "NVN"){
            rooms = nextProps.rooms;
            users            =     nextProps.userlist;
            let teachers     =     users.filter(user => user.role == config.role.gv);
            let POs          =     users.filter(user => user.role == config.role.po);
            let students     =     users.filter(user => user.role == config.role.hv);
            let assistants   =     users.filter(user => user.role == config.role.tg);
            this.setState({
                teachers,
                POs,
                students,
                assistants
            })
        }else{
            rooms = nextProps.roomTL;
            users =     nextProps.userTL;
            let teachers    = users.filter(user => user.roleid == 3);
            let students    = users.filter(user => user.roleid == 5);
            let assistants   = users.filter(user => user.roleid == 11);
            let pos   = users.filter(user => user.roleid == 9);
            this.setState({studentTL: students, teacherTL: teachers, teacherVN: assistants, POTL:pos});
        }


        let date = new Date();
        let roomsInSection = [];
        if(date.getMinutes() >= 45) {
            roomsInSection = rooms.filter(room => new Date(room.timeAvailable*1000).getHours() == date.getHours() + 1);
        } else {
            roomsInSection = rooms.filter(room => new Date(room.timeAvailable*1000).getHours() == date.getHours());
        }
        if(roomsInSection.length > 5){
            roomsInSection.length = 5;
        }
        this.setState({ roomsInSection });
        if(nextProps.rooms.length > 0) {
            this.setState({ created: true })
        }
    }

    getUsers = async () =>{
       this.props.dispatch(getUser());
    }

    getUsersTL = async () => {
        this.props.dispatch(getUserTL());
    } 

    filter = () => {
        let section = this.state.section ? this.state.section.slice(0,2) : null;
        sections.forEach((e, i) => {
            let room = this.props.rooms.filter((room => new Date(room.timeAvailable*1000).getHours() == section ));
            this.setState({ roomsInSection: room });
        })
    }

    renderLink = (room, user, type) => {
        let userInfo = JSON.parse(localStorage.getItem(config.localstorageKey.userinfo));
        if(user){
            let role;
            switch (user.role) {
                case config.role.gv:
                    role =  userInfo.system == 'NVN' ? config.role.teacher : 'teacher';
                    break;
                case config.role.po:
                    role = config.role.po
                    break;
                case config.role.hv:
                    role = config.role.student
                    break;
                case config.role.tg:
                    role = config.role.assistant
                    break;
                default:
                    break;
            }
            if(userInfo.system == 'NVN'){
                if(type == "WEB") {
                    return `${(this.state.startDomain && this.state.startDomain != "") ? this.state.startDomain : config.domain.vietnam.meet}/${room.id}?token=${btoa(unescape(encodeURIComponent(`token=${this.props.userInfo.tokenApi}&meetingId=${room.id}&userId=${user.id}&userName=${user.fullName}&userRole=${role}&idVcrx=${user.idVcrx}&idRoomVcrx=${room.idVcrx}`)))}`;
                } else if(type == "APP") 
                {
                    return `vcrxconnect://mobileportal/${user.id}/${user.idVcrx}/${room.id}/${room.idVcrx}/${room.timeAvailable}/${user.fullName}//MOBILE/vi`;
                }
                else{
                    return `vcrxconnect://mobileportal/${user.id}/${user.idVcrx}/${room.id}/${room.idVcrx}/${room.timeAvailable}/${user.fullName}//AUDIT/vi`;
                }
            } else {
                if(type == "WEB") {
                    let role = "";
                    switch (user.roleid) {
                        case "5":
                            role = "STUDENT"
                            break;
                        case "3":
                            role = "teacher"
                            break;
                        case "11":
                            role = "ASSISTANT"
                            break;
                        case "9":
                            role = "PO"
                        default:
                            break;
                    }
                    return `${config.domain.thailand.meet}/${room.id}?token=${btoa(unescape(encodeURIComponent(`token=${this.props.userInfo.tokenApi}&meetingId=${room.id}&userId=${user.id}&userName=${user.username}&userRole=${role}&idVcrx=${user.idVcrx}&idRoomVcrx=${room.idVcrx}`)))}`;
                } 
            }
        }
    }

    getRooms = async () =>{
        let userInfo = JSON.parse(localStorage.getItem(config.localstorageKey.userinfo));
        if(userInfo.system == "NVN"){
            this.props.dispatch(getRoomsNVN())
        }
        else{
            this.props.dispatch(getRoomsNTL())
        }
    }


    render() {
        let userInfo = JSON.parse(localStorage.getItem(config.localstorageKey.userinfo));
        return (
            <div className="col-12 open-class xtool-renderlink mg-left">
                <div className = "box-xtool">
                <h2 className="text-center qllh-header">XTOOL LMS</h2>

                {/* Start thailand */}
                {   userInfo.system == 'NTL' &&
                    <div className = "group-thailand">
                        <div className = "box-cheat-log">
                            <p>Cheat vào lớp:</p>
                            <input id = "username" type="text"  className = "form-control" placeholder = "Nhập user name"/>
                            <input id = "roomidto" type="text" className = "form-control" placeholder = "Nhập mã lớp"/>
                            <button onClick = {this.saveLogMove} className = "btn btn-info">Cheat vào lớp</button>
                        </div>
                        <div className = "list-room">
                            <label className="label-section">Ca học: </label>
                            <select className="mdb-select md-form colorful-select dropdown-primarym form-control select-time" onChange={e => this.setState({ section: e.target.value})} defaultValue="default">
                                <option value="default">Chọn</option>
                                {
                                    sections.map((e, i) => {
                                        return (
                                            <option value={e} key={i} >{e}</option>
                                        )
                                    })
                                }
                            </select>
                            <button className = "btn btn-info btn-filter" onClick={this.filter}>Lọc</button><br/><br/>
                            Danh sách lớp: 
                            <table className="table table-bordered">
                                <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>ID Room</th>
                                        <th>ID Room VCRX</th>
                                        <th>Slide</th>
                                        <th>Video</th>
                                        <th>Time Available</th>
                                        <th>Link</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {
                                        this.state.roomsInSection.map((room, index) => {
                                            if(this.state.studentTL[index]){
                                                let linkGV_WEB = this.renderLink(room, this.state.teacherTL[(index)], "WEB");
                                                let linkGVVN_WEB = this.renderLink(room, this.state.teacherVN[(index)], "WEB");
                                                let linkPO_WEB = this.renderLink(room, this.state.POTL[(index)], "WEB");
                                                let linkHV1_WEB = this.renderLink(room, this.state.studentTL[(index*6)], "WEB");
                                                let linkHV2_WEB = this.renderLink(room, this.state.studentTL[(index*6+1)], "WEB");
                                                let linkHV3_WEB = this.renderLink(room, this.state.studentTL[(index*6+2)], "WEB");
                                                let linkHV4_WEB = this.renderLink(room, this.state.studentTL[(index*6+3)], "WEB");
                                                let linkHV5_WEB = this.renderLink(room, this.state.studentTL[(index*6+4)], "WEB");
                                                let linkHV6_WEB = this.renderLink(room, this.state.studentTL[(index*6+4)], "WEB");
                                                return(
                                                    <tr key={index}>
                                                        <td>{index+1}</td>
                                                        <td>{room.id}</td>
                                                        <td>{room.idVcrx}</td>
                                                        <td><a href={room.slide} target='_blank' title="Link slide">Link</a></td>
                                                        {
                                                            room.video ?
                                                            <td><a href={room.video} target='_blank' title="Link video">Link</a></td>
                                                            :
                                                            <td><a href="https://qlhl.topicanative.edu.vn/uploads/28032018/02.04_.2018_How_are_you_feeling_today_fix_.mp4" target='_blank' title="Link video">Link</a></td>
                                                        }
                                                        
                                                        <td>{new Date(room.timeAvailable*1000).toLocaleString()}</td>
                                                        <td>
                                                            <a href={linkGV_WEB} target='_blank'>Teacher </a> <br/>
                                                            <a href={linkGVVN_WEB} target='_blank'>Assistant </a> <br/>
                                                            <a href={linkPO_WEB} target='_blank'>PO </a> <br/>
                                                            <a href={linkHV1_WEB} target='_blank'>{this.state.studentTL[(index*6)].username}</a> <br/>
                                                            <a href={linkHV2_WEB} target='_blank'>{this.state.studentTL[(index*6+1 )].username}</a> <br/>
                                                            <a href={linkHV3_WEB} target='_blank'>{this.state.studentTL[(index*6+2 )].username}</a> <br/>
                                                            <a href={linkHV4_WEB} target='_blank'>{this.state.studentTL[(index*6+3 )].username}</a> <br/>
                                                            <a href={linkHV5_WEB} target='_blank'>{this.state.studentTL[(index*6+4 )].username}</a> <br/>
                                                            <a href={linkHV6_WEB} target='_blank'>{this.state.studentTL[(index*6+5 )].username}</a> <br/>
                                                        </td>
                                                    </tr>
                                                )
                                            }
                                        })
                                    }
                                </tbody>
                            </table>
                        </div>
                    </div>
                }
                {/* End Thailand */}
                {    userInfo.system == 'NVN' &&
                        <div className = "box-button-open">
                            <div className="section-class">
                                <label className="label-section">Ca học: </label>
                                <select className="mdb-select md-form colorful-select dropdown-primary" onChange={e => this.setState({ section: e.target.value})} defaultValue="default">
                                    <option value="default">Chọn</option>
                                    {
                                        sections.map((e, i) => {
                                            return (
                                                <option value={e} key={i} >{e}</option>
                                            )
                                        })
                                    }
                                </select>
                                <button className = "btn btn-info btn-create-class" disabled={this.state.created} onClick = {this.handleOpenClass}>{this.props.callingApi && <i className = "fa fa-spinner fa-spin"></i>}Tạo lớp</button>
                                <button className = "btn btn-info btn-filter" onClick={this.filter}>Lọc</button>
                                <button className = "btn btn-info btn-create-class" onClick={this.openClasses}>{this.state.isLoading && <i className = "fa fa-spinner fa-spin"></i>}Mở lớp</button>
                            </div>
                            <h3 className="class-list">Danh sách lớp</h3>
                            <table className="table table-bordered">
                                <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>ID Room</th>
                                        <th>ID Room VCRX</th>
                                        <th>Slide</th>
                                        <th>Video</th>
                                        <th>Time Available</th>
                                        <th>Link</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {
                                        this.state.roomsInSection.map((room, index) => {
                                            if(this.state.teachers[index] && this.state.students[index]){
                                                let linkGV  = this.renderLink(room, this.state.teachers[index], "WEB");
                                                let linkPO  = this.renderLink(room, this.state.POs[index], "WEB");
                                                let linkTG  = this.renderLink(room, this.state.assistants[index], "WEB");
                                                let linkHV1_WEB = this.renderLink(room, this.state.students[(index*6)], "WEB");
                                                let linkHV1_APP = this.renderLink(room, this.state.students[(index*6)], "APP");
                                                let linkHV2_WEB = this.renderLink(room, this.state.students[(index*6+1)], "WEB");
                                                let linkHV2_APP = this.renderLink(room, this.state.students[(index*6+1)], "APP");
                                                let linkHV3_WEB = this.renderLink(room, this.state.students[(index*6+2)], "WEB");
                                                let linkHV3_APP = this.renderLink(room, this.state.students[(index*6+2)], "APP");
                                                let linkHV4_WEB = this.renderLink(room, this.state.students[(index*6+3)], "WEB");
                                                let linkHV4_APP = this.renderLink(room, this.state.students[(index*6+3)], "APP");
                                                let linkHV5_WEB = this.renderLink(room, this.state.students[(index*6+4)], "WEB");
                                                let linkHV5_APP = this.renderLink(room, this.state.students[(index*6+4)], "APP");
                                                let linkHV6_WEB = this.renderLink(room, this.state.students[(index*6+5)], "WEB");
                                                let linkHV6_APP = this.renderLink(room, this.state.students[(index*6+5)], "APP");

                                                return(
                                                    <tr key={index}>
                                                        <td>{index+1}</td>
                                                        <td>{room.id}</td>
                                                        <td>{room.idVcrx}</td>
                                                        <td><a href={room.slide} target='_blank' title="Link slide">Link</a></td>
                                                        <td><a href={room.video} target='_blank' title="Link video">Link</a></td>
                                                        <td>{new Date(room.timeAvailable*1000).toLocaleString()}</td>
                                                        <td>
                                                            <a href={linkGV} target='_blank'>{this.state.teachers[index].fullName}</a><br/>
                                                            <a href={linkPO} target='_blank'>{this.state.POs[index].fullName}</a><br/>
                                                            <a href={linkTG} target='_blank'>{this.state.assistants[index].fullName}</a><br/>
                                                            <a href={linkHV1_WEB} target='_blank'>{this.state.students[(index*6)].fullName+"-WEB"}</a> | <a href={linkHV1_APP} target='_blank'>{this.state.students[(index*6)].fullName+"-APP"}</a><br/>
                                                            <a href={linkHV2_WEB} target='_blank'>{this.state.students[(index*6+1)].fullName+"-WEB"}</a> | <a href={linkHV2_APP} target='_blank'>{this.state.students[(index*6+1)].fullName+"-APP"}</a><br/>
                                                            <a href={linkHV3_WEB} target='_blank'>{this.state.students[(index*6+2)].fullName+"-WEB"}</a> | <a href={linkHV3_APP} target='_blank'>{this.state.students[(index*6+2)].fullName+"-APP"}</a><br/>
                                                            <a href={linkHV4_WEB} target='_blank'>{this.state.students[(index*6+3)].fullName+"-WEB"}</a> | <a href={linkHV4_APP} target='_blank'>{this.state.students[(index*6+3)].fullName+"-APP"}</a><br/>
                                                            <a href={linkHV5_WEB} target='_blank'>{this.state.students[(index*6+4)].fullName+"-WEB"}</a> | <a href={linkHV5_APP} target='_blank'>{this.state.students[(index*6+4)].fullName+"-APP"}</a><br/>
                                                            <a href={linkHV6_WEB} target='_blank'>{this.state.students[(index*6+5)].fullName+"-WEB"}</a> | <a href={linkHV6_APP} target='_blank'>{this.state.students[(index*6+5)].fullName+"-APP"}</a><br/>
                                                        </td>
                                                    </tr>
                                                )
                                            }
                                        })
                                    }
                                </tbody>
                            </table>
                        </div>
                    }
                </div>
            </div>        
        );
    }
}

function _mapStateToPropsTop(state) {
	return {
        userlist : state['xtool/openclass'].userlist,
        rooms : state['xtool/openclass'].data,
        userInfo: state['xtool/app'].userInfo,
        userTL : state['xtool/openclass'].userTL,
        roomTL : state['xtool/openclass'].roomTL
	};
}
export default connect(_mapStateToPropsTop)(OpenClass);