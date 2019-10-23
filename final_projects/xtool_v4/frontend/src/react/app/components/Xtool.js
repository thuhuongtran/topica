import React, { Component }                             from 'react';
import { connect }                                      from 'react-redux';
import { submitRenderLink, submitRenderLinks, 
    submitRenderLinkFromIdUser, changePage
}                                                       from '../actions';

class Xtool extends Component {
    constructor(props) {
        super(props);
        this.state = {
            idroom: "",
            iduser: ""
        }
        this.submitRenderLink = this.submitRenderLink.bind(this);
    }
    componentWillMount(){
        this.props.dispatch(changePage("RENDERLINK"));
    }

    submitRenderLink(){
       
    }

    render() {
        return (
            <div className="col-xl-7 col-lg-7 col-md-12 col-sm-12 col-12 xtool-renderlink mg-left">
                <p>Xtool</p>
            </div>    
        );
    }
}

function _mapStateToPropsTop(state) {

}
export default connect(_mapStateToPropsTop)(Xtool);