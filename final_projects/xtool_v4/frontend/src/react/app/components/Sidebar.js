import React, { Component } from 'react';
import { Link }            	from 'react-router-dom';
import { connect }          from 'react-redux';
import { changePage }       from '../actions';
import { Redirect }                 from 'react-router-dom';

class Sidebar extends React.Component {
    constructor(props) {
        super(props);
    }

    activePage(page){
        this.props.dispatch(changePage(page));
    }

    render() {
        let cssClass = "media u-side-nav--top-level-menu-link u-side-nav--hide-on-hidden g-px-15 g-py-12";
        let cssClassActive = "media u-side-nav--top-level-menu-link u-side-nav--hide-on-hidden g-px-15 g-py-12 active";
        if(!this.props.userInfo){
            return <Redirect to={{ pathname: '/login' }} />
        }
        return (
            <div id="sideNav" className="col-auto u-sidebar-navigation-v1 u-sidebar-navigation--dark">
                <div className="">
                    <div id="accordion" className="">
                        <div className="card1">
                            <div className="">
                                <Link onClick = {this.activePage.bind(this,"DASHBOARD")} className= {this.props.page == "DASHBOARD" ? cssClassActive :cssClass} to={'/'} >
                                    <span className="d-flex align-self-center g-pos-rel g-font-size-18 g-mr-18">
                                        <i className="hs-admin-server"></i>
                                    </span>
                                    <span className="media-body align-self-center">{"Trang chủ"}</span>
                                    <span className="u-side-nav--has-sub-menu__indicator"></span>
                                </Link>
                            </div>
                        </div>

                        <div className="card1">
                            <div className="">
                                <a className="collapsed card-link media u-side-nav--top-level-menu-link u-side-nav--hide-on-hidden g-px-15 g-py-12" data-toggle="collapse" href="#collapseTwo">
                                    <span className="d-flex align-self-center g-pos-rel g-font-size-18 g-mr-18">
                                        <i className="hs-admin-server"></i>
                                    </span>
                                    <span className="media-body align-self-center">{"Công cụ"}</span>
                                </a>
                            </div>
                            <div id="collapseTwo" className={this.props.page == "RENDERLINK" ? "collapse show": "collapse"} data-parent="#accordion">
                                <Link  onClick = {this.activePage.bind(this,"RENDERLINK")} className= {this.props.page == "RENDERLINK" ? cssClassActive :cssClass} to={'/xtool'} >
                                    <span className="media-body align-self-center">{"Tạo Link nhanh"}</span>
                                    <span className="u-side-nav--has-sub-menu__indicator"></span>
                                    <span className="caret"></span>
                                </Link>
                                <Link  onClick = {this.activePage.bind(this,"LOGSCONNECTION")} className= {this.props.page == "LOGSCONNECTION" ? cssClassActive :cssClass} to={'/logs'} >
                                            <span className="media-body align-self-center">Logs connection</span>
                                            <span className="u-side-nav--has-sub-menu__indicator"></span>
                                            <span className="caret"></span>
                                </Link>
                                <Link  onClick = {this.activePage.bind(this,"OPENCLASS")} className= {this.props.page == "OPENCLASS" ? cssClassActive :cssClass} to={'/openclass'} >
                                    <span className="media-body align-self-center">Mở lớp</span>
                                    <span className="u-side-nav--has-sub-menu__indicator"></span>
                                    <span className="caret"></span>
                                </Link>
                                <Link  onClick = {this.activePage.bind(this,"ERROROPENCLASS")}
                                       to={'/status'} >
                                    <span className="media-body align-self-center">Error logs</span>
                                </Link>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}


function _mapStateToPropsTop(state) {
	return {
        userInfo: state['xtool/app'].userInfo
	};
}
export default connect(_mapStateToPropsTop)(Sidebar);