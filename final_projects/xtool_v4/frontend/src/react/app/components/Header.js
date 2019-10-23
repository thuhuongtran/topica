import React, { Component } from 'react';
import { Link }            	from 'react-router-dom';
import { connect }          from 'react-redux';
import { Redirect }         from 'react-router-dom';
import $                    from  'jquery';
import { 
    submitLogout
 }                          from '../actions';

class Header extends React.Component {
    constructor(props) {
         super(props);
        this.toggleNav      = this.toggleNav.bind(this);
        this.confirmLogout  = this.confirmLogout.bind(this);
        this.toggle         = this.toggle.bind(this);
    }
        componentWillMount(){
        switch (this.props.page){
            case "DASHBOARD" :
            $('title').html("Trang chủ");
            break;
            case "RENDERLINK" :
            $('title').html("Tạo link nhanh");
            break;
            case "MANAGEVCRX" :
            $('title').html("Vận hành lớp học");
            break;
            case "CHECKLOG" :
            $('title').html("Log lớp học");
            break;
            case "LOGAPI" :
            $('title').html("Log API");
            break;
        }
    }

    toggle(){
        $("#profileMenu").toggle();
        return false;
    }

    toggleNav(){
        $("#sideNav").toggleClass("hide");
        $(".mg-left").toggleClass("mg-left0");
    }

    confirmLogout(){
        this.props.dispatch(submitLogout());
    }

    render() {
            if(!this.props.userInfo){
                return <Redirect to={{ pathname: '/login' }} />
            }
            return (
                <header id="js-header" className="u-header u-header--sticky-top">
                    <div className="u-header__section u-header__section--admin-dark g-min-height-65">
                        <nav className="navbar no-gutters g-pa-0">
                            <div className="col-auto d-flex flex-nowrap u-header-logo-toggler g-py-12">
                                <a href="../index.html" className="navbar-brand d-flex align-self-center g-hidden-xs-down g-line-height-1 py-0 g-mt-5">
                                    <img className = "logo-vcrx" src = "./images/logo.png" alt = "logo"/>
                                </a>
                                <div className = "box_menu">
                                    <i onClick = {this.toggleNav} className="toggle_nav fa fa-bars"></i>
                                </div>
                            </div>
                            <div className="col-auto d-flex g-py-12 g-pl-40--lg ml-auto">
                                <a id="searchInvoker" className="g-hidden-sm-up text-uppercase u-header-icon-v1 g-pos-rel g-width-40 g-height-40 rounded-circle g-font-size-20" href="#!" aria-controls="searchMenu" aria-haspopup="true" aria-expanded="false" data-is-mobile-only="true" data-dropdown-event="click" data-dropdown-target="#searchMenu" data-dropdown-type="css-animation" data-dropdown-duration="300" data-dropdown-animation-in="fadeIn" data-dropdown-animation-out="fadeOut">
                                    <i className="hs-admin-search g-absolute-centered"></i>
                                </a>

                                <div className="col-auto d-flex g-pt-5 g-pt-0--sm g-pl-10 g-pl-20--sm">
                                    <div className="g-pos-rel g-px-10--lg">
                                        <a id="profileMenuInvoker" onClick={this.toggle} className="d-block" href="javascript:void()" aria-controls="profileMenu" aria-haspopup="true" aria-expanded="false" data-dropdown-event="click" data-dropdown-target="#profileMenu" data-dropdown-type="css-animation" data-dropdown-duration="300" data-dropdown-animation-in="fadeIn" data-dropdown-animation-out="fadeOut">
                                        <span className="g-pos-rel">
                                            <span className="u-badge-v2--xs u-badge--top-right g-hidden-sm-up g-bg-lightblue-v5 g-mr-5"></span>
                                            <img className="g-width-30 g-width-40--md g-height-30 g-height-40--md rounded-circle g-mr-10--sm" src="../assets/img-temp/130x130/img1.jpg" alt="Image description"/>
                                        </span>
                                            <span className="g-pos-rel g-top-2">
                                            <span className="g-hidden-sm-down">{this.props.userInfo.username+ " - " + this.props.userInfo.system}</span>
                                            <i className="hs-admin-angle-down g-pos-rel g-top-2 g-ml-10"></i>
                                        </span>
                                        </a>
                                        <ul id="profileMenu" className="g-pos-abs g-left-0 g-width-100x--lg g-nowrap g-font-size-14 g-py-20 g-mt-17 rounded u-dropdown--css-animation fadeIn" aria-labelledby="profileMenuInvoker">
                                            <li className="g-hidden-sm-up g-mb-10">
                                                <a className="media g-py-5 g-px-20" href="#!">
                                                <span className="d-flex align-self-center g-pos-rel g-mr-12">
                                                    <span className="u-badge-v1 g-top-minus-3 g-right-minus-3 g-width-18 g-height-18 g-bg-secondary g-font-size-10 g-color-white rounded-circle p-0">10</span>
                                                    <i className="hs-admin-comment-alt"></i>
                                                </span>
                                                    <span className="media-body align-self-center">Unread Messages</span>
                                                </a>
                                            </li>
                                            <li className="g-hidden-sm-up g-mb-10">
                                                <a className="media g-py-5 g-px-20" href="#!">
                                                <span className="d-flex align-self-center g-mr-12">
                                                <i className="hs-admin-bell"></i>
                                                </span>
                                                    <span className="media-body align-self-center">Notifications</span>
                                                </a>
                                            </li>
                                            <li className="g-mb-10">
                                                <a className="media g-color-primary--hover g-py-5 g-px-20" href="#!">
                                                <span className="d-flex align-self-center g-mr-12">
                                                    <i className="fa fa-user"></i>
                                                </span>
                                                    <span onClick={this.confirmLogout} className="media-body align-self-center">Thông tin cá nhân</span>
                                                </a>
                                            </li>
                                            <li className="g-mb-10">
                                                <a className="media g-color-primary--hover g-py-5 g-px-20" href="/login">
                                                <span className="d-flex align-self-center g-mr-12">
                                                    <i className="fa fa-sign-out"></i>
                                                </span>
                                                    <span onClick={this.confirmLogout} className="media-body align-self-center">Đăng xuất</span>
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </nav>
                    </div>
                </header>
            );
        }
    }

function _mapStateToPropsTop(state) {
	return {
        userInfo: state['xtool/app'].userInfo
	};
}
export default connect(_mapStateToPropsTop)(Header);
